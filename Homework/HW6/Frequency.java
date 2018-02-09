/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework 6. IgnoreCase class
 * @author Junrong Huang
 */

import java.util.Comparator;

/**
 * Sorts words according to their frequencies
 * Word with highest frequency comes first
 */
public class Frequency implements Comparator<Word> {
    @Override
    public int compare(Word w1, Word w2) {
        return w2.getFrequency() - w1.getFrequency();
    }
}
