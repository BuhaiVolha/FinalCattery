package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.BaseDAO;
import by.epam.cattery.dao.CatDAO;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Gender;
import by.epam.cattery.entity.dto.SearchCatTO;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class CatDAOImpl extends BaseDAO<Cat> implements CatDAO {
    private static final Logger logger = LogManager.getLogger(CatDAOImpl.class);

    private static final String CREATE_CAT =
            "INSERT INTO cat " +
                    "(name, lastname, gender, birth_date, description, body_colour_code, cat_eyes_colour_code, " +
                    "parent_female, parent_male, price, user_suggested_id, offer_made_id, cat_photo) " +
                    "VALUES(?, ?, ?, STR_TO_DATE(?, '%m/%d/%Y'), ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_CAT =
            "UPDATE cat " +
                    "SET name = ?, lastname = ?, gender = ?, birth_date = STR_TO_DATE(?, '%m/%d/%Y'), description = ?, " +
                    "body_colour_code = ?, cat_eyes_colour_code = ?, parent_female = ?, parent_male = ?, price = ? " +
                    "WHERE cat_id = ? " +
                    "AND NOT flag_cat_deleted;";
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
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, colour_name, " +
                    "eyes_colour_name, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                        "JOIN cat_colour ON (cat.body_colour_code = cat_colour.EMS_code) " +
                        "LEFT JOIN cat_eyes_colour ON (cat.cat_eyes_colour_code = cat_eyes_colour.FIFe_eyes_colour_code) " +
                    "WHERE NOT flag_cat_deleted ";
    private static final String GET_ALL_CATS =
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, colour_name, " +
                    "eyes_colour_name, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                        "JOIN cat_colour ON (cat.body_colour_code = cat_colour.EMS_code) " +
                        "LEFT JOIN cat_eyes_colour ON (cat.cat_eyes_colour_code = cat_eyes_colour.FIFe_eyes_colour_code) " +
                    "WHERE NOT flag_cat_deleted " +
                    "ORDER BY name LIMIT ? OFFSET ?;";
    private static final String GET_ALL_CATS_BY_STATUS =
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, colour_name, " +
                    "eyes_colour_name, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                        "JOIN cat_colour ON (cat.body_colour_code = cat_colour.EMS_code) " +
                        "LEFT JOIN cat_eyes_colour ON (cat.cat_eyes_colour_code = cat_eyes_colour.FIFe_eyes_colour_code) " +
                    "WHERE sale_status_id = ? " +
                    "AND NOT flag_cat_deleted " +
                    "ORDER BY name LIMIT ? OFFSET ?;";

    private static final String GET_CAT_BY_ID =
            "SELECT cat_id, name, lastname, gender, MONTH(CURDATE()) - MONTH(birth_date), description, colour_name, " +
                    "eyes_colour_name, parent_female, parent_male, price, sale_status_id, user_suggested_id, cat_photo " +
                    "FROM cat " +
                        "JOIN cat_colour ON (cat.body_colour_code = cat_colour.EMS_code) " +
                        "LEFT JOIN cat_eyes_colour ON (cat.cat_eyes_colour_code = cat_eyes_colour.FIFe_eyes_colour_code) " +
                    "WHERE cat_id = ? " +
                    "AND NOT flag_cat_deleted;";

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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


    @Override
    public void searchForCat(SearchCatTO searchCatTO, int page) throws DAOException {
        Cat cat = searchCatTO.getSearchedCat();
        List<Cat> cats = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            StringBuffer condition = new StringBuffer(" AND ");

            if (cat.getGender() != null) {
                condition.append(" gender = '" + cat.getGender().toString() + "' AND ");
            }
            if (cat.getStatus() != null) {
                condition.append(" sale_status_id = '" + cat.getStatus().toString() + "' AND ");
            }

            if (!cat.getBodyColour().isEmpty()) {
                condition.append(" body_colour_code = '" + cat.getBodyColour() + "' AND ");
            }

            if (!cat.getEyesColour().isEmpty()) {
                condition.append(" cat_eyes_colour_code = '" + cat.getEyesColour() + "' AND ");
            }

            if (cat.getPrice() != 0.0) {

                if (searchCatTO.getUserDiscount() == 0)  {
                    condition.append(" price <= " + cat.getPrice() + " AND ");
                } else  {
                    condition.append(" price - (price * " + searchCatTO.getUserDiscount() + ") / 100 <= " + cat.getPrice() + " AND ");
                }
            }
            condition.append(" 1 = 1");

            String SEARCH_QUERY_ENDING = " ORDER BY price LIMIT ? OFFSET ?;";
            final String GET_FOUND_CATS_COUNT = "SELECT COUNT(*) FROM cat WHERE NOT flag_cat_deleted ";

            ps = con.prepareStatement(GET_ALL_CATS_FOR_SEARCH + condition + SEARCH_QUERY_ENDING);

            searchCatTO.setSearchQuery(GET_FOUND_CATS_COUNT + condition);

            int itemsPerPage = searchCatTO.getItemsPerPage();
            ps.setInt(1, itemsPerPage);
            int startIndex = (page - 1) * itemsPerPage;
            ps.setInt(2, startIndex);

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


    @Override
    public int getCatIdByReservationId(int reservationId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int catId = 0;

        try {
            con = connectionProvider.obtainConnection();

            logger.log(Level.DEBUG, " cat obtained connection " + con);
            ps = con.prepareStatement(GET_CAT_ID_BY_RESERVATION_ID);
            ps.setInt(1, reservationId);

            rs = ps.executeQuery();
            rs.next();
            catId = rs.getInt(1);
//            if (rs.next()) {   // какаято проверка на то что EXIST
//                catId = rs.getInt(1);
//            }

            return catId;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception getting ID for user ", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void executeCreateQuery(PreparedStatement ps, Cat cat) throws SQLException {

        ps.setString(1, cat.getName());
        ps.setString(2, cat.getLastname());
        ps.setString(3, cat.getGender().toString());
        ps.setString(4, cat.getAge());
        ps.setString(5, cat.getDescription());
        ps.setString(6, cat.getBodyColour());
        ps.setString(7, cat.getEyesColour());
        ps.setString(8, cat.getFemaleParent());
        ps.setString(9, cat.getMaleParent());
        ps.setDouble(10, cat.getPrice());
        ps.setInt(11, cat.getUserMadeOfferId());
        ps.setInt(12, cat.getOfferMadeId());
        ps.setString(13, cat.getPhoto());

        logger.log(Level.DEBUG, "Cat has been saved to database");
    }


    @Override
    public void executeUpdateQuery(PreparedStatement ps, Cat cat) throws SQLException {

        ps.setString(1, cat.getName());
        ps.setString(2, cat.getLastname());
        ps.setString(3, cat.getGender().toString());
        ps.setString(4, cat.getAge());
        ps.setString(5, cat.getDescription());
        ps.setString(6, cat.getBodyColour());
        ps.setString(7, cat.getEyesColour());
        ps.setString(8, cat.getFemaleParent());
        ps.setString(9, cat.getMaleParent());
        ps.setDouble(10, cat.getPrice());
        ps.setDouble(11, cat.getId());

        logger.log(Level.DEBUG, "Cat has been updated");
    }


    @Override
    public void executeUpdateStatusQuery(PreparedStatement ps, String status, int id) throws SQLException {
        ps.setString(1, status);
        ps.setInt(2, id);
    }


    @Override
    public void executeDeleteQuery(PreparedStatement ps, int catId) throws SQLException {

        ps.setInt(1, catId);
        logger.log(Level.DEBUG, "Cat has been deleted from database :(");
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String getCreateQuery() {
        return CREATE_CAT;
    }


    @Override
    public String getUpdateQuery() {
        return UPDATE_CAT;
    }

    @Override
    public String getUpdateStatusQuery() {
        return UPDATE_CAT_STATUS;
    }

    @Override
    public String getUpdatePhotoQuery() {
        return UPDATE_PHOTO;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_CAT;
    }

    @Override
    public String getQueryForAllObjects() {
        return GET_ALL_CATS;
    }

    @Override
    public String getQueryForTotalCount() {
        return GET_CATS_COUNT;
    }


    @Override
    public String getQueryForSingleObject() {
        return GET_CAT_BY_ID;
    }

    @Override
    public String getQueryForStatusCheck() {
        return CHECK_CAT_STATUS;
    }

    @Override
    public String getQueryForAllObjectsByStatus() {
        return GET_ALL_CATS_BY_STATUS;
    }

    @Override
    public String getQueryForTotalCountByStatus() {
        return GET_CATS_COUNT_BY_STATUS;
    }

    @Override
    public String getQueryForTotalCountById() {
        logger.log(Level.WARN, "Counting all objects by id is not implemented for Cat");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForAllObjectsById() {
        logger.log(Level.WARN, "Taking all objects by id is not implemented for Cat");
        throw new UnsupportedOperationException();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Cat readResultSet(ResultSet rs) throws SQLException {
        Cat cat = new Cat();

        cat.setId(rs.getInt(1));
        cat.setName(rs.getString(2));
        cat.setLastname(rs.getString(3));
        cat.setGender(Gender.valueOf(rs.getString(4)));
        cat.setAge(rs.getString(5));
        cat.setDescription(rs.getString(6));
        cat.setBodyColour(rs.getString(7));
        cat.setEyesColour(rs.getString(8));
        cat.setFemaleParent(rs.getString(9));
        cat.setMaleParent(rs.getString(10));
        cat.setPrice(rs.getDouble(11));
        cat.setStatus(CatStatus.valueOf(rs.getString(12)));
        cat.setUserMadeOfferId(rs.getInt(13));
        cat.setPhoto(rs.getString(14));

        return cat;
    }
}
