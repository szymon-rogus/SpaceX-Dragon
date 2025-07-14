package spaceX.service;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;

public class MissionServiceTest {

    @Test
    public void testCorrectAddMission() {
        MissionService service = new MissionService();

        Assert.assertTrue(service.getMissions().isEmpty());

        service.add("Mars");
        service.add("Moon Walking");
        service.add("Landing");

        Assert.assertEquals(3, service.getMissions().size());
    }

    @Test
    public void testIncorrectAddMission() {
        MissionService service = new MissionService();

        Assert.assertTrue(service.getMissions().isEmpty());

        service.add("Mars");
        service.add("Moon Walking");

        Assert.assertThrows(SpaceException.class, () -> service.add("Moon Walking"));

        Assert.assertEquals(2, service.getMissions().size());

    }

    @Test
    public void testCorrectGetMission() {
        MissionService service = new MissionService();
        service.add("Earth seeing");
        service.add("Transit");
        service.add("Samples ejection");

        /// Override equals in Mission class?
        Assert.assertEquals("Transit", service.get("Transit").getName());
    }

    @Test
    public void testIncorrectGetMission() {
        MissionService service = new MissionService();
        service.add("Flowing");
        service.add("Horizontal Landing");

        Assert.assertThrows(SpaceException.class, () -> service.get("Landing"));
    }
}
