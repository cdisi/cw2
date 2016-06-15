package controllers.cihaz;

import java.sql.SQLException;

import model.Cihaz;

public class CihazController {
	private static CihazController instance = new CihazController();
	
	private CihazController(){}
	
	public static CihazController getInstance() {
		return instance;
	}
	
	public Cihaz save(Cihaz cihaz) throws SQLException {
		if (cihaz != null) {
			cihaz.save();
			notifyListeners(user);
		}
		return cihaz;
	}	
}
