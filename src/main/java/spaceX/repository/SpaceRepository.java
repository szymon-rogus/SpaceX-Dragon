package spaceX.repository;

import java.util.Collection;
import java.util.List;

public interface SpaceRepository<T> {

    void add(String name);

    T get(String name);

    Collection<T> getAll();

    int size();
}
