package spaceX.service;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class AssignServiceTest {

    @Test
    public void testAssignRocketToMission1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);

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

        Assert.assertThrows(SpaceException.class, () -> AssignService.assignRocketToMission(mission, rocket));
    }

    @Test
    public void testAssignRocketToMission3() {
        Mission mission1 = new Mission("Horizontal landing");
        Mission mission2 = new Mission("Vertical landing");
        Rocket rocket = new Rocket("Afrodita");

        AssignService.assignRocketToMission(mission1, rocket);

        Assert.assertThrows(SpaceException.class, () -> AssignService.assignRocketToMission(mission2, rocket));
    }

    @Test
    public void testUnassignRocketToMission1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);
        AssignService.unassignRocketFromMission(mission, rocket);

        Assert.assertFalse(rocket.inSpace());
        Assert.assertNotEquals(mission, rocket.getMission());
        Assert.assertEquals(0, mission.getRocketCount());
        Assert.assertTrue(mission.isScheduled());
    }

    @Test
    public void testUnassignRocketToMission2() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        Assert.assertThrows(SpaceException.class, () -> AssignService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testUnassignRocketToMission3() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);
        RepairService.moveRocketToRepair(rocket);

        Assert.assertThrows(SpaceException.class, () -> AssignService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testUnassignRocketToMission4() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);
        mission.endMission();

        Assert.assertThrows(SpaceException.class, () -> AssignService.unassignRocketFromMission(mission, rocket));
    }

    @Test
    public void testEndMission() {
        Mission mission = new Mission("Skylab");
        Rocket dragon1 = new Rocket("Dragon 1");
        Rocket dragon2 = new Rocket("Dragon 2");
        Rocket dragon3 = new Rocket("Dragon 3");

        AssignService.assignRocketToMission(mission, dragon1);
        AssignService.assignRocketToMission(mission, dragon2);
        AssignService.assignRocketToMission(mission, dragon3);

        AssignService.endMission(mission);

        Assert.assertTrue(mission.hasEnded());
        Assert.assertTrue(dragon1.onGround());
        Assert.assertTrue(dragon2.onGround());
        Assert.assertTrue(dragon3.onGround());
    }
}
