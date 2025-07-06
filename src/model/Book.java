package model;

import java.time.LocalDate;

public class Book {
  private Integer id;
  private String title;
  private Author author;
  private Boolean available;
  private LocalDate register;

  public Book(String title, Author author) {
    this.id = (int) (Math.random() * 1000) + 1;
    this.title = title;
    this.author = author;
    this.available = true;
    this.register = LocalDate.now();
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Author getAuthor() {
    return author;
  }

  public LocalDate getRegister() {
    return register;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }

}
