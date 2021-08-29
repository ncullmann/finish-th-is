package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WordPredictor
{

    private Map<String, WordNode> nodes;
    private Map<String, TreeSet<WordNode>> wordMap;

    public WordPredictor()
    {
        wordMap = new HashMap<>();
        nodes = new HashMap<>();
    }

    public TreeSet<WordNode> getPredictedWords(String typedWord)
    {
        typedWord = filterWord(typedWord);
        if (wordMap.containsKey(typedWord)) {
            return wordMap.get(typedWord);
        }
        return new TreeSet<>();
    }

    public List<String> availableWords(String partialWord) {
        return nodes.keySet().stream().filter(word -> word.contains(partialWord)).toList();
    }

    public void putWord(String firstWord, String secondWord)
    {
        firstWord = filterWord(firstWord);
        secondWord = filterWord(secondWord);
        TreeSet<WordNode> set;

        if (wordMap.containsKey(firstWord)) {
            WordNode followingNode;
            if (nodes.containsKey(secondWord)) {
                followingNode = nodes.get(secondWord);
            }
            else {
                followingNode = new WordNode(secondWord);
                nodes.put(firstWord, followingNode);
            }

            set = wordMap.get(firstWord);
            if (set.contains(followingNode)) {
                nodes.remove(secondWord);
                set.remove(followingNode);
                followingNode.incrementFrequency();
                nodes.put(secondWord, followingNode);
                set.add(followingNode);
            }
            else {
                WordNode newNode = new WordNode(secondWord);
                nodes.put(secondWord, newNode);
                set.add(newNode);
            }
        }
        else {
            set = new TreeSet<>();
            WordNode newNode = new WordNode(secondWord);
            nodes.put(firstWord, newNode);
            set.add(newNode);
        }

        wordMap.put(firstWord, set);
    }

    private String filterWord(String word)
    {
        return word.toLowerCase().trim().replaceAll("\\p{Punct}", "");
    }

    public String toString()
    {
        return //"Nodes: " + nodes.toString() + "\n" +
                "WordMap: " + wordMap.toString() + "\n";
    }
}
