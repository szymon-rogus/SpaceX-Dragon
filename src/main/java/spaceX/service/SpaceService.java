package spaceX.service;

import java.util.Map;

public interface SpaceService<T> {

    void add(String name);

    T get(String name);
}
