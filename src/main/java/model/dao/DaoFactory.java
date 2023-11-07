package model.dao;

import database.DB;
import model.dao.Impl.DepartmentDaoJDBC;
import model.dao.Impl.SellerDaoJDBC;

/**
 * Classe de fábrica para criar instâncias de objetos SellerDao.
 * Esta classe fornece um método estático para criar uma implementação concreta
 * de SellerDao, que é instanciada com uma conexão de banco de dados.
 */
public class DaoFactory {
  /**
   * Cria e retorna uma instância de SellerDao com uma conexão de banco de dados.
   *
   * @return Uma instância de SellerDao configurada com uma conexão de banco de dados.
   */
  public static SellerDao createSellerDao() {
    return new SellerDaoJDBC(DB.getConnection());
  }

  /**
   * Cria e retorna uma instância de DepartmentDao com uma conexão de banco de dados.
   *
   * @return Uma instância de DepartmentDao configurada com uma conexão de banco de dados.
   */
  public static DepartmentDao createDepartmentDao() {
    return new DepartmentDaoJDBC(DB.getConnection());
  }
}
