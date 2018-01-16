package eg.edu.alexu.csd.datastructure.queue.cs48;
import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

public class LinkedBased implements IQueue,ILinkedBased {
	public class Node{
		Object data;
		Node next;
	}
	
	private Node front;
	private Node rear;
	private int size;
	public  LinkedBased(){
		front=rear=null;
		size=0;
	}
	
	
	@Override
	public void enqueue(Object item) {
		// TODO Auto-generated method stub
		Node temp=new Node();
		temp.data=item;
		size++;
		if(isEmpty()){
			rear=temp;
			front=temp;
			return;
		}
		rear.next=temp;
		rear=temp;	
	}
	
	@Override
	public Object dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty())throw new RuntimeException();
		Object val=front.data;
		if(rear==front){
			rear=null;
			front=null;
		}else{
			front=front.next;
		}
		size--;
		return val;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (front==null && rear==null);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	
}
