package by.babanin.service;

import by.babanin.dao.entityBuilder.AuthorBuilder;
import by.babanin.dao.repository.AuthorRepository;
import by.babanin.model.Author;
import by.babanin.service.tool.ConverterCollectionToMap;

import java.util.List;
import java.util.Map;

public class AuthorService {
    private static final AuthorRepository authorRepository = new AuthorRepository(new AuthorBuilder());

    public static AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    public static List<Author> getAuthorList() {
        return authorRepository.getAllList();
    }

    public static Map<Long, Author> getAuthorMap() {
        return ConverterCollectionToMap.convertToHashMap(getAuthorList());
    }

    public static List<Author> getAuthorListByFio(String pattern) {
        return authorRepository.findAuthorsByFio(pattern);
    }

    public static Map<Long, Author> getAuthorMapByFio(String pattern) {
        return ConverterCollectionToMap.convertToHashMap(getAuthorListByFio(pattern));
    }
}
