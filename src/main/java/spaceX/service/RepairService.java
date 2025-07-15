package spaceX.service;

import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class RepairService {

    public static void moveRocketToRepair(Rocket rocket) throws SpaceException {
        Mission mission = rocket.getMission();

        if (rocket.onGround()) {
            throw new SpaceException("ERROR: Unassigned rockets cannot be moved to repair!");
        }
        if (rocket.inRepair()) {
            throw new SpaceException("ERROR: Rocket is already in repair!");
        }

        rocket.moveToRepair();
        if (mission != null) {
            mission.updateStatus();
        }
    }

    public static void moveRocketFromRepair(Rocket rocket) throws SpaceException {
        Mission mission = rocket.getMission();

        if (!rocket.inRepair()) {
            throw new SpaceException("ERROR: Rocket was not in repair");
        }

        rocket.moveFromRepair();
        if (mission != null) {
            mission.updateStatus();
        }
    }
}
