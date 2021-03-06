import java.awt.*;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
/**
 * Environment class
 * This class is responsible for creating, organising, and drawing the creature simulation.
 * It has static data fields, however an instance of this, called 'map', is constructed
 * in EvoApp when the world is generated, so that it can be drawn. 
 */
public class Environment extends JPanel{
/***DATA FIELDS***/
  /** numParents: Size of pool of parents used to create new gen of creatures.
    * Recommended to be set to around half of creatureNum. */
  static int numParents = 10; //~half creatureNum
  
    /** monsterSpeed: How many steps it takes a monster to mave one movement*/
  static int monsterSpeed =2;//5
  
  /** time: time between each step when animated. */
  static int time = 150;//150
  
  /** steps: how many steps in a single generation. */
  static int steps = 100; //100
  
  /** Number of creatures born each generation. */
  static int creatureNum = 25; //20
  
  /** Number of monsters born each generation. */
  static int monsterNum = 20; //20
  
  /** Number of food created each generation. */
  static int foodNum = 400; //700
  
  /** Number of poison created each generation. */
  static int poisonNum = 20; //400
  
  /** squareSize: Size of each grid position when animated. */
  static int squareSize = 15;//recommend 15 to 20
  
  /** gridX and gridY: Size of map. */
  static int gridX = 50; static int gridY = 50;// ~40
  
  /** energyList: used by EnergyGraph.java to plot avg energy values per generation */
  static ArrayList<Integer> energyList = new ArrayList<Integer>();//used for graph display
  
  /** animate: true if current generation is to be animated */
  static boolean animate = true;
  
  /** mapLength and mapHeight: mapLength = squareSize*gridX. mapheight = squareSize*gridY.
    * Used for graphical representation. */
  static int mapLength; static int mapHeight;
  
  /** These hold the instances of creatures and monsters */
  static Creature[] creatureList = new Creature[creatureNum]; 
  static Monster[] monsterList = new Monster[monsterNum];
  
  /** These arrays hold how many of each item are in a given xy position 
    * They are used primarily for drawing them on the map */
  static public int[][] creatureArray = new int[gridX][gridY];
  public static int[][] monsterArray = new int[gridX][gridY];
  public static int[][] foodArray = new int[gridX][gridY];
  public static int[][] poisonArray = new int[gridX][gridY];
//end data fields
  
/***CONSTRUCTOR***
    * Creates the size of the map based on the grid array sizes and the
    * data field 'squareSize', which is the size of each position on the map
    */
  public Environment(){
    mapLength = gridX * squareSize + 20;
    mapHeight = gridY * squareSize + 20;
    generate(true);
    setPreferredSize(new Dimension(mapLength, mapHeight));
  }//end default constructor
  
  
/***Accessors***/
  
  /** getGridX */
  public static int getGridX(){
    return gridX;
  }
  /** getGridY */
  public static int getGridY(){
    return gridY;
  }
  /** getGridSize */  
  public static int getGridSize(){
    return squareSize;
  } 
/**return the average energy of all creatures*/
  public static int getAverageEnergy(){
    int creatureCount = 0;
    int totEnergy = 0;
    int avEnergy = 0;
    int eatFoodCount = 0;
    for (Creature c: creatureList){
      totEnergy += c.getEnergy();
      creatureCount++;
      if (c.doEatFood){
      eatFoodCount++;
      }
    }
    //return totEnergy;
    return totEnergy / creatureCount;
  }
  //end accessors
  
/***MUTATORS***/
  
  /** moveItem: Updates the creature/monster arrays when they move.
    * Creature or Monster sends as parameters their old xy values, new
    xy values, and themselves to this method to be moved. */
  public static void moveItem(int x, int y, int newX, int newY, EvoItem e){
    if (e instanceof Creature){
      creatureArray[x][y]--;
      creatureArray[newX][newY]++;
    } else if (e instanceof Monster){
      monsterArray[x][y]--;
      monsterArray[newX][newY]++;
    }
  }//end moveItem
  
/***METHODS***/
  
/** Generate method makes new map 
  * @param init is true only for the first generation, which creates random 
  * creatures. Afterwards, when init is false, creatures are created from their
  * parents, who are determined by their energy levels at the end of the last 
  * generation.
  * Monsters, food and poison are always placed randomly.
  * Monsters/creatures can be placed randomy
  * Food can not be placed in a space containing poison and visa versa
  */
  public void generate(boolean init){
    Random r = new Random();
  //initialise creatures first time
    if (init == true){
      for (int i = 0; i < creatureNum; i++){
        creatureList[i] = new Creature();
      }
    } else {//end initial setup
  //create children creatures:
     //Sorts last gens creatures by energy level, and places those with the 
      //highest energy into a new parentList array, whose size is set by the 
      //datafield numParents, which is usually set to half the population.
      Arrays.sort(creatureList, new CreatureComparator()); //sort by energy level  
      Creature[] parentList = new Creature[numParents];//new array of parents

      for (int i = 0; i < numParents; i++){
        parentList[i] = creatureList[creatureNum -i-1]; //fill parents array with best
      }
      for (int i = 0; i<creatureNum; i++){ 
        Collections.shuffle(Arrays.asList(parentList)); //shuffle parent array
        Creature p1 = parentList[0];
        Creature p2 = parentList[1];
        creatureList[i] = new Creature( p1, p2 ); //new creature with random parents
      }
    } //end children setup
  //initialise monsters
    for (int i = 0; i < monsterNum; i++){
      monsterList[i] = new Monster();
    }
  //place monsters and creatures into arrays
    creatureArray = new int[gridX][gridY];
      monsterArray = new int[gridX][gridY];
      for (Creature c : creatureList){
        creatureArray[c.getX()][c.getY()]++;
      }
      for (Monster m : monsterList){
        monsterArray[m.getX()][m.getY()]++;
      }   
   //initialise food and poison;
      foodArray = new int[gridX][gridY];
      poisonArray = new int[gridX][gridY];
   //places poison randomly around map
      int poisonCount = 0;
      while (poisonCount < poisonNum){
        //if food array is empty: food and poison can't occupy same space!
        if (foodArray[r.nextInt(gridX)][r.nextInt(gridY)] == 0){
          poisonArray[r.nextInt(gridX)][r.nextInt(gridY)]++;
          poisonCount++;
        }
      }
   //places food randomly around map  
      int foodCount = 0;
      while (foodCount < foodNum){
        //if poison array is empty: food and poison can't occupy same space!
        if (poisonArray[r.nextInt(gridX)][r.nextInt(gridY)] == 0){
          foodArray[r.nextInt(gridX)][r.nextInt(gridY)]++;
          foodCount++;
        }
      }
  }//end generate method
  
/** run: runs the simulation through a single generation
  * A generation is made of an iteration of multiple 'steps'
  * This method is run through a loop of successive generations in EvoApp
  * A boolean 'animate' is set to true whenever the generation is to be animated.
  * This method automatically sets 'animate' to false after running once. 
  * Notable data fields used:
  *  steps: number of iterations in a single generation
  *  time: in ms, the approx time between steps/frames in animation
  */
  public void run(){
    for (int i = 0; i<steps; i++){
      //creatures act first
      for (Creature c : creatureList){
        c.act();
      }
      //then monsters move
      for (Monster m : monsterList){
        if (i%monsterSpeed==0){//every 3rd timestep
          m.moveMonster();
        }
      }
      //wait and repaint if animation is turned on
      if (animate){//animation uses a cheap Thread.sleep() method...
        try {
          Thread.sleep(time); //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        repaint();//redraws the map
      }    
    }//end iteration of steps
    animate = false;
  }//end run method
 
  
/** paintComponent method
  *This is where the entire map is drawn from. It creates new creature, monster, 
  * food and poison items and calls their paint methods to place them on the map.
  */
  public void paintComponent (Graphics g){
   //draw BG
    g.setColor(new Color(50, 150, 50));
    g.fillRect(0, 0, mapLength, mapHeight);
   //draw food/poison
    for (int x = 0; x < gridX; x++){
      for (int y = 0; y < gridY; y++){
        if (foodArray[x][y]>0){ //if there is food in this spot
          Food food = new Food(x, y);
          food.paint(g);
        } else if (poisonArray[x][y]>0){//if there is poison in this spot
          Poison poison = new Poison(x, y);
          poison.paint(g);
        }
      }
    }
   //draw creatures
    for (Creature c : creatureList){
      c.paint(g);
    }
   //draw monsters
    for (Monster m : monsterList){
      m.paint(g);
    }
  }//end paint component method
//end methods
}//end class