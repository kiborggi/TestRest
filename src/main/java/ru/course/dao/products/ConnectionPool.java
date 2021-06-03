package ru.course.dao.products;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {

    private static final String DATASOURCE_NAME="jdbc/ru";
    private static DataSource dataSource;
    private static Connection connection;



   /* static{

        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
        }
        catch (NamingException e) {
            System.out.println("Connection failed in Pool");
            e.printStackTrace();
        }

    }*/

    private ConnectionPool(){

    }

    public static Connection getConnection() throws SQLException {

//       Connection connection=dataSource.getConnection();
        connection = DriverManager.getConnection("jdbc:mysql://localhost/security?serverTimezone=Europe/Moscow&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false", "vladuser", "3208455258Ss");

        return connection;
    }


}
