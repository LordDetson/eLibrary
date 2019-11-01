package by.babanin.service;

import by.babanin.dao.entityBuilder.GenreBuilder;
import by.babanin.dao.repository.GenreRepository;
import by.babanin.model.Genre;
import by.babanin.service.tool.ConverterCollectionToMap;

import java.util.List;
import java.util.Map;

public class GenreService {
    public static final long DEFAULT_GENRE_ID = 0L;
    private static final GenreRepository genreRepository = new GenreRepository(new GenreBuilder());
    public static GenreRepository getGenreRepository() {
        return genreRepository;
    }

    public static List<Genre> getGenreList() {
        return genreRepository.getAllList();
    }

    public static Map<Long, Genre> getGenreMap() {
        return ConverterCollectionToMap.convertToHashMap(getGenreList());
    }

    public static Genre getGenreById(Long id) {
        return genreRepository.getById(id);
    }
}
