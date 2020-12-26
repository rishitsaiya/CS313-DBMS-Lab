import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeReport {
	private static int StudentsPassed;
	
	public static int StudentsInGradeReport(Connection connection) throws IOException, SQLException {

// Reading from csv file
		
		String csvFilePath = "grdreport.csv";
		BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
		
        String lineText = null;
        
// Initial Assignment		
		
		StudentsPassed = 0;
        int TotalStudentsInGradeReport = 0;
        
// Storing Data local		
		
        while ((lineText = lineReader.readLine()) != null) {
        	String[] data = lineText.split(",");
        	String id = data[0];
        	String grade = data[5];
        	String course_id = data[1];
        	
        	TotalStudentsInGradeReport = TotalStudentsInGradeReport + 1;
        	updateDatabase(connection, id, course_id, grade);
        }
        
        lineReader.close();
		
		return TotalStudentsInGradeReport;
	}

// Function to update Database
	
	public static void updateDatabase(Connection connection, String id, String course_id, String grade) throws SQLException {
		PreparedStatement checkCourse = connection.prepareStatement("SELECT COUNT(*) FROM course WHERE course_id = ?");
		PreparedStatement checkStudent = connection.prepareStatement("SELECT COUNT(*) FROM takes WHERE id = ? AND course_id = ?");
		
		checkCourse.setString(1, course_id);
    	ResultSet countCourse = checkCourse.executeQuery();
    	countCourse.next();

// Condition to execute query if count > 0
    	
    	if (countCourse.getInt("count") > 0) {
    		checkStudent.setString(1, id);
			checkStudent.setString(2, course_id);
    		ResultSet countStudent = checkStudent.executeQuery();
    		countStudent.next();
    		
    		if (countStudent.getInt("count") > 0) {
    			StudentsPassed = Student.studentsPassed(connection, course_id, grade, id, StudentsPassed);
    		}
    		else {
    			System.out.println("No student exists with id: " + id);
    		}
    	}
    	
    	else {
    		System.out.println("No course exists with the Course ID: " + course_id);
    	}
	}
	
// Returns number of students Passed	
	
	public static int NoOfStudentsPassed() {
		return StudentsPassed;
	}
}