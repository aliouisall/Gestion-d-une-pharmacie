/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testlogin;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ndeye Marie
 */
public class Connecter {
    Connection con;
    public Connecter(){
        try{
           Class.forName("com.mysql.jdbc.Driver") ;
        }
        catch(ClassNotFoundException e){
            System.err.println(e);
        }
        
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Pharmacie","root","");
        } catch(SQLException e) {
            System.err.println(e);
        }
        
    }
    Connection obtenirconnexion() { return con; }
}
