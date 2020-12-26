import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Public Class Student

public class Student {
	
// Function for TotalCreditsOfStudent	
	
	public static int TotalCreditsOfStudent(Connection connection, String id) throws SQLException {
		PreparedStatement TotalStudentCredits = connection.prepareStatement("SELECT tot_cred FROM student WHERE id = ?");
		
		TotalStudentCredits.setString(1, id);
		ResultSet totalCreditsRow = TotalStudentCredits.executeQuery();
		totalCreditsRow.next();
		int totalCredits = totalCreditsRow.getInt("tot_cred");
		
		return totalCredits;
	}
	
// Function for Credits of a course	
	
	public static int CourseCredits(Connection connection, String course_id) throws SQLException {
		PreparedStatement getCredits = connection.prepareStatement("SELECT credits FROM course WHERE course_id = ?");

		getCredits.setString(1, course_id);
		ResultSet noOfCredits = getCredits.executeQuery();
		noOfCredits.next();
		int courseCredits = Integer.parseInt(noOfCredits.getString("credits"));
		
		return courseCredits;
	}
	
// Function to give number of students	
	
	public static int GetNoOfStudentsPassed() {
		int passedStudents = 7;
		return passedStudents;
	}
	
	@SuppressWarnings("unused")
	public static int studentsPassed(Connection connection, String course_id, String grade, String id, int StudentsPassed) throws SQLException {
		PreparedStatement updateTotalStudentCredits = connection.prepareStatement("UPDATE student SET tot_cred = ? WHERE id = ?");
		PreparedStatement updateGrade = connection.prepareStatement("UPDATE takes SET grade = ? WHERE id = ? AND course_id = ?");
		
		updateGrade.setString(1, grade);
		updateGrade.setString(2, id);
		updateGrade.setString(3, course_id);
		
		int countUpdated = updateGrade.executeUpdate();
		
// Conditions if Grade isn't F			
		
		if (!grade.equals("F")) {
			
			StudentsPassed = StudentsPassed + 1;
			int courseCredits = CourseCredits(connection, course_id);
			int totalCredits = TotalCreditsOfStudent(connection, id);
			
			int creditsToUpdate = totalCredits + courseCredits;            							
			updateTotalStudentCredits.setInt(1, creditsToUpdate);
			updateTotalStudentCredits.setString(2, id);
			int countCreditsUpdated = updateTotalStudentCredits.executeUpdate();
		}
		return StudentsPassed;
	}
}