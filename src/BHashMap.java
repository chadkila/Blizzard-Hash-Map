import java.util.*;
import java.util.Map.*;

/**
 * 
 */

/**
 * This class implements a hash map, which uses MPQ hashing algorithm. This
 * version only accpets String as a key and any object as value.
 * 
 * @author yu
 * @see Map
 * @see HashMap
 */
public class BHashMap<K, V> {

	// hash table data
	private Entry<K, V>[] table;

	// total entries in the hash table
	private int size;

	// a randomized value applied to MPQ hash
	// value falls between 0 to 4
	private float hashSeed;

	// reference table for hashing
	private long cryptTable[];

	// the default initial capacity
	private final int DEFAULT_INITIAL_CAPACITY = 16;

	static final int MAXIMUM_CAPACITY = 1 << 30;

	// the default load factor
	private final float DEFAULT_LOAD_FACTOR = 0.75f;

	// The next size value at which to resize (capacity * load factor).
	private int threshold;

	// load factor for the hash table.
	private final float loadFactor;

	/**
	 * constructs an empty HashMap with default initial capacity and load factor
	 */
	public BHashMap() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.threshold = DEFAULT_INITIAL_CAPACITY;
		initTable();
	}

	/**
	 * return number of keys in hash table
	 * 
	 * @return number of keys
	 */
	public int size() {
		return size;
	}

	/**
	 * Tests if this hashtable maps no keys to values.
	 * 
	 * @return hash table is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	public V get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(K key, V value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		return null;
	}

	public V remove(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * constructs an empty HashMap and initialize hash seed and cryptTable
	 */
	private void initTable() {
		int capacity = DEFAULT_INITIAL_CAPACITY;
		this.table = new Entry[capacity];
		this.hashSeed = getRandom();
		initCryptTable();
	}

	private float getRandom() {
		Random ran = new Random();
		return ran.nextFloat() * 0.4f;
	}

	private long hash(String key) {
		long seed1 = 0x7FED7FED, seed2 = 0xEEEEEEEE;
		for (int i = 0; i < key.length(); i++) {
			int ch = key.charAt(i);
			seed1 = cryptTable[(int) (hashSeed * (1 << 8)) + ch]
					^ (seed1 + seed2);
			seed2 = (long) ch + seed1 + seed2 + (seed2 << 5) + 3L;
		}
		return 0;
	}

	/**
	 * constructs a encrypt table with 1280(0x500) elements.
	 */
	private void initCryptTable() {
		this.cryptTable = new long[0x500];
		long seed = 0x00100001;
		int index1 = 0, index2 = 0, i;
		for (index1 = 0; index1 < 0x100; index1++) {
			for (index2 = index1, i = 0; i < 5; i++, index2 += 0x100) {
				long temp1, temp2;
				seed = (seed * 125L + 3L) % 0x2AAAAB;
				temp1 = (seed & 0xFFFF) << 0x10;
				seed = (seed * 125L + 3L) % 0x2AAAAB;
				temp2 = (seed & 0xFFFF);
				cryptTable[index2] = (temp1 | temp2);
			}
		}
	}

}
