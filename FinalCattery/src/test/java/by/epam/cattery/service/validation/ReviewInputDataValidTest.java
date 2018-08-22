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
public class ReviewInputDataValidTest {
    Validator validator = Validator.getInstance();

    private Review review;


    public ReviewInputDataValidTest(Review review) {
        this.review = review;
    }

    @Parameterized.Parameters
    public static List<Review[]> dataForTest() {
        return Arrays.asList(new Review[][]{
                {new Review(0,0, "Kate", "Text dfdf", 3,
                        new Date(Calendar.getInstance().getTime().getTime()))},
                {new Review(0,0, "Kate", "43434", 5,
                        new Date(Calendar.getInstance().getTime().getTime()))},
                {new Review(0,0, "Kate", "Text fefefs", 1,
                        new Date(Calendar.getInstance().getTime().getTime()))}
        });
    }


    @Test
    public void validateReviewInputData() {
        Assert.assertTrue(validator.validateReviewInputData(review));
    }
}
