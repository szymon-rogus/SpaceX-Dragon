package spaceX.service;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class SpaceServiceTest {

    @Test
    public void testAssignRocketToMission1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);

        Assert.assertTrue(rocket.inSpace());
        Assert.assertEquals(mission, rocket.getMission());
        Assert.assertEquals(1, mission.getRocketCount());
        Assert.assertTrue(mission.inProgress());
    }

    @Test
    public void testAssignRocketToMission2() {
        Mission mission = new Mission("Horizontal landing");
        Rocket rocket = new Rocket("Afrodita");

        mission.endMission();

        Assert.assertThrows(SpaceException.class, () -> SpaceService.assignRocketToMission(mission, rocket));
    }

    @Test
    public void testAssignRocketToMission3() {
        Mission mission1 = new Mission("Horizontal landing");
        Mission mission2 = new Mission("Vertical landing");
        Rocket rocket = new Rocket("Afrodita");

        SpaceService.assignRocketToMission(mission1, rocket);

        Assert.assertThrows(SpaceException.class, () -> SpaceService.assignRocketToMission(mission2, rocket));
    }

    @Test
    public void testUnassignRocketToMission1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);
        SpaceService.unassignRocketFromMission(mission, rocket);

        Assert.assertFalse(rocket.inSpace());
        Assert.assertNotEquals(mission, rocket.getMission());
        Assert.assertEquals(0, mission.getRocketCount());
        Assert.assertTrue(mission.isScheduled());
    }

    @Test
    public void testUnassignRocketToMission2() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        Assert.assertThrows(SpaceException.class, () -> SpaceService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testUnassignRocketToMission3() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);
        SpaceService.moveRocketToRepair(rocket);

        Assert.assertThrows(SpaceException.class, () -> SpaceService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testUnassignRocketToMission4() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);
        mission.endMission();

        Assert.assertThrows(SpaceException.class, () -> SpaceService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testMoveRocketToRepair1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);
        SpaceService.moveRocketToRepair(rocket);

        Assert.assertTrue(rocket.inRepair());
        Assert.assertTrue(mission.isPending());
    }

    @Test
    public void testMoveRocketToRepair2() {
        Rocket rocket = new Rocket("Helios");

        Assert.assertThrows(SpaceException.class, () -> SpaceService.moveRocketToRepair(rocket));
    }

    @Test
    public void testMoveRocketToRepair3() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);
        SpaceService.moveRocketToRepair(rocket);

        Assert.assertThrows(SpaceException.class, () -> SpaceService.moveRocketToRepair(rocket));
    }

    @Test
    public void testMoveRocketFromRepair1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);
        SpaceService.moveRocketToRepair(rocket);

        Assert.assertTrue(rocket.inRepair());
        Assert.assertTrue(mission.isPending());

        SpaceService.moveRocketFromRepair(rocket);

        Assert.assertTrue(rocket.inSpace());
        Assert.assertTrue(mission.inProgress());
    }

    @Test
    public void testMoveRocketFromRepair2() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        SpaceService.assignRocketToMission(mission, rocket);

        Assert.assertThrows(SpaceException.class, () -> SpaceService.moveRocketFromRepair(rocket));
    }

    @Test
    public void testEndMission() {
        Mission mission = new Mission("Skylab");
        Rocket dragon1 = new Rocket("Dragon 1");
        Rocket dragon2 = new Rocket("Dragon 2");
        Rocket dragon3 = new Rocket("Dragon 3");

        SpaceService.assignRocketToMission(mission, dragon1);
        SpaceService.assignRocketToMission(mission, dragon2);
        SpaceService.assignRocketToMission(mission, dragon3);

        SpaceService.endMission(mission);

        Assert.assertTrue(mission.hasEnded());
        Assert.assertTrue(dragon1.onGround());
        Assert.assertTrue(dragon2.onGround());
        Assert.assertTrue(dragon3.onGround());
    }
}
