package pl.ksr.pon.ext.fea;
import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.NumericalFeature;
import pl.ksr.pon.ext.dic.UnitDictionary;

public class UnitFeature extends NumericalFeature {

    public UnitFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        numericalFeatureValue = extractNumericalFeature(content) / 10.0;
    }

    public int extractNumericalFeature(String content) {
        int siCounter = 0;
        int imperialCounter = 0;

        // si:
        for (String siUnit : UnitDictionary.getSiUnits()) {
            siCounter += StringUtils.countMatches(content, siUnit);
        }
        //imperial:
        for (String impUnit : UnitDictionary.getImperialUnits()) {
            imperialCounter += StringUtils.countMatches(content, impUnit);
        }

        return siCounter - imperialCounter;
    }
}
