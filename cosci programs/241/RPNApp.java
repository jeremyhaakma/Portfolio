

import java.util.Scanner;
import java.util.Stack;
/** RPNApp calculates output based on Reverse Polish Notation.
 * @author Jeremy Haakma and Scott Wyngaarden
 */

public class RPNApp {

    
    
    /** main.*/
    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNextLine()){            

            String line = sc.nextLine();

            if (line.equals("exit")){
                break;
            }

            String inString= makeString(line);
            if(inString.equals("bracket error")){
                System.out.println("Error: unmatched parantheses");
                break;
            }
            
            String calcInput = calculate(inString);
                
            
            System.out.println(calcInput);
        }
    }//end main

    private static String makeString (String s){
        Scanner scan = new Scanner (s);
        String[] stringArray = s.split(" ");

        
        int indexLeft = 0;//index of "("
        int indexRight = 0;//index of ")"

        
        
        int parenthesesCount = 0;//should be 0 or an even number, if not then an error
        String finalString = "";
        boolean unmatched = false;

        //only works for one set of parentheses
        for(int i = 0; i < stringArray.length; i++){
            if(stringArray[i].equals("(")){
                parenthesesCount ++;
                unmatched = true;
                indexLeft = i;
            }else if (stringArray[i].equals(")")){
                parenthesesCount ++;
                unmatched = false;
                indexRight = i;                
            }
        }
        //doesn't work, don't know why.
        // fail final test, get 'unmatched parentheses' expect 'too few operands'
        // also not working for "2 ) 2 3 4 ("
        
        //if brackets, is there even number?
        if(unmatched == true || parenthesesCount%2 != 0){    
            return "bracketsError";
        }

        //this works
        
        //if( (indexLeft == 0 && indexRight != 0) || (indexLeft != 0 && indexRight == 0) ){
        //    return "bracketsError";
        //}

        for(int i = 0; i < stringArray.length; i++){
            if(i == indexLeft && indexLeft > 1){
                i++;
                int kIndex = indexLeft - 1;
                int k = Integer.parseInt(stringArray[kIndex]);   

                int endStringIndex = finalString.length() - stringArray[kIndex].length() - 2;
                finalString = finalString.substring(0, endStringIndex);//fix
        
                for (int j = 0; j < k; j++){//loop through items within brackets k times
                    while (i != indexRight){
                        finalString = finalString + " " + stringArray[i];
                        i++;
                        
                    }

                    i = indexLeft+1;//move index counter back to first item WITHIN brackets
                }
                
                i = indexRight+1;//move index counter to item after brackets
                
            }else{
                finalString = finalString + stringArray[i] + " ";         
            }//end else if
        }//end for       
        
        return finalString;
    }//end makeString
    
    /** calculate input.*/
    private static String calculate (String input){
        Stack<Long> stack = new Stack<Long>();
        Scanner scan = new Scanner(input);
        long x;
        long y;//second input, popped first
        String output = "";
        while (scan.hasNext()){
            if (scan.hasNextLong()){
                stack.push(scan.nextLong());
            }else {
                String operation = scan.next();
                //operands
                switch (operation) {

                case "bracketsError":
                    return "Error: unmatched parentheses";
                    
                //single operands
                case "+":
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        y = stack.pop();
                        x = stack.pop();
                        stack.push(x+y);
                    }
                    break;
                case "-":
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        y = stack.pop();
                        x = stack.pop();
                        stack.push(x-y);
                    }
                    break;
                case "*":
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        y = stack.pop();
                        x = stack.pop();
                        stack.push(x*y);
                    }
                    break;
                case "/":
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        y = stack.pop();
                        x = stack.pop();
                        if (y== 0){
                            return "Error: division by 0";
                        }
                        stack.push(x/y);
                    }
                    break;
                case "%":
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        y = stack.pop();
                        x = stack.pop();
                        stack.push(x%y);
                    }
                    break;
                    
                // repeat operands
                case "+!" :
                    
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        while (stack.size() > 1){
                            y = stack.pop();
                            x = stack.pop();
                            stack.push(x+y);
                        }
                    }
                    break;

                case "-!" :
                    
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        while (stack.size() > 1){
                            y = stack.pop();
                            x = stack.pop();
                            stack.push(x-y);
                        }
                    }
                    break;

                case "*!" :
                    
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        while (stack.size() > 1){
                            y = stack.pop();
                            x = stack.pop();
                            stack.push(x*y);
                        }
                    }
                    break;

                case "/!" :
                    
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        while (stack.size() > 1){
                            y = stack.pop();
                            x = stack.pop();
                            stack.push(x/y);
                        }
                    }
                    break;

                case "%!" :
                    
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        while (stack.size() > 1){
                            y = stack.pop();
                            x = stack.pop();
                            stack.push(x%y);
                        }
                    }
                    break;

                //copying, duplicating etc
                case "d":
                    if (stack.size() <1){
                        return "Error: too few operands";
                    } else {
                        stack.push(stack.peek());
                    }
                    break;

                case "o":
                    if (stack.size() <1){
                        return "Error: too few operands";
                    } else {
                        System.out.print(stack.peek() + " ");
                        
                    }
                    break;

                case "c" :
                    if (stack.size() < 2){
                        return "Error: too few operands";
                    } else {
                        long c = stack.pop();
                        long n = stack.pop();
                        if (c<0){
                            return "Error: negative copy";
                        }
                        for (int i = 0; i < c; i++){
                            stack.push(n);
                        }
                    }
                    break;

                case "r":
                    if (stack.empty()){
                        return "Error: too few operands";
                    }else{
                        long k = stack.pop();//k-1 = places back to move n
                        long n = stack.pop();//item to be moved
                        Stack<Long> kStack = new Stack<Long>();//stack to place removed items
                        if (k-1 > stack.size()){
                            return "Error: too few operands";
                        } else if (k <0){
                            return "Error: negative roll";
                        }else {
                            for (int i = 0; i < k-1; i++){
                                kStack.push(stack.pop());
                            }
                            stack.push(n);
                            for (int i = 0; i< k-1; i++){
                                stack.push(kStack.pop());
                            }
                        }
                    }
                    break;

                    //---------------THIS IS BAD CODE------------------//
                case "(":
                    if(stack.empty()){
                        return "Error: too few operands";
                    }
                    break;

                    //---------------THIS IS BAD CODE------------------//
                    
                default: return "Error: bad token '" + operation + "'";
                }//end switch                
            }//end if else       
        }//end while
        
        
        return stack.toString();
    }//end createStack 



    
}//end class
