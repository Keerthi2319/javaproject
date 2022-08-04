package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.collections.ObservableList;

import java.sql.*;
public class StockInsert {
    public void stockListView(){
        Connection conn = null;
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student_information?"+
               "user=root&password=password" );
               System.out.println("Connnection succeded");

               Statement statement;
               statement = conn.createStatement();
               ResultSet resultSet;
               resultSet = statement.executeQuery(
                   "select * from student");
                String Student_id, Student_name , Phno , Class;
               
               System.out.println(resultSet);
               while (resultSet.next()) {
                   Student_id = resultSet.getString("Student_id");
                  Student_name = resultSet.getString("Student_name").trim();
                   Phno = resultSet.getString("Phno");
                  Class = resultSet.getString("Class").trim();
                   System.out.println("Student id : " + Student_id
                                      + " Student name : " +Student_name);
               }
               resultSet.close();
               statement.close();
               conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    // //create//
    public  void create() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student_information?", "root", "password");
           Statement stmt = conn.createStatement();
        ) {		      
            String sql = "CREATE TABLE if not exists student " +
                     "(Student_id INTEGER not NULL, " +
                     " Student_name VARCHAR(255), " + 
                     " Phno VARCHAR(255), " + 
                     " Class INTEGER, " + 
                     " PRIMARY KEY ( id ))"; 
  
           stmt.executeUpdate(sql);
           System.out.println("Created table in given database...");   	  
        } catch (SQLException e) {
           e.printStackTrace();
        } 
     }

     //insert
    public String insertstudent(String productname, String productprice , String stockavailable , String retailer){
        Connection conn = null;
        String result="null";
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student_information?"+
               "user=root&password=password" );
               System.out.println("Connnection succeded");
            System.out.println("Entered try ");
            Statement statement;
            statement = conn.createStatement();
            
            statement.executeUpdate(
                "Insert into student(`Student_id`, `Student_name`, `Phno`,`Class`)  values ("+productname+",'"+productprice+"','"+stockavailable+"','"+retailer+"')");
                System.out.println("Entered execute done ");
            statement.close();
            conn.close();
            result="Success";
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return result;
    }

    // //update
    public  void update() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student_information?", "root", "password");
           Statement stmt = conn.createStatement();
        ) {		      
           String sql = "UPDATE student " +
              "SET Student_id = 30 WHERE Student_name='dafd'";
           stmt.executeUpdate(sql);
           stmt.close();
           conn.close();
          
        } catch (SQLException e) {
           e.printStackTrace();
        } 
     }
    public static void main(String args[]){
        StockInsert stock = new StockInsert();
        stock.create();// creation
        // stock.insertstudent("1", "10", "99", "me"); // insertion
        stock.update();// update
        stock.stockListView();
        
        //  insertStockUpdate("Sample", "productprice", "stockavailable", "retailer");
    }
    public String insertStockUpdate(String text, String text2, String text3, String text4) {
        return null;
    }
}