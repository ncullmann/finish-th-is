package Engine;

import java.io.Serializable;
import java.util.Objects;

public class WordNode implements Comparable<WordNode>, Serializable {

    private final String WORD;
    private int frequency;

    public WordNode(String word) {
        this.WORD = word;
        frequency = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordNode wordNode = (WordNode) o;
        return frequency == wordNode.frequency &&
                WORD.equals(wordNode.WORD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WORD, frequency);
    }

    @Override
    public int compareTo(WordNode other) {
        // sort descending
        int i = -Integer.compare(frequency, other.getFrequency());
        if (WORD.equals(other.getWord())) {
            return 0;
        }
        // allow duplicate word frequencies
        return i == 0 ? -1 : i;
    }

    public String getWord() {
        return WORD;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incrementFrequency() {
        frequency++;
    }
}
