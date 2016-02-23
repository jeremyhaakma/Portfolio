package week09;

import java.util.Scanner;
import java.util.Stack;

/** RPNApp calculates output based on Reverse Polish Notation.
 * @author Jeremy Haakma and Scott Wyngaarden
 */
public class RPNApp {
    /** stack used for calculation. */
    static Stack<Long> stack = new Stack<Long>();
    /** scan reads user input. */
    static Scanner scan;
    /** is true if an error has occured. */
    static boolean hasError = false;

    
    /** For each new line of user input, performs RPN calculations.
     * Prints the results of the calculations if there are no errors.
     * @param args not used
     */
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            calculate(line);
            if (!hasError){
                System.out.println(stack);
            }
            stack = new Stack<Long>();//reset stack after each line.
            hasError = false;//reset error state.
        }
    }
    
    /** calculate calls errorCheck and operate methods.
     * @param input a single line of user input
     */
    public static void calculate(String input){
        scan = new Scanner(input);
        while (scan.hasNext()){
            if (scan.hasNextLong()){
                stack.push(scan.nextLong());
            }else {
                String operation = scan.next();
                errorCheck(operation);
                if (!hasError){
                    operate(operation);
                }
            }
        }
    }
    
    /** checks for basic errors.
     * @param operation a single token from the user input
     */
    private static void errorCheck(String operation){
        switch (operation) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "c":
                if (stack.size() < 2){
                    System.out.println("Error: too few operands");
                    hasError = true;
                }
                break;
            case "d":
            case "o":
            case "+!":
            case "-!":
            case "*!" :
            case "/!" :
            case "%!" :
                if (stack.size() < 1){
                    System.out.println("Error: too few operands");
                    hasError = true;
                }
                break;
            case "r":
                if (stack.empty()){
                    System.out.println("Error: too few operands");
                    hasError = true;
                }
                break;
            case "(":
                if (stack.isEmpty()){
                    System.out.println("Error: too few operands");
                    hasError = true;
                }
                break;
            case ")":
                System.out.println("Error: unmatched parentheses");
                hasError = true;
                break;
            default: 
                System.out.println("Error: bad token '" + operation + "'");
                hasError = true;
        }//end switch
    }//end errorCheck
    
    /** operate performs calculations using stack.
     * @param operation a single token from the user input
     */
    private static void operate(String operation){
        long x;
        long y;//second input, popped first
        switch (operation) {  
            //single operands
            case "+":
                y = stack.pop();
                x = stack.pop();
                stack.push(x+y);
                break;
            case "-":
                y = stack.pop();
                x = stack.pop();
                stack.push(x-y);
                break;
            case "*":
                y = stack.pop();
                x = stack.pop();
                stack.push(x*y);
                break;
            case "/":
                y = stack.pop();
                x = stack.pop();
                if (y == 0){
                    System.out.println("Error: division by 0");
                    hasError = true;
                } else {
                    stack.push(x/y);
                }
                break;
            case "%":
                y = stack.pop();
                x = stack.pop();
                stack.push(x%y);
                break;              
            case "+!":
                y = stack.pop();
                x = stack.pop();
                stack.push(x+y);
                break;
            case "-!":
                while (stack.size() > 1){
                    y = stack.pop();
                    x = stack.pop();
                    stack.push(x-y);
                }
                break;
            case "*!" :
                while (stack.size() > 1){
                    y = stack.pop();
                    x = stack.pop();
                    stack.push(x*y);
                }
                break;
            case "/!" :
                while (stack.size() > 1){
                    y = stack.pop();
                    x = stack.pop();
                    stack.push(x/y);
                }
                break;
            case "%!":
                while (stack.size() > 1){
                    y = stack.pop();
                    x = stack.pop();
                    stack.push(x%y);
                }
                break;
            //copying, duplicating etc
            case "d":
                stack.push(stack.peek());                        
                break;
            case "o":
                System.out.print(stack.peek() + " ");                        
                break;
            case "c" :
                long c = stack.pop();
                long n = stack.pop();
                if (c<0){
                    System.out.println("Error: negative copy");
                    hasError = true;
                }
                for (int i = 0; i < c; i++){
                    stack.push(n);
                }                        
                break;
            case "r":
                replace();
                break;
            case "(":
                parentheses();
                break;
            default: 
                System.out.println("Error: bad token '" + operation + "'");
                hasError = true;
        }//end switch
    }//end operate
    
    /** this method takes care of parentheses in the input.*/
    private static void parentheses(){
        String inPString = "";            
        Long multiplier = stack.pop(); 
        int pNum = 1;//0 if no. of '(' match no. of ')'
        
        for (int i = 0; i < multiplier; i++){
            while (scan.hasNext()){
                String nextItem = scan.next();
                if (nextItem.equals("(")){
                    pNum++;
                }
                if (nextItem.equals(")")){
                    pNum--;
                    if (pNum == 0){
                        break;
                    } else {
                        inPString = inPString + " " + nextItem;
                    }
                } else{
                    inPString = inPString + " " + nextItem;
                }
            }
            if (pNum != 0){
                System.out.println("Error: unmatched parentheses");
                hasError = true;
                break;
            }
            calculate(inPString);
        }
    }//end parentheses
    
    /** this method takes care of the replace operation. */
    private static void replace(){
        long k = stack.pop();//k-1 = places back to move n
        long move = stack.pop();//item to be moved
        Stack<Long> kStack = new Stack<Long>();
        //stack to place removed items
        if (k-1 > stack.size()){
            System.out.println("Error: too few operands");
            hasError = true;
        } else if (k <0){
            System.out.println("Error: negative roll");
            hasError = true;
        }else {
            for (int i = 0; i < k-1; i++){
                kStack.push(stack.pop());
            }
            stack.push(move);
            for (int i = 0; i< k-1; i++){
                stack.push(kStack.pop());
            }
        }           
    }//end replace
}//end class
