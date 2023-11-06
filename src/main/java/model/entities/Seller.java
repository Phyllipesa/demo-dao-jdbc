package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Representa um vendedor (Seller) com atributos como ID, nome, e-mail,
 * data de nascimento, salário base e departamento associado.
 * A classe implementa a interface Serializable para suportar serialização.
 */
public class Seller implements Serializable {
  private Integer id;
  private String name;
  private String email;
  private Date birthDate;
  private Double baseSalary;
  private Department department;

  /**
   * Construtor padrão da classe Seller.
   * Inicializa os atributos com valores padrão.
   */
  public Seller() {
  }

  /**
   * Construtor da classe Seller que permite a inicialização de todos os atributos.
   *
   * @param id          O ID do vendedor.
   * @param name        O nome do vendedor.
   * @param email       O endereço de e-mail do vendedor.
   * @param birthDate   A data de nascimento do vendedor.
   * @param baseSalary  O salário base do vendedor.
   * @param department  O departamento associado ao vendedor.
   */
  public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Department department) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.birthDate = birthDate;
    this.baseSalary = baseSalary;
    this.department = department;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Double getBaseSalary() {
    return baseSalary;
  }

  public void setBaseSalary(Double baseSalary) {
    this.baseSalary = baseSalary;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Seller seller)) return false;
    return Objects.equals(id, seller.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Seller{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", birthDate=" + birthDate +
        ", baseSalary=" + baseSalary +
        ", department=" + department +
        '}';
  }

//  @Override
//  public String toString() {
//    return "Seller { \n" +
//        " id= " + id +
//        ",\n name= '" + name + '\'' +
//        ",\n email= '" + email + '\'' +
//        ",\n birthDate= " + birthDate +
//        ",\n baseSalary= " + baseSalary +
//        ",\n " + department +
//        '}';
//  }
}
