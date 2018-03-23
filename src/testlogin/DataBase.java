/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testlogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ibnibrahim
 */
public class DataBase {
    
    public static void main(String[] args) {
        
        Connection myconObj = null;
        Statement mystatObj = null;
        ResultSet myresObj = null;
        
        try {
            myconObj = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pharmacie", "root", "");
            mystatObj = myconObj.createStatement();
            myresObj = mystatObj.executeQuery("SELECT * FROM Fournisseur");
            while (myresObj.next()) {
                String email = myresObj.getString("email");
                int numTel = myresObj.getInt("num_tel");
                String nom = myresObj.getString("nom");
                String address = myresObj.getString("adresse");
                System.out.println(email + " - " + numTel + " - " + nom  + " - " + address);
            }
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
