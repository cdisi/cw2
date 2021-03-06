package dao.interfaces;

import java.sql.SQLException;

import model.Cihaz;
import model.CihazOzellikAtama;

public interface CihazOzellikAtamaDao {
	CihazOzellikAtama insert(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException;
	CihazOzellikAtama update(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException;
	Boolean find(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException;

}
