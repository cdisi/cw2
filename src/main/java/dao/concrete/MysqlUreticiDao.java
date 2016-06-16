package dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.UreticiDao;
import daoFactory.DaoFactory;
import model.Uretici;
import model.YeniCihaz;

public class MysqlUreticiDao implements UreticiDao {
	
	private static final String FIND_BY_ID = "SELECT * FROM uretici WHERE id = ?";		
	private static final String ALL = "SELECT * FROM uretici";
	
	public List<Uretici> all() throws SQLException {
		ArrayList<Uretici> ureticiler = new ArrayList<Uretici>();
		
		Connection c = DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		while (rset.next()){
			ureticiler.add(createUretici(rset));
		}

		pstmt.close();
		c.close();

		return ureticiler;
	}	
	
	public Uretici findById(Integer id) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();

		PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
		pstmt.setInt(1, id);
		
		Uretici uretici = null;
		ResultSet rset = pstmt.executeQuery();

		while (rset.next()){
			uretici = createUretici(rset);
		}

		pstmt.close();
		c.close();

		return uretici;
	}	

	private Uretici createUretici(ResultSet rset) throws SQLException{
		Uretici uretici = new Uretici(rset.getString("ad"),rset.getString("logo_url"),rset.getString("gsm_arena_url"));							 		
		uretici.setId(rset.getInt("id"));
		uretici.setAktif(rset.getInt("aktif"));
		return uretici;
	}	
	
}
