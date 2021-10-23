package engine;

import java.io.*;
import java.util.*;

class PredictionEngine {

    // uses the user's last typed word and sorts words that come after by frequency
    private Map<String, Set<WordNode>> predictionMap;
    // map the hash of both strings to a unique WordNode to be put in the firstWord's Set
    // that way, we may pull the WordNode out of the predictionMap easily.
    private Map<Integer, WordNode> stringToNode;
    // sorted list by probability of next word
    private List<String> topAvailableWords;
    private static final int SUGGESTION_COUNT = 3;

    private PredictionEngine() {
        predictionMap = new HashMap<>();
        stringToNode = new HashMap<>();
        topAvailableWords = new ArrayList<>();
    }

    private static class Holder {
        private static final PredictionEngine INSTANCE = new PredictionEngine();
    }

    static PredictionEngine getInstance() {
        return Holder.INSTANCE;
    }

    /* we can visualize the predictionMap like this:
     *        dog -> {[barks: 10], [eats: 6], ...}
     *         it -> {[is: 75], [was: 53], [has: 24], ...}
     *
     * and stringToNode:
     *        dogbarks.hashCode() -> [barks: 10]
     */
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
        // save predictionMap and stringToNode as user dictionaries
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

    // for partially typed words
    List<String> getAvailableWords(String firstWord, String secondWord) {
        topAvailableWords = predictionMap.containsKey(firstWord) ?
                // get it from the set, if available
                predictionMap.get(firstWord)
                             .stream()
                             .map(WordNode::getWord)
                             .filter(word -> word.startsWith(secondWord))
                             .limit(SUGGESTION_COUNT)
                             .toList()
                // or just pull words that start with the current letters typed
              : predictionMap.keySet()
                             .stream()
                             .filter(word -> word.startsWith(secondWord))
                             .limit(SUGGESTION_COUNT)
                             .toList();
        return topAvailableWords;
    }

    List<String> getAvailableWords() {
        return topAvailableWords;
    }

    // for completely typed words
    List<String> getNextWords(String secondWord) {
        topAvailableWords = predictionMap.containsKey(secondWord) ?
                predictionMap.get(secondWord).stream()
                             .map(WordNode::getWord)
                             .limit(SUGGESTION_COUNT)
                             .toList()
                : new ArrayList<>();
        return topAvailableWords;
    }

    static int getSuggestionCount() {
        return SUGGESTION_COUNT;
    }
}
