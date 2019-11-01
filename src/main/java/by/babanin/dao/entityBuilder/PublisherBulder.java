package by.babanin.dao.entityBuilder;

import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.model.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherBulder implements EntityBuilderFromResultSet<Publisher, Long> {
    @Override
    public Publisher buildEntity(ResultSet resultSet) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setId(resultSet.getLong("id"));
        publisher.setName(resultSet.getString("name"));
        return publisher;
    }

    @Override
    public List<Publisher> buildListEntities(ResultSet resultSet) throws SQLException {
        List<Publisher> publishers = new ArrayList<>();
        while (resultSet.next()) publishers.add(buildEntity(resultSet));
        return publishers;
    }
}
