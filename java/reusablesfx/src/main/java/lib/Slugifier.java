package lib;

import java.text.Normalizer;

public final class Slugifier {

    private Slugifier() {
    }

    public static String slug(String input) {

        if (input == null || input.isBlank()) {
            return "";
        }

        String normalized =
                Normalizer.normalize(
                        input,
                        Normalizer.Form.NFD
                );

        normalized = normalized.replaceAll(
                "\\p{InCombiningDiacriticalMarks}+",
                ""
        );

        return normalized
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }
}