package controllers.cihaz;

import java.sql.SQLException;

import model.Cihaz;
import model.Uretici;

public class CihazController {
	private static CihazController instance = new CihazController();
	
	private CihazController(){}
	
	public static CihazController getInstance() {
		return instance;
	}
	
	public Cihaz save(Cihaz cihaz, Uretici uretici) throws SQLException {
		cihaz.insert(cihaz, uretici);
		return cihaz;
	}	
}
