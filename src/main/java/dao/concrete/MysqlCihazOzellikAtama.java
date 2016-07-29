package dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaces.CihazOzellikAtamaDao;
import daoFactory.DaoFactory;
import model.Cihaz;
import model.CihazOzellikAtama;

public class MysqlCihazOzellikAtama implements CihazOzellikAtamaDao {
	
	private static final String
	INSERT = "INSERT INTO cihaz_ozellik_atama (cihaz_id,ozellik_id,deger) VALUES (?,?,?)";
	private static final String
	UPDATE = "UPDATE cihaz_ozellik_atama SET deger=? WHERE cihaz_id=? AND ozellik_id=?";
	private static final String
	FIND = "SELECT * FROM cihaz_ozellik_atama WHERE cihaz_id = ? AND ozellik_id = ?";
	
	public CihazOzellikAtama insert(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cihazOzellikAtama.getOzellikId());
		pstmt.setString(3, cihazOzellikAtama.getDeger());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return cihazOzellikAtama;
	}
	
	public CihazOzellikAtama update(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cihazOzellikAtama.getOzellikId());
		pstmt.setString(3, cihazOzellikAtama.getDeger());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return cihazOzellikAtama;
	}	
	
	public Boolean find(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND);
		pstmt.setInt(1, cihaz.getId());
		pstmt.setInt(2, cihazOzellikAtama.getOzellikId());
		System.out.println(pstmt);
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			return true;
		}

		pstmt.close();
		c.close();

		return false;
	}	
	
}
