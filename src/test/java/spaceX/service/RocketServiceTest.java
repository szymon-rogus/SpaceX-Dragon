package spaceX.service;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class RocketServiceTest {

    @Test
    public void testCorrectAddRocket() {
        RocketService service = new RocketService();

        Assert.assertTrue(service.getRockets().isEmpty());

        service.add("MX-774");
        service.add("Vanguard");
        service.add("Titan I");

        Assert.assertEquals(3, service.getRockets().size());
    }

    @Test
    public void testIncorrectAddRocket() {
        RocketService service = new RocketService();

        Assert.assertTrue(service.getRockets().isEmpty());

        service.add("Delta 2000");
        service.add("Atlas H");

        Assert.assertThrows(SpaceException.class, () -> service.add("Atlas H"));

        Assert.assertEquals(2, service.getRockets().size());

    }

    @Test
    public void testCorrectGetRocket() {
        RocketService service = new RocketService();
        service.add("PGM-19 Jupiter");
        service.add("Little Joe");
        service.add("Delta 4000");

        Assert.assertEquals("Delta 4000", service.get("Delta 4000").getName());
    }

    @Test
    public void testIncorrectGetRocket() {
        RocketService service = new RocketService();
        service.add("Delta J");
        service.add("Delta L");

        Assert.assertThrows(SpaceException.class, () -> service.get("Delta M"));
    }

    @Test
    public void testAssignToMission() {
        RocketService service = new RocketService();
        Mission mission = new Mission("Apollo 2");
        Rocket rocket = new Rocket("Saturn V");

        service.assignToMission(mission, rocket);

        Assert.assertEquals(mission, rocket.getMission());
        Assert.assertTrue(rocket.isAssigned());
    }

    @Test
    public void testUnassignFromMission() {
        RocketService service = new RocketService();
        Mission mission = new Mission("Apollo 4");
        Rocket rocket = new Rocket("Saturn VI");

        service.assignToMission(mission, rocket);
        service.unassignFromMission(rocket);

        Assert.assertNotEquals(mission, rocket.getMission());
        Assert.assertFalse(rocket.isAssigned());
    }

    @Test
    public void testMoveToRepair() {
        RocketService service = new RocketService();
        Rocket rocket = new Rocket("Terran 1");

        Assert.assertFalse(rocket.inRepair());

        service.moveToRepair(rocket);

        Assert.assertTrue(rocket.inRepair());
    }

    @Test
    public void testMoveFromRepair() {
        RocketService service = new RocketService();
        Rocket rocket = new Rocket("Electron");

        Assert.assertFalse(rocket.inRepair());

        service.moveToRepair(rocket);
        service.moveFromRepair(rocket);

        Assert.assertFalse(rocket.inRepair());
    }
}
