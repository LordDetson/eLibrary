package by.babanin.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

public class Database {
    private static InitialContext initialContext;
    private static Connection connection;
    private static DataSource dataSource;

    static {
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("jdbc/Library");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
