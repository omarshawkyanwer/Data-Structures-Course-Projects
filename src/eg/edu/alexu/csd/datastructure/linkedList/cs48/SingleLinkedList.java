package eg.edu.alexu.csd.datastructure.linkedList.cs48;
 
import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
 
public class SingleLinkedList implements ILinkedList {
 
	public class Node {
		private Object value;
		private Node next;
 
		public Node(Object value) {
			this.value = value;
			next = null;
		}
		public Object getValue(){
			return value;
		}
		public Node getNext(){
			return next;
		}
	}
 
	public Node head = null;
	private int size = 0;
 
	@Override
	public void add(int index, Object element) {
		if (index < 0 || index > size)
			throw new RuntimeException();
		if (index == size) {
			add(element);
			return;
		}
		if (index == 0) {
			Node newNode = new Node(element);
			newNode.next = head;
			head = newNode;
			size++;
			return;
		}
		int counter = 0;
		Node p = head;
		size++;
		while (counter != index - 1) {
			p = p.next;
			counter++;
		}
		Node newOne = new Node(element);
		newOne.next = p.next;
		p.next = newOne;
	}
 
	@Override
	public void add(Object element) {
		Node newOne = new Node(element);
		Node p = head;
		size++;
		if (p == null) {
			head = newOne;
			return;
		}
		while (p.next != null) {
			p = p.next;
		}
		p.next = newOne;
	}
 
	@Override
	public Object get(int index) {
		if (size == 0 || index < 0 || index >= size)
			throw new RuntimeException();
		int counter = 0;
		Node p = head;
		while (counter != index) {
			p = p.next;
			counter++;
		}
		return p.value;
	}
 
	@Override
	public void set(int index, Object element) {
		if (size == 0 || index < 0 || index >= size)
			throw new RuntimeException();
		int counter = 0;
		Node p = head;
		while (counter != index) {
			p = p.next;
			counter++;
		}
		p.value = element;
	}
 
	@Override
	public void clear() {
		size = 0;
		head = null;
	}
 
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
 
	@Override
	public void remove(int index) {
		if (isEmpty() || index >= size || index < 0)
			throw new RuntimeException();
 
		if (index == 0 && size == 1) {
			head = null;
			size = 0;
			return;
		} else if (index == 0) {
			head = head.next;
			size--;
			return;
		}
 
		if (index == size - 1) {
			Node temp = head;
			for (int i = 0; i < size - 2; i++)
				temp = temp.next;
			temp.next = null;
			size--;
			return;
		}
 
		Node temp = head;
		for (int i = 0; i < index - 1; i++)
			temp = temp.next;
		temp.next = temp.next.next;
		size--;
 
	}
 
	@Override
	public int size() {
		return size;
	}
 
	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		if (fromIndex > toIndex || !(fromIndex >= 0 && toIndex < size))
			throw new RuntimeException();
 
		SingleLinkedList subList = new SingleLinkedList();
		Node temp = head;
		for (int i = 0; i < fromIndex; i++)
			temp = temp.next;
		for (int i = 0; i <= toIndex - fromIndex; i++) {
			subList.add(temp.value);
			temp = temp.next;
		}
		return subList;
	}
 
	@Override
	public boolean contains(Object o) {
		Node temp = head;
		while (temp != null) {
			if (o.equals(temp.value))
				return true;
			temp = temp.next;
		}
		return false;
	}
 
}