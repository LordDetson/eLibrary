package by.babanin.dao.repository;

import by.babanin.dao.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookContentRepository {
    public static byte[] findContentBookById(Long id) {
        byte[] content = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select content from `library`.`book` where id = " + id)
        ) {
            if (resultSet.next()) content = resultSet.getBytes("content");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return content;
    }
}
