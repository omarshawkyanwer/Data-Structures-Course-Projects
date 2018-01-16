package eg.edu.alexu.csd.datastructure.hangman.cs48;

import java.util.Random;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;

public class Hangman implements IHangman {
	
	static char[]temp;
	char[]tomanipulate;
	int counter=0;
	int rnd,maxWrongGuess,wordSize;
  	String []dictionary=null;
    static String ChosenWord="";
	public void setDictionary(String[] words)  {
		dictionary=words;
	}
	public String selectRandomSecretWord() {
	 if(dictionary==null)return null;
	   Random random = new Random();
	   
		int rnd=random.nextInt(dictionary.length);
		
		if (dictionary[rnd]==null)return null;
		ChosenWord=dictionary[rnd];
		wordSize=ChosenWord.length();
		temp=new char[wordSize];
		for(int i=0;i<wordSize;i++)
			temp[i]='-';
		tomanipulate=ChosenWord.toCharArray();
		return dictionary[rnd];
	}
	public String guess(Character c) {	
		String x=new String(temp);
		if(!x.contains("-"))return null;
		if(c==null){
			String b=new String(temp);
			return b;	
		}
		char cast=(char)c;
		char Capital=Character.toUpperCase(cast);
		char lower=Character.toLowerCase(cast);
		int flag=0;
		
		for(int i=0;i<wordSize;i++)
		{
			if(tomanipulate[i]==Capital ||tomanipulate[i]==lower)
			{
				temp[i]=tomanipulate[i];
				flag=1;
			}
		}
		if(flag==0)
		{	   counter++;  
	        	if(counter==maxWrongGuess)return null;
		       System.out.println(counter);
			   String b=new String(temp);
			   return b;
		}
		
		if(flag==1  )
		{	
			String b=new String(temp);
			return b;
		}
		
			return null;		
	}
	public void setMaxWrongGuesses(Integer max) {
		if(max==null)maxWrongGuess=0;
		else maxWrongGuess=(Integer)max;
	}
}
