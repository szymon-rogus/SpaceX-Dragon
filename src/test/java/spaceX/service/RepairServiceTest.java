package spaceX.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spaceX.exception.SpaceException;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class RepairServiceTest {

    private AssignService assignService;

    private RepairService repairService;

    @Before
    public void setUp() {
        assignService = new AssignService();
        repairService = new RepairService();
    }

    @Test
    public void testMoveRocketToRepair1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);
        repairService.moveRocketToRepair(rocket);

        Assert.assertTrue(rocket.inRepair());
        Assert.assertTrue(mission.isPending());
    }

    @Test
    public void testMoveRocketToRepair2() {
        Rocket rocket = new Rocket("Helios");

        Assert.assertThrows(SpaceException.class, () -> repairService.moveRocketToRepair(rocket));
    }

    @Test
    public void testMoveRocketToRepair3() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);
        repairService.moveRocketToRepair(rocket);

        Assert.assertThrows(SpaceException.class, () -> repairService.moveRocketToRepair(rocket));
    }

    @Test
    public void testMoveRocketFromRepair1() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);
        repairService.moveRocketToRepair(rocket);

        Assert.assertTrue(rocket.inRepair());
        Assert.assertTrue(mission.isPending());

        repairService.moveRocketFromRepair(rocket);

        Assert.assertTrue(rocket.inSpace());
        Assert.assertTrue(mission.inProgress());
    }

    @Test
    public void testMoveRocketFromRepair2() {
        Mission mission = new Mission("Orbiting");
        Rocket rocket = new Rocket("Helios");

        assignService.assignRocketToMission(mission, rocket);

        Assert.assertThrows(SpaceException.class, () -> repairService.moveRocketFromRepair(rocket));
    }
}
