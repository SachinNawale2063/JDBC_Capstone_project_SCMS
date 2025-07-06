package com.jdbc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.db.ConnectionProvider;

public class Student {

    // public void getPreparedStatement(){

    // }
    
    public void showStudents(){

        try {
            Connection con = ConnectionProvider.getConnection();
            Statement statement = con.createStatement();
            String query = "SELECT * FROM students";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                System.out.println("id:" + id +  "  name:" + name + "  email:" + email);
            }

            con.close();
            statement.close();
            result.close();            
        } catch (SQLException e) {
            e.getMessage();
        }

    }

}
