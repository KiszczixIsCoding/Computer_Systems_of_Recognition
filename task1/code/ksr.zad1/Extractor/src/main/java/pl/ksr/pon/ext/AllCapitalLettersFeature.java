package pl.ksr.pon.ext;

import lombok.Getter;


@Getter
public class AllCapitalLettersFeature implements NumericalFeature {
    private int allCapitalLettersCount;

    @Override
    public int extract(String content) {
        return 0;
    }
}
