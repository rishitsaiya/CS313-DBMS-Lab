import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
	
// Fetching input values from jsp page
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String category = request.getParameter("cat");
		String author = request.getParameter("author");

		Connection conn = null;
 		String url = "jdbc:postgresql://localhost:5432/library"; //PostgreSQL URL and followed by the database name
 		String username = "postgres"; //PostgreSQL username
 		String password = "123456"; //PostgreSQL password
		
// Connection Establishment
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
 		System.out.println("Printing connection object: " + conn);

// Prepared Statement to verify if the book already exists.
		PreparedStatement checkBook = conn.prepareStatement("SELECT count(*) FROM book WHERE book_id = ?"); // Query to check if the book exists
 		checkBook.setInt(1, id);
		ResultSet resultSet = checkBook.executeQuery();
		resultSet.next();
 		
// Prepared Statement to add data of the book
		if (resultSet.getInt("count") == 0) {
			PreparedStatement addBook = conn.prepareStatement("INSERT INTO book VALUES(?, ?, ?, ?)"); //Insert values of new book
	 		addBook.setInt(1, id);
			addBook.setString(2, title);
			addBook.setString(3, category);
			addBook.setString(4, author);
			int result = addBook.executeUpdate();

// If INSERT QUERY is successful, then load Result.jsp page
			if(result>0){
				RequestDispatcher rd = request.getRequestDispatcher("AddResult.jsp");
				rd.forward(request, response);
			}
		
		}
		
		else {
			System.out.println("Book Already Exists.");
		}

	}
		 catch (Exception e) {
 			e.printStackTrace();
 		}
	}
}