package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.db.ConnectionProvider;
import com.jdbc.model.Student;

public class StudentDao extends Student {

    public void addStudent(Scanner sc){

        try {

            Connection con = ConnectionProvider.getConnection();
            String query = "INSERT INTO students (name, email) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            System.out.print("Enter the name : ");
            String name = sc.next();
            System.out.print("Enter the email : ");
            String email = sc.next();
            ps.setString(1, name);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected > 0 ? "New Student Added Successfully!!" : "Student not added");
            System.out.println("\nUpdated students table");
            super.showStudents();

            con.close();
            ps.close();
            
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void deleteStudent(Scanner sc){
       try {
            System.out.println("Student table before deletion : ");
            super.showStudents();
            Connection con = ConnectionProvider.getConnection();
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            System.out.print("Enter the id of student you wanna delete : ");
            int id = sc.nextInt();
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Student " + id +" Deleted Successfully!!" : "Student not Deleted \n provide correct id");
            System.out.println("\nUpdated students table after deletion of id " + id);
            super.showStudents();

            con.close();
            ps.close();
            
        } catch (SQLException e) {
            e.getMessage();
        } 
    }
    
    public void deleteStudents(Scanner sc){

        try {

            System.out.println("Students before deletion : ");
            super.showStudents();

            Connection con = ConnectionProvider.getConnection();
            String query = "DELETE FROM students WHERE id = ?";
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

            System.out.println("Students after deletion : ");
            super.showStudents();
            
        } catch (SQLException e) {
            e.getMessage();
        }

    }


}
