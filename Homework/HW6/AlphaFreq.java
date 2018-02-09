/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework 6. AlphaFreq class
 * @author Junrong Huang
 */

import java.util.*;

/**
 * Sorts words according to alphabets first
 * adn if there is a tie, the words are sorted by their frequencies ascendingly
 */
public class AlphaFreq implements Comparator<Word> {
    @Override
    public int compare(Word w1, Word w2) {
        int result = w1.getWord().compareTo(w2.getWord());
        if (result != 0) {
            return result;
        }else {
            return w1.getFrequency() - w2.getFrequency();
        }
    }
}
