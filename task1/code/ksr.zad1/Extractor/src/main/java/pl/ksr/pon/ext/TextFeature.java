package pl.ksr.pon.ext;

import java.util.List;

public interface TextFeature {
    String extractTextFeature(String content);
    void extract(String mainContent, String comparingContent);
}
