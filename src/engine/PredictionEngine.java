package engine;

import java.io.*;
import java.util.*;

public class PredictionEngine {

    private static PredictionEngine instance = null;
    private Map<String, Set<WordNode>> predictionMap;
    private Map<Integer, WordNode> stringToNode;
    private List<String> topAvailableWords;

    public PredictionEngine() {
        predictionMap = new HashMap<>();
        stringToNode = new HashMap<>();
        topAvailableWords = new ArrayList<>();
    }

    static PredictionEngine getInstance() {
        if (instance == null) instance = new PredictionEngine();
        return instance;
    }

    void train(String firstWord, String secondWord) {
        if (firstWord.equals("") || secondWord.equals(""))
            return;

        var nodeKey = (firstWord + secondWord).hashCode();
        WordNode node;
        if (predictionMap.containsKey(firstWord)) {
            if (stringToNode.containsKey(nodeKey)) {
                node = stringToNode.get(nodeKey);
                predictionMap.get(firstWord).remove(node);
                node.incrementFrequency();
            } else {
                node = new WordNode(secondWord);
            }
            predictionMap.get(firstWord).add(node);
        } else {
            node = new WordNode(secondWord);
            TreeSet<WordNode> set = new TreeSet<>();
            set.add(node);
            predictionMap.put(firstWord, set);
        }
        stringToNode.put(nodeKey, node);
    }

    void saveState() throws IOException {
        var pmOutStream = new FileOutputStream("assets/pm.ser");
        var out = new ObjectOutputStream(pmOutStream);
        out.writeObject(predictionMap);
        pmOutStream.close();

        var stnOutStream = new FileOutputStream("assets/stn.ser");
        out = new ObjectOutputStream(stnOutStream);
        out.writeObject(stringToNode);
        stnOutStream.close();
    }

    void loadState() throws IOException, ClassNotFoundException {
        var pmInStream = new FileInputStream("assets/pm.ser");
        var in = new ObjectInputStream(pmInStream);
        predictionMap = (Map<String, Set<WordNode>>) in.readObject();
        pmInStream.close();

        var stnInStream = new FileInputStream("assets/stn.ser");
        in = new ObjectInputStream(stnInStream);
        stringToNode = (Map<Integer, WordNode>) in.readObject();
        stnInStream.close();
    }

    List<String> getAvailableWords(String firstWord, String secondWord) {
        topAvailableWords = new ArrayList<>();

        if (predictionMap.containsKey(firstWord)) {
            topAvailableWords = predictionMap.get(firstWord).stream().map(WordNode::getWord).filter(word -> word.startsWith(secondWord)).toList();
        } else {
            topAvailableWords = predictionMap.keySet().stream().filter(word -> word.startsWith(secondWord)).toList();
        }
        return topAvailableWords;
    }

    List<String> getAvailableWords() {
        return topAvailableWords;
    }

    List<String> getNextWords(String secondWord) {
        topAvailableWords = new ArrayList<>();
        if (predictionMap.containsKey(secondWord)) {
            var it = predictionMap.get(secondWord).iterator();
            for (int i = 0; i < 3 && it.hasNext(); i++) {
                topAvailableWords.add(it.next().getWord());
            }
        }
        return topAvailableWords;
    }
}
