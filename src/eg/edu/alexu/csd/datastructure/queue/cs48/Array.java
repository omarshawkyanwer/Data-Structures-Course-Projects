package eg.edu.alexu.csd.datastructure.queue.cs48;
import eg.edu.alexu.csd.datastructure.queue.IArrayBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;
public class Array implements IQueue ,IArrayBased{
	Object[] myQueue;
	private int front;
	private int rear;
	private int size;
	public Array(int n) {
		// TODO Auto-generated constructor stub
		myQueue=new Object[n];	
		front=rear=-1;
		size=0;
		}
	
	@Override
	public void enqueue(Object item) {
		// TODO Auto-generated method stub
		if( (rear+1)%myQueue.length ==front){
			throw new RuntimeException();
		}
		else if(isEmpty()){
			rear=front=0;
		}
		else {
			rear=(rear+1)%myQueue.length;
		}
		myQueue[rear]=item;
		size++;
	}
	@Override
	public Object dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty())throw new RuntimeException();
		Object temp=myQueue[front];
	    if(front==rear){
			front =rear=-1;
			size--;
			return temp;
		}
		else {
			front=(front+1)%myQueue.length;
		}
	    size--;
		return temp;
	}
	@Override
	public boolean isEmpty() {
		return (front== -1 && rear == -1);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}

