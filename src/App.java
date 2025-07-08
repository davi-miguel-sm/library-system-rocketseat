import java.util.Scanner;

import model.Author;
import model.Book;
import model.Client;
import model.Library;

public class App {
    private static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Integer menu = 99;

        while (menu != 0) {
            System.out.println("\nSelect the option:\n1 - Client\n2 - Adm\n0 - exit");
            menu = readIntegerOption();
            switch (menu) {
                case 1:
                    System.out.println("Insert client id:");
                    Integer clientId = readIntegerOption();
                    if (!validateClientId(clientId)) {
                        System.out.println("The client is not registered.");
                        break;
                    }
                    clientMenu(clientId);
                    break;
                case 2:
                    admMenu();
                    break;
                case 0:
                    System.out.println("The program will be finished.");
                    break;
                default:
                    System.out.println("Please, select a valid option.");
            }

        }
    }

    public static void clientMenu(Integer clientId) {
        Client client = Library.getClients()
                .stream().filter(c -> c.getId().equals(clientId))
                .findFirst()
                .orElse(null);
        Integer clientMenu = 99;
        String confirm = "";
        while (clientMenu != 0) {
            var clientBooks = Library.getClientLoans(client);

            System.out.println(
                    "\nSelect the option:\n1 - List my books\n2 - List available books\n3 - Check Out a book\n4 - Return a book\n0 - Return to main menu");
            clientMenu = readIntegerOption();
            switch (clientMenu) {
                case 1:
                    System.out
                            .println(clientBooks == null ? "You have no books." : "Your books: " + clientBooks);
                    break;
                case 2:
                    System.out.println("This is all available books: ");
                    Library.getAvailableBooks()
                            .forEach(bok -> System.out
                                    .println(bok.getTitle() + " " + bok.getAuthor().getName() + " " + bok.getId()));
                    break;
                case 3:
                    System.out.println(String.format("This is your name?(Y or N)\n%s", client.getName()));
                    confirm = read.nextLine();
                    if ("Y".equalsIgnoreCase(confirm)) {
                        System.out.println("This is all available books: " + Library.getAvailableBooks());
                        System.out.println("\nPlease insert the id of the book you want: ");
                        Integer bookId = readIntegerOption();
                        Book book = Library.getBookById(bookId);
                        Library.addLoan(book, client);
                    } else {
                        System.out.println("The actual logged client is not you, please restart the options.");
                    }
                    break;
                case 4:
                    System.out.println(String.format("This is your name?(Y or N)\n%s", client.getName()));
                    confirm = read.nextLine();
                    if ("Y".equalsIgnoreCase(confirm)) {
                        System.out.println("This is your books: " + clientBooks);
                        if (clientBooks == null) {
                            System.out.println("You have no books.");
                            break;
                        }
                        System.out.println("\nPlease insert the id of the book you want refund: ");
                        Integer bookId = readIntegerOption();
                        Book book = clientBooks.stream().filter(b -> b.getId().equals(bookId))
                                .findFirst()
                                .orElse(null);
                        if (book == null) {
                            System.out.println("An invalid option are selected, please insert a valid id.");
                        } else {
                            Library.finishLoan(book, client);
                            System.out.println("You book has been refunded.");
                        }
                    } else {
                        System.out.println("The actual logged client is not you, please restart the options.");
                    }
                    break;
                case 0:
                    System.out.println("The program will return to main menu.");
                    break;
                default:
                    System.out.println("Please, select a valid option.");

            }
        }
    }

    public static void admMenu() {
        Integer admMenu = 99;
        while (admMenu != 0) {

            System.out.println(
                    "\nSelect the option:\n1 - Add a book\n2 - Add a Client\n3 - List check outs\n4 - List Clients\n5 - List Books\n0 - Return to main menu");
            admMenu = readIntegerOption();
            switch (admMenu) {
                case 1:
                    System.out.println("Insert the Author name:");
                    String authorName = read.nextLine();
                    Author author = new Author(authorName);
                    System.out.println("Insert the book title:");
                    String bookTitle = read.nextLine();
                    Library.addAuthors(author);
                    Library.addBook(new Book(bookTitle, author));
                    break;
                case 2:
                    System.out.println("Insert the client name: ");
                    String clientName = read.nextLine();
                    Library.addClient(new Client(clientName));
                    break;
                case 3:
                    System.out.println("This is all Library's books: " + Library.getBooks());
                    System.out.println("\nInsert the book id you want to check: ");
                    Integer bookId = readIntegerOption();
                    Book book = Library.getBookById(bookId);
                    if (book == null) {
                        System.out.println("The inserted book is invalid.");
                        break;
                    }
                    System.out.println(Library.getBookLoan(book));
                    break;
                case 4:
                    System.out.println("This is our clients: ");
                    Library.getClients().forEach(cli -> System.out.println(cli.getName() + " " + cli.getId()));
                    break;
                case 5:
                    System.out.println("This is our books: ");
                    Library.getBooks().forEach(bok -> System.out.println(bok.getTitle() + " " + bok.getId()));
                    break;
                case 0:
                    System.out.println("The program will return to main menu.");
                    break;
                default:
                    System.out.println("Please, select a valid option.");

            }
        }
    }

    private static Integer readIntegerOption() {
        try {
            Integer menu = read.nextInt();
            read.nextLine();
            return menu;
        } catch (Exception e) {
            System.out.println("The selected option is invalid, please dont do that.");
            read.nextLine();
            return 0;
        }
    }

    private static boolean validateClientId(Integer clientId) {
        Client client = Library.getClientById(clientId);
        System.out.println("Validando " + (client != null ? client.getId() : "É nulo"));
        if (client == null) {
            return false;
        }
        return true;
    }
}
