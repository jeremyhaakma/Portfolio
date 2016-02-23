import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
/**
 * EnergyGraph
 * This class generates a JFrame window containing a graphical representation
 * of average energy of a creature population ove time (generations).
 * The graph is drawn in real time as the simulation iterates over generations. 
 */
public class EnergyGraph extends JPanel{
  private int xSqueeze = 3;//adjust width of graph
  private int ySquish = 6;//adjust height of graph
  private int yHeight = (Creature.MAXENERGY)*(ySquish);
  
  /** default constructor */
  public EnergyGraph(){
    setPreferredSize(new Dimension((EvoApp.generations/xSqueeze)+150, yHeight+60));
  }
  
  /** paintComponent method
    * creates a Background rectangle, draws x and y labelled axes
    */
  public void paintComponent (Graphics g){
    g.setColor(new Color(240, 240, 255));
    g.fillRect(0, 0, EvoApp.generations/xSqueeze +150, yHeight+60);
    g.setColor(Color.black);
    int xAxis = 0;
    int prevY = 0;
    ArrayList<Integer> e = new ArrayList<Integer>(Environment.energyList);
    
    //y axis text
    for (Integer i= 0; i <= yHeight; i+= 10){
      Integer height = i/ySquish;
      g.drawString(i.toString(), 78, yHeight-i*ySquish);
      g.setColor(new Color(150, 150, 255));
      g.drawLine(100, yHeight-i*ySquish, EvoApp.generations/xSqueeze+100, yHeight-i*ySquish);
      g.setColor(Color.black);
    } 
    //x axis text
    for (Integer i= 0; i <= EvoApp.generations; i+= 500){
      Integer length = i/xSqueeze;
      g.drawString(i.toString(), length+100, yHeight+20);
      g.drawLine(length+100, yHeight, length+100, yHeight+10);
    }
    g.drawString("Generations", (EvoApp.generations/(xSqueeze)/2)+80, yHeight+40);
    g.drawString("Average", 15, yHeight/2);
    g.drawString("Energy", 15, yHeight/2+15);
    
    //axes lines
    g.drawLine(100, yHeight, EvoApp.generations/xSqueeze+100, yHeight);
    g.drawLine(100, yHeight, 100, 20);
    //graph lines
    for(int y : e){
      if (xAxis%xSqueeze == 0){
        g.drawLine(xAxis/xSqueeze+100, (yHeight-prevY*ySquish), (xAxis/xSqueeze+101), (yHeight-y*ySquish));
        prevY = y;
      }
      xAxis++;
    }  
  }//end paintComponent method
  
}//end class