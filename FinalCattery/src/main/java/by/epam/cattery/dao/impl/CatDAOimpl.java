package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Gender;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CatDAOimpl implements CatDAO {
    private final ConnectionPool connectionPool;

    public CatDAOimpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public List<Cat> findAllCats() throws DAOException {
        List<Cat> cats = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("SELECT cat_id, name, lastname, gender, " +
                    "MONTH(CURDATE()) - MONTH(birth_date), description," +
                    "colour_name, eyes_colour_name, parent_female, parent_male, price, " +
                    "sale_status_id, user_suggested_id FROM cat JOIN cat_colour " +
                    "ON (cat.body_colour_code = cat_colour.EMS_code) " +
                    "JOIN cat_eyes_colour ON (cat.cat_eyes_colour_code " +
                    "= cat_eyes_colour.FIFe_eyes_colour_code)");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cat cat = new Cat();
                cat.setId(rs.getInt(1));
                cat.setName(rs.getString(2));
                cat.setLastname(rs.getString(3));
                cat.setGender(Gender.valueOf(rs.getString(4)));  //????
                cat.setAge(rs.getString(5));   // отдельные константы числа
                cat.setDescription(rs.getString(6));
                cat.setBodyColour(rs.getString(7));
                cat.setEyesColour(rs.getString(8));
                cat.setFemaleParent(rs.getString(9));
                cat.setMaleParent(rs.getString(10));
                cat.setPrice(rs.getDouble(11)); // int?
                cat.setUserMadeOfferId(rs.getInt(13));

                // Остальные параметры?
                cats.add(cat);
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("error while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("error during gathering cats; You must gather your cats before venturing forth", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }

        return cats;
    }


    @Override
    public void addUserCat(Cat cat, String offerId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            String ADD_CAT = "INSERT INTO cat (name, lastname, gender, birth_date, description, body_colour_code, " +
                    "cat_eyes_colour_code, parent_female, parent_male, price, sale_status_id, user_suggested_id) VALUES(?,?,?,STR_TO_DATE(?, '%m/%d/%Y'),?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(ADD_CAT);

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
            ps.setInt(11, 1);
            ps.setInt(12, cat.getUserMadeOfferId());

            ps.addBatch();
            ps.execute();

            String UPDATE_OFFER_STATUS_FOR_USER = "UPDATE `user_offer` SET `user_offer_status_id`=? WHERE `offer_id` = ?;";
            ps2 = con.prepareStatement(UPDATE_OFFER_STATUS_FOR_USER);
            ps2.setString(1, OfferStatus.SENT.toString());
            ps2.setString(2, offerId);
            ps2.executeUpdate();

            ps2.addBatch();
            ps2.execute();

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
                throw new DAOException("error during adding cat", e);

            } catch (SQLException e1) {

                throw new DAOException("error while rollback after error during adding", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }

}
