package ru.mtrefelov.accounting.persistence;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {
  @Id
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

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
}
