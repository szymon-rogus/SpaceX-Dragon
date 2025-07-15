package spaceX.model;

import lombok.Getter;
import spaceX.status.RocketStatus;

@Getter
public class Rocket {

    private final String name;

    private RocketStatus status;

    private Mission mission;

    public Rocket(String name) {
        this.name = name;
        status = RocketStatus.ON_GROUND;
        mission = null;
    }

    public void assignToMission(Mission mission) {
        this.mission = mission;
        status = RocketStatus.IN_SPACE;
    }

    public void unassignFromMission() {
        mission = null;
        status = RocketStatus.ON_GROUND;
    }

    public void moveToRepair() {
        status = RocketStatus.IN_REPAIR;
    }

    public void moveFromRepair() {
        if (mission == null) {
            status = RocketStatus.ON_GROUND;
        } else {
            status = RocketStatus.IN_SPACE;
        }
    }

    public boolean onGround() {
        return status == RocketStatus.ON_GROUND;
    }

    public boolean inSpace() {
        return status == RocketStatus.IN_SPACE;
    }

    public boolean inRepair() {
        return status == RocketStatus.IN_REPAIR;
    }

    @Override
    public String toString() {
        return name + " - " + status;
    }
}
