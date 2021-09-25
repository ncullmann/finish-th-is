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

    // FIXME: messes up suggestions mid typing
    public List<String> availableWords(String firstWord, String secondWord) {
        topAvailableWords = new ArrayList<>();
        if (predictionMap.containsKey(secondWord)) {
            var it = predictionMap.get(secondWord).iterator();
            int i = 0;
            while (it.hasNext() && i < 3) {
                topAvailableWords.add(it.next().getWord());
                i++;
            }
        } else if (predictionMap.containsKey(firstWord)) {
            topAvailableWords = predictionMap.get(firstWord).stream().map(WordNode::getWord).filter(word -> word.startsWith(secondWord)).toList();
        } else {
            topAvailableWords = predictionMap.keySet().stream().filter(word -> word.startsWith(secondWord)).toList();
        }
        return topAvailableWords;
    }

    public List<String> availableWords() {
        return topAvailableWords;
    }

    public void train(String firstWord, String secondWord) {
        if (firstWord.equals("") || secondWord.equals(""))
            return;

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
