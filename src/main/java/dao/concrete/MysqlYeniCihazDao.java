package dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.YeniCihaz;
import dao.interfaces.YeniCihazDao;
import daoFactory.DaoFactory;

public class MysqlYeniCihazDao implements YeniCihazDao {
	private static final String ALL = "SELECT * FROM cihaz_url";
	
	public List<YeniCihaz> all() throws SQLException {
		ArrayList<YeniCihaz> cihazlar = new ArrayList<YeniCihaz>();
		
		Connection c = DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt = c.prepareStatement(ALL);

		ResultSet rset = pstmt.executeQuery();
		while (rset.next()){
			cihazlar.add(createCihaz(rset));
		}

		pstmt.close();
		c.close();

		return cihazlar;
	}
	
	private YeniCihaz createCihaz(ResultSet rset) throws SQLException{
		YeniCihaz cihaz = new YeniCihaz(rset.getInt("id"),rset.getInt("uretici_id"),rset.getString("url"),
							 rset.getInt("aktif"));
		
		return cihaz;
	}	

}
