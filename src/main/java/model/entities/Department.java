package model.entities;

import java.io.Serializable;
import java.util.Objects;


/**
 * Representa um departamento (Department) com atributos como ID e nome.
 * A classe implementa a interface Serializable para suportar serialização.
 */
public class Department implements Serializable {

  private Integer id;
  private String name;

  /**
   * Construtor padrão da classe Department.
   * Inicializa os atributos com valores padrão.
   */
  public Department() {
  }

  /**
   * Construtor da classe Department que permite a inicialização de todos os atributos.
   *
   * @param id    O ID do departamento.
   * @param name  O nome do departamento.
   */
  public Department(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Department that)) return false;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

//  @Override
//  public String toString() {
//    return "Department= {" +
//        "\n     id= " + id +
//        ",\n     name= '" + name + '\'' + "\n   " +
//        '}' + "\n";
//  }
}
