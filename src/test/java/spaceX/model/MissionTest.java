package spaceX.model;

import org.junit.Assert;
import org.junit.Test;

public class MissionTest {

    @Test
    public void testInitialStatus() {
        Mission mission = new Mission("Pluto");

        Assert.assertTrue(mission.isScheduled());
    }

    @Test
    public void testAssign() {
        Mission mission = new Mission("Apollo");
        Rocket rocket = new Rocket("Io");

        mission.assignRocket(rocket);

        Assert.assertTrue(mission.inProgress());
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

        Assert.assertTrue(mission.inProgress());
        Assert.assertEquals(3, mission.getRocketCount());
    }

    @Test
    public void testUnassign() {
        Mission mission = new Mission("Transit");
        Rocket passenger = new Rocket("Passenger");

        mission.assignRocket(passenger);

        mission.unassignRocket(passenger);

        Assert.assertFalse(mission.inProgress());
        Assert.assertTrue(mission.getRockets().isEmpty());
    }

    @Test
    public void testMultipleUnassign() {
        Mission mission = new Mission("Transit");
        Rocket passenger = new Rocket("Passenger");
        Rocket delta = new Rocket("Delta");
        Rocket falcon = new Rocket("Falcon");

        mission.assignRocket(passenger);
        mission.assignRocket(delta);
        mission.assignRocket(falcon);

        mission.unassignRocket(passenger);
        mission.unassignRocket(delta);
        mission.unassignRocket(falcon);

        Assert.assertFalse(mission.inProgress());
        Assert.assertTrue(mission.getRockets().isEmpty());
    }

    @Test
    public void testUpdateStatus() {
        Mission mission = new Mission("Artemis");
        Rocket atlas = new Rocket("Atlas");
        Rocket delta = new Rocket("Delta C");

        Assert.assertTrue(mission.isScheduled());

        mission.assignRocket(atlas);
        mission.assignRocket(delta);
        mission.updateStatus();

        Assert.assertTrue(mission.inProgress());

        atlas.moveToRepair();
        mission.updateStatus();

        Assert.assertTrue(mission.isPending());

        atlas.moveFromRepair();
        mission.updateStatus();

        Assert.assertTrue(mission.inProgress());

        mission.unassignRocket(atlas);
        mission.unassignRocket(delta);

        Assert.assertTrue(mission.isScheduled());
    }

    @Test
    public void testEndMission() {
        Mission mission = new Mission("Minotaur");
        Rocket titan = new Rocket("Titan V");
        Rocket cronos = new Rocket("Cronos P");

        mission.assignRocket(titan);
        cronos.assignToMission(mission);

        mission.endMission();

        Assert.assertEquals(0, mission.getRocketCount());
        Assert.assertTrue(mission.hasEnded());
    }

    @Test
    public void testToString1() {
        Mission mission = new Mission("Transit");
        Rocket passenger = new Rocket("Passenger");
        Rocket delta = new Rocket("Delta");
        Rocket falcon = new Rocket("Falcon");

        mission.assignRocket(passenger);
        mission.assignRocket(delta);
        mission.assignRocket(falcon);
        delta.moveToRepair();
        mission.updateStatus();

        String expected = "Transit - PENDING - Dragons: 3\n\t" + passenger + "\n\t" + delta + "\n\t" + falcon;

        Assert.assertEquals(expected, mission.toString());
    }

    @Test
    public void testToString2() {
        Mission mission = new Mission("Optimus");
        Rocket cosmicWalker = new Rocket("CosmicWalker");

        mission.assignRocket(cosmicWalker);

        String expected = "Optimus - IN_PROGRESS - Dragons: 1\n\t" + cosmicWalker;

        Assert.assertEquals(expected, mission.toString());
    }
}
