package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    "TIMESTAMPDIFF(MONTH, age, CURDATE()), description," +
                    "colour_name, eyes_colour_name, parent_female, parent_male, price, " +
                    "sale_status_id, user_suggested_id FROM cat JOIN cat_colour " +
                    "ON (cat.EMS_code = cat_colour.EMS_code) " +
                    "JOIN cat_eyes_colour ON (cat.FIFe_eyes_colour_code " +
                    "= cat_eyes_colour.FIFe_eyes_colour_code)");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cat cat = new Cat();
                cat.setId(rs.getInt(1));
                cat.setName(rs.getString(2));
                cat.setLastname(rs.getString(3));
                cat.setGender(convertToGender(rs.getBoolean(4)));  //????
                cat.setAgeMonth(rs.getInt(5));   // отдельные константы числа
                cat.setDescription(rs.getString(6));
                cat.setBodyColor(rs.getString(7));
                cat.setEyesColor(rs.getString(8));
                cat.setPrice(rs.getDouble(11)); // int?

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

    private Gender convertToGender(boolean isFemale) {
        return (isFemale)? Gender.FEMALE : Gender.MALE;
    }
}
