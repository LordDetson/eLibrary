package by.babanin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityBuilderFromResultSet<T, I extends Number> {
    T buildEntity(ResultSet resultSet) throws SQLException;

    List<T> buildListEntities(ResultSet resultSet) throws SQLException;
}
