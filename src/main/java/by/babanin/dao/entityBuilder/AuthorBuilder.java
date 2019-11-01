package by.babanin.dao.entityBuilder;

import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorBuilder implements EntityBuilderFromResultSet<Author, Long> {
    @Override
    public Author buildEntity(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFio(resultSet.getString("fio"));
        author.setBirthday(resultSet.getString("birthday"));
        return author;
    }

    @Override
    public List<Author> buildListEntities(ResultSet resultSet) throws SQLException {
        List<Author> authors = new ArrayList<>();
        while (resultSet.next()) authors.add(buildEntity(resultSet));
        return authors;
    }
}
