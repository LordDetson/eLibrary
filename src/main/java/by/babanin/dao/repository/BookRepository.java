package by.babanin.dao.repository;

import by.babanin.dao.Database;
import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.dao.Repository;
import by.babanin.model.Author;
import by.babanin.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookRepository implements Repository<Book, Long> {
    private static final Logger log = Logger.getLogger(AuthorRepository.class.getSimpleName());
    private final EntityBuilderFromResultSet<Book, Long> bookBuilder;

    public BookRepository(EntityBuilderFromResultSet<Book, Long> bookBuilder) {
        this.bookBuilder = bookBuilder;
    }

    @Override
    public List<Book> getAllList() {
        List<Book> books = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id, name, page_count, isbn, genre_id, author_id, publish_year, publisher_id, image, `desc` from `library`.`book` order by id")
        ) {
            books = bookBuilder.buildListEntities(resultSet);
            Collections.reverse(books);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return books;
    }

    @Override
    public Book getById(Long id) {
        Book book = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id, name, page_count, isbn, genre_id, author_id, publish_year, publisher_id, image, `desc` from `library`.`book` where id = " + id)
        ) {
            resultSet.next();
            book = bookBuilder.buildEntity(resultSet);
        }catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return book;
    }

    public List<Book> findBooksByGenreId(Long id) {
        List<Book> books = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id, name, page_count, isbn, genre_id, author_id, publish_year, publisher_id, image, `desc` from `library`.`book` where genre_id = " + id)
        ) {
            books = bookBuilder.buildListEntities(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return books;
    }

    public List<Book> findBooksByTitle(String pattern) {
        List<Book> books = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id, name, page_count, isbn, genre_id, author_id, publish_year, publisher_id, image, `desc` from `library`.`book` where name like '%" + pattern + "%'")
        ) {
            books = bookBuilder.buildListEntities(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return books;
    }

    public List<Book> findBooksByFirstSymbol(String pattern) {
        List<Book> books = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id, name, page_count, isbn, genre_id, author_id, publish_year, publisher_id, image, `desc` from `library`.`book` where name like '" + pattern + "%'")
        ) {
            books = bookBuilder.buildListEntities(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return books;
    }

    public List<Book> findBooksByAuthor(Author author) {
        List<Book> books = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select id, name, page_count, isbn, genre_id, author_id, publish_year, publisher_id, image, `desc`from `library`.`book` where author_id = " + author.getId())
        ) {
            books = bookBuilder.buildListEntities(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return books;
    }
}
