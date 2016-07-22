package dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.interfaces.CihazOzellikAtamaDao;
import daoFactory.DaoFactory;
import model.Cihaz;
import model.CihazOzellikAtama;

public class MysqlCihazOzellikAtama implements CihazOzellikAtamaDao {
	
	private static final String
	INSERT = "INSERT INTO cihaz_ozellik_atama (ozellik_id,deger) VALUES (?,?)";

	public CihazOzellikAtama insert(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, cihazOzellikAtama.getOzellikId());
		pstmt.setString(2, cihazOzellikAtama.getDeger());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return cihazOzellikAtama;
	}
	
}
