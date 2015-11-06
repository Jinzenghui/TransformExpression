import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class TransformExpression{
	
	public static int priority(String str){
		
		String temp = str.charAt(0) + "";
		int pri;
		switch(temp)
		{
			case "+":
				pri = 1;
				break;
			case "-":
				pri = 1;
				break;
			case "*":
				pri = 2;
				break;
			case "/":
				pri = 2;
				break;
			default:
				pri = 0;
			break;
		}
		return pri;
	}
	
	public static String infixToPrefix(String strInput){
		
		char ch[] = strInput.trim().toCharArray();
		
		String str[] = new String[ch.length];	
		Stack<String> op = new Stack<String>();
		StringBuffer output = new StringBuffer();
		
		for(int i = 0; i <ch.length; i++){
			str[ch.length-1-i] = String.valueOf(ch[i]);
		}
		
		for(int i=0; i<str.length; i++){
			if(str[i].matches("^[1-9]\\d*$"))
			{
				output.append(str[i]);
			}else if(str[i].equals(")"))
			{
				op.push(str[i]);
			}else if(str[i].equals("("))
			{
				while(op.size()>0 && !op.peek().equals(")")){
					output.append(op.pop());
				}
			}else{
				if(op.isEmpty()){
					op.push(str[i]);
					continue;
				}else if(op.peek().equals(")")){
					op.push(str[i]);
					continue;
				}else if(priority(str[i]) >= priority(op.peek()))
				{
					op.push(str[i]);
				}else{
					do{
						output.append(op.pop());
					}while(!(op.isEmpty()||op.peek().equals(")")||priority(str[i]) >= priority(op.peek())));
					op.push(str[i]);
				}	
			}
		}
		
		while(!op.isEmpty()){
			String s = op.pop();
			if(!s.equals(")"))
				output.append(s);
		}
		
		return output.reverse().toString();
	} 
	
	public static String infixToPostfix(String strInput){
		
		char ch[] = strInput.trim().toCharArray();
		
		String str[] = new String[ch.length];
		
		StringBuffer output = new StringBuffer();
		Stack<String> op = new Stack<String>();
		
		for(int i = 0; i < ch.length; i++){
			str[i] = String.valueOf(ch[i]);
		}
		
		for(int i = 0; i < str.length; i++){
			if(str[i].matches("^[1-9]\\d*$"))
			{
				output.append(str[i]);
			}else if(str[i].equals("(")){
				op.push(str[i]);
			}else if(str[i].equals(")")){
				while(op.size()>0 && !op.peek().equals("(")){
					output.append(op.pop());
				}
				
			}else{
				if(op.isEmpty()){
					op.push(str[i]);
					continue;
				}else if(op.peek().equals("(")){
					op.push(str[i]);
					continue;
				}else if(priority(str[i]) > priority(op.peek()))
				{
					op.push(str[i]);
				}else{
					do{
						output.append(op.pop());
					}while(!(op.isEmpty()||op.peek().equals("(")||priority(str[i]) > priority(op.peek())));
					op.push(str[i]);
				}
			}
		}
		
		while(!op.isEmpty()){
			String s = op.pop();
			if(!s.equals("("))
				output.append(s);
		}	
		
		return output.toString();
	}
	
	public static void main(String[] args){
		
		String strInput = null;
		
		Stack<String> op = new Stack<String>();
		
		StringBuffer output = new StringBuffer();
		
		while(!StdIn.isEmpty()){
			strInput = StdIn.readLine();
		}
		
		StdOut.println(infixToPrefix(strInput));
		StdOut.println(infixToPostfix(strInput));
		
	}
}