import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

// Connection Establish 
		
		try (Connection connection =
		DriverManager.getConnection("jdbc:postgresql://localhost:5432/university", "postgres",
		"123456")) {

// Total Students with GradeReport Fetch
			
			int TotalStudentsInGradeReport = GradeReport.StudentsInGradeReport(connection);
			int StudentsPassed = GradeReport.NoOfStudentsPassed();

// Ouput Format  
  
            int StudentsFailed = TotalStudentsInGradeReport - StudentsPassed;
            System.out.println("The number of students in Grade Report: " + TotalStudentsInGradeReport);
            System.out.println("The number of students passed: " + StudentsPassed);
            System.out.println("The number of students with FF grade: " + StudentsFailed);
   
// Connection Close
   
            connection.close();
			
		} catch (SQLException e) {
			System.out.println("Connection failed to Establish.");
			e.printStackTrace();
		}
	}
}