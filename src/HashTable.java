
/**
* HashTable.java
* @author
* @author
* CIS 22C, Lab 5
*/
import java.util.ArrayList;

public class HashTable<T> {

    private int numElements;
    private ArrayList<LinkedList<T>> Table;

    /**
     * Constructor for the HashTable
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     *
     * @param size the table size
     */
    public HashTable(int size) {
        this.Table = new ArrayList<>();
        this.numElements = 0;

        for (int i = 0; i < size; i++) {
            this.Table.add(new LinkedList<>());
        }

    }

    /**
     * Constructor for HashTable class
     * Inserts the contents of the given array
     * into the Table at the appropriate indices
     *
     * @param array an array of elements to insert
     * @param size  the size of the Table
     */
    public HashTable(T[] array, int size) {
        if (array == null) {
            return;
        }
        this.Table = new ArrayList<>();
        this.numElements = 0;

        for (int i = 0; i < size; i++) { // size of table != length of array
            this.Table.add(new LinkedList<>()); // creating bucckes for the hash table
        }
        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }

    }

    /** Accessors */

    /**
     * returns the hash value in the Table
     * for a given Object
     *
     * @param t the Object
     * @return the index in the Table
     */
    private int hash(T t) {
        return Math.abs(t.hashCode()) % Table.size();
    }

    /**
     * Counts the number of elements at this index
     *
     * @param index the index in the Table
     * @precondition index > Table.size - 1 || index < 0
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException when
     *                                   the precondition is violated
     */
    public int countBucket(int index) throws IndexOutOfBoundsException {
        if (index > this.Table.size() || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index value!");
        }

        return this.Table.get(index).getLength(); // お茶を飲みますか？
        // Return's the length of the table's index (all LinkedList functions)
    }// バババカ

    /**
     * Determines total number of elements in the Table
     *
     * @return total number of elements
     */
    public int getNumElements() {
        return this.numElements;
    }

    /**
     * Accesses a specified element in the Table
     *
     * @param t the element to locate
     * @return the bucket number where the element
     *         is located or -1 if it is not found
     * @precondition t != null
     * @throws NullPointerException when the
     *                              precondition is violated
     */
    public int find(T t) throws NullPointerException {
        if (t == null) {
            throw new NullPointerException("BRUH");
        }

        // hash it, and then check if it's in that linked list of value

        LinkedList<T> bucket = Table.get(this.hash(t));
        if (bucket.findIndex(t) != -1) {
            return this.hash(t); // the bucket's index value is represented by this.hash(t)
        }
        return -1;
    }

    /**
     * Determines whether a specified element is in
     * the Table
     *
     * @param t the element to locate
     * @return whether the element is in the Table
     * @precondition n != null
     * @throws NullPointerException when the
     *                              precondition is violated
     */
    public boolean contains(T t) throws NullPointerException {
        if (t == null) {
            throw new NullPointerException("BRUH");
        }

        // hash it, and then check if it's in that linked list of value

        LinkedList<T> bucket = Table.get(this.hash(t));
        if (bucket.findIndex(t) != -1) {
            return true; // the bucket's index value is represented by this.hash(t)
        }
        return false;
    }

    /** Mutators */

    /**
     * Inserts a new element in the Table
     * at the end of the chain of the
     * correct bucket
     *
     * @param t the element to insert
     * @precondition t != null
     * @throws NullPointerException when the
     *                              precondition is violated
     */
    public void add(T t) throws NullPointerException {
        if (t == null) {
            throw new NullPointerException("BRUH");
        }
        System.out.println("Bucket " + this.hash(t) + " length: " + Table.get(hash(t)).getLength());
        Table.get(hash(t)).addLast(t);
        System.out.println("Bucket " + this.hash(t) + " length: " + Table.get(hash(t)).getLength());
        System.out.println(t.toString() + ": " + hash(t));
        this.numElements++;
    }

    /**
     * Removes the given element from the Table
     *
     * @param t the element to remove
     * @precondition t != null
     * @return whether t exists and was removed from the Table
     * @throws NullPointerException when the precondition
     *                              is violated
     */
    public boolean delete(T t) throws NullPointerException {
        if (t == null) {
            throw new NullPointerException("BRUH");
        }
        if (!this.contains(t)) {
            return false;
        } else {
            LinkedList<T> bucket = Table.get(this.hash(t));
            bucket.positionIterator();
            bucket.advanceIteratorToIndex(bucket.findIndex(t));
            System.out.println("Bucket " + this.hash(t) + " length: " + bucket.getLength());
            bucket.removeIterator();
            System.out.println("Bucket " + this.hash(t) + " length: " + bucket.getLength());
            numElements--;
            return true;
        }
    }

    /**
     * Resets the hash table back to the
     * empty state, as if the one argument
     * constructor has just been called.
     */
    public void clear() {
        int size = this.Table.size();
        this.Table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.Table.add(new LinkedList<>());
        }
        numElements = 0;
    }

    /** Additional Methods */

    /**
     * Computes the load factor
     *
     * @return the load factor
     */
    public double getLoadFactor() {
        return (double) numElements / (double) Table.size();
    }

    /**
     * Creates a String of all elements at a given bucket
     *
     * @param bucket the index in the Table
     * @return a String of elements, separated by spaces
     *         with a new line character at the end
     */
    public String bucketToString(int bucket) {
        return Table.get(bucket).toString();
    }

    /**
     * Creates a String of the bucket number
     * followed by a colon followed by the first
     * element at each bucket followed
     * by a new line
     * For empty buckets, add the bucket number
     * followed by a colon followed by empty
     *
     * @return a String of all first elements
     *         at each bucket
     */
    public String rowToString() {

        String whatever = "";
        for (int index = 0; index < this.Table.size(); index++) {
            whatever += "Bucket " + index + ": ";
            if (Table.get(index).getLength() != 0) {
                whatever += Table.get(index).getFirst().toString() + "\n";
            } else {
                whatever += "empty\n";
            }
        }
        return whatever;
    }

    /**
     * Starting at the 0th bucket, and continuing
     * in order until the last bucket,concatenates all
     * elements at all buckets into one String, with
     * a new line between buckets and one more new line at the
     * end of the entire String
     */
    @Override
    public String toString() {

        String whatever = "";
        for (int index = 0; index < this.Table.size(); index++) {
            if (Table.get(index).getLength() != 0) {
                whatever += Table.get(index).toString();
            }
        }
        return whatever + "\n";
    }
}
