package controllers.yeni_cihaz;

import java.sql.SQLException;
import java.util.List;

import model.YeniCihaz;

public class YeniCihazController {
	
	private static YeniCihazController instance = new YeniCihazController();
	private YeniCihazController(){};
	
	public static YeniCihazController getInstance() {
		return instance;
	}
	
	public List<YeniCihaz> yeniCihazlar() throws SQLException{
		return YeniCihaz.all();
	}
	
}
