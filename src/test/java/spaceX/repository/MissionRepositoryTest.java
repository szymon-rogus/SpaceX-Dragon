package spaceX.repository;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;

public class MissionRepositoryTest {

    @Test
    public void testCorrectAddMission() {
        MissionRepository service = new MissionRepository();

        Assert.assertTrue(service.getMissions().isEmpty());

        service.add("Mars");
        service.add("Moon Walking");
        service.add("Landing");

        Assert.assertEquals(3, service.getMissions().size());
    }

    @Test
    public void testIncorrectAddMission() {
        MissionRepository service = new MissionRepository();

        Assert.assertTrue(service.getMissions().isEmpty());

        service.add("Mars");
        service.add("Moon Walking");

        Assert.assertThrows(SpaceException.class, () -> service.add("Moon Walking"));

        Assert.assertEquals(2, service.getMissions().size());

    }

    @Test
    public void testCorrectGetMission() {
        MissionRepository service = new MissionRepository();
        service.add("Earth seeing");
        service.add("Transit");
        service.add("Samples ejection");

        Assert.assertNotNull(service.get("Transit"));
    }

    @Test
    public void testIncorrectGetMission() {
        MissionRepository service = new MissionRepository();
        service.add("Flowing");
        service.add("Horizontal Landing");

        Assert.assertThrows(SpaceException.class, () -> service.get("Landing"));
    }
}
