package model.dao;

import database.DB;
import model.dao.Impl.SellerDaoJDBC;

public class DaoFactory {
  public static SellerDao createSellerDao() {
    return new SellerDaoJDBC(DB.getConnection());
  }
}
