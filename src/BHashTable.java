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

	// a randomized value applied to MPQ hash
	private int hashSeed;

	private long cryptTable[];

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
		for (int i = 0; i < key.length(); i++) {
			int ch = key.charAt(i);
			seed1 = cryptTable[(hashSeed << 8) + ch] ^ (seed1 + seed2);
			seed2 = ch + seed1 + seed2 + (seed2 << 5) + 3;
		}
		return 0;
	}

	private void initCryptTable() {
		this.cryptTable = new long[0x500];
		long seed = 0x00100001;
		int index1 = 0, index2 = 0, i;
		for (index1 = 0; index1 < 0x100; index1++) {
			for (index2 = index1, i = 0; i < 5; i++, index2 += 0x100) {
				long temp1, temp2;
				seed = (seed * 125 + 3) % 0x2AAAAB;
				temp1 = (seed & 0xFFFF) << 0x10;
				seed = (seed * 125 + 3) % 0x2AAAAB;
				temp2 = (seed & 0xFFFF);
				cryptTable[index2] = (temp1 | temp2);
			}
		}
	}

}
