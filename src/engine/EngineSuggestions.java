package engine;

import java.util.List;

public class EngineSuggestions {

    private final PredictionEngine predictionEngine;


    public EngineSuggestions() {
        predictionEngine = PredictionEngine.getInstance();
    }

    public List<String> getAvailableWords(String firstWord, String secondWord) {
        return predictionEngine.getAvailableWords(filterWord(firstWord), filterWord(secondWord));
    }

    public List<String> getAvailableWords() {
        return predictionEngine.getAvailableWords();
    }

    public List<String> getNextWords(String secondWord) {
        return predictionEngine.getNextWords(filterWord(secondWord));
    }

    public int getNumberOfSuggestions() {
        return PredictionEngine.getSuggestionCount();
    }

    private String filterWord(String word) {
        return word.toLowerCase().trim().replaceAll("\\p{Punct}", "");
    }
}
