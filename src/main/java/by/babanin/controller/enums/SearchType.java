package by.babanin.controller.enums;

public enum SearchType {
    AUTHOR("form.search.type.author"), TITLE("form.search.type.title");
    private String key;

    SearchType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
