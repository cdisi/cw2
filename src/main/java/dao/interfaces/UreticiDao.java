package dao.interfaces;

import java.sql.SQLException;

import model.Uretici;

public interface UreticiDao {
	Uretici findById(Integer id) throws SQLException;
}
