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
	private static final String
	UPDATE = "UPDATE cihaz SET ad=?, uretici_id=?, duyurulma=?, sim=? WHERE id=?";
	private static final String
	FIND_BY_NAME = "SELECT * FROM cihaz WHERE uretici_id = ? AND ad=?";
	
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
	
	public Cihaz update(Cihaz cihaz, Uretici uretici) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		
		PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, cihaz.getAd());
		pstmt.setInt(2, uretici.getId());
		pstmt.setString(3, cihaz.getDuyurulma());
		pstmt.setString(4, cihaz.getSim());
		pstmt.setInt(5, cihaz.getId());
		pstmt.executeUpdate();

		pstmt.close();
		c.close();
		
		return cihaz;
	}	
	
	public Cihaz findByName(String ad, Uretici uretici) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_NAME);
		pstmt.setInt(1, uretici.getId());
		pstmt.setString(2, ad);
		ResultSet rset = pstmt.executeQuery();
		Cihaz cihaz = null; 
		while (rset.next()){
			cihaz = createCihaz(rset);
		}

		pstmt.close();
		c.close();

		return cihaz;
	}	
	
	private Cihaz createCihaz(ResultSet rset) throws SQLException{
		Cihaz cihaz = new Cihaz();
		cihaz.setId(rset.getInt("id"));
		cihaz.setAd(rset.getString("ad"));
		cihaz.setUreticiId(rset.getInt("uretici_id"));
		cihaz.setDuyurulma(rset.getString("duyurulma"));
		cihaz.setSim(rset.getString("sim"));
		return cihaz;
	}	
	
}
