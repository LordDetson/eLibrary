package by.babanin.service;

import by.babanin.dao.entityBuilder.BookBuilder;
import by.babanin.dao.repository.BookRepository;
import by.babanin.model.Author;
import by.babanin.model.Book;
import by.babanin.service.tool.ConverterCollectionToMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {
    private static final BookRepository bookRepository = new BookRepository(
            new BookBuilder(
                    GenreService.getGenreRepository(),
                    AuthorService.getAuthorRepository(),
                    PublisherService.getPublisherRepository()
            )
    );

    public static BookRepository getBookRepository() {
        return bookRepository;
    }

    public static List<Book> getBookList() {
        return bookRepository.getAllList();
    }

    public static Map<Long, Book> getBookMap() {
        return ConverterCollectionToMap.convertToHashMap(getBookList());
    }

    public static List<Book> getBookListByGenreId(Long id) {
        if (id == GenreService.DEFAULT_GENRE_ID) return getBookList();
        return bookRepository.findBooksByGenreId(id);
    }

    public static Map<Long, Book> getBookMapByGenreId(Long id) {
        return ConverterCollectionToMap.convertToHashMap(getBookListByGenreId(id));
    }

    public static List<Book> getBookListByTitle(String pattern) {
        return bookRepository.findBooksByTitle(pattern);
    }

    public static Map<Long, Book> getBookMapByTitle(String patter) {
        return ConverterCollectionToMap.convertToHashMap(getBookListByTitle(patter));
    }

    public static List<Book> getBookListByFirstSymbol(String symbol) {
        return bookRepository.findBooksByFirstSymbol(symbol);
    }

    public static Map<Long, Book> getBookMapByFirstSymbol(String symbol) {
        return ConverterCollectionToMap.convertToHashMap(getBookListByFirstSymbol(symbol));
    }

    public static List<Book> getBookListByAuthors(Collection<Author> authors) {
        List<Book> books = new ArrayList<>();
        authors.forEach(author -> books.addAll(bookRepository.findBooksByAuthor(author)));
        return books;
    }

    public static Map<Long, Book> getBookMapByAuthors(Collection<Author> authors) {
        return ConverterCollectionToMap.convertToHashMap(getBookListByAuthors(authors));
    }
}
