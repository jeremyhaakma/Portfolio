/*----------Packages----------*/
import java.util.*;
import java.lang.Math;

public class Perceptron {

/*----------Variables----------*/
public double alpha = Math.random();
public static int[][] training = new int[][]{
  {0,1,1,1,0,
   1,0,0,0,1,
   1,0,0,0,1,
   1,0,0,0,1,
   0,1,1,1,0, -1},
     
  {0,0,1,0,0,
   0,1,1,0,0,
   0,0,1,0,0,
   0,0,1,0,0,
   0,1,1,1,0, -1},
     
  {0,1,1,1,0,
   1,0,0,1,0,
   0,0,1,0,0,
   0,1,0,0,0,
   1,1,1,1,1, -1},
     
  {0,1,1,1,0,
   0,0,0,0,1,
   0,1,1,1,0,
   0,0,0,0,1,
   0,1,1,1,0, -1},
     
  {0,1,0,0,0,
   0,1,0,0,0,
   0,1,0,1,0,
   0,1,1,1,1,
   0,0,0,1,0, -1}
};

public static int[][] testing = new int[][]{
  {0,1,1,1,0,
   1,0,1,0,1,
   1,0,0,0,1,
   1,1,0,0,1,
   0,0,0,1,0, -1},
     
  {0,0,1,0,0,
   1,1,1,0,0,
   0,0,1,0,0,
   1,0,1,0,0,
   0,1,1,1,0, -1},
     
  {0,1,1,1,0,
   1,0,0,1,0,
   0,0,1,0,0,
   0,1,0,0,0,
   1,1,1,1,1, -1},
     
  {0,1,1,1,0,
   0,0,0,0,1,
   0,1,1,1,0,
   0,0,0,1,1,
   0,1,1,1,0, -1},
     
  {0,1,0,0,0,
   0,1,0,0,0,
   0,1,0,1,0,
   0,1,1,1,1,
   0,0,0,1,0, -1}
};

public int[] target_output = {0,1,0,1,0};
public static double[] w = new double[training[0].length];
public int epochs = 25;
public double delta = 0;
public int CORRECT = 0;
public int deltaCount = 0;

/*----------The Perceptron Method----------*/
public void perceptron(int[][] input){
  
      for (int i = 0; i < w.length; i++){ //set weights to random between -1 and 1
    w[i] = Math.random()*2 - 1;
  }
      
  epoch:
  for(int i = 0; i<epochs; i++){                        // for each epoch
    
    for(int example = 0; example < (input.length); example++){       // for each training example
      
      int res = result(input[example]);                 // get result()
      //System.out.println(res);
      delta = target_output[example] - res;             // compute DELTA
      if (delta == 0){
        deltaCount ++; //if delta = 0, weight hasn't changed.
      }
      
      for(int k=0; k< (w.length) ; k++){
        w[k] += alpha*delta*input[example][k];          // update weight
        } 
      
      
      }
    System.out.printf("Epoch %s Delta is: %s, Weights W1: %.2f W2: %.2f W3: %.2f %n", i, delta, w[0], w[1], w[2]);
    if (deltaCount == input.length){ // checks to see whether all weights haven't changed
      System.out.println("");
      break epoch;
    } 
    deltaCount = 0; //if not all deltas equal 0, resets for next epoch
      
      
    }
  
  
  
  }
  
/*----------Get Results Method----------*/
  public int result(int[] in){
    int sum = 0;
    for(int i = 0; i < (training[0].length); i++) {
      sum += in[i]*w[i];
    }
      
      if(sum>=0){
        return 1;
      }
      else{
        return 0;
      }
  }
  
  
/*----------Main----------*/
  public static void main(String args[]){
    Perceptron p = new Perceptron();
    System.out.println("TRAINING:");
    p.perceptron(training);
    System.out.println("TESTING:");
    p.perceptron(testing);
    
    
  }
 }  
