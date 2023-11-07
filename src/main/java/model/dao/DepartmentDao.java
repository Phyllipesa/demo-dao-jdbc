package model.dao;

import model.entities.Department;

import java.util.List;

/**
 * Esta interface define métodos para acessar e manipular dados de departamentos (Department) no banco de dados.
 */
public interface DepartmentDao {
  /**
   * Insere um departamento no banco de dados.
   *
   * @param obj O objeto Department a ser inserido no banco de dados.
   */
  void insert(Department obj);

  /**
   * Atualiza as informações de um departamento no banco de dados.
   *
   * @param obj O objeto Department com as informações atualizadas a serem persistidas.
   */
  void update(Department obj);

  /**
   * Remove um departamento do banco de dados com base em seu ID.
   *
   * @param obj O objeto Department a ser removido do banco de dados.
   */
  void deleteById(Integer id);

  /**
   * Busca um departamento no banco de dados com base em seu ID.
   *
   * @param id O ID do departamento que se deseja buscar no banco de dados.
   * @return Um objeto Department encontrado no banco de dados com base no ID fornecido.
   */
  Department findById(Integer id);

  /**
   * Recupera todos os departamentos existentes no banco de dados.
   *
   * @return Uma lista de objetos Department contendo todos os departamentos no banco de dados.
   */
  List<Department> findAll();
}

