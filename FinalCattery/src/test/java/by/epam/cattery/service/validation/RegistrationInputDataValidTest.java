package by.epam.cattery.service.validation;

import by.epam.cattery.entity.Role;
import by.epam.cattery.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class RegistrationInputDataValidTest {
    Validator validator = Validator.getInstance();

    private User user;

    public RegistrationInputDataValidTest(User user) {
        this.user = user;
    }

    @Parameterized.Parameters
    public static List<User[]> dataForTest() {
        return Arrays.asList(new User[][]{
                {new User(1, "Kate", "1234567", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "44 3232343", "A",
                        0, false, false)},
                {new User(2, "123", "1234567", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(3, "Мур", "1234567", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(4, "Kate", "1234567", Role.USER, "Anna",
                        "Main", "an-na@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(5, "KaTe", "1234567", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(6, "Kate", "1234567", Role.USER, "Anna",
                        "Main", "anNa@mail.ru", "sdfsdf", "A",
                        0, false, false)}
        });
    }


    @Test
    public void validateRegistrationInputData() {
        Assert.assertTrue(validator.validateRegistrationInputData(user));
    }
}