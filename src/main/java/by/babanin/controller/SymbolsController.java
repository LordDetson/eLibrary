package by.babanin.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(eager = true)
@ApplicationScoped
public class SymbolsController {
    private final  List<Character> chars_ru = new ArrayList<>();
    private final  List<Character> chars_en = new ArrayList<>();

    public SymbolsController() {
        fillCharacters();
    }

    private void fillCharacters() {
        for (int i = 1040; i < 1072; i++) {
            chars_ru.add((char) i);
        }
        for (int i = 65; i < 91; i++) {
            chars_en.add((char) i);
        }
    }

    public List<Character> getCharsRu() {
        return chars_ru;
    }

    public List<Character> getCharsEn() {
        return chars_en;
    }
}
