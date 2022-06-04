package com.revature.foundations.utils;


        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.Objects;


public class ConnectionUtil {
    private static ConnectionUtil instance;
    private ConnectionUtil() { super(); }
    public static ConnectionUtil getInstance() {
        if(instance == null) {
            instance = new ConnectionUtil();
        }
        return instance;
    }
    public static Connection getConnection() throws SQLException {
        try {
            String dbInfo = "jdbc:postgresql://localhost/Sigg-db?user=postgres&password=MyPassword";
            //String dbinfo = System.getenv("DB_CONNECTION");
            return DriverManager.getConnection(dbInfo);
        }  catch (SQLException e) {
            e.printStackTrace();
        }  catch (Throwable t) {
        }
        return null;
    }


    /*
    private static Connection connection = null;
    static {
        //System.out.println("Making a new connection!");
        try {
            //String dbInfo = "jdbc:postgresql://localhost/local-db?user=postgres&password=g5rEW^";
            //String dbinfo = System.getenv("DB_CONNECTION");
            connection = DriverManager.getConnection(System.getenv("DB_CONNECTION"));
        }  catch (SQLException e) {
            Complain(e);
            e.printStackTrace();
        }  catch (Throwable t) {
            Complain(t);
        }
    }
    public static Connection getConnection() {return connection;}
     */

}