/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework 6. IgnoreCase class
 * @author Junrong Huang
 */

import java.util.Comparator;

/**
 * Sorts words by case insensitive alphabetical order.
 * If this comparator is passed into buildIndex method,
 * then all of the words need to be converted into lower case,
 * and added into the BST
 */
public class IgnoreCase implements Comparator<Word> {
    @Override
    public int compare(Word w1, Word w2) {
        return w1.getWord().compareToIgnoreCase(w2.getWord());
    }
}
