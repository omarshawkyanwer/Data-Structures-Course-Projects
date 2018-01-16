package eg.edu.alexu.csd.datastructure.linkedList.cs48;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import eg.edu.alexu.csd.datastructure.linkedList.cs48.PolySolver;
public class Main {
	static Scanner input = new Scanner(System.in);
	static PolySolver polyObject = new PolySolver();
	public static void main(String[] args) {
		while (true) {
			printOptions();
			int selection = input.nextInt();
 
			switch (selection) {
			case 1:
				setPoly();
				break;
			case 2:
				printPoly();
				break;
			case 3:
				addPoly();
				break;
			case 4:
				subtractPoly();
				break;
			case 5:
				multiplyPoly();
				break;
			case 6:
				evaluatePoly();
				break;
			case 7:
				clearPoly();
				break;
			case 8:
				return;
			default:
				System.out.println("Invalid input.\n");
			}
		}
	}
 
	public static void printOptions() {
		System.out.println("Please choose an action\n-----------------------");
		System.out.println("1- Set a polynomial variable");
		System.out.println("2- Print the value of a polynomial variable");
		System.out.println("3- Add two polynomials");
		System.out.println("4- Subtract two polynomials");
		System.out.println("5- Multiply two polynomials");
		System.out.println("6- Evaluate a polynomial at some point");
		System.out.println("7- Clear a polynomial variable");
		System.out.println("8- Exit");
		System.out.println("====================================================================");
	}
 
	public static void setPoly() {
		System.out.println("Insert the variable name : A, B or C");
		char choice = input.next().charAt(0);
		System.out.println("Insert the polynomial terms in the form:\n(coeff1 , exponent1 ), (coeff2 , exponent2 ), ...");
 
		ArrayList<Point> terms = new ArrayList<Point>();
 
		try {
			String inputString=input.nextLine();
			inputString=input.nextLine();
			inputString=inputString.replaceAll("[(),]", " ");
			Scanner strScan=new Scanner(inputString);
 
			while (strScan.hasNext()) {
				Point temp = new Point();
				temp.x=strScan.nextInt();
				temp.y=strScan.nextInt();
				terms.add(temp);
			}
			strScan.close();
 
			int termsArr[][]=new int[terms.size()][2];
			int i=0;
			for(Point x: terms){
				termsArr[i][0]=x.x;
				termsArr[i][1]=x.y;
				i++;
			}
 
			polyObject.setPolynomial(choice, termsArr);
			System.out.println("Polynomial " + choice + " is set.\n");
		} 
		catch (Exception e) {
			System.out.println("Invalid input.\n");
		}
 
	}
 
	public static void printPoly() {
		System.out.print("Insert the variable name: ");
		char choice = input.next().charAt(0);
 
		String temp = polyObject.print(choice);
		if (temp == null) {
			System.out.println("Invalid input.\n");
		} else
			System.out.println("Value in "+ choice + " : " + temp + "\n");
 
	}
 
	public static void addPoly() {
		System.out.print("Insert the operands separated be a space: ");
		char choice1 = input.next().charAt(0);
		char choice2 = input.next().charAt(0);
 
		try {
			polyObject.add(choice1, choice2);
			System.out.print("Result set in R: ");
			String temp = polyObject.print('R');
			System.out.println(temp+"\n");
		} catch (Exception e) {
			System.out.println("Invalid input.\n");
		}
	}
 
	public static void subtractPoly() {
		System.out.print("Insert the operands separated be a space: ");
		char choice1 = input.next().charAt(0);
		char choice2 = input.next().charAt(0);
 
		try {
			polyObject.subtract(choice1, choice2);
			System.out.print("Result set in R: ");
			String temp = polyObject.print('R');
			System.out.println(temp+"\n");
		} catch (Exception e) {
			System.out.println("Invalid input.\n");
		}
	}
 
	public static void multiplyPoly() {
		System.out.print("Insert the operands separated be a space: ");
		char choice1 = input.next().charAt(0);
		char choice2 = input.next().charAt(0);
 
		try {
			polyObject.multiply(choice1, choice2);
			System.out.print("Result set in R: ");
			String temp = polyObject.print('R');
			System.out.println(temp+"\n");
		} catch (Exception e) {
			System.out.println("Invalid input.\n");
		}
	}
 
	public static void evaluatePoly() {
		System.out.print("Insert the variable name: ");
		char choice = input.next().charAt(0);
		System.out.print("Insert the point: ");
		float substiute = input.nextFloat();
 
		try {
			System.out.println("Result= "+polyObject.evaluatePolynomial(choice, substiute));
		} catch (Exception e) {
			System.out.println("Polynomial is unset.\n");
		}
	}
	
	public static void clearPoly() {
		System.out.print("Insert the variable name: ");
		char choice = input.next().charAt(0);
		try{
			polyObject.clearPolynomial(choice);
			System.out.println("Cleared.\n");
		}catch(Exception e){
			System.out.println("Invalid input.\n");
		}
	}
}