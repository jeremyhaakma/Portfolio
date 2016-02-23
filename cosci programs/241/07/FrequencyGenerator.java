package week07;

import java.util.*;
import java.io.*;
/**
 * Generates random words.
 * @author Jeremy Haakma */
public class FrequencyGenerator implements WordGenerator {

    /** Used to randomly select letters.*/
    private Random random;
    /** Number of letters in the english alphabet. **/
    private final int letters = 26;

    /** Constructor which sets the seed for the random variable.
     * @param r random
     */
    public FrequencyGenerator(Random r) {
        random = r;
    }

    /**
     * Generates a random word of n length.
     * @param n is the length of the words to be generated
     * @return StringBuilder return: the random word generated
     */
    public String nextWord(int n) {
        double[] weights = new double[letters];
        StringBuilder result = new StringBuilder();
        //import contents of letter frequencies file.
        try{
            Scanner sc = new Scanner(new File("letter-frequencies.txt"));
        
            for(int i = 0; i < letters; i++){
                String s = sc.nextLine();
                weights[i] = Double.parseDouble(s);
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }  
        //pick random double from 0-1.
        for (int i=0; i<n; i++){
            double rand = random.nextDouble();
            int index = 0;
            while(rand>weights[index]){
                rand -= weights[index];
                index++;
            }
            char c = (char) ('a' + index);
            result.append(c);
        }
        
        return result.toString();
    }

}
