package model.dao.Impl;

import database.DB;
import database.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.List;

/**
 * Implementação concreta da interface DepartmentDao que interage com o banco de dados usando JDBC.
 * Esta classe é responsável por fornecer métodos para acesso e manipulação de dados de departamentos.
 */
public class DepartmentDaoJDBC implements DepartmentDao {
  private Connection conn;

  /**
   * Construtor da classe DepartmentDaoJDBC que recebe uma conexão de banco de dados.
   *
   * @param conn A conexão de banco de dados a ser usada para as operações de acesso a dados.
   */
  public DepartmentDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  /**
   * insert - Insere um novo departamento no banco de dados.
   *
   * @param obj O objeto Department a ser inserido no banco de dados.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados ou se nenhuma linha foi afetada pela inserção.
   */
  @Override
  public void insert(Department obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
          "INSERT INTO department (Name) VALUE (?)",
          Statement.RETURN_GENERATED_KEYS);

      st.setString(1, obj.getName());

      int rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
          int id = rs.getInt(1);
          obj.setId(id);
        }
        DB.closeResultSet(rs);
      }
      else {
        throw new DbException("Unexpected error! No rows affected!");
      }
    }
    catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
    finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public void update(Department obj) {

  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public Department findById(Integer id) {
    return null;
  }

  @Override
  public List<Department> findAll() {
    return null;
  }
}
