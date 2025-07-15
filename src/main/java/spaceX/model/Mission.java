package spaceX.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Mission {

    private final String name;

    private Status status;

    private final List<Rocket> rockets;

    public Mission(String name) {
        this.name = name;
        status = Status.SCHEDULED;
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
        if (status == Status.ENDED) return;

        if (rockets.isEmpty()) {
            status = Status.SCHEDULED;
        } else if (hasRocketsInRepair()) {
            status = Status.PENDING;
        } else {
            status = Status.IN_PROGRESS;
        }
    }

    public void endMission() {
        rockets.clear();
        status = Status.ENDED;
    }

    private boolean hasRocketsInRepair() {
        return rockets.stream().anyMatch(Rocket::inRepair);
    }

    public int getRocketCount() {
        return rockets.size();
    }

    public boolean isScheduled() {
        return status == Status.SCHEDULED;
    }

    public boolean inProgress() {
        return status == Status.IN_PROGRESS;
    }

    public boolean isPending() {
        return status == Status.PENDING;
    }

    public boolean hasEnded() {
        return status == Status.ENDED;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name + " - " + status + " - Dragons: " + getRocketCount());
        for (Rocket rocket : rockets) {
            builder.append("\n\t").append(rocket);
        }

        return builder.toString();
    }

    private enum Status {
        SCHEDULED,
        PENDING,
        IN_PROGRESS,
        ENDED
    }
}
