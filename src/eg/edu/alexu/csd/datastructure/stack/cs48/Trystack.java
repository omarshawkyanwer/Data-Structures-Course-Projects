package eg.edu.alexu.csd.datastructure.stack.cs48;
import java.util.Scanner;
public class Trystack {
	static MyStack trial=new MyStack();
	static Scanner reader=new Scanner(System.in);
	public static void first()
	{
		
		int num=reader.nextInt();
		trial.push(num);
		System.out.println("insertion done successfuly");
	}
	
	public static void second()
	{
		System.out.println("the number of element should be greater than 0 or there is an error");
		System.out.println(trial.pop());
	}
	public static void third()
	{
		System.out.println("the index of element should be greater than 0 & less than or equal "+trial.size());
		System.out.println("please enter the number");
		int num=reader.nextInt();
		System.out.println("please enter the index");
		int num2=reader.nextInt();
		trial.add(num2, num);
		System.out.println("insertion done successfuly");
	}
	
	public static void main(String[] args) {
		
		while (true )
		{
			System.out.println("please choose one of options");
			System.out.println("1-Push in the Stack");
			System.out.println("2-pop from the Stack");
			System.out.println("3- add to certain index");
			System.out.println("4-show the peek value");
			System.out.println("5-show the number of elements in the stack");
			System.out.println("6-Exit from program");
			char choice;
			char c = reader.next().charAt(0);
			try{
				switch(c){
					case '1':
								first();
								break;
					case '2':
								second();
								break;
						
					case '3':
								third();
								break;
					case '4':	
								System.out.println(trial.peek());
								break;
					case '5':	
								System.out.println(trial.size());
								break;
					case '6':
						return ;
					default :
								System.out.println("please enter an existing choice");
				
				}
			}catch (Exception e)
			{
				System.out.println("please choose a valid opeation");
			}
		
		}
	
		
	}
}
