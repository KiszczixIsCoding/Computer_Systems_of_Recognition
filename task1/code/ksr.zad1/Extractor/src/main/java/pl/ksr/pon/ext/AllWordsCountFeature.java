package pl.ksr.pon.ext;

public class AllWordsCountFeature  implements NumericalFeature {

    @Override
    public int extract(String content) {

        String[] wordsList = content.split("\\s+");
        return wordsList.length;
    }
}
