package spaceX.model;

import lombok.Getter;
import lombok.Setter;
import spaceX.exception.SpaceException;
import spaceX.status.MissionStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Mission {

    private String name;

    private MissionStatus status;

    private List<Rocket> assignedRockets;

    public Mission(String name) {
        this.name = name;
        status = MissionStatus.SCHEDULED;
        assignedRockets = new ArrayList<>();
    }

    public void assignRocket(Rocket rocket) {
        if (rocket.isAssigned()) {
            throw new SpaceException("ERROR: Rocket is already assigned to different mission!");
        }
        assignedRockets.add(rocket);
        /// More logic?
    }

    public void assignRockets(List<Rocket> rockets) {
        rockets.forEach(this::assignRocket);
    }

    public void unassignRocket(Rocket rocket) {
        if (!assignedRockets.contains(rocket)) {
            throw new SpaceException("WARNING: Trying to unassign rocket which has not been assigned to this mission");
        }
        /// More logic?
    }

    public void unassignRocket(List<Rocket> rockets) {
        rockets.forEach(this::unassignRocket);
    }

    private void updateStatus() {
        /// TODO: multiple edge cases, method called on every assigned rocket change
        /// Implement observer?
    }
}
