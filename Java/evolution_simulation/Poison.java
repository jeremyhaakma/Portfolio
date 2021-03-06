import javax.swing.*;
import java.awt.*;
/**
 * This class is primarily used to contain the paint method
 * used to draw poison objects onto the JPanel map. The methods
 * to place these items on the map randomly are located in its parent
 * class, EvoItem.
 */
public class Poison extends EvoItem{
  
  /***CONSTRUCTORS***/
  
  /** 
   * Deafault constructor, should never be used
   */ 
  public Poison(){
    System.out.println("default food constructor should not be used");
  }
  /** This constructor should always be used. X and Y values 
    * are sent to the setXY method
    */ 
  public Poison(int x, int y){
    size = Environment.getGridSize();
    this.setXY(x, y);
  }
//end constructors
  
  
  /***METHODS***/
  
  /** paint:
   * Method that defines the appearance of poison
    * objects on the map. Currently a small black square.
    */
  public void paint (Graphics g){
    Color colour = (new Color(100, 50, 10));
    g.setColor(colour);
    g.fillOval(xDraw+(size/4), yDraw+(size/4), size/2, size/2);
    g.setColor(Color.black);
    g.drawOval(xDraw+(size/4), yDraw+(size/4), size/2, size/2);
  }//end paint
  
}//end class