package dao.interfaces;

import java.sql.SQLException;

import model.Cihaz;
import model.Uretici;

public interface CihazDao {
	Cihaz insert(Cihaz cihaz, Uretici uretici) throws SQLException;
	Cihaz update(Cihaz cihaz, Uretici uretici) throws SQLException;
	Cihaz findByName(String ad, Uretici uretici) throws SQLException;	
}
