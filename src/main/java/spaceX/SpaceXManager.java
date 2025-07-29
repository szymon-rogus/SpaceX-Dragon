package spaceX;

import lombok.Getter;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;
import spaceX.repository.MissionRepository;
import spaceX.repository.RocketRepository;
import spaceX.repository.SpaceRepository;
import spaceX.service.AssignService;
import spaceX.service.RepairService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class SpaceXManager {

    private final String name;

    private final SpaceRepository<Mission> missionRepository;

    private final SpaceRepository<Rocket> rocketRepository;

    private final AssignService assignService;

    private final RepairService repairService;

    public SpaceXManager(String name) {
        this.name = name;
        missionRepository = new MissionRepository();
        rocketRepository = new RocketRepository();
        assignService = new AssignService();
        repairService = new RepairService();
    }

    public void addRocket(String name) {
        try {
            rocketRepository.add(name);
        } catch (SpaceException e) {
            e.logMessage();
        }
    }

    public void addRockets(String... names) {
        for (String name : names) {
            addRocket(name);
        }
    }

    public void addMission(String name) {
        try {
            missionRepository.add(name);
        } catch (SpaceException e) {
            e.logMessage();
        }
    }

    public void addMissions(String... names) {
        for (String name : names) {
            addMission(name);
        }
    }

    public void assignRocketToMission(String missionName, String rocketName) {
        try {
            Mission mission = missionRepository.get(missionName);
            Rocket rocket = rocketRepository.get(rocketName);

            assignService.assignRocketToMission(mission, rocket);
        } catch (SpaceException e) {
            e.logMessage();
        }
    }

    public void assignRocketsToMission(String missionName, String... rocketNames) {
        for (String rocketName : rocketNames) {
            assignRocketToMission(missionName, rocketName);
        }
    }

    public void unassignRocketFromMission(String missionName, String rocketName) {
        try {
            Mission mission = missionRepository.get(missionName);
            Rocket rocket = rocketRepository.get(rocketName);

            assignService.unassignRocketFromMission(mission, rocket);
        } catch (SpaceException e) {
            e.logMessage();
        }
    }

    public void moveToRepair(String rocketName) {
        try {
            Rocket rocket = rocketRepository.get(rocketName);

            repairService.moveRocketToRepair(rocket);
        } catch (SpaceException e) {
            e.logMessage();
        }
    }

    public void moveFromRepair(String rocketName) {
        try {
            Rocket rocket = rocketRepository.get(rocketName);

            repairService.moveRocketFromRepair(rocket);
        } catch (SpaceException e) {
            e.logMessage();
        }
    }

    public void endMission(String missionName) {
        try {
            Mission mission = missionRepository.get(missionName);

            assignService.endMission(mission);
        } catch (SpaceException e) {
            e.logMessage();
        }
    }

    public List<Mission> getSummary() {
        List<Mission> missions = new ArrayList<>(missionRepository.getAll());

        missions.sort(Comparator.comparing(Mission::getRocketCount, Comparator.reverseOrder())
                .thenComparing(Mission::getName, Comparator.reverseOrder())
        );

        return missions;
    }

    @Override
    public String toString() {
        List<Mission> missions = getSummary();

        StringBuilder builder = new StringBuilder();
        for (Mission mission : missions) {
            builder.append(mission).append("\n");
        }

        return builder.toString();
    }
}
