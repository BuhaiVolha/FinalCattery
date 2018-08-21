package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.BaseDAO;
import by.epam.cattery.dao.CatDAO;

import by.epam.cattery.entity.*;
import by.epam.cattery.entity.dto.CatDetail;
import by.epam.cattery.entity.dto.LocalizedCat;
import by.epam.cattery.entity.dto.SearchCatTO;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * {@inheritDoc}
 *
 * @param {@link Cat} the type parameter
 *
 */
public class CatDAOImpl extends BaseDAO<Cat> implements CatDAO {
    private static final Logger logger = LogManager.getLogger(CatDAOImpl.class);

    private static final String CREATE_CAT =
            "INSERT INTO cat " +
                    "(gender, birth_date, body_colour_code, cat_eyes_colour_code, price, user_suggested_id, " +
                    "offer_made_id, cat_photo) " +
                    "VALUES(?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, ?, ?, ?, ?);";
    private static final String CREATE_CAT_DETAILS =
            "INSERT INTO cat_localized " +
                    "(cat_localized_id, locale, name, lastname, description, parent_female, parent_male) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_CAT =
            "UPDATE cat " +
                    "SET gender = ?, birth_date = STR_TO_DATE(?, '%d/%m/%Y'), body_colour_code = ?, " +
                    "cat_eyes_colour_code = ?, price = ? " +
                    "WHERE cat_id = ? " +
                    "AND NOT flag_cat_deleted;";
    private static final String UPDATE_CAT_DETAILS =
            "UPDATE cat_localized " +
                    "SET name = ?, lastname = ?, description = ?, parent_female = ?, parent_male = ? " +
                    "WHERE cat_localized_id = ? " +
                    "AND locale = ?;";
    private static final String UPDATE_CAT_STATUS =
            "UPDATE cat " +
                    "SET sale_status_id = ? " +
                    "WHERE cat_id = ? " +
                    "AND NOT flag_cat_deleted;";
    private static final String UPDATE_PHOTO =
            "UPDATE cat " +
                    "SET cat_photo = ? " +
                    "WHERE cat_id = ?;";

    private static final String DELETE_CAT =
            "UPDATE cat " +
                    "SET flag_cat_deleted = 1 " +
                    "WHERE cat_id = ?;";

    private static final String GET_ALL_CATS_FOR_SEARCH =
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, body_colour_code, " +
                    "cat_eyes_colour_code, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                        "JOIN cat_localized ON (cat_id = cat_localized_id) " +
                    "WHERE locale = ? " +
                    "AND NOT flag_cat_deleted ";

    private static final String GET_ALL_CATS =
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, body_colour_code, " +
                    "cat_eyes_colour_code, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                        "JOIN cat_localized ON (cat_id = cat_localized_id) " +
                    "WHERE NOT flag_cat_deleted " +
                    "AND locale = ? " +
                    "ORDER BY name LIMIT ? OFFSET ?;";

    private static final String GET_ALL_CATS_BY_STATUS =
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, body_colour_code, " +
                    "cat_eyes_colour_code, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                    "JOIN cat_localized ON (cat_id = cat_localized_id) " +
                    "WHERE NOT flag_cat_deleted " +
                    "AND sale_status_id = ? " +
                    "AND locale = ? " +
                    "ORDER BY name LIMIT ? OFFSET ?;";

    private static final String GET_CAT_BY_ID =
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, body_colour_code, " +
                    "cat_eyes_colour_code, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                    "JOIN cat_localized ON (cat_id = cat_localized_id) " +
                    "WHERE cat_id = ? " +
                    "AND locale = ? " +
                    "AND NOT flag_cat_deleted;";

    private static final String GET_CAT_DETAILS =
            "SELECT locale, name, lastname, description, parent_female, parent_male " +
                    "FROM cat_localized " +
                    "WHERE cat_localized_id = ? " +
                    "AND locale = ?;";

    private static final String GET_CATS_COUNT =
            "SELECT COUNT(*) " +
                    "FROM cat " +
                    "WHERE NOT flag_cat_deleted;";
    private static final String GET_CATS_COUNT_BY_STATUS =
            "SELECT COUNT(*) " +
                    "FROM cat " +
                    "WHERE sale_status_id = ? " +
                    "AND NOT flag_cat_deleted;";

    private static final String SET_RESERVED_CATS_AVAILABLE_IF_RESERVATIONS_EXPIRED =
            "UPDATE cat " +
                    "JOIN user_reservation ON (cat.cat_id = user_reservation.cat_id) " +
                        "SET sale_status_id = ? " +
                        "WHERE sale_status_id = ? " +
                        "AND timestampdiff(DAY, user_reservation.reservation_date, now()) > 3 " +
                        "AND NOT flag_cat_deleted;";
    private static final String SET_RESERVED_CATS_AVAILABLE_IF_USER_BANNED =
            "UPDATE cat " +
                    "JOIN user_reservation ON (cat.cat_id = user_reservation.cat_id) " +
                        "SET sale_status_id = ? " +
                        "WHERE sale_status_id=? " +
                        "AND user_reservation.user_id = ? " +
                        "AND NOT flag_cat_deleted;";

    private static final String CHECK_CAT_STATUS =
            "SELECT EXISTS " +
                    "(SELECT 1 FROM cat " +
                        "WHERE cat_id = ? " +
                        "AND sale_status_id = ?)";

    private static final String GET_CAT_ID_BY_RESERVATION_ID =
            "SELECT cat.cat_id " +
                    "FROM cat " +
                        "JOIN user_reservation ON (cat.cat_id = user_reservation.cat_id) " +
                    "WHERE user_reservation.reservation_id = ? " +
                    "AND NOT flag_cat_deleted;";

    private static final String GET_FOUND_CATS_COUNT =
            "SELECT COUNT(*) " +
                    "FROM cat " +
                    "WHERE NOT flag_cat_deleted ";
    private static final String SEARCH_QUERY_ENDING_FOR_PAGINATION =
            " ORDER BY price LIMIT ? OFFSET ?;";
    private static final String SEARCH_OPERATOR_AND = " AND ";
    private static final String SEARCH_OPERATOR_AND_WITH_QUOTE_SIGN = "' AND ";
    private static final String SEARCH_PARAM_CAT_GENDER = " gender = '";
    private static final String SEARCH_PARAM_CAT_STATUS = " sale_status_id = '";
    private static final String SEARCH_PARAM_CAT_BODY_COLOUR = " body_colour_code = '";
    private static final String SEARCH_PARAM_CAT_EYES_COLOUR = " cat_eyes_colour_code = '";
    private static final String SEARCH_PARAM_CAT_PRICE = " price <= ";
    private static final String SEARCH_EXPRESSION_TO_COUNT_PRICE_WITH_DISCOUNT_PT1 = " price - (price * ";
    private static final String SEARCH_EXPRESSION_TO_COUNT_PRICE_WITH_DISCOUNT_PT2 = ") / 100 <= ";
    private static final String SEARCH_ENDING_IN_CASE_NO_PARAMETERS = " 1 = 1";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int saveLocalizedCat(LocalizedCat cat) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CREATE_CAT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cat.getGender().toString());
            ps.setString(2, cat.getAge());
            ps.setString(3, cat.getBodyColour().toString());
            ps.setString(4, cat.getEyesColour().toString());
            ps.setDouble(5, cat.getPrice());
            ps.setInt(6, cat.getUserMadeOfferId());
            ps.setInt(7, cat.getOfferMadeId());
            ps.setString(8, cat.getPhoto());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during adding localized cat", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void saveLocalizedCatDetails(int catId, List<CatDetail> catDetails) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CREATE_CAT_DETAILS);

            for (CatDetail catDetail : catDetails) {
                ps.setInt(1, catId);
                ps.setString(2, catDetail.getLocaleLang().toString());
                ps.setString(3, catDetail.getName());
                ps.setString(4, catDetail.getLastname());
                ps.setString(5, catDetail.getDescription());
                ps.setString(6, catDetail.getFemaleParent());
                ps.setString(7, catDetail.getMaleParent());

                ps.addBatch();
            }
            ps.executeBatch();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during adding localized cat", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void updateLocalizedCat(LocalizedCat cat) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(UPDATE_CAT);

            ps.setString(1, cat.getGender().toString());
            ps.setString(2, cat.getAge());
            ps.setString(3, cat.getBodyColour().toString());
            ps.setString(4, cat.getEyesColour().toString());
            ps.setDouble(5, cat.getPrice());
            ps.setInt(6, cat.getId());

            int i = ps.executeUpdate();

            if (i != 1) {
                throw new DAOException("Couldn't update cat from the database");
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during updating localized cat", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void updateLocalizedCatDetails(int catId, List<CatDetail> catDetails) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(UPDATE_CAT_DETAILS);

            for (CatDetail catDetail : catDetails) {
                ps.setString(1, catDetail.getName());
                ps.setString(2, catDetail.getLastname());
                ps.setString(3, catDetail.getDescription());
                ps.setString(4, catDetail.getFemaleParent());
                ps.setString(5, catDetail.getMaleParent());
                ps.setInt(6, catId);
                ps.setString(7, catDetail.getLocaleLang().toString());

                ps.addBatch();
            }

            ps.executeBatch();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during updating localized cat details", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public List<Cat> loadAllCats(LocaleLang localeLang, int page, int itemsPerPage) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Cat> cats = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(GET_ALL_CATS);

            ps.setString(1, localeLang.toString());
            ps.setInt(2, itemsPerPage);
            int startIndex = (page - 1) * itemsPerPage;
            ps.setInt(3, startIndex);

            rs = ps.executeQuery();

            while (rs.next()) {
                cats.add(readResultSet(rs));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Loading all cats from the database to list failed", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }

        return cats;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public List<Cat> loadAllCatsByStatus(LocaleLang localeLang, CatStatus catStatus, int page, int itemsPerPage) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Cat> cats = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(GET_ALL_CATS_BY_STATUS);

            ps.setString(1, catStatus.toString());
            ps.setString(2, localeLang.toString());
            ps.setInt(3, itemsPerPage);
            int startIndex = (page - 1) * itemsPerPage;
            ps.setInt(4, startIndex);

            rs = ps.executeQuery();

            while (rs.next()) {
                cats.add(readResultSet(rs));
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Loading all cats by status from the database to list failed", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }

        return cats;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Cat getCatById(int catId, LocaleLang localeLang) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(GET_CAT_BY_ID);

            ps.setInt(1, catId);
            ps.setString(2, localeLang.toString());

            rs = ps.executeQuery();
            rs.next();

            return readResultSet(rs);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Loading cat from the database by ID failed", e);

        }  finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public CatDetail getCatDetail(int catId, LocaleLang localeLang) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(GET_CAT_DETAILS);

            ps.setInt(1, catId);
            ps.setString(2, localeLang.toString());

            rs = ps.executeQuery();
            rs.next();

            return createCatDetail(rs);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Loading cat details with localization from the database failed", e);

        }  finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }

    /**
     * Returns an object created from {@link ResultSet}
     *
     * @param rs the {@link ResultSet}
     * @return {@link CatDetail}
     * @throws SQLException the sql exception
     *
     */
    private CatDetail createCatDetail(ResultSet rs) throws SQLException {
        CatDetail catDetail = new CatDetail();

        catDetail.setLocaleLang(LocaleLang.valueOf(rs.getString(1)));
        catDetail.setName(rs.getString(2));
        catDetail.setLastname(rs.getString(3));
        catDetail.setDescription(rs.getString(4));
        catDetail.setFemaleParent(rs.getString(5));
        catDetail.setMaleParent(rs.getString(6));

        return catDetail;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void setCatsAvailableIfReservationsExpired() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(SET_RESERVED_CATS_AVAILABLE_IF_RESERVATIONS_EXPIRED);
            ps.setString(1, CatStatus.AVAIL.toString());
            ps.setString(2, CatStatus.RSRV.toString());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during making cats free after reservations expired", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void setCatsAvailableIfUserBanned(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(SET_RESERVED_CATS_AVAILABLE_IF_USER_BANNED);
            ps.setString(1, CatStatus.AVAIL.toString());
            ps.setString(2, CatStatus.RSRV.toString());
            ps.setInt(3, userId);

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during making cats free after user was banned", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void searchForCat(SearchCatTO searchCatTO, int page) throws DAOException {
        Cat cat = searchCatTO.getSearchedCat();
        List<Cat> cats = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            StringBuilder condition = new StringBuilder(SEARCH_OPERATOR_AND);

            if (cat.getGender() != null) {
                condition.append(SEARCH_PARAM_CAT_GENDER).append(cat.getGender()).append(SEARCH_OPERATOR_AND_WITH_QUOTE_SIGN);
            }

            if (cat.getStatus() != null) {
                condition.append(SEARCH_PARAM_CAT_STATUS).append(cat.getStatus()).append(SEARCH_OPERATOR_AND_WITH_QUOTE_SIGN);
            }

            if (cat.getBodyColour() != null) {
                condition.append(SEARCH_PARAM_CAT_BODY_COLOUR).append(cat.getBodyColour()).append(SEARCH_OPERATOR_AND_WITH_QUOTE_SIGN);
            }

            if (cat.getEyesColour() != null) {
                condition.append(SEARCH_PARAM_CAT_EYES_COLOUR).append(cat.getEyesColour()).append(SEARCH_OPERATOR_AND_WITH_QUOTE_SIGN);
            }

            if (cat.getPrice() != 0.0) {

                if (searchCatTO.getUserDiscount() == 0
                        && cat.getStatus() != null
                        && !cat.getStatus().equals(CatStatus.SOLD))  {
                    condition.append(SEARCH_PARAM_CAT_PRICE).append(cat.getPrice()).append(SEARCH_OPERATOR_AND);

                } else  {
                    condition.append(SEARCH_EXPRESSION_TO_COUNT_PRICE_WITH_DISCOUNT_PT1).append(searchCatTO.getUserDiscount())
                            .append(SEARCH_EXPRESSION_TO_COUNT_PRICE_WITH_DISCOUNT_PT2).append(cat.getPrice())
                            .append(SEARCH_OPERATOR_AND);
                }
            }
            condition.append(SEARCH_ENDING_IN_CASE_NO_PARAMETERS);

            ps = con.prepareStatement(GET_ALL_CATS_FOR_SEARCH + condition + SEARCH_QUERY_ENDING_FOR_PAGINATION);
            searchCatTO.setSearchQuery(GET_FOUND_CATS_COUNT + condition);

            int itemsPerPage = searchCatTO.getItemsPerPage();
            ps.setString(1, searchCatTO.getLocaleLang().toString());
            ps.setInt(2, itemsPerPage);
            int startIndex = (page - 1) * itemsPerPage;
            ps.setInt(3, startIndex);

            rs = ps.executeQuery();

            while (rs.next()) {
                Cat foundCat = readResultSet(rs);
                cats.add(foundCat);
            }
            searchCatTO.setCats(cats);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during searching for cat", e);
        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void getTotalCountForFoundCats(SearchCatTO searchCatTO) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(searchCatTO.getSearchQuery());
            rs = ps.executeQuery();
            rs.next();

            searchCatTO.setPageCount(rs.getInt(1));

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during counting how much cats were found", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getCatIdByReservationId(int reservationId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(GET_CAT_ID_BY_RESERVATION_ID);
            ps.setInt(1, reservationId);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException("No cat for this reservation was found");
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception getting ID for user ", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public void executeCreateQuery(PreparedStatement ps, Cat cat) throws SQLException {
        logger.log(Level.WARN, "Not supported for Cat due to localization issues");
        throw new UnsupportedOperationException();
    }


    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public void executeUpdateQuery(PreparedStatement ps, Cat cat) throws SQLException {
        logger.log(Level.WARN, "Not supported for Cat due to localization issues");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void executeUpdateStatusQuery(PreparedStatement ps, String status, int id) throws SQLException {
        ps.setString(1, status);
        ps.setInt(2, id);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void executeDeleteQuery(PreparedStatement ps, int catId) throws SQLException {
        ps.setInt(1, catId);
        logger.log(Level.DEBUG, "Cat has been deleted from database :(");
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public String getCreateQuery() {
        logger.log(Level.WARN, "Not supported for Cat due to localization issues");
        throw new UnsupportedOperationException();
    }


    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public String getUpdateQuery() {
        logger.log(Level.WARN, "Not supported for Cat due to localization issues");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getUpdateStatusQuery() {
        return UPDATE_CAT_STATUS;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getUpdatePhotoQuery() {
        return UPDATE_PHOTO;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getDeleteQuery() {
        return DELETE_CAT;
    }

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public String getQueryForAllObjects() {
        logger.log(Level.WARN, "Not supported for Cat due to localization issues");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForTotalCount() {
        return GET_CATS_COUNT;
    }

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public String getQueryForSingleObject() {
        logger.log(Level.WARN, "Not supported for Cat due to localization issues");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForStatusCheck() {
        return CHECK_CAT_STATUS;
    }

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public String getQueryForAllObjectsByStatus() {
        logger.log(Level.WARN, "Not supported for Cat due to localization issues");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForTotalCountByStatus() {
        return GET_CATS_COUNT_BY_STATUS;
    }

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public String getQueryForTotalCountById() {
        logger.log(Level.WARN, "Counting all objects by id is not implemented for Cat");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not supported for Cat
     *
     */
    @Override
    public String getQueryForAllObjectsById() {
        logger.log(Level.WARN, "Taking all objects by id is not implemented for Cat");
        throw new UnsupportedOperationException();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Cat readResultSet(ResultSet rs) throws SQLException {
        Cat cat = new Cat();

        cat.setId(rs.getInt(1));
        cat.setName(rs.getString(2));
        cat.setLastname(rs.getString(3));
        cat.setGender(Gender.valueOf(rs.getString(4)));
        cat.setAge(rs.getString(5));
        cat.setDescription(rs.getString(6));
        cat.setBodyColour(CatBodyColour.valueOf(rs.getString(7)));
        cat.setEyesColour(CatEyesColour.valueOf(rs.getString(8)));
        cat.setFemaleParent(rs.getString(9));
        cat.setMaleParent(rs.getString(10));
        cat.setPrice(rs.getDouble(11));
        cat.setStatus(CatStatus.valueOf(rs.getString(12)));
        cat.setUserMadeOfferId(rs.getInt(13));
        cat.setPhoto(rs.getString(14));

        return cat;
    }
}
