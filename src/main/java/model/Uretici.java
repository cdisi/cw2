package model;

public class Uretici {
	
	private Integer id;
	private String ad;
	private String logoUrl;
	private String gsmArenaUrl;
	private Integer aktif;
	
	public Uretici(String ad, String logoUrl, String gsmArenaUrl){
		this.ad=ad;
		this.logoUrl=logoUrl;
		this.gsmArenaUrl=gsmArenaUrl;
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
	
	public String getLogoUrl(){
		return this.logoUrl;
	}
	
	public void setLogoUrl(String logoUrl){
		this.logoUrl=logoUrl;
	}
	
	public String getGsmArenaUrl(){
		return this.gsmArenaUrl;
	}
	
	public void setGsmArenaUrl(String gsmArenaUrl){
		this.gsmArenaUrl=gsmArenaUrl;
	}
	
	public Integer getAkif(){
		return this.aktif;
	}
	
	public void setAktif(Integer aktif){
		this.aktif=aktif;
	}
	
}
