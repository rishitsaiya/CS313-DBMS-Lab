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
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class IssueServlet
 */
@WebServlet("/IssueServlet")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public IssueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
	
//Fetching Input values from jsp page
		int studentId = Integer.parseInt(request.getParameter("s_id"));
		int bookId = Integer.parseInt(request.getParameter("b_id"));
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
		java.sql.Date issueDate = new java.sql.Date(date.getTime()) ;

		Connection conn = null;
 		String url = "jdbc:postgresql://localhost:5432/library"; //PostgreSQL URL and followed by the database name
 		String username = "postgres"; //PostgreSQL username
 		String password = "123456"; //PostgreSQL password
		
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
 		System.out.println("Printing connection object: " + conn);

// Prepared Statement to check if the student exsits in the database
		PreparedStatement checkStudent = conn.prepareStatement("SELECT count(*) FROM student WHERE student_id = ?"); //Query to check if the student exists
 		checkStudent.setInt(1, studentId);
		ResultSet resultSet = checkStudent.executeQuery();
		resultSet.next();
		
// Prepared Statement to add issue data
		if (resultSet.getInt("count") != 0) {
			PreparedStatement addIssue = conn.prepareStatement("INSERT INTO issue VALUES(?, ?, ?)"); //Query to insert values
			addIssue.setInt(1, studentId);
			addIssue.setInt(2, bookId);
			addIssue.setDate(3, issueDate);
			int result = addIssue.executeUpdate();
				
//If insert query is successful, then load Result.jsp page 
			if(result>0) {				
				RequestDispatcher rd = request.getRequestDispatcher("IssueResult.jsp");
				rd.forward(request, response);
			}
		}
		else {
			System.out.println("Student does not exist.");
		}

	}
		 catch (Exception e) {
 			e.printStackTrace();
 		}
	
	}

}