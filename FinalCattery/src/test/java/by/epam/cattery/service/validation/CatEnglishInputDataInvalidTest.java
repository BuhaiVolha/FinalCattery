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
public class CatEnglishInputDataInvalidTest {
    Validator validator = Validator.getInstance();

    private CatDetail catDetail;


    public CatEnglishInputDataInvalidTest(CatDetail catDetail) {
        this.catDetail = catDetail;
    }

    @Parameterized.Parameters
    public static List<CatDetail[]> dataForTest() {
        return Arrays.asList(new CatDetail[][]{
                {new CatDetail(0, "Murka", "Milacoon", "It's a kitten",
                        "Kitty     Milacoon", "Tom Milacoon", LocaleLang.EN)},
                {new CatDetail(0, "Мурка", "Milacoon", "It's a kitten",
                        "Kitty Milacoon", "Tom Milacoon", LocaleLang.EN)},
                {new CatDetail(0, "Murka", "Милакун", "It's a kitten",
                        "Kitty Milacoon", "Tom Milacoon", LocaleLang.EN)},
                {new CatDetail(0, "Murka", "Milacoon", "It's a kitten",
                        "Kitty Кот", "Tom Milacoon", LocaleLang.EN)},
                {new CatDetail(0, "Murka", "Milacoon", "It's a kitten",
                        "Kitty Milacoofffffffffffffffgfgfgfgfffffffffffffffn",
                        "Tom Milacoon", LocaleLang.EN)}
        });
    }


    @Test
    public void validateCatDetailRu() {
        Assert.assertFalse(validator.validateCatDetailEn(catDetail));
    }
}
