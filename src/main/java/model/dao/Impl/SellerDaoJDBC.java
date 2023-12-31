package model.dao.Impl;

import database.DB;
import database.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Implementação concreta da interface SellerDao que interage com o banco de dados usando JDBC.
 * Esta classe é responsável por fornecer métodos para acesso e manipulação de dados de vendedores.
 */
public class SellerDaoJDBC implements SellerDao {
  private Connection conn;

  /**
   * Construtor da classe SellerDaoJDBC que recebe uma conexão de banco de dados.
   *
   * @param conn A conexão de banco de dados a ser usada para as operações de acesso a dados.
   */
  public SellerDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  /**
   * Insere um novo vendedor no banco de dados.
   *
   * @param obj O objeto Seller a ser inserido no banco de dados.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados ou se nenhuma linha foi afetada pela inserção.
   */
  @Override
  public void insert(Seller obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
          "INSERT INTO seller "
              + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
              + "VALUES "
              + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
      );

      st.setString(1, obj.getName());
      st.setString(2, obj.getEmail());
      st.setDate(3,new Date(obj.getBirthDate().getTime()));
      st.setDouble(4, obj.getBaseSalary());
      st.setInt(5, obj.getDepartment().getId());

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
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
    finally {
      DB.closeStatement(st);
    }
  }

  /**
   * update - Atualiza as informações de um vendedor no banco de dados.
   *
   * @param obj O objeto Seller com as informações atualizadas a serem persistidas.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public void update(Seller obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
          "UPDATE seller "
              + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
              + "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS
      );

      st.setString(1, obj.getName());
      st.setString(2, obj.getEmail());
      st.setDate(3,new Date(obj.getBirthDate().getTime()));
      st.setDouble(4, obj.getBaseSalary());
      st.setInt(5, obj.getDepartment().getId());
      st.setInt(6, obj.getId());
      st.executeUpdate();

    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
    finally {
      DB.closeStatement(st);
    }
  }

  /**
   * Remove um vendedor do banco de dados com base em seu ID.
   *
   * @param id O ID do vendedor a ser excluído do banco de dados.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public void deleteById(Integer id) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
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
   * findById - Busca um vendedor no banco de dados com base no seu ID.
   *
   * Este método realiza uma consulta SQL para selecionar informações do vendedor
   * e do departamento associado a esse vendedor, caso exista, e então cria e
   * retorna um objeto Seller com essas informações.
   *
   * @param id O ID do vendedor que se deseja buscar no banco de dados.
   * @return Um objeto Seller encontrado no banco de dados com base no ID fornecido.
   *         Se o vendedor não for encontrado, retorna null.
   * @throws DbException Lançada se ocorrerem erros de banco de dados durante a execução
   *                    da consulta SQL.
   */
  @Override
  public Seller findById(Integer id) {
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
      st = conn.prepareStatement(
          "SELECT seller.*, department.Name as DepName "
          + "FROM seller INNER JOIN department "
          + "ON seller.DepartmentId = department.Id "
          + "WHERE seller.Id = ?"
      );

      st.setInt(1, id);
      rs = st.executeQuery();

      if (rs.next()) {
        Department dep = instantiateDepartment(rs);
        return instantiateSeller(rs, dep);
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

  /**
   * instantiateSeller - Cria e retorna um objeto Seller a partir dos dados obtidos de um ResultSet.
   *
   * Este método é responsável por criar um objeto Seller com base nas informações
   * recuperadas de um ResultSet, incluindo o ID, nome, e-mail, salário base, data de nascimento
   * e departamento associado.
   *
   * @param rs O ResultSet que contém os dados do vendedor.
   * @param dep O objeto Department associado ao vendedor.
   * @return Um objeto Seller criado a partir dos dados no ResultSet.
   * @throws SQLException Lançada em caso de erro ao acessar dados no ResultSet.
   */
  private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
    Seller obj = new Seller();
    obj.setId(rs.getInt("Id"));
    obj.setName(rs.getString("Name"));
    obj.setEmail(rs.getString("Email"));
    obj.setBaseSalary(rs.getDouble("BaseSalary"));
    obj.setBirthDate(rs.getDate("BirthDate"));
    obj.setDepartment(dep);
    return obj;
  }

  /**
   * instantiateDepartment - Cria e retorna um objeto Department a partir dos dados obtidos de um ResultSet.
   *
   * Este método é responsável por criar um objeto Department com base nas informações
   * recuperadas de um ResultSet, incluindo o ID e o nome do departamento.
   *
   * @param rs O ResultSet que contém os dados do departamento.
   * @return Um objeto Department criado a partir dos dados no ResultSet.
   * @throws SQLException Lançada em caso de erro ao acessar dados no ResultSet.
   */
  private Department instantiateDepartment(ResultSet rs) throws SQLException {
    Department dep = new Department();
    dep.setId(rs.getInt("DepartmentId"));
    dep.setName(rs.getString("DepName"));
    return dep;
  }

  /**
   * findAll - Recupera todos os vendedores existentes no banco de dados.
   *
   * @return Uma lista de objetos Seller contendo todos os vendedores no banco de dados.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public List<Seller> findAll() {
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
      st = conn.prepareStatement(
          "SELECT seller.*, department.Name as DepName "
              + "FROM seller INNER JOIN department "
              + "ON seller.DepartmentId = department.Id "
              + "ORDER BY Name");

      rs = st.executeQuery();
      List<Seller> list = new ArrayList<>();
      Map<Integer, Department> map = new HashMap<>();

      while (rs.next()) {
        Department dep = map.get(rs.getInt("DepartmentId"));

        if (dep == null) {
          dep = instantiateDepartment(rs);
          map.put(rs.getInt("DepartmentId"), dep);
        }
        Seller obj = instantiateSeller(rs, dep);
        list.add(obj);
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

  /**
   * findByDepartment - Busca vendedores com base no departamento especificado.
   *
   * @param department O objeto Department que representa o departamento pelo qual deseja-se buscar vendedores.
   * @return Uma lista de objetos Seller contendo os vendedores que pertencem ao departamento especificado.
   * @throws DbException Lançada em caso de erro ao acessar o banco de dados.
   */
  @Override
  public List<Seller> findByDepartment(Department department) {
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
      st = conn.prepareStatement(
          "SELECT seller.*, department.Name as DepName "
          + "FROM seller INNER JOIN department "
          + "ON seller.DepartmentId = department.Id "
          + "WHERE DepartmentId = ? "
          + "ORDER BY Name");

      st.setInt(1, department.getId());
      rs = st.executeQuery();
      List<Seller> list = new ArrayList<>();
      Map<Integer, Department> map = new HashMap<>();

      while (rs.next()) {
        Department dep = map.get(rs.getInt("DepartmentId"));

        if (dep == null) {
          dep = instantiateDepartment(rs);
          map.put(rs.getInt("DepartmentId"), dep);
        }
        Seller obj = instantiateSeller(rs, dep);
        list.add(obj);
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
