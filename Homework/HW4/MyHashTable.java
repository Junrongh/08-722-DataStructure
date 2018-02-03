/**
 * 08-722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * @author Junrong Huang
 */

public class MyHashTable implements MyHTInterface {

	private static final int DEFAULT_CAP = 10;
	private static final double DEFAULT_FACTOR = 0.5;
	private static final int ALPHABET_CAP = 27;
	private static final DataItem EMPITY = new DataItem(null);
	private DataItem[] hashArray;
	private int ArraySize;
	private int Size;
	private double Loadfactor;

	/**
	 * Define element of the array
	 * including string value and int frequency
	 */
	private static class DataItem {
		private String value;
		private int frequency;

		public DataItem(String newvalue) {
			this.value = newvalue;
			this.frequency = 1;
		}

		public void addFreq() {
			this.frequency++;
		}

		public void deletFreq() {
			this.frequency--;
		}

		public String getValue() {
			return this.value;
		}
	}

	/**
	 * Constructor without client input, default capacity = 10
	 */
	public MyHashTable() {
		this.Loadfactor = 0;
		this.Size = 0;
		this.ArraySize = DEFAULT_CAP;
		this.hashArray = new DataItem[this.ArraySize];
	}

	/**
	 * Constructor with client input
	 */
	public MyHashTable(int arraysize) {
		if (arraysize <= 0) {
			throw new IllegalArgumentException(
			    "Initial Capacity should be bigger than 0");
		}
		this.Loadfactor = 0;
		this.Size = 0;
		this.ArraySize = arraysize;
		this.hashArray = new DataItem[this.ArraySize];
	}

	/**
	 * Hash Function
	 * @param input: input string to be converted
	 * @return symbol: output int as the hash value to be stored
	 */
	private int hashFunc(String input) {
		int symbol = 0;
		for (int i = 0; i < input.length(); i++) {
			if ((input.charAt(i) - 'a' + 1) < 0) {
				return -1;
			} else {
				symbol = (ALPHABET_CAP * symbol +
				          (input.charAt(i) - 'a' + 1)) % this.ArraySize;
			}
		}
		return symbol;
	}

	/**
	 * Support Function(not required in HW)
	 * To test whether the input is a word
	 * @param text: the text to be added
	 */
	private boolean validWord(String text) {
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

	/**
	 * Returns the hash value of a String.
	 * @param value value for which the hash value should be calculated
	 * @return int hash value of a String
	 */
	@Override
	public int hashValue(String value) {
		int hashnumber = 0;
		if (validWord(value)) {
			return hashFunc(value);
		} else {
			return -1;
		}
	}

	/**
	 * Inserts a new String value (word).
	 * Frequency of each word to be stored too.
	 * @param value String value to add
	 */
	@Override
	public void insert(String value) {
		DataItem item = new DataItem(value);
		int hashval = hashValue(value);
		if (hashval != -1) {
			while (this.hashArray[hashval] != null
			        && this.hashArray[hashval] != EMPITY) {
				if (value.equals(this.hashArray[hashval].getValue())) {
					this.hashArray[hashval].addFreq();
					return;
				}
				hashval ++;
				hashval %= this.ArraySize;
			}
		}
		this.hashArray[hashval] = item;
		this.Size++;
		this.Loadfactor = (double) this.Size / (double) this.ArraySize;
		if (this.Loadfactor > DEFAULT_FACTOR) {
			rehash();
		}
	}

	/**
	 * Discover the next prime number of input
	 */
	private int nextprime(int num) {
		while (!isprime(num)) {
			num++;
		}
		return num;
	}

	/**
	 * Support function to discover the next prime number of input
	 */
	private boolean isprime(int num) {
		if (num == 2) {
			return true;
		} else if (num < 2) {
			return false;
		} else if (num % 2 == 0) {
			return false;
		} else {
			for (int i = 3; i < Math.sqrt(num); i++) {
				if (num % i == 0) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * Rehash function to allow the load factor to be less than 0.5
	 */
	private void rehash() {
		int newsize = nextprime(this.ArraySize * 2);
		System.out.println("Rehashing " + this.Size + " items, new size is "
		                   + newsize);
		MyHashTable newHashTable = new MyHashTable(newsize);
		for (int i = 0; i < this.hashArray.length; i++) {
			if (this.hashArray[i] != null && this.hashArray[i] != EMPITY) {
				while (this.hashArray[i].frequency > 0) {
					newHashTable.insert(this.hashArray[i].value);
					this.hashArray[i].deletFreq();
				}
			}
		}
		this.hashArray = newHashTable.hashArray;
		this.ArraySize = newHashTable.ArraySize;
		this.Size = newHashTable.Size;
		this.Loadfactor = newHashTable.Loadfactor;
	}


	/**
	 * Returns the size, number of items, of the table.
	 * @return the number of items in the table
	 */
	@Override
	public int size() {
		return this.Size;
	}

	/**
	 * Displays the values of the table.
	 * If an index is empty, it shows **
	 * If previously existed data item got deleted, then it should show #DEL#
	 */
	@Override
	public void display() {
		for (DataItem item : this.hashArray) {
			if (item == null) {
				System.out.print("** ");
			} else if (item.getValue() == null) {
				System.out.print("#DEL# ");
			} else {
				System.out.print(
				    "[" + item.value + "," + item.frequency + "] ");
			}
		}
		System.out.println();
	}

	/**
	 * Returns true if value is contained in the table.
	 * @param key String key value to search
	 * @return true if found, false if not found.
	 */
	@Override
	public boolean contains(String key) {
		if (key == null) {
			return false;
		} else {
			int hashval = hashValue(key);
			if (hashval != -1) {
				while (this.hashArray[hashval] != null) {
					if (key.equals(this.hashArray[hashval].value)) {
						return true;
					}
					hashval++;
					hashval %= this.ArraySize;
				}
			}
			return false;
		}
	}

	/**
	 * Returns the number of collisions in relation to insert and rehash.
	 * When rehashing process happens, the number of collisions should be properly updated.
	 *
	 * The definition of collision is "two different keys map to the same hash value."
	 * Be careful with the situation where you could overcount.
	 * Try to think as if you are using separate chaining.
	 * "How would you count the number of collisions?" when using separate chaining.
	 * @return number of collisions
	 */
	@Override
	public int numOfCollisions() {
		int num = 0;
		int hashvals[] = new int[this.ArraySize];
		int numofcollisions = 0;
		for (DataItem item : this.hashArray) {
			if (item != null && item != EMPITY) {
				hashvals[num] = hashFunc(item.value);
				num++;
			}
		}
		boolean[] compare = new boolean[num];
		for (int i = 0; i < num; i++) {
			if (compare[i] == false) {
				for (int j = i + 1; j < num; j++) {
					if (compare[j] == false) {
						if (hashvals[i] == hashvals[j]) {
							numofcollisions++;
							compare[i] = true;
							compare[j] = true;
						}
					}
				}
			}
		}
		return numofcollisions;
	}

	/**
	 * Returns the frequency of a key String.
	 * @param key string value to find its frequency
	 * @return frequency value if found. If not found, return 0
	 */
	@Override
	public int showFrequency(String key) {
		int hashval = hashValue(key);
		if (hashval != -1) {
			while (this.hashArray[hashval] != null) {
				if (key.equals(this.hashArray[hashval].value)) {
					return this.hashArray[hashval].frequency;
				}
				hashval++;
				hashval %= this.ArraySize;
			}
		}
		return 0;
	}

	/**
	 * Removes and returns removed value.
	 * @param key String to remove
	 * @return value that is removed. If not found, return null
	 */
	@Override
	public String remove(String key) {
		int hashval = hashValue(key);
		if (hashval != -1) {
			while (this.hashArray[hashval] != null) {
				if (key.equals(this.hashArray[hashval].value)) {
					String removed = this.hashArray[hashval].value;
					this.hashArray[hashval] = EMPITY;
					this.Size--;
					this.Loadfactor = (double) this.Size / (double) this.ArraySize;
					return removed;
				}
				hashval++;
				hashval %= this.ArraySize;
			}
		}
		return null;
	}



}