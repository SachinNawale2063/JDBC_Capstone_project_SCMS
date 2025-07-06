package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.db.ConnectionProvider;
import com.jdbc.model.Course;

public class CourseDao extends Course {

    public void addCourse(Scanner sc) {

        try {

            
            Connection con = ConnectionProvider.getConnection();
            String query = "INSERT INTO courses (title, description) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            System.out.print("Enter the title: ");
            String title = sc.nextLine(); 

            System.out.print("Enter the description: ");
            String description = sc.nextLine(); 

            ps.setString(1, title);
            ps.setString(2, description);

            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected > 0 ? "New Course Added Successfully!!" : "Course not added");

            System.out.println("\nUpdated Course table");
            super.showCourses();

            ps.close();
            con.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void deleteCourse(Scanner sc){
       try {
            System.out.println("Course table before deletion : ");
            super.showCourses();
            Connection con = ConnectionProvider.getConnection();
            String query = "DELETE FROM courses WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            System.out.print("Enter the id of course you wanna delete : ");
            int id = sc.nextInt();
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Course " + id +" Deleted Successfully!!" : "Course not Deleted \n provide correct id");
            System.out.println("\nUpdated courses table after deletion of id " + id);
            super.showCourses();

            con.close();
            ps.close();
            
        } catch (SQLException e) {
            e.getMessage();
        } 
    }

    public void deleteCourses(Scanner sc){

        try {

            System.out.println("Courses before deletion : ");
            super.showCourses();

            Connection con = ConnectionProvider.getConnection();
            String query = "DELETE FROM courses WHERE id = ?";
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

            System.out.println("Courses after deletion : ");
            super.showCourses();
            
        } catch (SQLException e) {
            e.getMessage();
        }

    }
}
