package by.tc.task01.dao.creator.withReflection;

import by.tc.task01.entity.criteria.Parameters;
import by.tc.task01.exception.ItemCreationFailedException;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CreatorWithReflectionTest {
    CreatorWithReflection creator;
    String type;
    Map<String, String> criterion;
    by.tc.task01.entity.Laptop laptop;

    @BeforeClass
    public void setUp() {
        creator = new CreatorWithReflection();
        type = "Laptop";
        criterion = new HashMap<>();

    }

    @AfterClass
    public void tearDown() {
        creator = null;
        type = null;
        criterion = null;
    }


    @Test
    public void testCreateGoodsWithDoubleValue() throws Exception {
        String parameter = Parameters.Laptop.CPU.toString();
        String value = "1.2";
        criterion.put(parameter, value);

        laptop = (by.tc.task01.entity.Laptop) creator.createGoodsAndParameterize(type, criterion);
        double actual = laptop.getCpu();
        double expected = 1.2;

        assertEquals(actual, expected, 0.001);
    }

    @Test
    public void testCreateGoodsWithStringValue() throws Exception {
        String parameter = Parameters.Laptop.OS.toString();
        String value = "Windows";
        criterion.put(parameter, value);

        laptop = (by.tc.task01.entity.Laptop) creator.createGoodsAndParameterize(type, criterion);
        String actual = laptop.getOs();
        String expected = value;

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ItemCreationFailedException.class)
    public void testCreateGoodsWithWrongType() throws Exception {
        String parameter = "SomethingWrong";
        String value = "Windows";
        criterion.put(parameter, value);

        laptop = (by.tc.task01.entity.Laptop) creator.createGoodsAndParameterize(type, criterion);


    }
}