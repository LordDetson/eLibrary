package by.babanin.dao;

import by.babanin.model.Identifiable;

import java.util.List;

public interface Repository<T extends Identifiable<I>, I> {
    List<T> getAllList();

    T getById(I id);
}
