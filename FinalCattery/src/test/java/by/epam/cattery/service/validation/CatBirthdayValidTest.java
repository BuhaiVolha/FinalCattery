package by.epam.cattery.service.validation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(Parameterized.class)
public class CatBirthdayValidTest {
    Validator validator = Validator.getInstance();

    private String birthDate;

    public CatBirthdayValidTest(String birthDate) {
        this.birthDate = birthDate;
    }

    @Parameterized.Parameters
    public static List<String> passwordsForTest() {
        return Arrays.asList(
                "05/12/2017",
                new SimpleDateFormat("dd/MM/yyyy").format(new Date())
        );
    }

    @Test
    public void validateCatBirthDate() {
        Assert.assertTrue(validator.validateCatBirthDate(birthDate));
    }
}