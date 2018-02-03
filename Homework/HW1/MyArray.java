/**
 * 08-722 Data Structures for Applications Programmers.
 *
 * HW1 Collecion
 *
 * @author Junrong Huang
 */

import java.util.Arrays;

public class MyArray {
	/**
	 * The datastructure to be sorted
	 */
	private String[] thisArray;

	/**
	 * The effective size of the data structure
	 */
	private int size;

	/**
	 * The capacity of the data structure
	 */
	private int capacity;

	/**
	 * Constructor
	 * @param initialCapacity: the default capacity
	 */
	public MyArray(int initialCapacity) {
		this.thisArray = new String[initialCapacity];
		this.size = 0;
		this.capacity = initialCapacity;
	}

	/**
	 * Function
	 * Add an element into thisArray.
	 * Double thisArray's capacity if necessary
	 *
	 * @param word: the text to be added
	 */
	public void add(String text) {
		if (validWord(text)) {
			if (this.size < this.capacity) {
				this.thisArray[this.size++] = text;
				return;
			} else if (this.capacity == 0) {
				this.capacity = 1;
			} else {
				this.capacity = 2 * this.capacity;
			}
			String[] newArray = new String[this.capacity];
			System.arraycopy(this.thisArray, 0, newArray, 0, this.size);
			newArray[this.size++] = text;
			this.thisArray = newArray;
			return;
		}
	}


	/**
	 * Function
	 * Search whether the keyword is in the data
	 */
	public boolean search(String key) {
		for (int i = 0; i<this.size; i++){
			if (this.thisArray[i].equals(key)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Function
	 * Get the effectice size of the data structure
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Function
	 * Get the maximum capacity of the data structure
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Function
	 * Display the data in one array
	 */
	public void display() {	
		for (String word : this.thisArray) {
			if (word != null)
				System.out.print(word + ' ');
		}
		System.out.println();
		return;
		
	}

	/**
	 * Function
	 * Remove dupilicated words in the data structure
	 */
	public void removeDups() {
		String [] newArray = new String[this.capacity];
		int index = 0;
		for (int i = 0; i < this.size; i++){
			boolean dupilicate = false;
			for (int j = 0; j < i; j ++){
				if (this.thisArray[i].equals(this.thisArray[j])){
					dupilicate = true;
					break;
				}
			}
			if (!dupilicate){
				newArray[index++] = this.thisArray[i];
			}
		}
		this.thisArray = newArray;
		this.size = index;
	}

	/**
	 * Support Function(not required in HW)
	 * To test whether the input is a word
	 * @param text: the text to be added
	 */
	public boolean validWord(String text) {
		if (text == null) {
			return false;
		} else if ((text.trim()).length() == 0) {
			return false;
		} else {
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
					continue;
				} else if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}