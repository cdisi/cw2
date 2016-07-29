package controllers.cihaz_ozellik_atama;

import java.sql.SQLException;

import model.Cihaz;
import model.CihazOzellikAtama;

public class CihazOzellikAtamaController {
	
	private static CihazOzellikAtamaController instance = new CihazOzellikAtamaController();
	
	private CihazOzellikAtamaController(){}
	
	public static CihazOzellikAtamaController getInstance() {
		return instance;
	}	
	
	public Cihaz save(Cihaz cihaz, CihazOzellikAtama cihazOzellikAtama) throws SQLException {
		System.out.println(cihaz.getId());
		if (cihazOzellikAtama.find(cihaz, cihazOzellikAtama)) {
			cihazOzellikAtama.update(cihaz, cihazOzellikAtama);			
		}else{
			cihazOzellikAtama.insert(cihaz, cihazOzellikAtama);	
		}
		return cihaz;
	}		
	
}
