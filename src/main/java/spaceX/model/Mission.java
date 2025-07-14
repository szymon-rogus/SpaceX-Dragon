package spaceX.model;

import lombok.Getter;
import lombok.Setter;
import spaceX.status.MissionStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Mission {

    private final String name;

    @Setter
    private MissionStatus status;

    private final List<Rocket> rockets;

    public Mission(String name) {
        this.name = name;
        status = MissionStatus.SCHEDULED;
        rockets = new ArrayList<>();
    }

    public void assignRocket(Rocket rocket) {
        rockets.add(rocket);
    }

    public void unassignRocket(Rocket rocket) {
        rockets.remove(rocket);
    }

    public void updateStatus() {
        if (status == MissionStatus.ENDED) return;

        if (rockets.isEmpty()) {
            status = MissionStatus.SCHEDULED;
        } else if (hasRocketsInRepair()) {
            status = MissionStatus.PENDING;
        } else {
            status = MissionStatus.IN_PROGRESS;
        }
    }

    private boolean hasRocketsInRepair() {
        return rockets.stream().anyMatch(Rocket::inRepair);
    }

    public int getRocketCount() {
        return rockets.size();
    }

    @Override
    public String toString() {
        return name + " - " + status + " - Dragons: " + getRocketCount();
    }
}
