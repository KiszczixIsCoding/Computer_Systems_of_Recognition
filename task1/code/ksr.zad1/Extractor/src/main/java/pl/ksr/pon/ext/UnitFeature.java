package pl.ksr.pon.ext;
import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.dic.UnitDictionary;

public class UnitFeature implements NumericalFeature {


    @Override
    public int extract(String content) {
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
