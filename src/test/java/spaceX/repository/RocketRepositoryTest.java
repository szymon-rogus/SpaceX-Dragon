package spaceX.repository;

import org.junit.Assert;
import org.junit.Test;
import spaceX.exception.SpaceException;

public class RocketRepositoryTest {

    @Test
    public void testCorrectAddRocket() {
        RocketRepository service = new RocketRepository();

        Assert.assertTrue(service.getRockets().isEmpty());

        service.add("MX-774");
        service.add("Vanguard");
        service.add("Titan I");

        Assert.assertEquals(3, service.getRockets().size());
    }

    @Test
    public void testIncorrectAddRocket() {
        RocketRepository service = new RocketRepository();

        Assert.assertTrue(service.getRockets().isEmpty());

        service.add("Delta 2000");
        service.add("Atlas H");

        Assert.assertThrows(SpaceException.class, () -> service.add("Atlas H"));

        Assert.assertEquals(2, service.getRockets().size());

    }

    @Test
    public void testCorrectGetRocket() {
        RocketRepository service = new RocketRepository();
        service.add("PGM-19 Jupiter");
        service.add("Little Joe");
        service.add("Delta 4000");

        Assert.assertEquals("Delta 4000", service.get("Delta 4000").getName());
    }

    @Test
    public void testIncorrectGetRocket() {
        RocketRepository service = new RocketRepository();
        service.add("Delta J");
        service.add("Delta L");

        Assert.assertThrows(SpaceException.class, () -> service.get("Delta M"));
    }
}
