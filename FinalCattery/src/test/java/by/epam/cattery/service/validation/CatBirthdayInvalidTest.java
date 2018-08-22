package by.epam.cattery.service.validation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class CatBirthdayInvalidTest {
    Validator validator = Validator.getInstance();

    private String birthDate;

    public CatBirthdayInvalidTest(String birthDate) {
        this.birthDate = birthDate;
    }

    @Parameterized.Parameters
    public static List<String> passwordsForTest() {
        return Arrays.asList(
                "05/12/2050",
                "05/22/2017",
                "84/12/2017",
                "cats",
                "05.12.2017",
                "1/3/2017",
                "05/   12/2017",
                "05/12/",
                ""
        );
    }

    @Test
    public void validateCatBirthDate() {
        Assert.assertFalse(validator.validateCatBirthDate(birthDate));
    }
}
