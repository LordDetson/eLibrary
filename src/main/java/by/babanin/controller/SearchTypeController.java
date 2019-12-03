package by.babanin.controller;

import by.babanin.controller.enums.SearchType;
import by.babanin.nls.MainContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@RequestScoped
public class SearchTypeController {
    private Map<String, SearchType> searchTypeMap = new HashMap<>();
    public SearchTypeController() {
        Arrays.stream(SearchType.values()).forEach(searchType ->
                searchTypeMap.put(new MainContent().getString(searchType.getKey()), searchType));
    }

    public Map<String, SearchType> getSearchTypeMap() {
        return searchTypeMap;
    }

    public void setSearchTypeMap(Map<String, SearchType> searchTypeMap) {
        this.searchTypeMap = searchTypeMap;
    }
}
