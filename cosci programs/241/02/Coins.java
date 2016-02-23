package week02;
import java.util.Random;
/**
 * Class coins creates series of coin flips.
 *
 * @author Jeremy Haakma
 */
public class Coins{
    /** Data field boolean HEADS.
     */
    public static final boolean HEADS = true;
    /** Data field boolean TAILS.*/
    public static final boolean TAILS = false;
    /** Data field boolean array coins.*/
    private boolean[] coins;
    
    /** Main method.
     * @param args String array for Main method
     */
    public static void main(String[] args){
        boolean[] b = {HEADS, TAILS, HEADS, HEADS, TAILS};
        Coins c = new Coins("THTTHT");
        System.out.println("number of heads: " + c.countHeads());
        System.out.println("tosses: " + c);
        System.out.println("number of runs: " + c.countRuns());
    }//end main

    /*Constructors*/

    /**Constructor sets coins[] with an array.
     * @param coins boolean array that sets coins data field
     */
    public Coins(boolean[] coins) {
        this.coins = coins;
    }//end constructor


    /**Constructor sets coins[] with a string.
     * @param c String of 'H's and 'T's which sets coins array
     */
    public Coins(String c){
        this.coins = new boolean[c.length()];
        for(int i=0; i< c.length(); i++){
            if (c.charAt(i)== 'H'){
                coins[i] = HEADS;
            } else if (c.charAt(i) == 'T'){
                coins[i] = TAILS;
            }
        }
      
    }//end constructor


    /** Constructor sets coins[] length using int and randomly assigns H/T.
     * @param length int which sets length of coins array
     */
    public Coins(int length){
        this.coins = new boolean[length];
        Random rand = new Random();
        for(int i=0; i< coins.length; i++){
            if (rand.nextBoolean()){
                coins[i] = HEADS;
            } else {
                coins[i] = TAILS;
            }
        }
    }

    /*Methods*/

    /** Method that counts the number of runs (consecutive heads/tails).
     * @return runs
     */
    public int countRuns(){
        int runs = 0;
        for(int i = 1; i< coins.length; i++){
            if(coins[i] != coins[i-1]){
                runs++;
            }
        }
        runs++;
        return runs;
    }//end countRuns

    /** Method that counts how many heads landed.
     * @return int heads
     */
    public int countHeads(){
        int heads = 0;
        for(boolean b : coins){
            if (b==HEADS){
                heads++;
            }
        }
        return heads;
    }//end countHeads

    /** toString method returns a string of 'H' and 'T' representing tosses.
     * @return tosses
     */
    public String toString(){
        String tosses = "";
        for (boolean b : coins){
            if (b==HEADS){
                tosses = tosses + "H";
            } else if (b==TAILS) {
                tosses = tosses + "T";
            }
        }
        return tosses;
    }//end toString

}//end class
