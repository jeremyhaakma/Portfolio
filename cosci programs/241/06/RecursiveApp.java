package week06;

public class RecursiveApp{

    public static void main(String[] args){
        long test = 56345;
        System.out.println(digits(test));
        System.out.println(sumOfDigits(test));
    }

    public static long digits(long n){
        long d = 0;
        if (n < 10 && n > -10){
            return 1;
        } else {
            return 1 + digits(n/10);
        }

    }

    public static long sumOfDigits(long n){
        if (n < 10 && n > -10){
            return n;
        } else return n%10 + sumOfDigits(n/10);
    }




}
