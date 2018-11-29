
import java.time.LocalDate;
import java.util.Scanner;

public class BookCreator {
    private static Scanner sc = new Scanner(System.in);

    public static Book create() {

        System.out.println("Podaj wszystkie dane, potrzebne do dodania książki:");
        sc.nextLine();
        System.out.println("Tytuł:");
        String title = sc.nextLine();
        System.out.println("Autor:");
        String author = sc.nextLine();
        System.out.println("Rok wydania:");
        int year = sc.nextInt();
        if(year> LocalDate.now().getYear()&&!(year<=LocalDate.now().getYear())) throw new WrongYearException();
        sc.nextLine();
        System.out.println("ISBN:");
        String isbn = sc.nextLine();
        if (isbn.length()>6||isbn.length()<6) throw new WrongIsbnException();
        return new Book(title,author,year,isbn);
    }
}
