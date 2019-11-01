package by.babanin.dao.entityBuilder;

import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreBuilder implements EntityBuilderFromResultSet<Genre, Long> {
    @Override
    public Genre buildEntity(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getLong("id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }

    @Override
    public List<Genre> buildListEntities(ResultSet resultSet) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        while (resultSet.next()) genres.add(buildEntity(resultSet));
        return genres;
    }
}
