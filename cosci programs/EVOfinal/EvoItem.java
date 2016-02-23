import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

/** EvoItem class
  * This is the parent class for all items that can be drawn on the map.
  * This includes creatures, monsters, food and poison. 
  * This class holds data fields and methods that are used by multiple classes. 
  * These include: 
  *  - Direction: Enumerated type that holds x y relative coordinates for the 8 possible directions
  */
public class EvoItem extends JFrame{
  /***DATA FIELDS***/
  /** xPos and yPos: array location of item*/
  int xPos;
  int yPos;
  
  /** Size of item when animated. setXY() sets this to Environment.squareSize */
  int size;
  
  /**xDraw and yDraw: map location when animated (set by *ing xPos/yPos by size in setXY() ) */
  int xDraw;//xDraw and yDraw = used for graphical representation
  int yDraw;
//end data fields
  final int MAXSIGHT = 5;
  int sight;
  
  /**Direction:
    * Each direction holds the relative xy coordinates for that direction
    * Used for move methods in both Creature and Monsters classes.
    */
  enum Direction{
    NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0), 
      NORTHEAST(1, -1), NORTHWEST(-1, -1), SOUTHEAST(1, 1), SOUTHWEST(-1, 1);
    int x; 
    int y;
    
    Direction (int x, int y){
      this.x = x;
      this.y = y;
    }
    public Direction opposite() {
      switch(this) {
        case NORTH: return Direction.SOUTH;
        case SOUTH: return Direction.NORTH;
        case EAST: return Direction.WEST;
        case WEST: return Direction.EAST;
        case NORTHEAST: return Direction.SOUTHWEST;
        case NORTHWEST: return Direction.SOUTHEAST;
        case SOUTHWEST: return Direction.NORTHEAST;
        case SOUTHEAST: return Direction.NORTHWEST;
        
        default: throw new IllegalStateException("This should never happen: " + this + " has no opposite.");
      }
    }
  }//end Direction enum
  
  /***CONSTRUCTOR***/
  public EvoItem(){
    Random r = new Random();
    //set size
    size = Environment.getGridSize();
    int x = r.nextInt(Environment.getGridX());
    int y = r.nextInt(Environment.getGridY());
    setXY(x, y);
  }
  /***ACCESSORS***/
  public int getX(){
    return xPos;
  }
  public int getY(){
    return yPos;
  }
  /***MUTATORS***/
  public void setXY(int x, int y){
    int oldX = xPos;
    int oldY = yPos;
    this.xPos = x;
    this.yPos = y;
    this.xDraw = x*size + 10;
    this.yDraw = y*size + 10; //all y cords +50 
    Environment.moveItem(oldX, oldY, xPos, yPos, this);
  }
  
  /***METHODS***/
  
  /** moveRandom:
    * This does not use the 'Direction' enum. Instead, it generates a random int
    * between 0 and 3, and changes direction according to the switch case.
    */
  public void moveRandom(){
    Random r = new Random();
    int direction = r.nextInt(4);//NOT Direction enum!
    int oldX = xPos;
    int oldY = xPos; 
    switch (direction) {
      case 0: 
        if (xPos < Environment.getGridX()-1){
        setXY(xPos+1, yPos);
        break;
      } else {
        direction++;
      }
      case 1: 
        if (xPos > 0){
        setXY(xPos-1, yPos);
        break;
      } else {
        direction++;
      }
      case 2:
        if (yPos < Environment.getGridY()-1){
        setXY(xPos, yPos+1);
        break;
      } else {
        direction++;
      }
      case 3: 
        if (yPos > 0){
        setXY(xPos, yPos-1);
        break;
      } else {
        direction++;
      }
    }
  }//end moverandom method
  
  
  /** lookFor:
    * look for an EvoItem, returns the direction it is found in.
    * If multiple EvoItems are found, returns one at random.
    * @param item A String representing the name of the item to be searched.
    * @return dir Direction of one of the found items, or null if nothing found
    */
  public Direction lookFor(String item){ 
    //For every item found in this method, its direction is placed in this arrayList
    ArrayList<Direction> found = new ArrayList<Direction>();
    
    //looking in every direction: N, S, E, W, NE, NW, SE, SW.
    for (Direction dir : Direction.values()){
      //x and y: location of where we are looking. 
      
      for (int i = 1; i <= sight; i++){
        int x = xPos + dir.x*i;//current position  -1 for west, +1 for east
        int y = yPos + dir.y*i;//current position -1 for north, +1 for south
        
        //while x and y are not out of bounds
        if (x > 0 && y > 0 && x < Environment.getGridX() && y < Environment.getGridY()){
          //parameter String should equal one of these!
          if (item.equals("Creature")){
            if (Environment.creatureArray[x][y] > 0){
              found.add(dir);
            }
          } else if (item.equals("Monster")){
            if (Environment.monsterArray[x][y] > 0){
              found.add(dir);
            }
          } else if (item.equals("Food")){
            if (Environment.foodArray[x][y] > 0){
              found.add(dir);
            }
          } else if (item.equals("Poison")){
            if (Environment.poisonArray[x][y] > 0){
              found.add(dir);
            }
          } else {
            System.out.println("Invalid String: must be \"Creature\", \"Monster\", \"Food\" or \"Poison\"");
          }
        }
        
      }
      //More than one item may have been found; 
      //Shuffle the directions so that one can be picked from random
      java.util.Collections.shuffle(found, new Random());
      if (!found.isEmpty()){
        return found.get(0);
      }
    }
    return null;
    
  }//end lookFor method
  
}//end class



