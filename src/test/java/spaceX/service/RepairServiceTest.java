package spaceX.service;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class RepairServiceTest {

    @Test
    public void testMoveRocketToRepair1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);
        RepairService.moveRocketToRepair(rocket);

        Assert.assertTrue(rocket.inRepair());
        Assert.assertTrue(mission.isPending());
    }

    @Test
    public void testMoveRocketToRepair2() {
        Rocket rocket = new Rocket("Helios");

        Assert.assertThrows(SpaceException.class, () -> RepairService.moveRocketToRepair(rocket));
    }

    @Test
    public void testMoveRocketToRepair3() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);
        RepairService.moveRocketToRepair(rocket);

        Assert.assertThrows(SpaceException.class, () -> RepairService.moveRocketToRepair(rocket));
    }

    @Test
    public void testMoveRocketFromRepair1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);
        RepairService.moveRocketToRepair(rocket);

        Assert.assertTrue(rocket.inRepair());
        Assert.assertTrue(mission.isPending());

        RepairService.moveRocketFromRepair(rocket);

        Assert.assertTrue(rocket.inSpace());
        Assert.assertTrue(mission.inProgress());
    }

    @Test
    public void testMoveRocketFromRepair2() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        AssignService.assignRocketToMission(mission, rocket);

        Assert.assertThrows(SpaceException.class, () -> RepairService.moveRocketFromRepair(rocket));
    }
}
