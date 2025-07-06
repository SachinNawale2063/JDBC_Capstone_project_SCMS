package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.db.ConnectionProvider;
import com.jdbc.model.Course;
import com.jdbc.model.Enrollment;
import com.jdbc.model.Student;

public class EnrollmentDao extends Enrollment {
    
    public void addEnrollment(Scanner sc){

        try {

            Connection con = ConnectionProvider.getConnection();
            String query = "INSERT INTO enrollments (student_id, course_id, enrollment) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println("Available students : ");
            Student st = new Student();
            st.showStudents();
            System.out.println("Available Courses : ");
            Course cs = new Course();
            cs.showCourses();
            System.out.print("Enter the Student id : ");
            int student_id = sc.nextInt();
            System.out.print("Enter the course id : ");
            int course_id = sc.nextInt();
            System.out.print("Enter the date of enrollment (yyyy-mm-dd) : ");
            String date = sc.next();
            ps.setInt(1, student_id);
            ps.setInt(2, course_id);
            ps.setString(3, date);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected > 0 ? "New Enrollment Added Successfully!!" : "Enrollment not added");
            System.out.println("\nUpdated Enrollment table");
            super.showEnrollments();

            con.close();
            ps.close();
            
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void deleteEnrollment(Scanner sc){
       try {
            System.out.println("Enrollment table before deletion : ");
            super.showEnrollments();
            Connection con = ConnectionProvider.getConnection();
            String query = "DELETE FROM enrollments WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            System.out.print("Enter the id of enrollment you wanna delete : ");
            int id = sc.nextInt();
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Enrollement " + id +" Deleted Successfully!!" : "Enrollment not Deleted \n provide correct id");
            System.out.println("\nUpdated Enrollement table after deletion of id " + id);
            super.showEnrollments();

            con.close();
            ps.close();
            
        } catch (SQLException e) {
            e.getMessage();
        } 
    }

    public void deleteEnrollments(Scanner sc){

        try {

            System.out.println("Enrollments before deletion : ");
            super.showEnrollments();

            Connection con = ConnectionProvider.getConnection();
            String query = "DELETE FROM enrollments WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);


            while (true) {
                System.out.print("Enter the id you want to delete : ");
                int id = sc.nextInt();
                ps.setInt(1, id);
                ps.addBatch();
                System.out.println("You want to delete more (Y/N)? ");
                String choice = sc.next();
                if(choice.toUpperCase().equals("N")) break;
            }

            ps.executeBatch();

            System.out.println("Enrollments after deletion : ");
            super.showEnrollments();
            
        } catch (SQLException e) {
            e.getMessage();
        }

    }

}
