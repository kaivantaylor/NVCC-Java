//---------------------------------------------------------------------------
// HMap.java                by Dale/Joyce/Weems                     Chapter 8
//
// Implements a map using an array-based hash table, linear probing collision
// resolution.
//
// The remove operation is not supported. Invoking it will result in the
// unchecked UnsupportedOperationException being thrown.
//
// A map provides (K = key, V = value) pairs, mapping the key onto the value.
// Keys are unique. Keys cannot be null.
//
// Methods throw IllegalArgumentException if passed a null key argument.
//
// Values can be null, so a null value returned by put or get does
// not necessarily mean that an entry did not exist.
//---------------------------------------------------------------------------
package Taylor_quiz;

import java.util.Iterator;

public class HMap<K, V>  implements MapInterface<K,V>
{
  protected MapEntry[] map;

  protected final int DEFCAP = 100;     // default capacity
  protected final double DEFLOAD = .75; // default load

  protected int origCap;  // original capacity
  protected int currCap;  // current capacity
  protected double load;
  private static int UNIQUE_HASHCODE = 23;

  protected int numPairs = 0;    // number of pairs in this map

  public HMap()
  {
    map =  new MapEntry[DEFCAP];
    origCap = DEFCAP;
    currCap = DEFCAP;
    load = DEFLOAD;
  }

  public HMap(int initCap, double initLoad)
  {
    map = new MapEntry[initCap];
    origCap = initCap;
    currCap = initCap;
    load = initLoad;
  }

  private void enlarge()
  // Increments the capacity of the map by an amount
  // equal to the original capacity.
  {
    // create a snapshot iterator of the map and save current size
    Iterator<MapEntry<K,V>> i = iterator();
    int count = numPairs;

    // create the larger array and reset variables
    map = new MapEntry[currCap + origCap];
    currCap = currCap + origCap;
    numPairs = 0;

    // put the contents of the current map into the larger array
    MapEntry entry;
    for (int n = 1; n <= count; n++)
    {
      entry = i.next();
      this.put((K)entry.getKey(), (V)entry.getValue());
    }
  }

  public V put(K k, V v) {

		int insertion_location = k.hashCode() % UNIQUE_HASHCODE;
		MapEntry currentloc = map[insertion_location];

		if (currentloc == null) {
			map[insertion_location] = new MapEntry(k, v);
			numPairs++;
			return null;
		}

		else {
			while (currentloc.nextEntry != null) {
				currentloc = currentloc.nextEntry;
			}
			currentloc.nextEntry = new MapEntry(k, v);
			numPairs++;
			return null;
		}
	}

	public V get(K k)
	// If an entry in this map with a key k exists then the value associated
	// with that entry is returned; otherwise null is returned.
	{
		int insertion_location = k.hashCode() % UNIQUE_HASHCODE;
		MapEntry currentloc = map[insertion_location];

		while (currentloc != null) {
			if (currentloc.getKey().equals(k)) {
				return (V) currentloc.getValue();
			}
			currentloc = currentloc.nextEntry;
		}
		
		return null;
	}

	public V remove(K k)

	{
		int insertion_location = k.hashCode() % UNIQUE_HASHCODE;
		MapEntry currentloc = map[insertion_location];

		if (currentloc == null) {
			map[insertion_location] = new MapEntry(k, null);
			numPairs--;
			return null;
		}

		else {
			while (currentloc.nextEntry != null) {
				currentloc = currentloc.nextEntry;
			}
			currentloc.nextEntry = new MapEntry(k, null);
			numPairs--;
			return null;
		}
  }

  public boolean contains(K k)
  // Returns true if an entry in this map with key k exists;
  // Returns false otherwise.
  {
	int insertion_location = k.hashCode() % UNIQUE_HASHCODE;
	MapEntry currentloc = map[insertion_location];
	
	while(currentloc != null) {
		if(currentloc.getKey().equals(k)) {
			return true;
		}
		currentloc = currentloc.nextEntry;
	}
	return false;
  }

  public boolean isEmpty()
  // Returns true if this map is empty; otherwise, returns false.
  {
    return (numPairs == 0);
  }

  public boolean isFull()
  // Returns true if this map is full; otherwise, returns false.
  {
    return false;  // An HMap is never full
  }

  public int size()
  // Returns the number of entries in this map.
  {
    return numPairs;
  }

  private class MapIterator implements Iterator<MapEntry<K,V>>
  // Provides a snapshot Iterator over this map.
  // Remove is not supported and throws UnsupportedOperationException.
  {
    int listSize = size();
    private MapEntry[] list = new MapEntry[listSize];
    private int previousPos = -1; // previous position returned from list

    public MapIterator()
    {
      int next = -1;
      for (int i = 0; i < listSize; i++)
      {
        next++;
        while (map[next] == null)
          next++;
        list[i] = map[next];
      }
    }

    public boolean hasNext()
    // Returns true if the iteration has more entries; otherwise returns false.
    {
      return (previousPos < (listSize - 1)) ;
    }

    public MapEntry<K,V> next()
    // Returns the next entry in the iteration.
    // Throws NoSuchElementException - if the iteration has no more entries
    {
      if (!hasNext())
        throw new IndexOutOfBoundsException("illegal invocation of next " +
                           " in HMap iterator.\n");
      previousPos++;
      return list[previousPos];
    }

    public void remove()
    // Throws UnsupportedOperationException.
    // Not supported. Removal from snapshot iteration is meaningless.
    {
      throw new UnsupportedOperationException("Unsupported remove attempted on "
                                            + "HMap iterator.\n");
    }
  }

  public Iterator<MapEntry<K,V>> iterator()
  // Returns a snapshot Iterator over this map.
  // Remove is not supported and throws UnsupportedOperationException.

  {
    return new MapIterator();
  }
}
