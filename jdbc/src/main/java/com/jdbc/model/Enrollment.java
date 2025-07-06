package com.jdbc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.db.ConnectionProvider;

public class Enrollment {
    
    public void showEnrollments(){

        try {
            Connection con = ConnectionProvider.getConnection();
            Statement statement = con.createStatement();
            String query = "SELECT e.id, e.student_id, e.course_id,\r\n" + //
                                "       e.enrollment AS enrollment_date,\r\n" + //
                                "       s.name AS student_name, s.email AS student_email,\r\n" + //
                                "       c.title AS course_title, c.description AS course_description\r\n" + //
                                "FROM enrollments AS e\r\n" + //
                                "JOIN students AS s ON e.student_id = s.id\r\n" + //
                                "JOIN courses AS c ON e.course_id = c.id;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                int student_id = result.getInt("student_id");
                int course_id = result.getInt("course_id");
                String enrollment_date = result.getString("enrollment_date");
                String student_name = result.getString("student_name");
                String student_email = result.getString("student_email");
                String course_title = result.getString("course_title");
                String course_description = result.getString("course_description");
                System.out.println("id:" + id +  "  student_id:" + student_id + "  course_id:" + course_id +  "  enrollment_date:" + enrollment_date + "  student_name:" + student_name +  "  student_email:" + student_email + "  course_title:" + course_title + "  course_description:"+course_description);
            }

            con.close();
            statement.close();
            result.close();            
        } catch (SQLException e) {
            e.getMessage();
        }

    }

}
