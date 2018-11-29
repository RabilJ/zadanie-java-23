import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public void Loop() {
        BookDAO bookDAO = new BookDAO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Witaj programie zarządzającym bazą danych 'Library'. Dostępne opcje:");
        int option = 0;

        while (option != 4) {
            boolean check = true;
            printOptions();
            int id;
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    while (check) {
                        try {
                            Book book = BookCreator.create();
                            bookDAO.add(book);
                            check = false;
                        } catch (InputMismatchException ex) {
                            System.out.println("Podałeś błędne dane spróbuj znowu");
                        } catch (WrongIsbnException e) {
                            System.out.println("Błędny ISBN, spróbuj znowu");
                        } catch (WrongYearException exx) {
                            System.out.println("Książki mogą mieć co najwyżej obecny rok");
                        }
                    }
                    break;
                case 2:
                    while (check) {
                        try {
                            System.out.println("Wpisz ID szukanej książki");
                            id = sc.nextInt();
                            System.out.println(bookDAO.search(id));
                            check = false;
                        } catch (InputMismatchException exx) {
                            System.out.println("Błędne dane, tylko wartości numeryczne");
                            sc.next();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Wpisz ID książki, którą chcesz usunąć z bazy danych:");
                    id = sc.nextInt();
                    bookDAO.delete(id);
                    System.out.println("Udało się poprawnie usunąć książkę z bazy danych");
                    break;
                case 4:
                    System.out.println("Dziękujemy za skorzystanie z programu");
                    break;
            }
        }
    }

    private static void printOptions() {
        for (Choice value : Choice.values()) {
            System.out.println(value.getNum() + " - " + value.getDesc());
        }
    }
}
