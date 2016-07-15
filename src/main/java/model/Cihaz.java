package model;

import java.sql.SQLException;

import dao.interfaces.CihazDao;
import daoFactory.DaoFactory;

public class Cihaz {

	private Integer id;
	private String ad;
	private Integer ureticiId;
	private String duyurulma;
	private String duyurulmaYil;
	private String duyurulmaAy;
	private String sim;
	
	public Cihaz(){
	}	
	
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
	
	public Integer getUreticiId(){
		return this.ureticiId;
	}
	
	public void setUreticiId(Integer ureticiId){
		this.ureticiId=ureticiId;
	}
	
	public void setDuyurulmaYil(String y){
		this.duyurulmaYil=y;
	}
	
	public String getDuyurulmaYil(){
		return this.duyurulmaYil;
	}
	
	public void setDuyurulmaAy(String a){
		this.duyurulmaAy=a;
	}
	
	public String getDuyurulmaAy(){
		return this.duyurulmaAy;
	}
	
	public void setDuyurulma(String d){
		this.duyurulma=d;
	}
	
	public String getDuyurulma(){
		return this.duyurulma;
	}
	
	public void setSim(String s){
		this.sim=s;
	}
	
	public String getSim(){
		return this.sim;
	}
	
	private static CihazDao cihazDAO(){
		DaoFactory dao = DaoFactory.getDatabase();
		return dao.getCihazDao();
	}	
	
	public void save(Cihaz cihaz, Uretici uretici) throws SQLException{
		cihazDAO().insert(cihaz, uretici);
	}	
}
