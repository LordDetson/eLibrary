package by.babanin.service.tool;

import by.babanin.model.Identifiable;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ConverterCollectionToMap {
    public static <T extends Identifiable<I>, I> HashMap<I, T> convertToHashMap(Collection<T> list) {
        return list.stream().collect(Collectors.toMap(T::getId, t -> t, (oldT, newT) -> newT, HashMap::new));
    }
}
