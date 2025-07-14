package spaceX.model;

import lombok.Getter;
import lombok.Setter;
import spaceX.status.MissionStatus;
import spaceX.status.RocketStatus;

@Getter
@Setter
public class Rocket {

    private String name;

    private RocketStatus status;

    private Mission currentMission;

    public Rocket(String name) {
        this.name = name;
        status = RocketStatus.ON_GROUND;
        currentMission = null;
    }

    public boolean isAssigned() {
        return status != RocketStatus.ON_GROUND;
    }

}
