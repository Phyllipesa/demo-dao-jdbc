package model.dao.Impl;

import database.DB;
import database.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
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

  /**
   * update - Atualiza as informações de um departamento no banco de dados.
   *
   * @param obj O objeto Department com as informações atualizadas a serem persistidas.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public void update(Department obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
          "UPDATE department SET Name = ? "
              + "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS
      );

      st.setString(1, obj.getName());
      st.setInt(2, obj.getId());
      st.executeUpdate();
    }
    catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
    finally {
      DB.closeStatement(st);
    }
  }

  /**
   * Remove um departamento do banco de dados com base em seu ID.
   *
   * @param id O ID do departamento a ser excluído do banco de dados.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public void deleteById(Integer id) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
          "DELETE FROM department WHERE Id = ?");
      st.setInt(1, id);
      st.executeUpdate();
    }
    catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
    finally {
      DB.closeStatement(st);
    }
  }

  /**
   * findById - Busca um departamento no banco de dados com base em seu ID.
   *
   * @param id O ID do departamento a ser buscado.
   * @return Um objeto Department contendo as informações do departamento encontrado, ou nulo se não for encontrado.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public Department findById(Integer id) {
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
      st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
      st.setInt(1, id);
      rs = st.executeQuery();

      if (rs.next()) {
        Department dep = instantiateDepartment(rs);
        return dep;
      }
      return null;
    }
    catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
    finally {
      DB.closeStatement(st);
      DB.closeResultSet(rs);
    }
  }

  private Department instantiateDepartment(ResultSet rs) throws SQLException {
    return new Department(
        rs.getInt("Id"),
        rs.getString("Name")
    );
  }

  /**
   * findAll - Recupera todos os departamentos existentes no banco de dados.
   *
   * @return Uma lista de objetos Department contendo todos os departamentos no banco de dados.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public List<Department> findAll() {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement("SELECT * FROM department");
      rs = st.executeQuery();
      List<Department> list = new ArrayList<>();

      while (rs.next()) {
        list.add(instantiateDepartment(rs));
      }
      return list;
    }
    catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
    finally {
      DB.closeStatement(st);
      DB.closeResultSet(rs);
    }
  }
}
