package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.Speakers;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class SpeakersCreator implements Creatable {
    @Override
    public Goods parameterize(Map<String, String> parameters) {
        Speakers speakers = new Speakers();

        speakers.setCordLength(Double.parseDouble(parameters.get(Parameters.GoodsType.Speakers.CORD_LENGTH.toString())));
        speakers.setFrequencyRange(parameters.get(Parameters.GoodsType.Speakers.FREQUENCY_RANGE.toString()));
        speakers.setNumberOfSpeakers(Double.parseDouble(parameters.get(Parameters.GoodsType.Speakers.NUMBER_OF_SPEAKERS.toString())));
        speakers.setPowerConsumption(Double.parseDouble(parameters.get(Parameters.GoodsType.Speakers.POWER_CONSUMPTION.toString())));

        return speakers;
    }
}
