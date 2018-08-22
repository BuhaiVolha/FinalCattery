package by.epam.cattery.service.validation;

import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.dto.CatDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class CatRussianInputDataValidTest {
    Validator validator = Validator.getInstance();

    private CatDetail catDetail;


    public CatRussianInputDataValidTest(CatDetail catDetail) {
        this.catDetail = catDetail;
    }

    @Parameterized.Parameters
    public static List<CatDetail[]> dataForTest() {
        return Arrays.asList(new CatDetail[][]{
                {new CatDetail(0, "Мурка", "Милакун", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Милакун", "Окрас солидный голубой (solid A)",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Белый Лев", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Милакун", "Это котенок",
                        "Кошка Белый Лев", "Кот Котов", LocaleLang.RU)}
        });
    }


    @Test
    public void validateCatDetailRu() {
        Assert.assertTrue(validator.validateCatDetailRu(catDetail));
    }
}
