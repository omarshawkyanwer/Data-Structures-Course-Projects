package eg.edu.alexu.csd.datastructure.stack.cs48;

import eg.edu.alexu.csd.datastructure.stack.IStack;

public class MyStack implements IStack {
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
	int bla;
	
	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub
		index=size-index;
		if (index < 0 || index > size || element==null)
			throw new RuntimeException();
		if (index == size) {
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
	public Object pop() {
		// TODO Auto-generated method stub
		if(size==0)throw new RuntimeException();
		Object val=head.value;
		head=head.next;
		size--;
		return val;
	}

	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		if(size==0)throw new RuntimeException();
		Object val=head.value;
		return val;
	}
	
	@Override
	public void push(Object element) {
		if(element==null)throw new RuntimeException();
		Node newNode=new Node(element);
		size++;
		newNode.next=head;
		head=newNode;
	}

	@Override
	public boolean isEmpty() {
		if(size==0)return true;
		return false;
	}

	@Override
	public int size() {	
		return this.size;
	}
	
}
