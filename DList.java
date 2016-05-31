/**
 * This a doubly linked list implementation with convenience methods
 * @author buzzonetwo
 *
 * @param <T> the generic
 */
public class DList<T> {

	/**
	 * Length of the linked list
	 */
	private int n;
	/**
	 * Dummy Node that has null values
	 */
	private Node<T> dummy;
	/**
	 * Creates a Node that includes a reference to the next Node and the previous Node and holds data T
	 * @author buzzonetwo
	 * @param <T> the generic
	 */
	public class Node<T> {

		T data;
		Node<T> prev;
		Node<T> next;
		
		
	}	
	/**
	 * Creates a doubly linked list - starts with a length of zero and a dummy Node whose 
	 * data is null
	 */
	public DList() {
		dummy = new Node<T>();
		dummy.next = dummy;
		dummy.prev = dummy;
		n = 0;
	}
	
	/**
	 * Gets a Node at a position by cycling forwards from the beginning or backwards from the end
	 * @param i the integer position of the Node to get
	 * @return the Node at position i
	 */
	private Node<T> getNode(int i) {
		Node<T> temp = null;
		if (i < n / 2) {
			temp = dummy.next;
			for (int j = 0; j < i; j++) {
				temp = temp.next;
			}
		}
		else {
			temp = dummy;
			for (int j = n; j > i; j--) {
				temp = temp.prev;
			}
		}
		return temp;
	}
	/**
	 * Gets data from a specific position in the list
	 * @param i the integer position of the Node to get data from
	 * @return the data of the Node at position i
	 */
	public T get(int i) {
		return getNode(i).data;
	}
	
	/**
	 * Sets data at a specific position in the list
	 * @param i the integer position of the Node which data is to be changed
	 * @param x the data to change to 
	 * @return the data previously at position i
	 */
	public T set(int i, T x) {
		Node<T> temp = getNode(i);
		T y = temp.data;
		temp.data = x;
		return y;
	}
	/**
	 * Adds a Node with data before another Node in the list
	 * @param d the Node to put a new Node before
	 * @param x the data to be contained in that Node
	 * @return a reference to the Node that was inserted
	 */
	private Node<T> addBefore(Node<T> d, T x) {
		Node<T> temp = new Node<T>();
		temp.data = x;
		temp.prev = d.prev;
		temp.next = d;
		temp.next.prev = temp;
		temp.prev.next = temp;
		n++;
		return temp;
	}
	
	/**
	 * Adds a Node with data at a specific position in the list
	 * @param i the integer position of the place to add data
	 * @param x the data to be contained in that Node
	 */
	public void add(int i, T x) {
		addBefore(getNode(i), x);
	}
	/**
	 * Adds a Node with data at the end of the list
	 * @param x the data to be contained in that Node
	 */
	public void addEnd(T x) {
		add(n+1, x);
	}
	/**
	 * Removes a Node from the list
	 * @param w the Node to remove
	 */
	
	
	private void remove(Node<T> w) {
		w.prev.next = w.next;
		w.next.prev = w.prev;
		n--;
	}
	
	/**
	 * Removes a Node from the list with certain data
	 * @param dat the data of the Node to be removed
	 */
	public void remove(T dat) {
		Node<T> w = getNode(dat);
		w.prev.next = w.next;
		w.next.prev = w.prev;
		n--;
	}
	/**
	 * Removes a Node at a specific position in the list
	 * @param i the integer position of the Node to be removed
	 * @return the data of the removed Node
	 */
	public T remove(int i) {
		Node<T> w = getNode(i);
		remove(w);
		return w.data;
	}
	/**
	 * Returns the length of the DList
	 * @return the length of the DList
	 */
	public int getLength() {
		return n;
	}
	/**
	 * Sequentially looks for the Node with specified data
	 * @param indata the data of the Node to return
	 * @return the Node with data indata, null if no Node has data indata
	 */
	private Node<T> getNode(T indata) {
		Node<T> temp = getNode(1);
		Node<T> first = getNode(0);
		
		boolean trigger = false;
		if (first.data.equals(indata)) {
			trigger = true;
			temp = first;
		}
		else if (temp.data.equals(indata)) {
			trigger = true;
		}
		else {
		while ((temp != first)) {
			temp = temp.next;
			if (!(temp.data != null) || temp.data.equals(indata)) {
				trigger = true;
				break;
			}
			trigger = false;
		}
		}
		if (trigger) {
			return temp;
		}
		else {
			return null;
		}
	}
	
	@Override 
	public String toString() {
		String s = "";
		for (int i = 0; i < n; i++ ) {
			s += getNode(i).data.toString(); 
			s += ", ";
		}
		return s;
	}
	
}



