package by.babanin.controller;

import by.babanin.controller.enums.SearchType;
import by.babanin.dao.repository.BookContentRepository;
import by.babanin.model.Book;
import by.babanin.nls.MainContent;
import by.babanin.service.AuthorService;
import by.babanin.service.BookService;
import by.babanin.service.GenreService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean
@SessionScoped
public class SearchController implements Serializable {
    @ManagedProperty("#{genreController}")
    private GenreController genreController;

    private SearchType currentSearchType;
    private String pattern;
    private String currentSearch;
    private Map<Long, Book> currentBooks;
    private Map<Long, Book> currentBooksOnPage;
    private int booksOnPage = 3;
    private List<Integer> pageNumbers;
    private int selectedPageNumber;

    private static Map<String, SearchType> searchTypeMap = new HashMap<>();
    static {
        Arrays.stream(SearchType.values()).forEach(searchType ->
                searchTypeMap.put(MainContent.getInstance().getString(searchType.getKey()), searchType));
    }

    public SearchController() {
        currentSearch = GenreService.getGenreById(GenreService.DEFAULT_GENRE_ID).getName();
        fillCurrentBooks(BookService.getBookMapByGenreId(GenreService.DEFAULT_GENRE_ID));
    }

    public void fillBooksByGenre() {
        Map<String, String> requestParameterMap = getRequestParameterMap();
        Long genreId = Long.valueOf(requestParameterMap.get("genre_id"));
        currentSearch = genreController.getAllGenreMap().get(genreId).getName();
        fillCurrentBooks(BookService.getBookMapByGenreId(genreId));
    }

    public void fillBooksByFirstSymbol() {
        Map<String, String> requestParameterMap = getRequestParameterMap();
        String searchSymbol = requestParameterMap.get("search_symbol");
        currentSearch = searchSymbol;
        fillCurrentBooks(BookService.getBookMapByFirstSymbol(searchSymbol));
    }

    public void fillBooksByPattern() {
        currentSearch = pattern;
        switch (currentSearchType) {
            case TITLE:
                fillCurrentBooks(BookService.getBookMapByTitle(pattern));
                break;
            case AUTHOR:
                fillCurrentBooks(BookService.getBookMapByAuthors(AuthorService.getAuthorListByFio(pattern)));
                break;
            default:
                fillCurrentBooks(BookService.getBookMap());
        }
    }

    public void selectPage() {
        Map<String, String> requestParameterMap = getRequestParameterMap();
        selectedPageNumber = Integer.parseInt(requestParameterMap.get("page_number"));
        fillBooksOnPage();
    }

    public byte[] getBookImage(Long id) {
        return currentBooks.get(id).getImage();
    }

    public byte[] getBookContent(Long id) {
        Book book = currentBooks.get(id);
        if (book.getContent() == null) {
            book.setContent(BookContentRepository.findContentBookById(id));
        }
        return book.getContent();
    }

    public int getTotalBooksCount() {
        return currentBooks.size();
    }

    public GenreController getGenreController() {
        return genreController;
    }

    public void setGenreController(GenreController genreController) {
        this.genreController = genreController;
    }

    public static Map<String, SearchType> getSearchTypeMap() {
        return searchTypeMap;
    }

    public static void setSearchTypeMap(Map<String, SearchType> searchTypeMap) {
        SearchController.searchTypeMap = searchTypeMap;
    }

    public SearchType getCurrentSearchType() {
        return currentSearchType;
    }

    public void setCurrentSearchType(SearchType currentSearchType) {
        this.currentSearchType = currentSearchType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getCurrentSearch() {
        return currentSearch;
    }

    public void setCurrentSearch(String currentSearch) {
        this.currentSearch = currentSearch;
    }

    public Map<Long, Book> getCurrentBooks() {
        return currentBooks;
    }

    public void setCurrentBooks(Map<Long, Book> currentBooks) {
        this.currentBooks = currentBooks;
    }

    public Map<Long, Book> getCurrentBooksOnPage() {
        return currentBooksOnPage;
    }

    public void setCurrentBooksOnPage(Map<Long, Book> currentBooksOnPage) {
        this.currentBooksOnPage = currentBooksOnPage;
    }

    public int getBooksOnPage() {
        return booksOnPage;
    }

    public void setBooksOnPage(int booksOnPage) {
        this.booksOnPage = booksOnPage;
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(List<Integer> pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public int getSelectedPageNumber() {
        return selectedPageNumber;
    }

    public void setSelectedPageNumber(int selectedPageNumber) {
        this.selectedPageNumber = selectedPageNumber;
    }

    private static void fillSearchTypeMap() {

    }

    private void fillCurrentBooks(Map<Long, Book> bookMap) {
        selectedPageNumber = 1;
        currentBooks = bookMap;
        fillPageNumbers();
        fillBooksOnPage();
    }

    private void fillPageNumbers() {
        int pageCount = getTotalBooksCount() > 0 ? getTotalBooksCount() / getBooksOnPage() : 0;
        AtomicInteger i = new AtomicInteger(1);
        pageNumbers = Stream.generate(i::getAndIncrement).limit(pageCount).collect(Collectors.toList());
    }

    private void fillBooksOnPage() {
        currentBooksOnPage = currentBooks.entrySet().stream()
                .skip((selectedPageNumber - 1) * booksOnPage)
                .limit(booksOnPage)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<String, String> getRequestParameterMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }
}
