package model;

import java.time.LocalDate;

public class Client {
  private Integer id;
  private String name;
  private LocalDate birth;
  private String email;

  public Client(String name) {
    this.id = (int) (Math.random() * 999) + 1;
    this.name = name;
    this.birth = LocalDate.of((int) (Math.random() * 50) + 1950, (int) (Math.random() * 11) + 1,
        (int) (Math.random() * 29) + 1);
    this.email = String.format("%s@email.com", this.name);
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getBirth() {
    return birth;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
