package week07;

import java.util.*; import java.io.*;

/**
 * Creates random words.
 * Uses frequencies of letters proceeding previous letters in English.
 * @author Jeremy Haakma
 **/
public class DigramGenerator implements WordGenerator {
    /** Used to randomly select letters.*/
    private Random random;
    /** Number of letters in the english alphabet. **/
    private final int letters = 26;


    /** Constructor which sets the seed for the random variable.
     * @param r random
     */
    public DigramGenerator(Random r) {
        random = r;
    }
    /**
     * Generates a random word of n length.
     * @param n is the length of the words to be generated.
     * @return StringBuilder return: the random word generated.
     */
    public String nextWord(int n) {

        String[] frequencies = new String[letters] ;
        StringBuilder result = new StringBuilder();
        String firstLetters = "";
        try{

            firstLetters = new Scanner(new File("first-letters.txt"))
                .useDelimiter("\\Z").next();

            Scanner cont = new Scanner(new File("continuations.txt"));
            for(int i = 0; i < letters; i++){
                frequencies[i] = cont.nextLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }//end file imports

        char nextLetter = firstLetters.charAt
            (random.nextInt(firstLetters.length()));
        result.append(nextLetter);
        
        for (int i = 0; i< n-1; i++){
            String letterIndex = frequencies[nextLetter - 'a'];
            nextLetter = letterIndex.charAt
                (random.nextInt(letterIndex.length()));
            result.append(nextLetter);
        }
        
        return result.toString();
    }

}
