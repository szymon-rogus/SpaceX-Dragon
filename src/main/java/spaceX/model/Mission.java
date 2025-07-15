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
        updateStatus();
    }

    public void unassignRocket(Rocket rocket) {
        rockets.remove(rocket);
        updateStatus();
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

    public void endMission() {
        rockets.clear();
        setStatus(MissionStatus.ENDED);
    }

    private boolean hasRocketsInRepair() {
        return rockets.stream().anyMatch(Rocket::inRepair);
    }

    public int getRocketCount() {
        return rockets.size();
    }

    public boolean isScheduled() {
        return status == MissionStatus.SCHEDULED;
    }

    public boolean inProgress() {
        return status == MissionStatus.IN_PROGRESS;
    }

    public boolean isPending() {
        return status == MissionStatus.PENDING;
    }

    public boolean hasEnded() {
        return status == MissionStatus.ENDED;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name + " - " + status + " - Dragons: " + getRocketCount());
        for (Rocket rocket : rockets) {
            builder.append("\n\t").append(rocket);
        }

        return builder.toString();
    }
}
