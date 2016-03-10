import javax.swing.*;
import java.awt.*;
/**
 * This class is primarily used to contain the paint method
 * used to draw food objects onto the JPanel map. The methods
 * to place these items on the map randomly are located in its parent
 * class, EvoItem.
 */
public class Food extends EvoItem{
  
  /***CONSTRUCTORS***/
  
  /**Default constructor should not be used!*/
  public Food(){
    System.out.println("default food constructor should not be used");
  }
  
  /**Constructor takes x and y values and calls setXY() with them. */
  public Food(int x, int y){
    size = Environment.getGridSize();
    this.setXY(x, y);
  }
//end constructors 
  
  
  /***METHODS***/
  
  /** paint:
    * Method which determines the appearance of food items.
    * The colour of the food is a function of the number of 
    * food items in a given position. More food = lighter colour
    */
  public void paint (Graphics g){
    Color colour;
    colour = new Color(255/( Environment.foodArray[xPos][yPos]+1), 200, 255/(Environment.foodArray[xPos][yPos]+1));
    g.setColor(colour);
    g.fillOval(xDraw+(size/4), yDraw+(size/4), size/2, size/2);
    g.setColor(Color.black);
    g.drawOval(xDraw+(size/4), yDraw+(size/4), size/2, size/2);
    int fontSize = Environment.getGridSize();
  }//end paint
  
}//end class