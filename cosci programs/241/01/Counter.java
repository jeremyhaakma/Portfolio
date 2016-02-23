

package week01;
import java.util.Scanner;
/**
 * Counter class.
 * COSC241 lab 1
 * @author Jeremy Haakma
 */
public class Counter {
    /** Main method. Counts number of words and lines from an input.
     * @param args main
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int words = 0;
        int lines = 0;
        
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            Scanner scLine = new Scanner(line);
            while (scLine.hasNext()){
                words ++;
                scLine.next();
            }
            lines ++;
        }
        System.out.println("lines: " + lines +
                           "\nwords: " + words);
    }
}

