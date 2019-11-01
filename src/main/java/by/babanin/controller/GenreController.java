package by.babanin.controller;

import by.babanin.model.Genre;
import by.babanin.service.GenreService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(eager = true)
@ApplicationScoped
public class GenreController implements Serializable {
    private Map<Long, Genre> allGenres;

    public GenreController() {
        allGenres = GenreService.getGenreMap();
    }

    public Map<Long, Genre> getAllGenreMap() {
        return allGenres;
    }
}
