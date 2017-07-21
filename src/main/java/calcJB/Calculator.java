package calcJB;

import java.util.*;

public class Calculator {
	private static Calculator c;
	private Calculator() {
	}
	
	public static Calculator constructor(){
		if(c==null){
			c = new Calculator();
			return c;
		}else{
			return c;
		}
	}
	//methods
	/*
	 * function get string 
	 * if have operator return operator
	 * else return String space " "
	 * TODO return array of operator
	 */
public boolean ifHaveOp(String str){
	
	for(int i=0;i<str.length();i++){
		if(str.charAt(i)=='E'){
			i++;
			continue;
		}
			
		switch (str.charAt(i)) {
		case 43: //+
			return true;
		case 45://-
			return true;
		case 47: // -/-
			return true;
		case 42: // *
			return true;
		case 94: // ^
			return true;
		default:
			break;
		}
	}
			
	
	return false;

}
public ArrayList<String> catchOperator(String str){
		ArrayList<String> ans = new ArrayList<String>();
		boolean flag = true;
		int counterBrackets = 0;
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)=='('){
				counterBrackets++;
				flag = false;
			}
			if(str.charAt(i)==')'){
				counterBrackets--;				
				if(counterBrackets==0)
				flag = true;
			}
			
			
			if(flag){
			switch (str.charAt(i)) {
			case 43: 
				ans.add("+");
				
				break;
			case 45:
				if(str.charAt(i-1)!='E')
				ans.add("-");
				break;
			case 47: 
				ans.add("/"); 
				break;
			case 42: 
				ans.add("*"); 
				break;
			case 94:
				ans.add("^"); 
				break;
			default:
				break;
			}
			}
		}
		return ans;
	}
	
	/* function get string 
	 * if have operator return operator
	 * else return String space " "
	 * TODO return array of operator
	 */
	
public Double rNum(String str){
		if(!ifHaveOp(str)){
			str = removeBrackets(str);
			return Double.valueOf(str);
		}
		
		str = ifTheFirstIsMinus(str);
		HashMap<String,Operator> map = getSet() ;
		//split the string numbers
		ArrayList<Double> nums = new ArrayList<Double>();
	    nums = splitD(str); 
		// set the operator
	    ArrayList<String> operator = new ArrayList<String>();
		 operator = catchOperator(str); 
		// casting from String to Integer
		

		Double ansBefor = map.get(operator.get(0)).clac(nums.get(0), nums.get(1));
		nums.remove(0);
		nums.remove(0);
		operator.remove(0);
		//System.out.println(ansBefor);
		if(operator.size()>=1){
			while(!operator.isEmpty()){
				ansBefor = map.get(operator.get(0)).clac(ansBefor, nums.get(0));
				//System.out.println(ansBefor);
				operator.remove(0);
				nums.remove(0);
			}
			
		}
		return ansBefor;
		//System.out.println("========");	
		//System.out.println(ansBefor);
		
			
		
			
	}
	public Double RealNumbers(String str){
		if(!ifHaveOp(str)){
			return Double.valueOf(str);
		}
		HashMap<String,Operator> map = getSet() ;
		//split the string numbers
		ArrayList<Double> nums = new ArrayList<Double>();
	    nums = splitD(str); 
		// set the operator
	    ArrayList<String> operator = new ArrayList<String>();
		 operator = catchOperator(str); 
		// casting from String to Integer
		

		Double ansBefor = map.get(operator.get(0)).clac(nums.get(0), nums.get(1));
		nums.remove(0);
		nums.remove(0);
		operator.remove(0);
		//System.out.println(ansBefor);
		return ansBefor;
//		if(operator.size()>=1){
//			for(int i=0;i<=operator.size();i++){
//				ansBefor = map.get(operator.get(0)).clac(ansBefor, nums.get(0));
//				System.out.println(ansBefor);
//				operator.remove(0);
//				nums.remove(0);
//			}
//		}
		//System.out.println("========");	
		//System.out.println(ansBefor);
		
			
		
		
	}
	public HashMap<String,Operator> getSet (){
		HashMap<String,Operator> t = new HashMap<String,Operator>();
		t.put("+", new Plus());
		t.put("-", new Minus());
		t.put("/", new Divide());
		t.put("*", new Multiplied());
		t.put("^", new Power());	
		t.put("√", new Root());
		return t;
		}
	public ArrayList<String> split(String str) {

		// String[] temp = str.split("[-,+,*,^,/]");
		ArrayList<String> ans = new ArrayList<String>();
		boolean flag = true;
		int counterBrackets =0;
		String temp1 = "";
		String temp2 = "";
		for (int i = 0; i < str.length(); i++) {
			String c = str.substring(i, i + 1);
			if (c.equals( "(" )) {
			if(counterBrackets==0){
				flag = false;
				temp1 = "";
			}
			counterBrackets++;
			}
			if (flag) {
				char c2 = ' ';
				if (i > 0 && str.charAt(i - 1) == 'E') 
					c2='E';
					if (c2!='E' && c.equals("-") || c2!='E' && c.equals("+") || c2!='E' && c.equals("*") ||c2!='E' && c.equals("^") ||c2!='E' && c.equals("/")) {
						if (!temp2.equals("")) {
							ans.add(temp2);
							temp2 = "";
						}
					} else {
						temp2 += c;
					}
				
			}
			temp1 += c;
			if (c.equals( ")" )) {
				counterBrackets--;
				if(counterBrackets==0){
				flag = true;
				//ans.add(temp1);
				temp2 = temp1.substring(0);
				}
			}
			if(i==str.length()-1){
				ans.add(temp2);
			}
		}

		return ans;
	}
	public String removeBrackets(String str){
		String ans = "";
		for (int i = 0; i < str.length(); i++) {
			if(!(str.charAt(i)=='(' || str.charAt(i)==')' )){
				ans+=str.substring(i, i+1);
			}
		}
		return ans;
	}
	public ArrayList<Double> splitD(String str) {
		// String[] temp = str.split("[-,+,*,^,/]");
		str = ifTheFirstIsMinus(str);
		
		ArrayList<Double> ans = new ArrayList<Double>();
		boolean flag = true;
		boolean eE = true;
		int counterBrackets =0;
		String temp1 = "";
		String temp2 = "";
		for (int i = 0; i < str.length(); i++) {
			if(i>0){
				if(str.charAt(i-1)=='E'){
					eE = false;
				}else{
					eE = true;
				}
			}
			String c = str.substring(i, i + 1);
			if (c.equals( "(" )) {
			if(counterBrackets==0){
				flag = false;
				temp1 = "";
			}
			counterBrackets++;
			}
			if (flag) {
				if (eE && (c.equals("-") || c.equals("+") || c.equals("*") || c.equals("^") || c.equals("/"))) {
					temp2 = removeBrackets(temp2);
					ans.add(Double.valueOf(temp2));
					temp2 = "";
				} else {
					temp2 += c;
				}
			}
			temp1 += c;
			if (c.equals( ")" )) {
				counterBrackets--;
				if(counterBrackets==0){
				flag = true;
				//ans.add(temp1);
				temp2 = temp1.substring(0);
				}
			}
			if(i==str.length()-1){
				temp2 = removeBrackets(temp2);
				ans.add(Double.valueOf(temp2));
			}
		}

		return ans;
	}
	public String roundBrackets(String str){
		ArrayList<String> num = split(str);
		 ArrayList<String> op = catchOperator(str);
		 String temp = "";
		 String result = "";
		 while(!num.isEmpty()|| !op.isEmpty()){
		
			 if(!op.isEmpty()){
			 temp = op.get(0);
			 op.remove(0);
			}else{
				temp = "";
			}
			 if(temp.equals("*")||temp.equals("/") ||temp.equals("^")){
				 result+="(";
				 result+=num.get(0);
				 num.remove(0);
				 result+=temp;
				 result+=num.get(0);
				 num.remove(0);
				 result+=")";
				 if(!op.isEmpty()){
					 result+= op.get(0);
					 op.remove(0); 
				 }
			 }else{
				 result+=num.get(0);
				 num.remove(0);
				 result+=temp;
			 }
		 }
		 if(!str.equals(result)){
			 return roundBrackets(result);
		 }
		return result;
	}
	public boolean ifHaveBrackets(String str){
		  ArrayList<String> num = split(str);
		  for (String s : num) {
			if(s.charAt(0)=='('){
				if(ifHaveTwoNum(s)){
					return true;
					
				}else{
				}
			}
		}
		
		
		return false;
	}
	public boolean ifHaveTwoNum(String str){
		str = str.substring(1,str.length()-1);
		ArrayList<String> num = split(str);
		if(num.size()==2){
			return true;
		}
		return false;
	}
	public String solveBrackets(String str){
	
		String ans = null;
		if(ifHaveBrackets(str)){
		   ArrayList<String> num = split(str);
		   ArrayList<String> op = catchOperator(str);
		   int count =-1;
		   for (String s : num) {
			   count++;
			if(s.charAt(0)=='(' && ifHaveTwoNum(s)){
				String temp = s.substring(1, s.length()-1);
				if(ifHaveBrackets(temp)){
					num.remove(count);
					num.add(count, roundBrackets(solveBrackets(temp)));
					break;
				}else{
				num.remove(count);
				//num.add(count, ifTheFirstIsMinus(RealNumbers(temp).toString()));
				num.add(count, bracketsForAns(RealNumbers(temp).toString()));
				break;
				}
			}
				
		}
		   ans = num.get(0);
			num.remove(0);
		   if(op.size()>=1){
			   	int s = op.size();
				for(int i=0;i<=s;i++){
					if(!op.isEmpty()){
					ans += op.get(0);
					op.remove(0);
					}
					if(!num.isEmpty()){
					ans += num.get(0);
					num.remove(0);
					}
				}
			}
		ans = connectingOp(ans);
		ans = ifTheFirstIsMinus(ans);
		}
		
		if(ans==null){
			return str;
		}else{
			if(ifHaveBrackets(ans)){
				
				return solveBrackets(ans);
			}
		return ans;
		}
	}
	public String helpConnnectingOp(String str,int i,String op){
		
		
			    String result = str.substring(0,i);
				result+=op;
				result+=str.substring(i+2);
			
			
		
		return result;
	}
	public String convertMarks(String str){
		String result = null;
		for (int i = 0; i < str.length()-1; i++) {
			if(ifHaveOp(str.substring(i, i+1)) && ifHaveOp(str.substring(i+1, i+2)) ){
				
				if(str.substring(i, i+1).equals("-") && str.substring(i+1, i+2).equals("-") ){
					//System.out.println("-  -" );
					result = helpConnnectingOp(str, i, "+");
					return convertMarks(result);
					}
				
				if(str.substring(i, i+1).equals("+") && str.substring(i+1, i+2).equals("+") ){
					//System.out.println("+  +" );
					result = helpConnnectingOp(str, i, "+");
					return convertMarks(result);
				}
				if(str.substring(i, i+1).equals("-") && str.substring(i+1, i+2).equals("+") ){
					//System.out.println("-  +" );
					result = helpConnnectingOp(str, i, "-");
					return convertMarks(result);
				}
				if(str.substring(i, i+1).equals("+") && str.substring(i+1, i+2).equals("-") ){
					//System.out.println("+  -" );
					result = helpConnnectingOp(str, i, "-");
					return convertMarks(result);
				}
				if(str.substring(i, i+1).equals("/") && str.substring(i+1, i+2).equals("+")  ||
				   str.substring(i, i+1).equals("*")	&& str.substring(i+1, i+2).equals("+")  ||
				   str.substring(i, i+1).equals("^") && str.substring(i+1, i+2).equals("+") ){
					result = helpConnnectingOp(str, i, str.substring(i, i+1));
					return convertMarks(result);
				}
//				if(str.substring(i, i+1).equals("/") && str.substring(i+1, i+2).equals("-")  ||
//						   str.substring(i, i+1).equals("*")	&& str.substring(i+1, i+2).equals("-")  ||
//						   str.substring(i, i+1).equals("^") && str.substring(i+1, i+2).equals("-") ){
//							
//							result = helpConnnectingOp(str, i, str.substring(i, i+1));
//							return convertMarks(result);
//						}
			}
		}
		return str;
	}
	public  String ifTheFirstIsMinus(String str){
		String result =null;
		if(str.substring(0,1).equals("-")){
			result = "(";
			for (int i = 1; i < str.length()-1; i++) {
				if(ifHaveOp(str.substring(i,i+1))){
					result+=str.substring(0,i);
					result+=")";
					result+=str.substring(i);
					return result;
				}
				
			}
		}
		return str;
		
	}
	public  String connectingOp(String str){
		String result =null;
		for (int i = 0; i < str.length()-1; i++) {
			if(ifHaveOp(str.substring(i, i+1)) && ifHaveOp(str.substring(i+1, i+2)) ){
				if(str.substring(i+1, i+2).equals("-")){
					result = str.substring(0,i+1);
					result+="(";
					for (int j = i+2; j < str.length(); j++) {
						if(ifHaveOp(str.substring(j,j+1))){
							result+=str.substring(i+1,j);
							result+=")";
							result+=str.substring(j);
							return connectingOp(result);
						}else if(j==str.length()-1){
							result += str.substring(i+1)+")";
							return connectingOp(result);
						}
					}
				}
			}
		}
		return str;
	}
	public boolean ifHaveNum(String str){
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c>=48 && c<=57)
				return true;
		}
		return false;
	}
public static String bracketsForAns(String str){
	String ans = "("+str+")";
	return ans;
}
public String removeFirstCharIfIsPlus(String str){
		if(str.substring(0,1).equals("+")){
			String temp = str.substring(1);
			return temp;
		}
		return str;
	}
public String removeBracketsVoid(String str){
	String temp="";
	for (int i = 0; i < str.length(); i++) {
		if(str.charAt(i)=='('){
			if(i<str.length() && str.charAt(i+1)==')'){
				i++;
			}else{
				temp+=str.charAt(i);
			}
		}else{
			temp+=str.charAt(i);
		}
	}
	
	return temp;
}
/*
	
 * function check if input string is legal 
	 * numbers 0-9
	 * operator + - * / ^
	 * signal point (.)
	 * 
	 */	
 	public boolean checkIfNormalNum(String str){
		if(!ifHaveNum(str))
			return false;
		str = removeBracketsVoid(str);
		if(str=="")
			return false;
 		str = convertMarks(str);
		str = removeFirstCharIfIsPlus(str);
		str = ifTheFirstIsMinus(str);
		str = connectingOp(str);
		//str = roundBrackets(str);
		for(int i=0;i<str.length();i++){
			
			/*
			 * check if in the first character have operator
			 * note ! because roundBrackets function even if the first character is minus
			 * He will wrap it in parentheses
			 */
			
			if(i==0 && ifHaveOp(str.substring(0, 1)) )
				return false;
			//check in all the string have operator
//			if(!ifHaveOp(str))
//				return false;
			//check if have character it is not number or operator
			if(str.charAt(i)<40 ){
				return false;
			}
			if(str.charAt(i)==44){
				return false;
			}
			if(str.charAt(i)>57 && str.charAt(i) != 94 &&str.charAt(i) != 69){
				return false;
			}
			int temp = i+2;
			if(i+2>str.length()){
				temp = str.length();
			}
			if(ifHaveOp(str.substring(i,i+1)) && ifHaveOp(str.substring(i+1,temp))){
				if(!str.substring(i+1,temp).equals("-")){
					return false;
				}
			}
			//check the lest character if is't operator 
			if(i==str.length()-1){
				if( ifHaveOp(str.substring(str.length()-1,str.length()))){
					return false;
				}
			}
			
		}
		return true;
	}
public String runForGui(String question){
	int calcType =0;
		if (question.indexOf("i") != -1) {
			calcType = 2;
		} else if (question.indexOf("{") != -1) {
			calcType = 3;
		} else if (checkIfNormalNum(question)) {
			calcType = 1;
		} else {
			return "syntax ERROR";
		}
	/*
	 * switch recognize the type calculation
	 *  send the String question to the correct function 
	 *  the user get the answer
	 *  end of program
	 */
	switch (calcType) {
	case 1:
		//System.out.println("ממשיים");
		question = removeBracketsVoid(question);
		question = convertMarks(question);
		question = removeFirstCharIfIsPlus(question);
		question = ifTheFirstIsMinus(question);
		question = connectingOp(question);
		return rNum(solveBrackets(roundBrackets(question))).toString();
	case 2:
		System.out.println("מורכבים");
		break;
	case 3:
		System.out.println("מטריצות");
		break;
	default:
		break;
	}
	return "ERROR";
}
public void runCal(){

		Scanner s = new Scanner(System.in);
		int calcType =0;
		String question = "";
		// "סוג מחשבון: (1)ממשיים(2)מורכבים(3)מטריצות"
		/*
		 *  loop get String from user 
		 *  check if legal insert the type of calculator
		 *  else tell to user insert again
		 *  
		 */

		while (calcType == 0) {
			System.out.println("?איזה חישוב תרצה לבצע ");
		    question = s.next();
			if (question.indexOf("i") != -1) {
				calcType = 2;
			} else if (question.indexOf("{") != -1) {
				calcType = 3;
			} else if (checkIfNormalNum(question)) {
				calcType = 1;
			} else {
				System.out.println("המחשבון לא מזהה את התרגיל ");
				System.out.println("[  "+question+"  ]");
			}
		}
		/*
		 * switch recognize the type calculation
		 *  send the String question to the correct function 
		 *  the user get the answer
		 *  end of program
		 */
		switch (calcType) {
		case 1:
			System.out.println("ממשיים");
			question = removeBracketsVoid(question);
			question = convertMarks(question);
			question = removeFirstCharIfIsPlus(question);
			question = ifTheFirstIsMinus(question);
			question = connectingOp(question);
			System.out.println(rNum(solveBrackets(roundBrackets(question))));
			break;
		case 2:
			System.out.println("מורכבים");
			break;
		case 3:
			System.out.println("מטריצות");
			break;
		default:
			break;
		}
		
	s.close();
		
		
	
	}
}
