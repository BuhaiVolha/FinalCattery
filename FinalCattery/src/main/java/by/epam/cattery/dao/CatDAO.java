package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.dto.CatDetail;
import by.epam.cattery.entity.dto.LocalizedCat;
import by.epam.cattery.entity.dto.SearchCatTO;

import java.util.List;


public interface CatDAO extends GenericDAO<Cat> {
    void searchForCat(SearchCatTO searchCatTO, int page) throws DAOException;
    void getTotalCountForFoundCats(SearchCatTO searchCatTO) throws DAOException;
    void setCatsAvailableIfReservationsExpired() throws DAOException;
    int getCatIdByReservationId(int reservationId) throws DAOException;
    void setCatsAvailableIfUserBanned(int userId) throws DAOException;
    void updateLocalizedCat(LocalizedCat cat) throws DAOException;
    void updateLocalizedCatDetails(int catId, List<CatDetail> catDetails) throws DAOException;
    int saveLocalizedCat(LocalizedCat cat) throws DAOException;
    void saveLocalizedCatDetails(int catId, List<CatDetail> catDetails) throws DAOException;
    List<Cat> loadAllCats(LocaleLang localeLang, int page, int itemsPerPage) throws DAOException;
    List<Cat> loadAllCatsByStatus(LocaleLang localeLang, CatStatus catStatus, int page, int itemsPerPage) throws DAOException;
    Cat getCatById(int catId, LocaleLang localeLang) throws DAOException;
    CatDetail getCatDetail(int catId, LocaleLang localeLang) throws DAOException;
}
