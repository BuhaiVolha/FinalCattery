package by.epam.cattery.service.validation;

import by.epam.cattery.entity.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


@RunWith(Parameterized.class)
public class ReviewInputDataInvalidTest {
    Validator validator = Validator.getInstance();

    private Review review;


    public ReviewInputDataInvalidTest(Review review) {
        this.review = review;
    }

    @Parameterized.Parameters
    public static List<Review[]> dataForTest() {
        return Arrays.asList(new Review[][]{
                {new Review(0,0, "Kate", "Text", 6,
                        new Date(Calendar.getInstance().getTime().getTime()))},
                {new Review(0,0, "Kate", "Text", -4,
                        new Date(Calendar.getInstance().getTime().getTime()))},
                {new Review(0,0, "Kate", "", 0,
                        new Date(Calendar.getInstance().getTime().getTime()))},
                {new Review(0,0, "Kate", "            ", 5,
                        new Date(Calendar.getInstance().getTime().getTime()))}
        });
    }


    @Test
    public void validateReviewInputData() {
        Assert.assertFalse(validator.validateReviewInputData(review));
    }
}
