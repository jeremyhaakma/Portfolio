package week06;
import java.util.Random;

public class Coins{

    public static final boolean HEADS = true;
    public static final boolean TAILS = false;
	
    private boolean[] coins;

    public static void main(String[] args){
        
        Coins coin = new Coins(8);
        System.out.println(coin.countHeads());
        System.out.println(coin.countRuns());
        System.out.println(coin);
    }
    
    public Coins(boolean[] coins) {
        this.coins = coins;
    }

    public Coins(String c){
        this.coins = new boolean[c.length()];
        for(int i = 0; i < c.length(); i++){
            if (c.charAt(i) == 'H'){
                coins[i] = HEADS;
            } else {
                coins[i] = TAILS;
            }
        }
    }

    public Coins(int length){
        Random r = new Random();
        this.coins = new boolean[length];
        for (int i = 0; i < length; i++){
            coins[i] = r.nextBoolean() ? HEADS:TAILS;
        }
    }

    public int countHeads(){
        int heads = 0;
        for (boolean b : coins){
            heads += b ? 1:0;
        }
        return heads;
    }
    
    public int countRuns(){
        int count = 1;
        for(int i = 1; i < coins.length; i++){
            if (coins[i] != coins[i-1]){
                count++;
            }
        }
        return count;
    }

    public String toString(){
        String tosses = "";
        for(boolean b : coins){
            if (b == HEADS){
                tosses = tosses + "H";
            } else {
                tosses = tosses + "T";
            }
        }
        return tosses;
    }
}
