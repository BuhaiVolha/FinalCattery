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
public class CatRussianInputDataInvalidTest {
    Validator validator = Validator.getInstance();

    private CatDetail catDetail;


    public CatRussianInputDataInvalidTest(CatDetail catDetail) {
        this.catDetail = catDetail;
    }

    @Parameterized.Parameters
    public static List<CatDetail[]> dataForTest() {
        return Arrays.asList(new CatDetail[][]{
                {new CatDetail(0, "Murka", "Милакун", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Milacoon", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Милакун", "   ",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "", "Милакун", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Милакун", "Это котенок",
                        "Кошка", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Милакун", "Это котенок",
                        "Кошка Кош5ковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Милакун", "Это котенок",
                        "Кошка Кошковна", "  ", LocaleLang.RU)},
                {new CatDetail(0, "М4урка", "Милакун", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Ми45лакун", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "434334", "Милакун", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "434334", "Это котенок",
                        "Кошка Кошковна", "Кот Котов", LocaleLang.RU)},
                {new CatDetail(0, "Мурка", "Кототоыввавааааааааааааааааааааааааааааааааааава",
                        "Это котенок","Кошка Кошковна", "Кот Котов", LocaleLang.RU)}
        });
    }


    @Test
    public void validateCatDetailRu() {
        Assert.assertFalse(validator.validateCatDetailRu(catDetail));
    }
}
