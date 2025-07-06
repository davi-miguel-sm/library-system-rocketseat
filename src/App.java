import java.util.Scanner;

public class App {
    private static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Integer menu = 99;

        while (menu != 0) {
            System.out.println("Select the option:\n1 - Client\n2 - Adm\n0 - exit");
            menu = readMenuOption();
            switch (menu) {
                case 1:
                    clientMenu();
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

    public static void clientMenu() {
        // Validar cliente existente

        // Permitir listagem dos proprios livros

        // Permitir listagem de todos os livros passando filtros disponiveis por genero
        // do livro

        // Permitir devolver livro
        Integer clientMenu = 99;
        while (clientMenu != 0) {

            System.out.println(
                    "Select the option:\n1 - List my books\n2 - List available books\n3 - Check Out a book\n0 - Return to main menu");
            clientMenu = readMenuOption();
            switch (clientMenu) {
                case 1:
                    System.out.println("Listar livros do cliente");
                    break;
                case 2:
                    System.out.println("Listar todos os livros do disponiveis e apresentar filtros(Genero e Autor)");
                    break;
                case 3:
                    System.out.println("Associar o cliente a um livro e bloquear o livro para outros clientes");
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
                    "Select the option:\n1 - Add a book\n2 - Add a Client\n3 - List check outs\n0 - Return to main menu");
            admMenu = readMenuOption();
            switch (admMenu) {
                case 1:
                    System.out.println("Adicionar Livro");
                    break;
                case 2:
                    System.out.println("Adicionar Cliente");
                    break;
                case 3:
                    System.out.println("Listar Emprestimos por livro ou por cliente");
                    break;
                case 0:
                    System.out.println("The program will return to main menu.");
                    break;
                default:
                    System.out.println("Please, select a valid option.");

            }
        }
    }

    private static Integer readMenuOption() {
        try {
            Integer menu = read.nextInt();
            return menu;
        } catch (Exception e) {
            System.out.println("The selected option is invalid, please dont do that.");
            return 0;
        }
    }
}
