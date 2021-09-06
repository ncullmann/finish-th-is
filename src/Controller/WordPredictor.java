package Controller;

import java.util.*;
import java.util.stream.Collectors;

public class WordPredictor
{

    private Map<String, Set<WordNode>> wordMap;
    private Map<Map<String, String>, WordNode> nodes;

    public WordPredictor()
    {
        wordMap = new HashMap<>();
        nodes = new HashMap<>();
    }

    public List<String> availableWords(String typedWord) {
        String s = filterWord(typedWord);
        if (wordMap.containsKey(s)) {
            Iterator<WordNode> it = wordMap.get(s).iterator();
            List<String> topWords = new ArrayList<>();
            int i = 0;
            while (it.hasNext() && i < 3) {
                topWords.add(it.next().getWord());
                i++;
            }
            return topWords;
        }
        return wordMap.keySet().stream().filter(word -> word.startsWith(s)).toList();
    }

    public void putWord(String firstWord, String secondWord) {
        firstWord = filterWord(firstWord);
        secondWord = filterWord(secondWord);

        if (wordMap.containsKey(firstWord)) {
            Map<String, String> nodeKey = Map.of(firstWord, secondWord);
            WordNode node;
            if (nodes.containsKey(nodeKey)) {
                node = nodes.get(nodeKey);
                wordMap.get(firstWord).remove(node);
                node.incrementFrequency();
            } else {
                node = new WordNode(secondWord);
            }
            wordMap.get(firstWord).add(node);
            nodes.put(Map.of(firstWord, secondWord), node);
        } else {
            WordNode node = new WordNode(secondWord);
            TreeSet<WordNode> set = new TreeSet<>();
            set.add(node);
            wordMap.put(firstWord, set);
            nodes.put(Map.of(firstWord, secondWord), node);
        }
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
