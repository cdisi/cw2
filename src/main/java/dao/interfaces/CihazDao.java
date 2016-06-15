package dao.interfaces;

import java.sql.SQLException;

import model.Cihaz;

public interface CihazDao {
	Cihaz insert(Cihaz object) throws SQLException;
}
