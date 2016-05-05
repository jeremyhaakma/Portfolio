/**
 * Etude 4
 * Sir Tet's Carpets
 * 
 * Jeremy Haakma
 * Emma Stocker
 * Rory Mearns
 * Scott Wyngaarden
 * 
 * Performs a dymanic programming approch to calculate the 
 * total number of combinations of carpet pieces to make a 
 * rectangular carpet.
 **/

/**
 * --------------------------------------------------------------------
 *                          SOME DEFINITIONS
 * --------------------------------------------------------------------
 * 
 * WIDTH:
 *  = X value
 *  = carpet[0].length
 *  = Column
 *  = startingX
 *
 * HEIGHT:
 *  = Y value
 *  = carpet.length
 *  = Row
 * 
 *  = startingY
 *
 * ARRAY:
 *  = carpet[height][width]
 *  = carpet[row][column]
 *  = carpet[y][x]
 *
 *  TO GET 'X' AND 'Y' VALUES:
 *  
 *  for (int row = 0; row < carpet.length; row++){
 *    for (int col = 0; col < carpet[0].length; col++){
 *      startingX = col;  
 *      startingY = row;
 *    }
 *  }
 *
 * --------------------------------------------------------------------
 */

import java.util.*;
import java.math.BigInteger;

public class etude4 {
 
  static int width;
  static int height;
  
  /* Array that stores the number of combinations that can 
   * make full carpets for each height of carpet */
  static int[] combinationsForRow;
  
  /* key: X: occupied, O: free space */
  static HashMap<String, BigInteger[]> map;
  static int currentSize = 0;
  
  /* Array of instructions on how to build each shape */
  static int[][] shapes = new int[][]{
    
      { 1, 0,  2, 0,  1, 1 },//  'T' 
      { 0, 1,  1, 1,  0, 2 },//  '|-'
      {-1, 1,  0, 1,  1, 1 },//  upsidedown 'T' 
      {-1, 1,  0, 1,  0, 2 },//  '-|'
        
      { 0, 1,  0, 2,  0, 3 },//   '|'
      { 1, 0,  2, 0,  3, 0 },//   '--'
      
      { 0, 1,  1, 1,  1, 2 },//   'S'
      {-1, 1,  0, 1,  1, 0 },//   'S' 90deg rotated
      { 1, 0,  1, 1,  2, 1 },//   'Z'
      { 0, 1, -1, 1, -1, 2 },//   'Z' 90deg rotated
      
      { 0, 1,  0, 2,  1, 2 },//   'L'  
      { 1, 0,  1, 1,  1, 2 },//   'L' upsidedown
      { 0, 1,  1, 0,  2, 0 },//   pistol pointing right
      { 0, 1, -1, 1, -2, 1 },//   'L' on its back
      { 0, 1,  0, 2, -1, 2 },//   'J'
      { 1, 0,  0, 1,  0, 2 },//   'J' upsidedown
      { 1, 0,  2, 0,  2, 1 },//   pistol pointing left
      { 0, 1,  1, 1,  2, 1 },//   'J' on its back
        
      { 1, 0,  0, 1,  1, 1 },//   square
        
    }; //end shapes
  
  public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in);
    String result;
    long time; //time to process result
     System.out.println("Enter carpet width (max width 6):");
      width = scanner.nextInt();
      System.out.println("Enter carpet height (max height 100):");
      height = scanner.nextInt();
      //Ensure carpet area is factor of 4
      if ( (width*height)%4 == 0){
        System.out.println("Calculating...");
        //Time the process
        long startTime = System.currentTimeMillis();
        result = nextScenario();
        long endTime = System.currentTimeMillis();
        time = (endTime - startTime);
        System.out.println("There are " + result + " possible combinations for a carpet of " +
                         "width " + width + " and height "+ height +". It took " + time + " milliseconds to calculate.\n");
   
      } else {
        System.out.println("There are 0 possible combinations for a carpet of " +
                         "width " + width + " and height "+ height +". The area of the carpet must be a factor of 4.");
        
      }
    
  } //end main

  /**
   *
   *
   */
  public static String nextScenario () {
    map = new HashMap<String, BigInteger[]>(6000);
    
    
    
    /* Check carpet dimensions are less than 6x100 */
    if( width > 100 || height > 100 ) {
      System.err.println("width and height must be < 6 and < 100 respectively");
      System.exit(0);
    }
    
    /* Initialise size of array of combinations */
    boolean[][] initialCarpet = new boolean[height][width];
    for (int i = 0; i < initialCarpet.length; i ++){
      for (int j = 0; j < initialCarpet[0].length; j++){
        initialCarpet[i][j] = true;
      }
    }
    
    // Start recursion, then print solution
    BigInteger solution = solve(initialCarpet);
    return solution.toString();

  }

  /**
   * Solves "how many unique ways can we create
   * this carpet using tetrominoes" 
   *
   * @param carpet: 2D Boolean array representing a carpet state
   * @return BigInteger: The number of ways to create this carpet
   */
  public static BigInteger solve(boolean[][] carpet){
    
    // Check if carpet is empty, if so, return 1
    boolean isEmpty = true;
    for(boolean[] row : carpet){
      for(boolean b : row){
        if (b){isEmpty = false;}
      }
    }
    if (isEmpty){
      return new BigInteger("1");  
    }
    
    /* This is broken! I don't know if we need it though(?) it appears to work fine without */

    // Find the number of full rows in shape
     int fullRows = 0;
     for(int row = carpet.length-1; row >= 0; row--){
       int rowBlocks = width;
       for(int i = 0; i < carpet[0].length; i++){
         if (carpet[row][i]){
           rowBlocks--;
         }
       }
       if (rowBlocks == 0){
         fullRows++;
       } else break;
     }

    
    
    // Find the fringe, convert to String key then trim the O's from the end
    String fringe = "";
    for (int i = carpet.length-fullRows-1; i >= 0; i--){ // **CHANGED** from "carpet.length-fullrows-1"
      for(int j = 0; j < width; j++){
        if(carpet[i][j]){
          fringe = fringe + "X";
        } else {
          fringe = fringe + "O";
        }
      }
    } 

    // System.out.println("Fringe length: "+fringe.length()); // Testing
      //Had to wrap this function in an 'if' statement, some fringes were 0 length
      while(fringe.length() > 0 && fringe.charAt(fringe.length() - 1) == 'O'){
        fringe = fringe.substring(0, fringe.length() - 1);
      }
      //printCarpet(carpet);
      
    
    // Check if current shape is already in the hash map 
    if(map.containsKey(fringe)){
      if(map.get(fringe)[fullRows] != null){
        //System.out.println("found fringe " + fringe + " at fullRow: " + fullRows + " with value " + map.get(fringe)[fullRows]);
        return map.get(fringe)[fullRows];
      }
    }
    
    /* Add shapes and call solve() on results */
    BigInteger values = new BigInteger("0");
    for (int[] shape : shapes){
      boolean[][] newCarpet = removeShape(carpet, shape);
      if(newCarpet != null){
        values = values.add(solve(newCarpet));
      }
    }
    if (!map.containsKey(fringe)){
      map.put(fringe, new BigInteger[height + 1]);
    }
    //printCarpet(carpet);
    map.get(fringe)[fullRows] = values;
    
    return values;
  }

  
  /**
   * Removes a shape from a carpet and returns
   * a new carpet with that piece removed
   *
   * @param c: Carpet piece will be removed from
   * @param shape: Shape to remove from the carpet
   * @return boolean[][]: Array representing the new carpet with 
   * shape removed, null if unable to remove shape from the carpet
   */
  public static boolean[][] removeShape(boolean[][] c, int[] shape){
    
    // Clone the incomming carpet
    boolean[][] carpet = new boolean[height][width];
    for (int row = 0; row < c.length; row++){
      for (int col = 0; col < c[0].length; col++){
        carpet[row][col] = c[row][col];
      }
    }

    // Find the "top-most-left-most" position still tiled
    int row = 0;
    int col = 0;
    outerloop:
    for (row = 0; row < carpet.length; row++){
      for (col = 0; col < carpet[0].length; col++){
        if (carpet[row][col]){
          break outerloop;
        }
      }
    }
    
    // Set the root position of each tile to be false
    carpet[row][col] = false;

    // Go through each co-ordinate in the shape checking for overlap & out of bounds
    for(int i = 0; i < shape.length; i+=2){
      
      //check not out of bounds
      if ((shape[i] + col) < 0 || (shape[i] + col) >= carpet[0].length) {
       return null; // x is out of bounds
      }
      if (shape[i+1] + row >= carpet.length) {
        return null; // y is out of bounds
      }
      
      // Check if space is filled 
      if (carpet[ (shape[i+1]+row) ][ (shape[i]+col) ] == true) {
        carpet[ (shape[i+1]+row) ][ (shape[i]+col) ] = false;
      } else { 
        return null; // shape overlap
      }
    }
    
    return carpet;
  }
  
  /**
   * Prints a carpet to stdout for testing purposes
   *
   * @param c: Carpet to be printed out
   */
  public static void printCarpet(boolean[][] c) {
    System.out.println("carpet: ");
    for (boolean[] row : c){
      for(boolean b : row){
        if (b){
          System.out.print("X");
        } else{
          System.out.print(".");
        }
      }
      System.out.print("\n");
    }
    System.out.print("\n");
  }
}

