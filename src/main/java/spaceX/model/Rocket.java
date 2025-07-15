package spaceX.model;

import lombok.Getter;

@Getter
public class Rocket {

    private final String name;

    private Status status;

    private Mission mission;

    public Rocket(String name) {
        this.name = name;
        status = Status.ON_GROUND;
        mission = null;
    }

    public void assignToMission(Mission mission) {
        this.mission = mission;
        status = Status.IN_SPACE;
    }

    public void unassignFromMission() {
        mission = null;
        status = Status.ON_GROUND;
    }

    public void moveToRepair() {
        status = Status.IN_REPAIR;
    }

    public void moveFromRepair() {
        if (mission == null) {
            status = Status.ON_GROUND;
        } else {
            status = Status.IN_SPACE;
        }
    }

    public boolean onGround() {
        return status == Status.ON_GROUND;
    }

    public boolean inSpace() {
        return status == Status.IN_SPACE;
    }

    public boolean inRepair() {
        return status == Status.IN_REPAIR;
    }

    @Override
    public String toString() {
        return name + " - " + status;
    }

    private enum Status {
        ON_GROUND,
        IN_SPACE,
        IN_REPAIR
    }
}
