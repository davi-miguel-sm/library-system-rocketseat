package model;

import java.time.LocalDate;

public class Author {
  private Integer id;
  private String name;
  private LocalDate birth;

  public Author(String name) {
    this.name = name;
    this.id = (int) (Math.random() * 999) + 1;
    this.birth = LocalDate.of((int) (Math.random() * 50) + 1950, (int) (Math.random() * 11) + 1,
        (int) (Math.random() * 29) + 1);
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
}
