package eg.edu.alexu.csd.datastructure.stack.cs48;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;
public class Applications implements IExpressionEvaluator {

	boolean isDigi(char sign){
		return (sign>='0' && sign<='9');
	}
	@Override
	public String infixToPostfix(String expression) {
		StringBuilder res=new StringBuilder();
		int count_open=0;
		boolean isSymbol=false;
		boolean isPreSym=false;
		if(expression==null || expression=="")throw new RuntimeException();
		MyStack operators=new MyStack();
		char previousTaken='u';	
		for(int i=0;i<expression.length();i++)
		{	
			char taken = expression.charAt(i);
			if(taken==' ')continue;
			isSymbol=(taken=='+' ||taken=='-' ||taken=='*' || taken=='/');
			if((isSymbol && isPreSym==true) )throw new RuntimeException();
			if(taken=='+' || taken=='-')
			{	isPreSym=true;
				if(operators.isEmpty()){
				operators.push(taken);
				}
				else{
						while(!operators.isEmpty() && (char)operators.peek()!='(' )
						{	res.append(operators.pop());
							res.append(" ");	
						}
						operators.push(taken);
					}				
			}
			else if(taken=='*' || taken=='/')
			{	isPreSym=true;
				if(operators.isEmpty()){
					operators.push(taken);
					}
					else{	
							while(!operators.isEmpty() && (char)operators.peek()!='('   
							&&(char)operators.peek()!='+' && (char)operators.peek()!='-' )
							{
								res.append(operators.pop());
								res.append(" ");
							}
							operators.push(taken);
						}			
			}
			else if(taken=='(')
			{	count_open++;
				operators.push(taken);
			}
			else if(taken==')')
			{ 	count_open--;
				while((char)operators.peek()!='(')
				{
					res.append(operators.pop());
					res.append(" ");
				}
				operators.pop();
			}
			else if((taken>='0' && taken<='9')||(taken>='a' && taken <='z')) {
					isPreSym=false;
					Integer operand=taken-'0';
					char temp;
					while(i<expression.length()-1 ) {
						i++;
						temp=expression.charAt(i);
						if(!isDigi(temp))
						{	if(temp!=' ')i--;
							break;
						}
						operand = (operand*10) + (temp- '0'); 
					}
					res.append(operand.toString());
					res.append(" ");
				}
				previousTaken=taken;
			}
		if(isPreSym==true || count_open!=0)
		{
			throw new RuntimeException();
		}
		while(!operators.isEmpty())
		{
			res.append(operators.pop());
			if(operators.size()!=0)
			res.append(" ");
		}
		return res.toString();
	}
	@Override
	public int evaluate(String expression) {
	
		if(expression==null || expression=="" )
			throw new RuntimeException();
		// TODO Auto-generated method stub
		
		MyStack evaluation=new MyStack();
	
		for(int i=0;i<expression.length();i++)
		{
			char taken = expression.charAt(i);
			if(taken==' ')continue;
			boolean isSymbol=(taken=='+' ||taken=='-' ||taken=='*' || taken=='/');
			boolean isDigit = (taken >= '0' && taken <= '9');
			if(isDigit)
			{
				double operand = 0;
				char temp;
				while(i<expression.length() ) {
					temp=expression.charAt(i);
					if(!isDigi(temp))break;
					if(temp=='(' || temp==')' ||temp=='}' || temp=='{')throw new RuntimeException();	 
					operand = (operand*10) + (temp- '0'); 
					i++;
				}
				i--;
				evaluation.push(operand);
			}
			else if(isSymbol)
			{	if(taken=='(' || taken==')' ||taken=='}' || taken=='{')throw new RuntimeException();	
				double operandOne=(double)evaluation.pop();
				double operandTwo=(double)evaluation.pop();
				if(taken=='+')evaluation.push(operandOne+operandTwo);
				else if(taken=='-')evaluation.push(operandTwo-operandOne);
				else if(taken=='*')evaluation.push(operandOne*operandTwo);
				else if(taken=='/')evaluation.push(operandTwo/operandOne);
			}
			else 
			{
				throw new RuntimeException();
			}
			
			
		}
		if(evaluation.size()!=1)
		{	
			return 0;
		}
			
		double value=(double)evaluation.pop();
		if( !evaluation.isEmpty())
			throw new RuntimeException();
		
		return (int )value;
	}

}
