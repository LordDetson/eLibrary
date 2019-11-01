package by.babanin.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class Author implements Serializable, Identifiable<Long> {
    private long id;
    private String fio;
    private String birthday;

    public Author() {
    }

    public Author(String fio, String birthday) {
        this.fio = fio;
        this.birthday = birthday;
    }

    public Author(long id, String fio, String birthday) {
        this.id = id;
        this.fio = fio;
        this.birthday = birthday;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Author.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("fio='" + fio + "'")
                .add("birthday=" + birthday)
                .toString();
    }
}
