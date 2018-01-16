package eg.edu.alexu.csd.datastructure.stack.cs48;

import java.util.Scanner;

public class TryApplication {
	static Scanner input=new Scanner(System.in);
	public static void main(String[] args) {
		int flag=1;
		while(flag!=-1){
			System.out.println("please enter the expression you wanna evaluate");
			Applications trial=new Applications();
			String expression=input.nextLine();
			System.out.println(trial.evaluate(trial.infixToPostfix(expression)));
			System.out.println("please enter -1 to end progam or anything else to continue");
		    flag=input.nextInt();
		    input.nextLine();
		}
		System.out.println("End of program");
		
	}

}
