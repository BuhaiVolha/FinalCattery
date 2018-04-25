package by.tc.task01.dao.creator.withReflection;

import by.tc.task01.entity.Laptop;
import by.tc.task01.entity.criteria.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CreatorWithReflectionTest {

    @BeforeMethod
    public void setUp() throws Exception {
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateGoodsAndParameterizeWithDouble() throws Exception {
        CreatorWithReflection creator = new CreatorWithReflection();
        String type = "Laptop";

        Map<String, String> criterion = new HashMap<>();
        String parameter = Parameters.GoodsType.Laptop.CPU.toString();
        String value = "1.2";

        criterion.put(parameter, value);

        Laptop laptop = (Laptop) creator.createGoodsAndParameterize(type, criterion);
        double actual = laptop.getCpu();
        double expected = 1.2;

        assertEquals(actual, expected, 0.001);
    }

    @Test
    public void testCreateGoodsAndParameterizeWithString() throws Exception {
        CreatorWithReflection creator = new CreatorWithReflection();
        String type = "Laptop";

        Map<String, String> criterion = new HashMap<>();
        String parameter = Parameters.GoodsType.Laptop.OS.toString();
        String value = "Windows";

        criterion.put(parameter, value);

        Laptop laptop = (Laptop) creator.createGoodsAndParameterize(type, criterion);
        String actual = laptop.getOs();
        String expected = value;

        assertEquals(actual, expected);
    }
}