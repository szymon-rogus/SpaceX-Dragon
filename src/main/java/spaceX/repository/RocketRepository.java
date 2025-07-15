package spaceX.repository;

import lombok.Getter;
import spaceX.exception.SpaceException;
import spaceX.model.Rocket;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RocketRepository implements SpaceRepository<Rocket> {

    private final Map<String, Rocket> rockets;

    public RocketRepository() {
        rockets = new HashMap<>();
    }

    @Override
    public void add(String name) throws SpaceException {
        if (rockets.containsKey(name)) {
            throw new SpaceException("ERROR: Rocket " + name + " already exists!");
        }
        rockets.put(name, new Rocket(name));
    }

    @Override
    public Rocket get(String name) throws SpaceException {
        if (!rockets.containsKey(name)) {
            throw new SpaceException("ERROR: Rocket " + name + " does not exists!");
        }
        return rockets.get(name);
    }
}
