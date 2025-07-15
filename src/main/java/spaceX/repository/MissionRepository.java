package spaceX.repository;

import lombok.Getter;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MissionRepository implements SpaceRepository<Mission> {

    private final Map<String, Mission> missions;

    public MissionRepository() {
        missions = new HashMap<>();
    }

    @Override
    public void add(String missionName) {
        if (missions.containsKey(missionName)) {
            throw new SpaceException("ERROR: Mission already exists!");
        }
        missions.put(missionName, new Mission(missionName));
    }

    @Override
    public Mission get(String name) {
        if (!missions.containsKey(name)) {
            throw new SpaceException("ERROR: Mission with this name does not exists!");
        }
        return missions.get(name);
    }
}
