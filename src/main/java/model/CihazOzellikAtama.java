package model;

import java.sql.SQLException;

import dao.interfaces.CihazOzellikAtamaDao;
import daoFactory.DaoFactory;

public class CihazOzellikAtama {
	
	public Integer ozellikId;
	public String deger;
	
	public CihazOzellikAtama(Integer ozellikId, String deger){
		this.ozellikId=ozellikId;
		this.deger=deger;
	}
	
	public Integer getOzellikId(){
		return this.ozellikId;
	}
	
	public String getDeger(){
		return this.deger;
	}
	
	private static CihazOzellikAtamaDao cihazOzellikAtamaDao(){
		DaoFactory dao = DaoFactory.getDatabase();
		return dao.getCihazOzellikAtamaDao();
	}	
	
	public Boolean find(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		return cihazOzellikAtamaDao().find(cihaz,cihazOzellikAtama);
	}	
	
	public void insert(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException{
		cihazOzellikAtamaDao().insert(cihaz, cihazOzellikAtama);
	}
	public void update(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException{
		cihazOzellikAtamaDao().update(cihaz, cihazOzellikAtama);
	}
	
}
