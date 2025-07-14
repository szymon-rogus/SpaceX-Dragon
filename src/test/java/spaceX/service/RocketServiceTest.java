package spaceX.service;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;

/// Currently the creation of rocket is going to be inside the "add" method in specific Service
/// TODO: Consider the two "add" methods for String name and ready Rocket object
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

        /// Override equals in Rocket class?
        Assert.assertEquals("Delta 4000", service.get("Delta 4000").getName());
    }

    @Test
    public void testIncorrectGetRocket() {
        RocketService service = new RocketService();
        service.add("Delta J");
        service.add("Delta L");

        Assert.assertThrows(SpaceException.class, () -> service.get("Delta M"));
    }
}
