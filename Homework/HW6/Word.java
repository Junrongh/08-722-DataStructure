/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework 6. Word class
 * @author Junrong Huang
 */

import java.util.*;

public class Word implements Comparable<Word> {
    private String word;
    private Set<Integer> index;
    private int frequency;

    // TODO implement methods below.

    /**
     * Constructor
     * @param wd: the input word of String
     */
    public Word(String wd) {
        if (wd == null || wd.length() == 0 || !wd.matches("[a-zA-Z]+")) {
            return;
        }
        this.word = wd;
        this.index = new HashSet<Integer>();
        this.frequency = 1;
    }

    /**
     * Constructor
     * @param wd: the input word of String
     * @param indexSet: the set contains all index of this word
     * @param freq: the frequency wo this word
     */
    public Word(String wd, Set<Integer> indexSet, int freq) {
        if (wd == null || wd.length() == 0 || !wd.matches("[a-zA-Z]+")) {
            return;
        }
        this.word = wd;
        this.index = new HashSet<Integer>(indexSet);
        this.frequency = freq;
    }


    /**
     * Get the word in Word.
     * @return the word in the Word class.
     */
    public String getWord() {
        return this.word;
    }

   /**
     * Get the index Set of Word class.
     * @return the index Set of Word class.
     */
    public Set<Integer> getIndex() {
        return this.index;
    }

    /**
     * Get the frequency of Word class.
     * @return the frequency of Word class.
     */
    public int getFrequency() {
        return this.frequency;
    }

    /**
     * Set the word with str.
     * @param str the word to be used in the new node.
     */
    public void setWord(String str) {
        if (word == null || word.length() == 0 || !word.matches("[a-zA-Z]+")) {
            return;
        }
        this.word = str;
    }

    /**
     * Set the indexSet with new set.
     * @param set the set to replace the indexSet.
     */
    public void setIndex(Set<Integer> set) {
        this.index = new HashSet<Integer>(set);
    }

    /**
     * Set the frequency of the Word Class.
     * @param freq the frequency of the Word Class.
     */
    public void setFrequency(int freq) {
        int res = freq;
        this.frequency = res;
    }

    /**
     * @param line: the new line number for the word
     *              that should be added to the index
     */
    public void addToIndex(Integer line) {
        this.index.add(line);
    }

    /**
     * @return String, words and its descriptors
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String space = " ";
        sb.append(this.word);
        sb.append(space);
        sb.append(this.frequency);
        sb.append(space);
        sb.append("index : [");
        int i = 0;
        for (Integer num : index) {
            sb.append(num);
            if (i != index.size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * compareTo method
     * @param wd: word to be compared
     * @return comparison result
     */
    @Override
    public int compareTo(Word wd) {
        return this.word.compareTo(wd.word);
    }

}
