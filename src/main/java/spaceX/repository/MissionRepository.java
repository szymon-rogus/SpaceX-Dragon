package spaceX.repository;

import lombok.Getter;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
public class MissionRepository implements SpaceRepository<Mission> {

    private final Map<String, Mission> missions;

    public MissionRepository() {
        missions = new HashMap<>();
    }

    @Override
    public void add(String name) throws SpaceException {
        if (missions.containsKey(name)) {
            throw new SpaceException("ERROR: Mission " + name + " already exists!");
        }
        missions.put(name, new Mission(name));
    }

    @Override
    public Mission get(String name) throws SpaceException {
        if (!missions.containsKey(name)) {
            throw new SpaceException("ERROR: Mission " + name + " does not exists!");
        }
        return missions.get(name);
    }

    @Override
    public Collection<Mission> getAll() {
        return missions.values();
    }

    @Override
    public int size() {
        return missions.size();
    }
}
