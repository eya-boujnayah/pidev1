package tn.esprit.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Maconnexion {



        private final String URL = "jdbc:mysql://localhost:3306/padel";
        private final String USERNAME = "root";
        private final String PWD = "";

        private Connection con;
        private static Maconnexion instance;

        public Maconnexion() {
            try {
                con = DriverManager.getConnection(URL, USERNAME, PWD);
                System.out.println("Connected to the database!");
            } catch (SQLException e) {
                System.err.println("Failed to connect to the database: " + e.getMessage());
            }
        }
        //singleton//
    public static Maconnexion getInstance() {
        if (instance == null) {
            instance = new Maconnexion();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }
}



