/**
 *
 * 08-722 Data Structures for Application Programmers
 * HW 5 compare two documents and assess the imilarity
 *
 * @author Junrong Huang
 */

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Similarity {

	/**
	 * Use the map to store the frequency of each word in the data
	 */
	private Map<String, BigInteger> map;

	/**
	 * The number of lines in the data
	 */
	private int numOfLines;

	/**
	 * The number of words in the data
	 */
	private BigInteger numOfWords;

	/**
	 * Get number of lines in the data.
	 * @return number of lines
	 */
	public int numOfLines() {
		return this.numOfLines;
	}

	/**
	 * Get number of words in the data.
	 * @return number of words
	 */
	public BigInteger numOfWords() {
		return this.numOfWords;
	}

	/**
	 * Get number of words without duplicates in the data.
	 * @return number of words without duplicates
	 */
	public int numOfWordsNoDups() {
		return this.map.size();
	}


	/**
	 * Constructor, read statics of the input string
	 * @param String type of input string
	 */
	public Similarity(String string) {
		// initialization
		this.map = new HashMap<String, BigInteger>();
		this.numOfLines = 0;
		this.numOfWords = BigInteger.ZERO;
		// validate input string
		if (string == null || string.length() == 0) {
			return;
		}
		readString(string);
		// System.out.println(string);
	}

	private void readString(String string) {
		this.numOfLines = string.split("\n").length;
		// Str.trim(): delete spaces at start/end
		String[] includewords = string.trim().split("\\W");
		for (String word : includewords) {
			// validate word
			if (word.length() == 0 || !word.matches("[a-zA-Z]+")) {
				continue;
			}
			// add word to the map
			this.numOfWords = this.numOfWords.add(BigInteger.ONE);
			word = word.toLowerCase();
			if (this.map.containsKey(word)) {
				this.map.put(word, this.map.get(word).add(BigInteger.ONE));
			} else {
				this.map.put(word, BigInteger.ONE);
			}
		}
	}
	/**
	 * Constructor, read statics of the input file
	 * @param File type of input file
	 */
	public Similarity(File file) {
		// initialization
		this.map = new HashMap<String, BigInteger>();
		this.numOfLines = 0;
		this.numOfWords = BigInteger.ZERO;
		// validate input file
		if (file == null) {
			return;
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(file, "latin1");
			while (scanner.hasNextLine()) {
				String thisLine = scanner.nextLine();
				readString(thisLine);
			}
			this.numOfLines++;
		} catch (Exception error) {
			return;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	/**
	 * Compute the auclidean Norm of the map in the class.
	 * @return the auclidean Norm
	 */
	public double euclideanNorm() {
		if (this.map == null || this.map.size() == 0) {
			return 0d;
		}
		BigInteger res = BigInteger.ZERO;
		for (String key : this.map.keySet()) {
			BigInteger num = this.map.get(key);
			res = res.add(num.multiply(num));
		}
		return Math.sqrt(res.doubleValue());
	}

	/**
	 * Compute the auclidean Norm of the map in the class.
	 * @return the auclidean Norm
	 */
	public double anothereuclideanNorm(Map < String, BigInteger > anothermap) {
		if (anothermap == null || anothermap.size() == 0) {
			return 0d;
		}
		BigInteger res = BigInteger.ZERO;
		for (String key : anothermap.keySet()) {
			BigInteger num = anothermap.get(key);
			res = res.add(num.multiply(num));
		}
		return Math.sqrt(res.doubleValue());
	}

	/**
	 * Compute the dot product of the input map.
	 * @param map2 input map to be computed
	 * @return the dot product
	 */
	public double dotProduct(Map < String, BigInteger > anothermap) {
		if (anothermap == null || anothermap.size() == 0) {
			return 0d;
		}

		BigInteger res = BigInteger.ZERO;
		for (String key : this.map.keySet()) {
			if (!anothermap.containsKey(key)) {
				continue;
			}
			res = res.add(this.map.get(key).multiply(anothermap.get(key)));
		}
		return res.doubleValue();
	}


	/**
	 * Compute the distance of the input map.
	 * @param map2 input map to be computed
	 * @return the distance
	 */
	public double distance(Map < String, BigInteger > anothermap) {
		return Math.acos(dotProduct(anothermap) / (
		                     euclideanNorm() * anothereuclideanNorm(anothermap)));
	}


	/**
	 * Return the map in the class.
	 * @return the map in the class
	 */
	public Map<String, BigInteger> getMap() {
		return new HashMap<String, BigInteger>(this.map);
	}

}