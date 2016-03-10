import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
import java.text.DecimalFormat;
/**
 * Creature class
 * When an instance of Creature is created, it calls its parent (EvoItem) class
 * constructor, which sets its x and y values, both for its array position and 
 * graphical location (which is array position * Environment.squareSize).
 */
public class Creature extends EvoItem{
/***DATA FIELDS***/
  
  /** mutation: Mutation rate, between 0 and 1. Higher rate: more mutations occur */
  static double mutation = .003;//0.005
  
  /** energy: energy of a given creature, initialised to max Energy when a creature is born*/
    int energy;
    
  /** MAXENERGY: Maximum energy a creature can have */
  final static int MAXENERGY =  Environment.steps - (Environment.steps/5);
  
  /** sustinance and potency: How much food/poison ++/-- energy, respectively*/
  static int sustinance = MAXENERGY/20;//12
  static int potency = MAXENERGY/3;//20
    

  /** dead: true if creature has died */
  boolean dead = false;

  /**Chromosome values (genes) */
  /** doEatFood/doEatPoison: boolean is true if creature does eat, false if it doesn't */
  boolean doEatFood; //associated weight: weights[0]
  boolean doEatPoison;// associated weight: weights[1]
  
  /** moveWhen*: The following four genes determine how the creature moves when encountering a
    * thing on the map. 
    * They are placed into an array movements[], which is what is actually used for processing.
    * Int values correspond to: 0 = towards, 1 = away, 2 = random, 3 = ignore*/
  
  int moveWhenFood;//associated weight: weights[2], movements[0]
  int moveWhenPoison;//associated weight: weights[3], movements[1]
  int moveWhenMonster;//associated weight: weights[4], movements[2]
  int moveWhenCreature;//associated weight: weights[5], movements[3]
  int[] movements = new int[] { moveWhenFood, moveWhenPoison, moveWhenMonster, moveWhenCreature};
  
  /** moveDefault: how creature moves when no other action is set. 
    * Int values correspond to: 0 = random, 1 = north, 2 = east, 3 = south, 4 = west */
  int moveDefault;
  
  /**weights: between 0 and 1, heighest weight takes action priority
    * Each weight associated with another gene (see comments next to each gene)*/
  double[] weights = new double[6];
  
  
  
  /** actionList: Actions are added to this list, sorted by weight, and one is initiated */ 
  ArrayList<Action> actionList = new ArrayList<Action>();
  
  //end datafields
  
  
/**CONSTRUCTORS**/
  
  /** Constructs a new creature with random properties*/
  public Creature(){
    super();
    Random r = new Random();
    energy = MAXENERGY;  
    //set random actions
    //Eat actions:
    doEatFood = r.nextBoolean();
    doEatPoison = r.nextBoolean();
    //Movement actions:
    for (int i = 0; i < 4; i++){
      movements[i] = r.nextInt(4);//between 0 and 3
    }
    //Default movement:
    moveDefault = r.nextInt(5);//between 0 and 5
    //Weights:
    for (int i = 0; i < 6; i++){
      weights[i] = r.nextDouble();
    }
    //sight
    sight = r.nextInt(MAXSIGHT)+1;
  }//end default constructor
  
  /** Constructs a new creature from two parents.
    * Utilises crossover and mutation.
    * **CROSSOVER**: a random int between 0 and 13(exclusive) determines the crossover
    * point. For each gene, if the crossover point is before it's
    * location on the chrosome, it will inherit the father(p1) gene, otherwise
    * it will inherit the mother(p2) gene. 
    * **MUTATION**: using a double (set as a data field) as the mutation rate:
    * for each gene, there is a chance the gene will be randomly set, irrespective
    * of parents
    * @param p1 first parent(father), chosen from list of successful forbearers
    * @param p3 second parent(mother), chosen from list of successful forbearers
    */
  public Creature(Creature p1, Creature p2){
    super();   
    Random r = new Random();
      energy = MAXENERGY;
      int crossPoint = r.nextInt(14);//location of crossover in chromosome
   //eatfood
    if (r.nextDouble() < mutation){//chance of mutation
      doEatFood = r.nextBoolean(); //random
    } else if ( crossPoint <= 0){ //if to the left of crossover point = p1
      doEatFood = p1.doEatFood;
    } else {                      //if to the right of crossover poit = p1
      doEatFood = p2.doEatFood;
    }
   //eat poison
    if (r.nextDouble() < mutation){//mutation
      doEatPoison = r.nextBoolean();
    } else if (crossPoint <= 1){
      doEatPoison = p1.doEatPoison;
    } else{
      doEatPoison = p2.doEatPoison;
    } 
   //movement genes
    for (int i = 0; i < 4; i++){
      if (r.nextDouble() < mutation){
        movements[i] =  r.nextInt(4);//mutation
      } else if (crossPoint <= i+2){//
        movements[i] = p1.movements[i];
      } else{
        movements[i] = p2.movements[i];
      }
    }
   //default move
    if (r.nextDouble() < mutation){
      moveDefault =  r.nextInt(4);//mutation
    } else if (crossPoint <= 6){
      moveDefault = p1.moveDefault;
    } else{
      moveDefault = p2.moveDefault;
    }
   //weights
    for (int i = 0; i < 6; i++){
      if (r.nextDouble() < mutation){
        weights[i] =  r.nextDouble();//mutation
      } else if (crossPoint <= 6+i){
        weights[i] = p1.weights[i];
      } else{
        weights[i] = p2.weights[i];
      }
    }
    //sight
    if (r.nextDouble() < mutation){
        sight = r.nextInt(MAXSIGHT)+1;
      } else if (crossPoint <= 13){
        sight = p1.sight;
      } else{
        sight = p2.sight;
      }
    
  }//end creature constructor with parents
//end constructors
  
/***ACCESSORS***/
  /** getEnergy: used by Environment.getAverageEnergy() */
    public int getEnergy(){
    return energy;
    }
//end accessors  
  
/***METHODS***/
    
    /** move:
      * Moves creature in specified direction .
      * If direction is a diagonal, this method randomly chooses either a horizontal 
      * or vertical movement
      * @param d Direction in which to move
      */
    private void move(Direction d){
      Direction direction = d;
      Random r = new Random();
      switch (direction) {
        case EAST: 
          if (xPos < Environment.getGridX()-1){
          setXY(xPos+1, yPos);
        } break;
        case WEST: 
          if (xPos > 0){
          setXY(xPos-1, yPos);
        }
          break;
        case SOUTH:
          if (yPos < Environment.getGridY()-1){
          setXY(xPos, yPos+1);
        } break;
        case NORTH: 
          if (yPos > 0){
          setXY(xPos, yPos-1);
        } break;
        //corner cases: randomly picks one of two directions
        //e.g if NORTHEAST, pick either NORTH or EAST
        case NORTHEAST: 
          if (r.nextBoolean()){
          if (yPos > 0){
            setXY(xPos, yPos-1);//50% chance move north
          } 
        } else if (xPos < Environment.getGridX()-1){//50% go east
          setXY(xPos+1, yPos);
        } break;
        case NORTHWEST: 
          if (r.nextBoolean()){
          if (yPos > 0){
            setXY(xPos, yPos-1);//50% chance move north
          } 
        } else if (xPos > 0){
          setXY(xPos-1, yPos);//50% move west
        } break;
        case SOUTHEAST: 
          if (r.nextBoolean()){
          if (yPos < Environment.getGridY()-1){//50% move south
            setXY(xPos, yPos+1);
          }
        } else if (xPos < Environment.getGridX()-1){//50% move east
          setXY(xPos+1, yPos);
        } break;
        case SOUTHWEST: 
          if (r.nextBoolean()){
          if (yPos < Environment.getGridY()-1){//50% move south
            setXY(xPos, yPos+1);
          }
        } else if (xPos > 0){
          setXY(xPos-1, yPos);//50% move west
        } break;
      }
    }//end move method
  
  /** die:
    * This kills the creature.
    * Called when eaten by a monster or creature runs our of energy
    */ 
  private void die(){
    //System.out.println("dead");
    energy = 0;
    dead = true;
    Environment.creatureArray[xPos][yPos]--;
  }//end die method
  
  
  /** act:
    * Performed once per tick 
    * This is the method that a creature performs every cycle to determine its
    * next move. Does nothing if creature is dead.
    * 1. kills creature if in the same spot as a monster
    *   (Would have been nice to have this in Monster class, but very difficult with this setup)
    * 2. calls lookfor method on food, creature, monsters
    *  adds actions to actionList based on what it finds
    * Finally, kills creature if it has run out of energy
    */
  public void act(){
    if (!dead){
      //if eaten by a monster then die
      if (Environment.monsterArray[xPos][yPos] > 0){
        die();
      }
     //These next two create new Actions using the (bool, double) constructor, which creates an "eat" action
      //the boolean is true for an eat food action, false for eat poison action
      
      //Look for food to eat
      if (doEatFood && Environment.foodArray[xPos][yPos] > 0){//if gene set to eat food and there is some
        //System.out.println("eat food");
        actionList.add(new Action(true, weights[0]) );//adds a new action to the actionList
      }                                               //params(bool, double) determines 'eat', true means eat *food*
      //look for poison to eat
      if (doEatPoison && Environment.poisonArray[xPos][yPos] > 0){//if gene set to eat poison and there is some
        actionList.add(new Action(false, weights[1]) );//params(bool, double) determines 'eat', false means eat *poison*
      }                                                //see Action class for constructor details
      
     //The following create new Actions using the (Direction, double) constructor, which codes a 'move' action
      
      //look for food to move
      Direction foodDir = lookFor("Food");//lookFor returns Direction of food, or null if no food is found
      if (foodDir != null){//if there is food
        if (movements[0] == 0 ){//if gene is set to move towards food
          actionList.add(new Action(foodDir, weights[2]) );
        } else if (movements[0] == 1){//gene set to move away from food
          actionList.add(new Action(foodDir.opposite(), weights[2]) );
        } else if (movements[0] == 2){//gene set to move randomly
          actionList.add(new Action(weights[2]) );
        } else if (movements[0] == 3){ /* ignore: do nothing */}
      } 
      
      //look for poison to move
      Direction poisonDir = lookFor("Poison");
      if (poisonDir != null){
        if (movements[1] == 0 ){//if gene is set to move towards poison
          actionList.add(new Action(poisonDir, weights[3]) );
        } else if (movements[1] == 1){//gene set to move away from poison
          //System.out.println("move away from poison");
          actionList.add(new Action(poisonDir.opposite(), weights[3]) );
        } else if (movements[1] == 2){//gene set to move randomly
          actionList.add(new Action(weights[3]) );
        } else if (movements[1] == 3){ /* ignore: do nothing */}
      }
      
      //look for monster
      Direction monsterDir = lookFor("Monster");
      if (monsterDir != null){
        if (movements[2] == 0 ){//if gene is set to move towards monster
          //System.out.println("move towards monster");
          actionList.add(new Action(monsterDir, weights[4]) );
        } else if (movements[2] == 1){//gene set to move away from monster
          //System.out.println("move away from monster");
          actionList.add(new Action(monsterDir.opposite(), weights[4]) );
        } else if (movements[2] == 2){//gene set to move randomly
          actionList.add(new Action(weights[4]) );
        } else if (movements[2] == 3){ /* ignore: do nothing */}
      }
      
      //look for creature
      Direction creatureDir = lookFor("Creature");
      if (creatureDir != null){
        if (movements[3] == 0 ){//if gene is set to move towards creature
          actionList.add(new Action(creatureDir, weights[5]) );
        } else if (movements[3] == 1){//gene set to move away from creature
          actionList.add(new Action(creatureDir.opposite(), weights[5]) );
        } else if (movements[3] == 2){//gene set to move randomly
          actionList.add(new Action(weights[5]) );
        } else if (movements[3] == 3){ /* ignore: do nothing */}
      }
      
     //This is where the creature actually acts! First it checks whether it has any actions lined up, 
      //if not then it performs its default movement. If there are actions in the actionList, it sorts them
      // by weight and performs the act with the strongest weight. 
      
      //default if no actions are queued
      if (actionList.isEmpty()){
        if (moveDefault == 0){//if default is random
          moveRandom();
        } else if (moveDefault == 1){//default is NORTH
          move(Direction.NORTH);
        } else if (moveDefault == 2){//default is EAST
          move(Direction.EAST);
        }else if (moveDefault == 3){//default is SOUTH
          move(Direction.SOUTH);
        }else if (moveDefault == 4){//default is WEST
          move(Direction.WEST);
        }
      } else {//sort list by weights, highest to lowest
        java.util.Collections.sort(actionList, new Comparator<Action>(){
          @Override
          public int compare(Action a1, Action a2) {
            if (a1.getWeight() < a2.getWeight()) return -1;
            if (a1.getWeight() > a2.getWeight()) return 1;
        return 0;
          }
        });
     //perform actions with highest weight
        actionList.get(0).initiate();
        //clear actionList
        actionList = new ArrayList<Action>();
      }
      //reduce energy
       energy--;
       //if energy depleted, die
      if (energy <= 0){
        die();
      }
      //Energy can not exceed Max energy
      if (energy > MAXENERGY*2){
        energy = MAXENERGY*2;
      }
    }
  }//end act method
  
  /** overridden toString method:
    * Prints out chromosome information
    * @return chromosome: String detailing chromosome info
    */
  public String toString(){
    DecimalFormat df = new DecimalFormat(".###");
    String chromosome = "";
    String isAlive = dead ? "***DEAD***" : "***ALIVE***";
    chromosome += 
      isAlive + 
      "\n eat food: " + doEatFood + " , weight: " + df.format(weights[0]) +
      "\n eat poison: " + doEatPoison + " , weight: " + df.format(weights[1]) + 
      "\n move(0 = move towards; 1 = move away; 2 = random; 3 = ignore)" +
      "\n    food: " + movements[0] + ", weight: " + df.format(weights[2]) + 
      "\n    poison: " + movements[1] + ", weight: " + df.format(weights[3]) + 
      "\n    Monster: " + movements[2] + ", weight: " + df.format(weights[4]) + 
      "\n    Creature: " + movements[3] + ", weight: " + df.format(weights[5]) + 
      "\n move default(0 = random; 1, 2, 3, 4 = N, E, S, W): " + String.valueOf(moveDefault) + 
      "\n energy: " + Integer.toString(energy) + 
      "\n sight distance: " + sight + "\n";
    return chromosome;
  }
  
  
  /** paint method determines appearance of creature */
  public void paint (Graphics g){
    

    if (!dead){
      Color colour = Color.yellow; //creature fill colour
      g.setColor(colour);
      g.fillOval(xDraw, yDraw, size, size);
      g.setColor(Color.black);
      g.drawOval(xDraw, yDraw, size, size);
    }
  }//end paint
//end methods  
  
/***INNER CLASSES***/    
  
/** This inner class is an 'action', either a movement or 
    * the act of eating food/poison. Each action is initialised, added
    * to the actionList, and one action from the list is chosen randomly each turn
    * to be performed.
    * The parameters determine whether the action is an "eat", "move", or "move random" action
    * In all cases the double is the weight of the action
    * "Eat" method is sent (boolean, double). boolean EatFood ? eat food : eat poison
    * "Move" method is sent (Direction, double). Direction: where to move
    * "move random" is (double), just the weight of the action. 
    * 
    * This method also uses the data fields sustinence and potency. These are how much
    * food and poison incrase or reduce energy levels, respectively
    */
  public class Action{
    Direction dir;//if eat action, direction is null
    double weight;
    boolean eatFood;
    boolean random = false;
    //constructor for eating action
    public Action(boolean eatFood, double d){//true eatfood, false eastpoison
      this.eatFood = eatFood;
      
      
    }
    //constructor for movement action
    public Action(Direction dir, double d){//constructor for move
      this.dir = dir;
      this.weight = d;
    }
    
    //constructor for random movement
    public Action(double d){
      random = true;
      dir = Direction.NORTH;//this is just so that the direction is not null
    }
    public double getWeight(){
      return weight;
    }
    
    //method to intiate action based on what kind of action was constructed
    public void initiate(){
      if (random){//if action is random movement
        moveRandom();
      } else if (dir == null){//if no directions set: action is "eat"
        if (eatFood){//if eat food
          Environment.foodArray[xPos][yPos]--;
          energy += sustinance;//eat food and increase energy
        } else if (!eatFood){//if eat poison
          Environment.poisonArray[xPos][yPos]--;
          //this differs from the specifications: poison now reduces energy
          energy -= potency;
          //die(); //original result of eating poison
        }
      } else {//otherwise a move action, call move method
        move(dir);
      }
    }
  }//end Action class
  
  
}//end class