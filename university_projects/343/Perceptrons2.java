import java.util.Random;
import java.util.ArrayList;

public class Perceptrons2 {
    static int example = 0;
    static int epochs = 0;
    static int correct = 0;  
    static int numExamples = 5;
    static int examples[][] = new int[numExamples][3];
    
  public static void main(String[] args) { 
    Random rand = new Random(); //random generator
    double[] weight = new double[3]; //array of weights
    for (int i = 0; i< 3; i++){
      weight[i] = rand.nextDouble();
      if (rand.nextBoolean()){
        weight[i] *= -1;
      }                         //sets weights to random between -1 and 1
    }
    
    
    for (int row = 0; row < numExamples; row++){
      for (int a = 0; a < 3; a++){
        examples[row][a] = rand.nextInt(2);
      }
    } 
    
    /*
    ArrayList<ArrayList<Integer>> examples = new ArrayList<ArrayList<Integer>>();

    for (int row= 0; row < 3; row++){
      examples.add(new ArrayList<Integer>());
    }
    
    
    for (int row= 0; row < 3; row++){
      for (int col = 0; col < numExamples; col++){
        examples.get(col).add(rand.nextInt(2));//creates random training examples
        System.out.println(examples.get(col));
      } 
    }
    */
   
   System.out.println(result(examples[0], weight)); //call/print perceptron
    
    
  }
  
  
  
  
  
  
  /** result returns output of perceptron */
  public static int result(int[] training, double[] weight){
    
    for (double d : weight){
      System.out.println("weights: " + d);
    }//prints weights
    
    System.out.println("a1 = "+ training[0]);
    System.out.println("a2 = "+ training[1]);
    System.out.println("output = "+ training[2]);

    
    
    double inSum = -1 * weight[0]; //bias
    

      for (int a: training){
        inSum = inSum + a * weight[a];
      }
      System.out.print("result: ");
      if (inSum >= 0){
        return 1;
      } else {
        return 0;
        
      }

  }
  
}