import org.junit.Assert;
import org.junit.Test;
import spaceX.SpaceXManager;
import spaceX.model.Mission;
import spaceX.model.Rocket;

public class SpaceXManagerTest {

    @Test
    public void testAddRocket() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addRocket("Luna");

        Assert.assertEquals(1, spaceXManager.getRocketRepository().size());
    }

    @Test
    public void testAddRockets() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addRockets("Luna", "Luna", "Valiant", "Valiant");

        Assert.assertEquals(2, spaceXManager.getRocketRepository().size());
    }

    @Test
    public void testAddMission() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMission("Apollo");

        Assert.assertEquals(1, spaceXManager.getMissionRepository().size());
    }

    @Test
    public void testAddMissions() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMissions("Apollo", "Fenix", "Apollo", "Io", "Fenix");

        Assert.assertEquals(3, spaceXManager.getMissionRepository().size());
    }

    @Test
    public void assignRocketToMission() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMissions("Apollo", "Fenix");
        spaceXManager.addRockets("Luna", "Hermes", "Zeus", "Valiant");

        spaceXManager.assignRocketToMission("Apollo", "Luna");
        spaceXManager.assignRocketToMission("Fenix", "Zeus");

        Rocket luna = spaceXManager.getRocketRepository().get("Luna");
        Mission apollo = spaceXManager.getMissionRepository().get("Apollo");

        Assert.assertEquals(apollo, luna.getMission());
    }

    @Test
    public void unassignRocketFromMission() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMissions("Apollo", "Fenix");
        spaceXManager.addRockets("Luna", "Hermes", "Zeus", "Valiant");

        spaceXManager.assignRocketToMission("Apollo", "Luna");
        spaceXManager.assignRocketToMission("Fenix", "Zeus");
        spaceXManager.unassignRocketFromMission("Apollo", "Luna");

        Rocket luna = spaceXManager.getRocketRepository().get("Luna");
        Mission apollo = spaceXManager.getMissionRepository().get("Apollo");

        Assert.assertNotEquals(apollo, luna.getMission());
    }

    @Test
    public void moveToRepair() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMission("Firefly");
        spaceXManager.addRocket("Luna");

        spaceXManager.assignRocketToMission("Firefly", "Luna");
        spaceXManager.moveToRepair("Luna");

        Assert.assertTrue(spaceXManager.getRocketRepository().get("Luna").inRepair());
    }

    @Test
    public void moveFromRepair() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMission("Firefly");
        spaceXManager.addRocket("Luna");

        spaceXManager.assignRocketToMission("Firefly", "Luna");
        spaceXManager.moveToRepair("Luna");

        Assert.assertTrue(spaceXManager.getRocketRepository().get("Luna").inRepair());

        spaceXManager.moveFromRepair("Luna");

        Assert.assertFalse(spaceXManager.getRocketRepository().get("Luna").inRepair());
    }

    @Test
    public void endMission() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMission("Sky");
        spaceXManager.addRockets("Luna", "Hermes", "Zeus", "Valiant");

        spaceXManager.assignRocketsToMission("Sky", "Luna", "Hermes", "Zeus", "Valiant");

        spaceXManager.endMission("Sky");

        Assert.assertTrue(spaceXManager.getMissionRepository().get("Sky").hasEnded());
    }

    @Test
    public void testSummary() {
        SpaceXManager spaceXManager = new SpaceXManager("SpaceXManager");
        spaceXManager.addMissions("Apollo", "Fenix");
        spaceXManager.addRockets("Luna", "Hermes", "Zeus", "Valiant");

        spaceXManager.assignRocketsToMission("Apollo", "Luna", "Hermes", "Zeus");
        spaceXManager.assignRocketsToMission("Fenix", "Valiant");

        String expected = "Apollo - IN_PROGRESS - Dragons: 3\n\tLuna - IN_SPACE\n\tHermes - IN_SPACE\n\tZeus - IN_SPACE"
                + "\nFenix - IN_PROGRESS - Dragons: 1\n\tValiant - IN_SPACE\n";

        Assert.assertEquals(expected, spaceXManager.toString());
    }
}
