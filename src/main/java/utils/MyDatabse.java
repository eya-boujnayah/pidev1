package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabse {
<<<<<<< HEAD
   final String URL = "jdbc:mysql://localhost:3306/padelRadar";
=======
   final String URL = "jdbc:mysql://localhost:3307/padelRadar";
>>>>>>> 0d7d319c8522df9b743469e316fcc962f832a3a6

  final   String USERNAME = "root";

   final String PWD = "";

   Connection con ;

   public static MyDatabse instance ;
    private MyDatabse (){
       try {
           con = DriverManager.getConnection(URL,USERNAME,PWD);

           System.out.println("connnnnected !!!");
       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }

   }

public   static MyDatabse getInstance(){

        if(instance==null)
            instance = new MyDatabse() ;

        return  instance ;
   }

    public Connection getCon() {
        return con;
    }
}
