package week03;
/**
 * RecursiveApp.
 * Uses recursive logic to calculate the length and sum of an input.
 * @author Jeremy Haakma
 */


public class RecursiveApp{
    /** Main method.
     * @param args main method input
     */
    public static void main(String[] args){

    }
    /** digits method.
     * @param n number to be checked
     * @return length number of digits in n
     */
    public static long digits(long n){
        long length;
        if (Math.abs(n)<10){
            length = 1;
        } else{
            length = 1 + digits(n/10);
        }
        return length;   
    }
    /** sumOfDigits method.
     * @param n input whose digits are to be summed
     * @return sum sum of digits of n
     */
    public static long sumOfDigits(long n){
        long sum;
       
        if (Math.abs(n)<10){
            sum = n;
        } else {
            long length = digits(n);
            sum = ((n/ ( (long)Math.pow(10, (length-1) ) ))
                   + sumOfDigits(n%( (long)Math.pow(10, (length-1) ) )));
        }
        System.out.println("sum: " + sum);
        return sum;
    }

}
