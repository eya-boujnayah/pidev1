package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;
import static javax.xml.registry.LifeCycleManager.USER;

public class MyDatabse {
    static final String URL = "jdbc:mysql://localhost:3306/padelRadar?autoReconnect=true&useSSL=false";

    static final   String USERNAME = "root";

    static final String PWD = "";

   static Connection con ;

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

    public static Connection getCon() throws SQLException {
        if (con == null || con.isClosed()) {
            System.out.println("🔄 Ouverture d'une nouvelle connexion MySQL...");
            con = DriverManager.getConnection(URL, USERNAME, PWD);
        }
        return con;
    }
}
