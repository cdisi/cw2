package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Uretici;

public interface UreticiDao {
	List<Uretici> all() throws SQLException;
	Uretici findById(Integer id) throws SQLException;
	Uretici findByAd(String ad) throws SQLException;
}
