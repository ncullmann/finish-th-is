package Controller;

import Controller.WordNode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class WordPredictor {

    private Map<String, WordNode> nodes;
    private Map<String, TreeSet<WordNode>> wordMap;

    public WordPredictor() {
        wordMap = new HashMap<>();
        nodes = new HashMap<>();
    }

    public TreeSet<WordNode> getPredictedWord(String typedWord) {
        typedWord = filterWord(typedWord);
        if (wordMap.containsKey(typedWord)) {
            return wordMap.get(typedWord);
        }
        return new TreeSet<>();
    }

    public void putWord(String firstWord, String secondWord) {
        firstWord = filterWord(firstWord);
        secondWord = filterWord(secondWord);
        TreeSet<WordNode> set;
        if (wordMap.containsKey(firstWord)) {
            set = wordMap.get(firstWord);

            WordNode followingNode;
            if (nodes.containsKey(secondWord)) {
                followingNode = nodes.get(secondWord);
            }
            else {
                followingNode = new WordNode(secondWord);
                nodes.put(firstWord, followingNode);
            }
            if (set.contains(followingNode)) {
                nodes.remove(secondWord);
                set.remove(followingNode);
                followingNode.incrementFrequency();
                nodes.put(secondWord, followingNode);
                set.add(followingNode);
            }
            else {
                WordNode node = new WordNode(secondWord);
                nodes.put(secondWord, node);
                set.add(node);
            }
        }
        else {
            set = new TreeSet<>();
            WordNode node = new WordNode(secondWord);
            nodes.put(firstWord, node);
            set.add(node);
        }
        wordMap.put(firstWord, set);
    }

    private String filterWord(String word) {
        return word.toLowerCase().trim().replaceAll("\\p{Punct}", "");
    }

    public String toString() {
        return //"Nodes: " + nodes.toString() + "\n" +
                "WordMap: " + wordMap.toString() + "\n";
    }
}
