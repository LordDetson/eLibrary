package by.babanin.dao.repository;

import by.babanin.dao.Database;
import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.dao.Repository;
import by.babanin.model.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreRepository implements Repository<Genre, Long> {
    private static final Logger log = Logger.getLogger(AuthorRepository.class.getSimpleName());
    private final EntityBuilderFromResultSet<Genre, Long> genreBuilder;

    public GenreRepository(EntityBuilderFromResultSet<Genre, Long> genreBuilder) {
        this.genreBuilder = genreBuilder;
    }

    @Override
    public List<Genre> getAllList() {
        List<Genre> genres = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from `library`.`genre`")
        ) {
            genres = genreBuilder.buildListEntities(resultSet);
            genres.sort(Comparator.comparing(Genre::getName));
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return genres;
    }

    @Override
    public Genre getById(Long id) {
        Genre genre = null;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from `library`.`genre` where id = " + id)
        ) {
            resultSet.next();
            genre = genreBuilder.buildEntity(resultSet);
        } catch (SQLException e) {
            log.log(Level.SEVERE, null, e);
        }
        return genre;
    }
}
