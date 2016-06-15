package daoFactory;

import java.sql.Connection;

import dao.interfaces.YeniCihazDao;
import dao.interfaces.UreticiDao;
import dao.interfaces.CihazDao;
public abstract class DaoFactory {

  /* 
   * There will be a method for each DAO that can be
   * created. The concrete factories will have to
   * implement these methods.
   */
  public abstract Connection openConnection();	
  public abstract YeniCihazDao getYeniCihazDao();
  public abstract UreticiDao getUreticiDao();
  public abstract CihazDao getCihazDao();
  
  public static DaoFactory getDatabase() {
      return new Mysql();
  }
}
