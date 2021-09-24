import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PredictionEngine {
    private static PredictionEngine instance = null;
    private Map<String, Set<WordNode>> predictionMap;
    private Map<Map<String, String>, WordNode> stringToNode;
    private List<String> topAvailableWords;

    public PredictionEngine() {
        predictionMap = new HashMap<>();
        stringToNode = new HashMap<>();
        topAvailableWords = new ArrayList<>();
    }

    public static PredictionEngine getInstance() {
        if (instance == null) instance = new PredictionEngine();
        return instance;
    }

    public List<String> availableWords(String typedWord) {
        topAvailableWords = new ArrayList<>();
        if (predictionMap.containsKey(typedWord)) {
            var it = predictionMap.get(typedWord).iterator();
            var i = 0;
            while (it.hasNext() && i < 3) {
                topAvailableWords.add(it.next().getWord());
                i++;
            }
        } else {
            topAvailableWords = predictionMap.keySet().stream().filter(word -> word.startsWith(typedWord)).toList();
        }
        return topAvailableWords;
    }

    public List<String> availableWords() {
        return topAvailableWords;
    }

    public void train(String firstWord, String secondWord) {
//        firstWord = filterWord(firstWord);
//        secondWord = filterWord(secondWord);

        if (predictionMap.containsKey(firstWord)) {
            var nodeKey = Map.of(firstWord, secondWord);
            WordNode node;

            if (stringToNode.containsKey(nodeKey)) {
                node = stringToNode.get(nodeKey);
                predictionMap.get(firstWord).remove(node);
                node.incrementFrequency();
            } else {
                node = new WordNode(secondWord);
            }
            predictionMap.get(firstWord).add(node);
            stringToNode.put(Map.of(firstWord, secondWord), node);
        } else {
            var node = new WordNode(secondWord);
            TreeSet<WordNode> set = new TreeSet<>();
            set.add(node);
            predictionMap.put(firstWord, set);
            stringToNode.put(Map.of(firstWord, secondWord), node);
        }
    }


    public String toString() {
        return "WordMap: " + predictionMap.toString() + "\n";
    }
}
