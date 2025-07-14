package spaceX.model;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.AssignmentException;
import spaceX.status.MissionStatus;
import spaceX.status.RocketStatus;

public class MissionTest {

    @Test
    public void testInitialStatus() {
        Mission mission = new Mission("Pluto");

        Assert.assertEquals(MissionStatus.SCHEDULED, mission.getStatus());
    }

    @Test
    public void testCorrectAssignment() {
        Mission mission = new Mission("Apollo");
        Rocket rocket = new Rocket("Io");

        mission.assignRocket(rocket);

        Assert.assertEquals(1, mission.getAssignedRockets().size());
    }

    @Test
    public void testFirstIncorrectAssignment() {
        Mission mission = new Mission("Apollo");
        Rocket rocket = new Rocket("Sputnik");

        rocket.setStatus(RocketStatus.IN_SPACE);

        Assert.assertThrows(AssignmentException.class, () -> {
            mission.assignRocket(rocket);
        });
    }

    @Test
    public void testSecondIncorrectAssignment() {
        Mission mission = new Mission("Apollo");
        Rocket rocket = new Rocket("Sputnik");

        rocket.setStatus(RocketStatus.IN_REPAIR);

        Assert.assertThrows(AssignmentException.class, () -> {
            mission.assignRocket(rocket);
        });
    }

    @Test
    public void testCorrectUnassignment() {
        Mission mission = new Mission("Transit");
        Rocket rocket = new Rocket("Passenger");

        mission.assignRocket(rocket);
        mission.unassignRocket(rocket);

        Assert.assertTrue(mission.getAssignedRockets().isEmpty());
    }

    @Test
    public void testIncorrectUnassignment() {
        Mission mission = new Mission("Transit");
        Rocket rocket1 = new Rocket("Passenger");
        Rocket rocket2 = new Rocket("Nostradamus");

        mission.assignRocket(rocket1);

        Assert.assertThrows(AssignmentException.class, () -> {
            mission.unassignRocket(rocket2);
        });
    }
}
