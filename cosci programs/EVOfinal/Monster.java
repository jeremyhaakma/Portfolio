import javax.swing.*;
import java.awt.*;
import java.util.Random;
/** Monster class
  * A big scary monster that chases creatures and eats them. 
  * A monster has fairly simple behaviour: 
  * It looks for a creature in its immediate vicinity;
  * If it finds one, it moves towards it;
  * If it doesn't find one, it moves in a random direction.
  */
public class Monster extends EvoItem{
/***CONSTRUCTORS***/
  
  
  /** Constructs a new monster with random Coordinates*/
  public Monster(){
    super();
    sight = MAXSIGHT+1;
  }
//end constructors

  /***METHODS***/
  
  /** MoveMonster: 
   * Monster moves towards nearest creature, otherwise randomly moves around the map */
  public void moveMonster(){
    Direction d = lookFor("Creature");
    if (d != null){
      moveToCreature(d);
      
    } else{
      moveRandom();
    }  
  }//end moveMonster
  
    
  /** moveToCreature:
   * Move method moves creature in specified direction 
    * @param d Direction in which to move
    * With this method, if a monster hits a wall it just doesn't move.
    * (This is basically identical to the move method in the Creature class. The reason I
    * didn't just put it in the EvoItem parent class is because the name 'move' clashes with a JFrame
    * method and I'm too lazy to rename it)
    */
  private void moveToCreature(Direction d){
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
  }//end moveToCreature
  
  /** paint: 
   * paint method determines appearance of Monster */
  public void paint (Graphics g){
    int monSize = size;
    Color colour = Color.red; //monster fill colour
    g.setColor(colour);
    g.fillRect(xDraw, yDraw, monSize, monSize);
    g.setColor(Color.black);
    g.drawRect(xDraw, yDraw, monSize, monSize);
    
  }//end paint
  
}//end class