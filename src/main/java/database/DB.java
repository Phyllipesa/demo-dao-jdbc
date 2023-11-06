package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


/**
 * Classe utilitária para operações de banco de dados, incluindo conexão, fechamento de recursos e
 * carregamento de propriedades de configuração do banco de dados.
 */
public class DB {
  private static Connection conn = null;

  /**
   * Obtém uma conexão de banco de dados.
   *
   * @return Uma instância de Connection representando a conexão com o banco de dados.
   */
  public static  Connection getConnection() {
    if (conn == null) {
      try {
        Properties props = loadProperties();
        String url = props.getProperty("dburl");
        conn = DriverManager.getConnection(url, props);
      }
      catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
    return conn;
  }

  /**
   * Carrega as propriedades de configuração do banco de dados a partir de um arquivo.
   *
   * @return Um objeto Properties contendo as propriedades de configuração do banco de dados.
   */
  private static Properties loadProperties() {

    try(FileInputStream fs = new FileInputStream("db.properties")) {
      Properties props = new Properties();
      props.load(fs);
      return props;
    }
    catch (IOException e) {
      throw new DbException(e.getMessage());
    }
  }

  /**
   * Fecha a conexão com o banco de dados, se estiver aberta.
   */
  public static void closeConnection() {
    if (conn != null) {
      try {
        conn.close();
      }
      catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }

  /**
   * Fecha um Statement, liberando os recursos associados a ele.
   *
   * @param st O objeto Statement a ser fechado.
   */
  public static void closeStatement(Statement st) {
    if (st != null) {
      try {
        st.close();
      }
      catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }

  /**
   * Fecha um ResultSet, liberando os recursos associados a ele.
   *
   * @param rs O objeto ResultSet a ser fechado.
   */
  public static void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      }
      catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }
}
