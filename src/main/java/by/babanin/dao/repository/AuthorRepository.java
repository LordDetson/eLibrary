package by.babanin.dao.repository;

import by.babanin.dao.Database;
import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.dao.Repository;
import by.babanin.model.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorRepository implements Repository<Author, Long> {
    private static final Logger log = Logger.getLogger(AuthorRepository.class.getSimpleName());

    private final EntityBuilderFromResultSet<Author, Long> authorBuilder;

    public AuthorRepository(EntityBuilderFromResultSet<Author, Long> authorBuilder) {
        this.authorBuilder = authorBuilder;
    }

    @Override
    public List<Author> getAllList() {
        List<Author> authors = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from `library`.`author`")
        ) {
            authors = authorBuilder.buildListEntities(resultSet);
            authors.sort(Comparator.comparing(Author::getFio));
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return authors;
    }

    @Override
    public Author getById(Long id) {
        Author author = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from `library`.`author` where id = " + id)
        ) {
            resultSet.next();
            author = authorBuilder.buildEntity(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return author;
    }

    public List<Author> findAuthorsByFio(String pattern) {
        List<Author> authors = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from `library`.`author` where fio like '%" + pattern + "%'")
        ) {
            authors = authorBuilder.buildListEntities(resultSet);
            authors.sort(Comparator.comparing(Author::getFio));
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return authors;
    }
}
