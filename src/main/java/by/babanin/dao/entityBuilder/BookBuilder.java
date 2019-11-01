package by.babanin.dao.entityBuilder;

import by.babanin.dao.EntityBuilderFromResultSet;
import by.babanin.dao.Repository;
import by.babanin.model.Author;
import by.babanin.model.Book;
import by.babanin.model.Genre;
import by.babanin.model.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBuilder implements EntityBuilderFromResultSet<Book, Long> {
    private final Repository<Genre, Long> genreRepository;
    private final Repository<Author, Long> authorRepository;
    private final Repository<Publisher, Long> publisherRepository;

    public BookBuilder(
            Repository<Genre, Long> genreRepository,
            Repository<Author, Long> authorRepository,
            Repository<Publisher, Long> publisherRepository
    ) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Book buildEntity(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setName(resultSet.getString("name"));
        book.setPageCount(resultSet.getInt("page_count"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setGenre(genreRepository.getById(resultSet.getLong("genre_id")).getName());
        book.setAuthor(authorRepository.getById(resultSet.getLong("author_id")).getFio());
        book.setPublishDate(resultSet.getInt("publish_year"));
        book.setPublisher(publisherRepository.getById(resultSet.getLong("publisher_id")).getName());
        book.setImage(resultSet.getBytes("image"));
        book.setDescription(resultSet.getString("desc"));
        return book;
    }

    @Override
    public List<Book> buildListEntities(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) books.add(buildEntity(resultSet));
        return books;
    }
}
