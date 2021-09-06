package Controller;

import java.util.Objects;

public class WordNode implements Comparable<WordNode>
{
    private String word;
    private int frequency;

    public WordNode(String word)
    {
        this.word = word;
        frequency = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordNode wordNode = (WordNode) o;
        return frequency == wordNode.frequency &&
                word.equals(wordNode.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, frequency);
    }

    @Override
    public int compareTo(WordNode other)
    {
        // sort descending
        int i = -Integer.compare(frequency, other.getFrequency());
        if (word.equals(other.getWord())) {
            return 0;
        }
        // allow duplicate word frequencies
        return i == 0 ? -1 : i;
    }

    @Override
    public String toString() { return word + ": " + frequency; }

    public String getWord() { return word; }
    public int getFrequency() { return frequency; }
    public void incrementFrequency() { frequency++; }
}
