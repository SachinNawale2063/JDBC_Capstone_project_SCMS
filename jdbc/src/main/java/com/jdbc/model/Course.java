package com.jdbc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.db.ConnectionProvider;

public class Course {
   
   
    public void showCourses(){
        try {
            Connection con = ConnectionProvider.getConnection();
            Statement statement = con.createStatement();
            String query = "SELECT * FROM courses";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String description = result.getString("description");
                System.out.println("id:" + id +  "  title:" + title + "  description:" + description);
            }

            con.close();
            statement.close();
            result.close();            
        } catch (SQLException e) {
            e.getMessage();
        }

    }
}
