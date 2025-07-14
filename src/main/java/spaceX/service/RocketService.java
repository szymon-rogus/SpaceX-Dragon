package spaceX.service;

import lombok.Getter;
import spaceX.exception.SpaceException;
import spaceX.model.Rocket;
import spaceX.status.RocketStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RocketService implements SpaceService<Rocket> {

    private final Map<String, Rocket> rockets;

    public RocketService() {
        rockets = new HashMap<>();
    }

    @Override
    public void add(String rocketName) {
        if (rockets.containsKey(rocketName)) {
            throw new SpaceException("ERROR: Rocket already exists!");
        }
        rockets.put(rocketName, new Rocket(rocketName));
    }

    @Override
    public Rocket get(String name) {
        if (!rockets.containsKey(name)) {
            throw new SpaceException("ERROR: Rocket with this name does not exists!");
        }
        return rockets.get(name);
    }

    public void changeRocketStatus(String rocketName, RocketStatus status) {
        /// Few edge cases
    }
}
