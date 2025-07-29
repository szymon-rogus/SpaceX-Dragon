package spaceX.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class AssignServiceTest {

    private AssignService assignService;

    @Before
    public void setUp() {
        assignService = new AssignService();
    }

    @Test
    public void testAssignRocketToMission1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);

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

        Assert.assertThrows(SpaceException.class, () -> assignService.assignRocketToMission(mission, rocket));
    }

    @Test
    public void testAssignRocketToMission3() {
        Mission mission1 = new Mission("Horizontal landing");
        Mission mission2 = new Mission("Vertical landing");
        Rocket rocket = new Rocket("Afrodita");

        assignService.assignRocketToMission(mission1, rocket);

        Assert.assertThrows(SpaceException.class, () -> assignService.assignRocketToMission(mission2, rocket));
    }

    @Test
    public void testUnassignRocketToMission1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);
        assignService.unassignRocketFromMission(mission, rocket);

        Assert.assertFalse(rocket.inSpace());
        Assert.assertNotEquals(mission, rocket.getMission());
        Assert.assertEquals(0, mission.getRocketCount());
        Assert.assertTrue(mission.isScheduled());
    }

    @Test
    public void testUnassignRocketToMission2() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        Assert.assertThrows(SpaceException.class, () -> assignService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testUnassignRocketToMission3() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);
        rocket.moveToRepair();

        Assert.assertThrows(SpaceException.class, () -> assignService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testUnassignRocketToMission4() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);
        mission.endMission();

        Assert.assertThrows(SpaceException.class, () -> assignService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testEndMission() {
        Mission mission = new Mission("Skylab");
        Rocket dragon1 = new Rocket("Dragon 1");
        Rocket dragon2 = new Rocket("Dragon 2");
        Rocket dragon3 = new Rocket("Dragon 3");

        assignService.assignRocketToMission(mission, dragon1);
        assignService.assignRocketToMission(mission, dragon2);
        assignService.assignRocketToMission(mission, dragon3);

        assignService.endMission(mission);

        Assert.assertTrue(mission.hasEnded());
        Assert.assertTrue(dragon1.onGround());
        Assert.assertTrue(dragon2.onGround());
        Assert.assertTrue(dragon3.onGround());
    }
}
