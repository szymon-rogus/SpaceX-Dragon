package spaceX.repository;

public interface SpaceRepository<T> {

    void add(String name);

    T get(String name);

    int size();
}
