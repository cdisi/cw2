package dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daoFactory.DaoFactory;
import model.Cihaz;
import model.Uretici;
import dao.interfaces.CihazDao;

public class MysqlCihazDao implements CihazDao {
	private static final String
	INSERT = "INSERT INTO cihaz (ad,uretici_id,duyurulma,sim) VALUES (?,?,?,?)";
	
	public Cihaz insert(Cihaz cihaz, Uretici uretici) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, cihaz.getAd());
		pstmt.setInt(2, uretici.getId());
		pstmt.setString(3, cihaz.getDuyurulma());
		pstmt.setString(4, cihaz.getSim());
		pstmt.executeUpdate();

		ResultSet rset = pstmt.getGeneratedKeys();

		rset.next();
		Integer idGenerated = rset.getInt(1);
		cihaz.setId(idGenerated);

		pstmt.close();
		c.close();
		
		return cihaz;
	}
	
}
