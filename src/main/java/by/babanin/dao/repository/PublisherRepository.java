package by.babanin.dao.repository;

import by.babanin.dao.Database;
import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.dao.Repository;
import by.babanin.model.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PublisherRepository implements Repository<Publisher, Long> {
    private static final Logger log = Logger.getLogger(AuthorRepository.class.getSimpleName());
    private final EntityBuilderFromResultSet<Publisher, Long> publisherBuilder;

    public PublisherRepository(EntityBuilderFromResultSet<Publisher, Long> publisherBuilder) {
        this.publisherBuilder = publisherBuilder;
    }

    @Override
    public List<Publisher> getAllList() {
        List<Publisher> genres = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from `library`.`publisher`")
        ) {
            genres = publisherBuilder.buildListEntities(resultSet);
            genres.sort(Comparator.comparing(Publisher::getName));
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return genres;
    }

    @Override
    public Publisher getById(Long id) {
        Publisher genre = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from `library`.`publisher` where id = " + id)
        ) {
            resultSet.next();
            genre = publisherBuilder.buildEntity(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return genre;
    }
}
