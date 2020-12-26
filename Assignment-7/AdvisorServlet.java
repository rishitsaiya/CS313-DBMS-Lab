
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdvisorServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		
		String advisor_id = request.getParameter("id");//getting student id as input from index.html page
		
		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement  stmt = null;
		String dept_name=null;
		try {
			out.println("<!DOCTYPE html>");//print in the form of HTML code
			out.println("<html>");
			out.println("<head><title>Advisor Qurey Servlet</title></head>");
			out.println("<body>");
			Class.forName("org.postgresql.Driver"); //loading postgres driver
			String query="SELECT DISTINCT instructor.dept_name, advisor.i_id FROM instructor, advisor WHERE instructor.id = advisor.i_id AND advisor.i_id = ?"; //query to get the advisor dept with id 
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/university", "postgres", "123456");//postgres connection with username and password
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, advisor_id);
			ResultSet rset = ps.executeQuery();
			int count=0;
			while(rset.next()) {
				dept_name=rset.getString("dept_name");//getting student name and storing in a variable
				++count;
			}
			out.println("Advisor ID is " + advisor_id + ". Department name is "+ dept_name);//printing student id and name
			out.println("<p>==== " + count + " rows found =====</p>");
			out.println("</body></html>");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();  // closing connection and statement variables
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}

