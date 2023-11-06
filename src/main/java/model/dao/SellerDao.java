package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

/**
 * Esta interface define métodos para acessar e manipular dados de vendedores (Seller) no banco de dados.
 */
public interface SellerDao {
  /**
   * Insere um vendedor no banco de dados.
   *
   * @param obj O objeto Seller a ser inserido no banco de dados.
   */
  void insert(Seller obj);

  /**
   * Atualiza as informações de um vendedor no banco de dados.
   *
   * @param obj O objeto Seller com as informações atualizadas a serem persistidas.
   */
  void update(Seller obj);

  /**
   * Remove um vendedor do banco de dados com base em seu ID.
   *
   * @param obj O objeto Seller a ser removido do banco de dados.
   */
  void deleteById(Seller obj);

  /**
   * Busca um vendedor no banco de dados com base em seu ID.
   *
   * @param id O ID do vendedor que se deseja buscar no banco de dados.
   * @return Um objeto Seller encontrado no banco de dados com base no ID fornecido.
   */
  Seller findById(Integer id);

  /**
   * Recupera todos os vendedores existentes no banco de dados.
   *
   * @return Uma lista de objetos Seller contendo todos os vendedores no banco de dados.
   */
  List<Seller> findAll();

  /**
   * Busca vendedores com base no departament especificado.
   *
   * @param department O ID do department que se deseja buscar no banco de dados.
   * @return Uma lista de objetos Seller contendo os vendedores que pertencem ao departamento especificado.
   */
  List<Seller> findByDepartment(Department department);
}
