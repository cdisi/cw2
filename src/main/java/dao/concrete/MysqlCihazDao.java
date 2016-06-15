package dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daoFactory.DaoFactory;
import model.Cihaz;
import dao.interfaces.CihazDao;

public class MysqlCihazDao implements CihazDao {
	private static final String
	INSERT = "INSERT INTO cihaz (ad) VALUES (?)";
	
	public Cihaz insert(Cihaz cihaz) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, cihaz.getAd());

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
