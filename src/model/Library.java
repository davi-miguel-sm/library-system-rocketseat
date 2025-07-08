package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Library {
  private static final Set<Client> clients = new HashSet<>();
  private static final Set<Book> books = new HashSet<>();
  private static final Set<Author> authors = new HashSet<>();
  private static final Map<Book, Client> bookLoans = new HashMap<>();
  private static final Map<Client, Set<Book>> clientLoans = new HashMap<>();

  private Library() {
  }

  public static Client getBookLoan(Book book) {
    if (book.getAvailable()) {
      System.out.println("This book are not loaned.");
      return null;
    }
    return bookLoans.get(book);
  }

  public static Set<Book> getClientLoans(Client client) {
    return clientLoans.get(client);
  }

  public static Set<Book> getBooks() {
    return Collections.unmodifiableSet(books);
  }

  public static List<Book> getAvailableBooks() {
    return books.stream().filter(bok -> bok.getAvailable()).toList();
  }

  public static Book getBookById(Integer id) {
    return books.stream().filter(bok -> bok.getId().equals(id)).findFirst().orElse(null);
  }

  public static Set<Author> getAuthors() {
    return Collections.unmodifiableSet(authors);
  }

  public static Set<Client> getClients() {
    return Collections.unmodifiableSet(clients);
  }

  public static Client getClientById(Integer id) {
    return clients.stream().filter(cli -> cli.getId().equals(id)).findFirst().orElse(null);
  }

  public static Boolean addClient(Client client) {
    return clients.add(client);
  }

  public static Boolean addBook(Book book) {
    return books.add(book);
  }

  public static Boolean addAuthors(Author author) {
    return authors.add(author);
  }

  public static void addLoan(Book book, Client client) {
    if (book == null) {
      System.out.println("An invalid book are selected, please insert a valid id.");
    } else if (book.getAvailable()) {
      book.setAvailable(false);
      bookLoans.put(book, client);
      clientLoans.computeIfAbsent(client, key -> new HashSet<>()).add(book);
      System.out.println("You book has been loaned.");
    } else {
      System.out.println("The book is not available.");
    }
  }

  public static void finishLoan(Book book, Client client) {
    if (!clientLoans.get(client).contains(book)) {
      System.out.println("This client not has this book.");
      return;
    }
    if (!book.getAvailable()) {
      book.setAvailable(true);
      bookLoans.remove(book);
      clientLoans.get(client).remove(book);
    } else {
      System.out.println("The book is not loaned.");
    }
  }
}
