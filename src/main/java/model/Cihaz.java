package model;

import dao.interfaces.CihazDao;
import daoFactory.DaoFactory;

public class Cihaz {

	private Integer id;
	private String ad;
	
	public Cihaz(String ad){
		this.ad=ad;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id=id;
	}	
	
	public String getAd(){
		return this.ad;
	}
	
	public void setAd(String ad){
		this.ad=ad;
	}
	
	private static CihazDao cihazDAO(){
		DaoFactory dao = DaoFactory.getDatabase();
		return dao.getCihazDao();
	}	
}
