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
        var s = filterWord(typedWord);
        topAvailableWords = new ArrayList<>();
        if (predictionMap.containsKey(s)) {
            var it = predictionMap.get(s).iterator();
            var i = 0;
            while (it.hasNext() && i < 3) {
                topAvailableWords.add(it.next().getWord());
                i++;
            }
        } else {
            topAvailableWords = predictionMap.keySet().stream().filter(word -> word.startsWith(s)).toList();
        }
        return topAvailableWords;
    }

    public List<String> availableWords() {
        return topAvailableWords;
    }

    public void feed(String firstWord, String secondWord) {
        firstWord = filterWord(firstWord);
        secondWord = filterWord(secondWord);

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

    private String filterWord(String word) {
        return word.toLowerCase().trim().replaceAll("\\p{Punct}", "");
    }

    public String toString() {
        return //"Nodes: " + nodes.toString() + "\n" +
                "WordMap: " + predictionMap.toString() + "\n";
    }

}
