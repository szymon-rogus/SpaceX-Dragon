package spaceX.model;

import org.junit.Assert;
import org.junit.Test;
import spaceX.status.RocketStatus;

public class RocketTest {

    @Test
    public void testInitialStatus() {
        Rocket rocket = new Rocket("Spider");

        Assert.assertEquals(RocketStatus.ON_GROUND, rocket.getStatus());
    }

    @Test
    public void testNotAssigned() {
        Rocket rocket = new Rocket("Spruce");

        Assert.assertFalse(rocket.isAssigned());
    }

    @Test
    public void testAssignToMission() {
        Rocket rocket = new Rocket("Tiger");
        Mission mission = new Mission("MilkyWay");

        rocket.assignToMission(mission);

        Assert.assertTrue(rocket.isAssigned());
        Assert.assertEquals(mission, rocket.getMission());
    }

    @Test
    public void testUnassignFromMission() {
        Rocket rocket = new Rocket("Dragonfly 23");
        Mission mission = new Mission("MilkyWay");

        rocket.assignToMission(mission);
        rocket.unassignFromMission();

        Assert.assertFalse(rocket.isAssigned());
        Assert.assertNotEquals(mission, rocket.getMission());
    }

    @Test
    public void testMoveToRepair() {
        Rocket rocket = new Rocket("Ares I");

        Assert.assertFalse(rocket.inRepair());

        rocket.moveToRepair();

        Assert.assertTrue(rocket.inRepair());
    }

    @Test
    public void testMoveFromRepair() {
        Rocket rocket = new Rocket("Ares I");

        Assert.assertFalse(rocket.inRepair());

        rocket.moveToRepair();
        rocket.moveFromRepair();

        Assert.assertFalse(rocket.inRepair());
    }

    @Test
    public void testToString1() {
        Rocket rocket = new Rocket("Nova");

        Assert.assertEquals("Nova - ON_GROUND", rocket.toString());
    }

    @Test
    public void testToString2() {
        Rocket rocket = new Rocket("Mosquito");
        rocket.setStatus(RocketStatus.IN_SPACE);

        Assert.assertEquals("Mosquito - IN_SPACE", rocket.toString());
    }

    @Test
    public void testToString3() {
        Rocket rocket = new Rocket("Javelin");
        rocket.setStatus(RocketStatus.IN_REPAIR);

        Assert.assertEquals("Javelin - IN_REPAIR", rocket.toString());
    }
}
