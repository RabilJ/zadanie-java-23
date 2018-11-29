import java.sql.*;
import java.util.InputMismatchException;

public class BookDAO {
    private static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "admin";
private Connection connection;

public BookDAO(){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url,username,password);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
public void add(Book book){
    final String insert = "INSERT INTO books (title,author,year,isbn) VALUES (?,?,?,?);";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1,book.getTitle());
        preparedStatement.setString(2,book.getAuthor());
        preparedStatement.setInt(3,book.getYear());
        preparedStatement.setString(4,book.getIsbn());
        preparedStatement.executeUpdate();
        System.out.println("Książka dodana poprawnie.");
    } catch (SQLException e) {
        System.out.println("Nie udało się dodać książki do bazy danych.");
    }
}
public Book search(long id){
    final String find = "SELECT*FROM books WHERE id=?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setYear(resultSet.getInt("year"));
            book.setIsbn(resultSet.getString("isbn"));
            return book;
        }
    } catch (SQLException e) {
        System.out.println("Pod tym ID nie istnieje żadna książka");
    }catch (InputMismatchException exx){
        System.out.println("Błędne dane, tylko wartości numeryczne");
    }
    return null;
}
public void delete(long id){
    final String remove = "DELETE FROM books WHERE id=?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(remove);
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Książka została pomyślnie usunięta z bazy danych");
    }catch (InputMismatchException exx){
        System.out.println("Błędne dane, tylko wartości numeryczne");
    }
}
}
