package spaceX.model;

import lombok.Getter;
import lombok.Setter;
import spaceX.status.RocketStatus;

@Getter
public class Rocket {

    private final String name;

    @Setter
    private RocketStatus status;

    private Mission mission;

    public Rocket(String name) {
        this.name = name;
        status = RocketStatus.ON_GROUND;
        mission = null;
    }

    public void assignToMission(Mission mission) {
        this.mission = mission;
        setStatus(RocketStatus.IN_SPACE);
    }

    public void unassignFromMission() {
        mission = null;
        setStatus(RocketStatus.ON_GROUND);
    }

    public void moveToRepair() {
        setStatus(RocketStatus.IN_REPAIR);
    }

    public void moveFromRepair() {
        if (mission == null) {
            setStatus(RocketStatus.ON_GROUND);
        } else {
            setStatus(RocketStatus.IN_SPACE);
        }
    }

    public boolean isAssigned() {
        return status != RocketStatus.ON_GROUND;
    }

    public boolean inRepair() {
        return status == RocketStatus.IN_REPAIR;
    }

    @Override
    public String toString() {
        return name + " - " + status;
    }
}
