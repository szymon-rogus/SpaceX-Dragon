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
    public void testInSpaceAssigned() {
        Rocket rocket = new Rocket("Tiger");
        rocket.setStatus(RocketStatus.IN_SPACE);

        Assert.assertTrue(rocket.isAssigned());
    }

    @Test
    public void testInRepairAssigned() {
        Rocket rocket = new Rocket("Tiger");
        rocket.setStatus(RocketStatus.IN_REPAIR);

        Assert.assertTrue(rocket.isAssigned());
    }
}
