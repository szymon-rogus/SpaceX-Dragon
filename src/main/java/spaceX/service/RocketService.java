package spaceX.service;

import lombok.Getter;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

import java.util.HashMap;
import java.util.List;
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

    public void assignToMission(Mission mission, Rocket rocket) {
        rocket.assignToMission(mission);
    }

    public void unassignFromMission(Rocket rocket) {
        rocket.unassignFromMission();
    }

    public void unassignFromMission(List<Rocket> rocket) {
        rocket.forEach(this::unassignFromMission);
    }

    public void moveToRepair(Rocket rocket) {
        rocket.moveToRepair();
    }

    public void moveFromRepair(Rocket rocket) {
        rocket.moveFromRepair();
    }
}
