package daoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.concrete.MysqlYeniCihazDao;
import dao.concrete.MysqlUreticiDao;
import dao.concrete.MysqlCihazDao;
import dao.concrete.MysqlCihazOzellikAtama;

import dao.interfaces.YeniCihazDao;
import dao.interfaces.UreticiDao;
import dao.interfaces.CihazDao;
import dao.interfaces.CihazOzellikAtamaDao;

public class Mysql extends DaoFactory {
	
	private static String url = "jdbc:mysql://10.5.0.81:3306/";
	private static String database = "cw";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "zkaya";
	private static String password = "";
	
	public Connection openConnection() {   
		try {
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url + database, user, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			System.err.println(ex.getMessage());
				
		}
		return null;
	}
	
	@Override
	public YeniCihazDao getYeniCihazDao() {
		return new MysqlYeniCihazDao();
	}
	@Override
	public UreticiDao getUreticiDao() {
		return new MysqlUreticiDao();
	}	
	@Override
	public CihazDao getCihazDao() {
		return new MysqlCihazDao();
	}	
	@Override
	public CihazOzellikAtamaDao getCihazOzellikAtamaDao() {
		return new MysqlCihazOzellikAtama();
	}	
}
