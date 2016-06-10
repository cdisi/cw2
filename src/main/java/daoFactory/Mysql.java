package daoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.concrete.MysqlUserDao;
import dao.interfaces.UserDao;

public class Mysql extends DaoFactory {
	
	private static String url = "jdbc:mysql://10.5.0.70:3306/";
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
			System.err.println(
				"Não foi possével salvar os dados! O Banco de dados não estão respondendo!");
		}
		return null;
	}
	
	@Override
	public UserDao getUserDao() {
		return new MysqlUserDao();
	}
}