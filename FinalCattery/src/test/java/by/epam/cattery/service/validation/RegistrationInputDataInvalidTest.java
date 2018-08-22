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
public class RegistrationInputDataInvalidTest {
    Validator validator = Validator.getInstance();

    private User user;

    public RegistrationInputDataInvalidTest(User user) {
        this.user = user;
    }

    @Parameterized.Parameters
    public static List<User[]> dataForTest() {
        return Arrays.asList(new User[][]{
                {new User(1, "Kate", "1234567", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "293232343", "A",
                        0, false, false)},
                {new User(2, "Kate", "123", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(3, "Kate", "1234567", Role.USER, "Anna",
                        "Main", "annamail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(4, "Kate", "1234567", Role.USER, "A",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(5, "Kate", "1234567", Role.USER, "Andfdfdfdfdfdfgfgfgfgfgfgfgfgfgfgfgfggdfdfdfdfdfdfdfdfdfna",
                        "Main", "anna@mail.ru", "293232343", "A",
                        0, false, false)},
                {new User(6, "Kate", "1234567", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "sdfsdf", "A",
                        0, false, false)},
                {new User(7, "Kate", "1234567", Role.USER, "Anna",
                        "Mai5n", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(8, "Kate", "1234567", Role.USER, "Anna",
                        "Main", "anna@mailru", "29 3232343", "A",
                        0, false, false)},
                {new User(9, "324", "1234567", Role.USER, "A43nna",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(10, "anna", "12343434343434343434343434344567", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(10, "anna", "", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(10, "anna", "12343434", Role.USER, "",
                        "Main", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(10, "anna", "12343434", Role.USER, "Anna",
                        "Main", "anna@mail.ru", "", "A",
                        0, false, false)},
                {new User(10, "anna", "123444567", Role.USER, "Anna",
                        "", "anna@mail.ru", "29 3232343", "A",
                        0, false, false)},
                {new User(10, "anna", "123434343", Role.USER, "Anna",
                        "Main", "", "29 3232343", "A",
                        0, false, false)}
        });
    }


    @Test
    public void validateRegistrationInputData() {
        Assert.assertFalse(validator.validateRegistrationInputData(user));
    }
}
