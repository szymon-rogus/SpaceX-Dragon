package spaceX.model;

import org.junit.Assert;
import org.junit.Test;
import spaceX.status.MissionStatus;

public class MissionTest {

    @Test
    public void testInitialStatus() {
        Mission mission = new Mission("Pluto");

        Assert.assertEquals(MissionStatus.SCHEDULED, mission.getStatus());
    }

    @Test
    public void testAssign() {
        Mission mission = new Mission("Apollo");
        Rocket rocket = new Rocket("Io");

        mission.assignRocket(rocket);

        Assert.assertEquals(1, mission.getRocketCount());
    }

    @Test
    public void testMultipleAssign() {
        Mission mission = new Mission("Apollo");
        Rocket sputnik = new Rocket("Sputnik");
        Rocket nike = new Rocket("Nike");
        Rocket hopiDart = new Rocket("Hopi Dart");

        mission.assignRocket(sputnik);
        mission.assignRocket(nike);
        mission.assignRocket(hopiDart);

        Assert.assertEquals(3, mission.getRocketCount());
    }

    @Test
    public void testUnassign() {
        Mission mission = new Mission("Transit");
        Rocket passenger = new Rocket("Passenger");

        mission.assignRocket(passenger);

        mission.unassignRocket(passenger);

        Assert.assertTrue(mission.getRockets().isEmpty());
    }

    @Test
    public void testMultipleUnassign() {
        Mission mission = new Mission("Transit");
        Rocket passenger = new Rocket("Passenger");
        Rocket delta = new Rocket("Delta");
        Rocket falcon = new Rocket("falcon");

        mission.assignRocket(passenger);
        mission.assignRocket(delta);
        mission.assignRocket(falcon);

        mission.unassignRocket(passenger);
        mission.unassignRocket(delta);
        mission.unassignRocket(falcon);

        Assert.assertTrue(mission.getRockets().isEmpty());
    }
}
