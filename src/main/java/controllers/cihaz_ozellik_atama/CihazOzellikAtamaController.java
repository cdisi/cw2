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
		if (cihazOzellikAtama != null) {
			cihazOzellikAtama.save(cihaz, cihazOzellikAtama);			
		}
		return cihaz;
	}		
	
}
