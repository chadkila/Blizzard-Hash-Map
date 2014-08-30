import java.util.*;

/**
 * have to test 
 * (int) (hashSeed * (1 << 8))
 * (k = (String) e.key) == key || key.equals(k)
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
	private int cryptTable[];

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
	 * @return hash tableK is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	public V get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(String key, V value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		int hash = hash(key);
		int i = hash % table.length;
		for (Entry<K, V> e = table[i]; e != null; e = e.next) {
			String k;
			if (e.hash == hash
					&& ((k = (String) e.key) == key || key.equals(k))) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}
		// addentry
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

	/**
	 * generates a random value falls between from 0f to 4f
	 * 
	 * @return hash seed
	 */
	private float getRandom() {
		Random ran = new Random();
		return ran.nextFloat() * 4.f;
	}

	/**
	 * Uses MPQ hash algorithm to generate a long type variable as hashed value.
	 * This version only accepts String.
	 * 
	 * @param key
	 *            receive a string as key in hash map.
	 * @return returns a long type argument as hashed value
	 */
	private int hash(String key) {
		int seed1 = 0x7FED7FED, seed2 = 0xEEEEEEEE;
		for (int i = 0; i < key.length(); i++) {
			int ch = key.charAt(i);
			seed1 = cryptTable[(int) (hashSeed * (1 << 8)) + ch]
					^ (seed1 + seed2);
			seed2 = ch + seed1 + seed2 + (seed2 << 5) + 3;
		}
		return seed1;
	}

	/**
	 * constructs a encrypt table with 1280(0x500) elements.
	 */
	private void initCryptTable() {
		this.cryptTable = new int[0x500];
		int seed = 0x00100001;
		int index1 = 0, index2 = 0, i;
		for (index1 = 0; index1 < 0x100; index1++) {
			for (index2 = index1, i = 0; i < 5; i++, index2 += 0x100) {
				int temp1, temp2;
				seed = (seed * 125 + 3) % 0x2AAAAB;
				temp1 = (seed & 0xFFFF) << 0x10;
				seed = (seed * 125 + 3) % 0x2AAAAB;
				temp2 = (seed & 0xFFFF);
				cryptTable[index2] = (temp1 | temp2);
			}
		}
	}

	static class Entry<K, V> implements Map.Entry<K, V> {
		final K key;
		V value;
		Entry<K, V> next;
		long hash;

		/**
		 * Creates new entry.
		 */
		Entry(long h, K k, V v, Entry<K, V> n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return value;
		}

		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		public final boolean equals(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			Map.Entry e = (Map.Entry) o;
			Object k1 = getKey();
			Object k2 = e.getKey();
			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || (v1 != null && v1.equals(v2)))
					return true;
			}
			return false;
		}

		public final int hashCode() {
			return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
		}

		public final String toString() {
			return getKey() + "=" + getValue();
		}

	}

}
