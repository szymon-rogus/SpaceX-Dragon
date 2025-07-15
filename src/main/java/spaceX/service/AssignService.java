package spaceX.service;

import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

import java.util.List;

public class AssignService {

    public static void assignRocketToMission(Mission mission, Rocket rocket) throws SpaceException {
        if (mission.hasEnded()) {
            throw new SpaceException("ERROR: Mission has ended!");
        }
        if (!rocket.onGround()) {
            throw new SpaceException("ERROR: Rocket is already assigned to a mission!");
        }

        rocket.assignToMission(mission);
        mission.assignRocket(rocket);
    }

    public static void unassignRocketFromMission(Mission mission, Rocket rocket) throws SpaceException {
        if (mission.hasEnded()) {
            throw new SpaceException("ERROR: Mission has ended!");
        }
        if (rocket.onGround()) {
            throw new SpaceException("WARNING: Rocket is unassigned, therefore it cannot be unassigned!");
        }
        if (rocket.inRepair()) {
            throw new SpaceException("ERROR: Rocket has to be repaired before unassignment!");
        }

        rocket.unassignFromMission();
        mission.unassignRocket(rocket);
    }

    public static void endMission(Mission mission) {
        List<Rocket> rockets = mission.getRockets();

        rockets.forEach(Rocket::unassignFromMission);
        mission.endMission();
    }
}
