/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework 6. Index class
 * @author Junrong Huang
 */

import java.util.*;
import java.io.*;
public class Index {


    /**
     * Simple testing method
     */
    public static void main(String[] args) throws IOException {
        String inputFile = "test.txt";
        Index index = new Index();
        BST<Word> tree1 = index.buildIndex(inputFile);
        System.out.println("the height is " + tree1.getHeight());
        System.out.println("the number of nodes is " + tree1.getNumberOfNodes());
        System.out.println();

        System.out.println("tree sorted by alpha");
        System.out.println(index.sortByAlpha(tree1));
        System.out.println();
    }


    /**
     * Build an index with a given file
     * @param fileName: string file name
     */
    public BST<Word> buildIndex(String fileName) {
        return buildIndex(fileName, null);
    }

    /**
     * Build an index with a given file and a comparator
     * @param fileName: string file name
     * @param comparator: a comparator used for object Word
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> bst = new BST<Word>(comparator);

        if (fileName == null) {
            return null;
        }

        Scanner scanner = null;
        int lineN = 0;
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineN ++;
                String[] wordinLine = line.split("\\W");
                for (String words : wordinLine) {
                    if (isValid(words)) {
                        if (comparator instanceof IgnoreCase) {
                            words = words.toLowerCase();
                        }
                        Word wordnode = new Word(words);
                        if (bst.search(wordnode) != null) {
                            bst.search(wordnode).setFrequency(
                                bst.search(wordnode).getFrequency() + 1);
                        } else {
                            wordnode.addToIndex(lineN);
                            bst.insert(wordnode);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return bst;
    }

    /**
     * Build an index with a given file and a comparator
     * @param list: ArrayList of object Word
     * @param comparator: a comparator used for object Word
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        if (list == null) {
            return null;
        }
        BST<Word> bst = new BST<Word>(comparator);
        if (comparator instanceof IgnoreCase) {
            for (Word words : list) {
                bst.insert(new Word(words.getWord().toLowerCase(),
                                    words.getIndex(), words.getFrequency()));
            }
        } else {
            for (Word words : list) {
                bst.insert(words);
            }
        }
        return bst;
    }


    /**
     * Sort the BST by AlphaFreq.
     * @param tree the BST tree.
     * @return the list of words sorted by Alpha.
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        /*
         * Even though there should be no ties with regard to words in BST,
         * in the spirit of using what you wrote,
         * use AlphaFreq comparator in this method.
         */
        ArrayList<Word> list = new ArrayList<Word>();
        if (tree == null) {
            return list;
        }
        for (Word words : tree) {
            list.add(words);
        }
        Collections.sort(list, new AlphaFreq());
        return list;
    }

    /**
     * Sort the BST by Frequency.
     * @param tree the BST tree.
     * @return the list of words sorted by Frequency.
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> list = new ArrayList<Word>();
        if (tree == null) {
            return list;
        }
        for (Word words : tree) {
            list.add(words);
        }
        Collections.sort(list, new Frequency());
        return list;
    }

    /**
     * Get a list of words with highest frequency.
     * @param tree the BST tree.
     * @return the list of words with highest frequency.
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> list = sortByFrequency(tree);
        if (tree == null) {
            return list;
        }
        ArrayList<Word> res = new ArrayList<Word>();
        int i = 0;
        while (i < list.size()) {
            res.add(list.get(i));
            i++;
            if (i >= list.size()) {
                break;
            }
            if (list.get(i).getFrequency() < list.get(i - 1).getFrequency()) {
                break;
            }
        }
        return res;
    }

    /**
     * Valid an input is a word or not
     * @param word: string input to be validated
     */
    private boolean isValid(String word) {
        if (word == null) {
            return false;
        }
        if (!word.matches("[a-zA-Z]+")) {
            return false;
        }
        return true;
    }
}
