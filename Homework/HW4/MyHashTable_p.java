/**
 *********************************************************
 * it is said that there should have some words but I didn't know what can I
 * write that just said happy new year swag =。=
 * 
 * @author panpangu
 *********************************************************
 */

public class MyHashTable implements MyHTInterface {

	public static final int DEFAULT_INITIAL_CAPCITY = 10;
	public static final double DEFAULT_LOAD_FACTOR = 0.5;
	public static final int ALPHABET_NUM = 27;// including the blank as 0
	private static final DataItem NONE = new DataItem(null);// deleted item key is -1

	private DataItem[] hashArray;
	private int ArraySize;
	private int size;
	private double loadfactor;

	private static class DataItem {
		private String value;
		private int frequency;

		public DataItem(String newValue) {
			value = newValue;
			frequency = 1;
		}

		// if there have the same value,increment the frequency by 1;
		public void incrementFreq() {
			frequency++;
		}

		public void deleteFreq() {
			frequency--;
		}

		public String getkey() {
			return value;
		}
	}

	public MyHashTable() {
		loadfactor = 0;
		size = 0;
		ArraySize = DEFAULT_INITIAL_CAPCITY;
		hashArray = new DataItem[ArraySize];
	}

	public MyHashTable(int newArraySize) {
		if (newArraySize <= 0) {
			throw new java.lang.IllegalArgumentException("the initial capcity should be bigger than 0");
		}
		loadfactor = 0;
		size = 0;
		ArraySize = newArraySize;
		hashArray = new DataItem[newArraySize];
	}
	
	 /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
	public void insert(String value) {
		DataItem item = new DataItem(value);
		int hashval = hashValue(value);
		if(hashval == -1)
			return;
		while (hashArray[hashval] != null && hashArray[hashval].getkey() != null) {

			if (value.equals(hashArray[hashval].value)) {
				hashArray[hashval].incrementFreq();
				return;
			} else {
				hashval++;// go to next cell
				hashval %= ArraySize;// wrap around if necessary

			}
		}

		hashArray[hashval] = item;
		size++;
		loadfactor = (double) size / (double) ArraySize;

		if (loadfactor > 0.5) { // ensure the load factor stays in 0.5
			rehash();
		}
	}

	private void rehash() {
		
		if(loadfactor > DEFAULT_LOAD_FACTOR) {
			int newSize = nextPrime(ArraySize * 2);
			System.out.println("After rehashing " + size + " the new hashtable size is " + newSize);
			MyHashTable newhashtable = new MyHashTable(newSize);
			for(int i = 0; i < hashArray.length ;i ++) {
				if(hashArray[i] != null && hashArray[i].getkey() != "#DEL#") {
					while(hashArray[i].frequency > 0) {
						newhashtable.insert(hashArray[i].value);
						hashArray[i].deleteFreq();
					}
				}
			}
			hashArray = newhashtable.hashArray;
			ArraySize = newhashtable.ArraySize;
			size = newhashtable.size;
			loadfactor = newhashtable.loadfactor;
			
		}
//		int newSize = nextPrime(ArraySize * 2);
//		int tempSize = ArraySize;
//		ArraySize = newSize;
//		DataItem[] tmpHashArray = hashArray;
//		hashArray = new DataItem[ArraySize];
//		int count = 0;
//		MyHashTable newhashtable = new MyHashTable(newSize);
//
//		if (loadfactor > DEFAULT_LOAD_FACTOR) {
//			for (int i = 0; i < tempSize; i++) {
//				if (tmpHashArray[i] != null && tmpHashArray[i].getkey() != "#DEL#") {
//					int hashVal = hashFunc(tmpHashArray[i].getkey());
//					while (hashArray[hashVal] != null) {
//						hashVal++;
//						hashVal %= newSize;
//					}
//					hashArray[hashVal] = tmpHashArray[i];
//					count++;
//				}
//			}
//			
//			System.out.println("After rehashing " + count + " the new hashtable size is " + newSize);
//		}
	}

	/**
	 * Returns the size, number of items, of the table.
	 * 
	 * @return the number of items in the table
	 */
	@Override
	public int size() {
		int count = 0;
		for (int i = 0; i < ArraySize; i++) {
			if (hashArray[i] != null && hashArray[i].getkey() != null) {
				count++;
			}
		}
		return count;
//		return size;
	}

	/**
	 * Displays the values of the table. If an index is empty, it shows ** If
	 * previously existed data item got deleted, then it should show #DEL#
	 */
	@Override
	public void display() {
		for (int i = 0; i < ArraySize; i++) {
			if (hashArray[i] == null) {
				System.out.print("** ");
			} else if (hashArray[i].getkey() == null) {
				System.out.print(" #DEL# ");
			} else if (hashArray[i].value != null) {
				System.out.print(" [" + hashArray[i].value + "," + hashArray[i].frequency + "] ");
			}
		}
		System.out.println();
	}

	/**
	 * Returns true if value is contained in the table.
	 * 
	 * @param key
	 *            String key value to search
	 * @return true if found, false if not found.
	 */
	@Override
	public boolean contains(String key) {
		if (key == null)
			return false;
		int hashval = hashValue(key);
		if (hashval != -1) {
			while (hashArray[hashval] != null) {
				if (hashArray[hashval].value.equals(key)) {
					return true;
				}else {
				hashval++;
				hashval %= ArraySize;
				}
			}
		}
		return false;
	}

	/**
	 * Returns the number of collisions in relation to insert and rehash. When
	 * rehashing process happens, the number of collisions should be properly
	 * updated.
	 *
	 * The definition of collision is "two different keys map to the same hash
	 * value." Be careful with the situation where you could overcount. Try to think
	 * as if you are using separate chaining. "How would you count the number of
	 * collisions?" when using separate chaining.
	 * 
	 * @return number of collisions
	 */
	@Override
	public int numOfCollisions() {
		int num = 0;
		int hashVals[] = new int[ArraySize];
		int numOfCollisions = 0;
		for (DataItem temp : hashArray) {
			if (temp != null && temp != NONE)
				hashVals[num++] = hashFunc(temp.value);
		}
		boolean[] IJ = new boolean[num];
		for (int i = 0; i < num; i++) {
			if (IJ[i] == false) {
				for (int j = i + 1; j < num; j++) {
					if (IJ[j] == false) {
						if (hashVals[i] == hashVals[j]) {
							numOfCollisions++;
							IJ[i] = true;
							IJ[j] = true;
						}
					}

				}
			}
		}
		return numOfCollisions;
	}

	/**
	 * Returns the hash value of a String.
	 * 
	 * @param value
	 *            value for which the hash value should be calculated
	 * @return int hash value of a String
	 */
	@Override
	public int hashValue(String value) {
		if (isIlegalVal(value)) {
			return hashFunc(value);
		}
		return -1;
	}

	private int hashFunc(String tmp) {
		int hashNumber = 0;
		if(isIlegalVal(tmp)) {
			for (int i = 0; i < tmp.length(); i++) {
				int cur = tmp.charAt(i) - 'a' + 1;
				hashNumber = (hashNumber * ALPHABET_NUM + cur) % ArraySize;	
			}
			return hashNumber;
		}
		return -1;	
	}

	private int nextPrime(int min) {
		for (int j = min + 1; true; j++) {
			if (isPrime(j))
				return j;
		}
	}

	private boolean isPrime(int num) {
		for (int j = 2; (j * j < num); j++)
			if (num % j == 0)
				return false;
		return true;
	}

	private boolean isIlegalVal(String word) {
		String regex = "[a-z]+$";
		if (word == null) {
			return false;
		}
		if (word.trim().length() == 0) {
			return false;
		}
		if(!word.matches(regex)) {
				return false;
			}
		
		return true;
	}

	/**
	 * Returns the frequency of a key String.
	 * 
	 * @param key
	 *            string value to find its frequency
	 * @return frequency value if found. If not found, return 0
	 */
	@Override
	public int showFrequency(String key) {
		int hashValFQ = hashValue(key);
		if (hashValFQ != -1) {
			while (hashArray[hashValFQ] != null && hashArray[hashValFQ].getkey() != null) {
				if (key.equals(hashArray[hashValFQ].value)) {
					return hashArray[hashValFQ].frequency;
				} else {
					hashValFQ++;
					hashValFQ %= ArraySize;
				}
			}
		}
		return 0;
	}

	/**
	 * Removes and returns removed value.
	 * 
	 * @param key
	 *            String to remove
	 * @return value that is removed. If not found, return null
	 */
	@Override
	public String remove(String key) {
		int hashValRM = hashValue(key);
		if (hashArray[hashValRM] != null) {
			if (hashArray[hashValRM].value.equals(key)) {
				DataItem tmp = hashArray[hashValRM];
				hashArray[hashValRM] = NONE;
				size--;
				loadfactor = (double) size / (double) ArraySize;
				return tmp.value;
			}
			hashValRM++;
			hashValRM %= ArraySize;
		}
		return key;
	}

}
