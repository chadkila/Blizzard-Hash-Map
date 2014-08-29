import java.util.*;
import java.util.Map.*;

/**
 * 
 */

/**
 * This class implements a hash table, which uses MPQ hashing algorithm. This
 * version only accpets String as a key and any object as
 * 
 * @author yu
 * @see Map
 * @see HashMap
 */
public class BHashTable<K, V> {

	// hash table data
	private Entry<K, V>[] table;

	// total entries in the hash table
	private int count;

	/**
	 * 
	 */
	public BHashTable() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * return number of keys in hash table
	 * 
	 * @return number of keys
	 */
	public int size() {
		return count;
	}

	/**
	 * Tests if this hashtable maps no keys to values.
	 * 
	 * @return hash table is empty or not
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	public V get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(K key, V value) {
		if (value == null) {
			throw new NullPointerException();
		}
		return null;
	}

	public V remove(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	private long hash(String key) {
		long seed1 = 0x7FED7FED, seed2 = 0xEEEEEEEE;
		return 0;
	}

}
