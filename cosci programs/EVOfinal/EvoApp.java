import javax.swing.*;
import java.awt.*;

/**** EvoApp ***
 * This application class generates a simulation of creatures in a hostile environment. The creatures'
 * behaviour is determined by a 13 gene-long chromosome, which evolves over successive
 * generations using crossover and mutation. 
 * 
 * To alter the simulations, the following data fields should be considered. Recommended
 * values are placed in the comments next to these date fields.
 * In EvoApp.java:
 *  - generations: On a decent computer, this simulation can run through
 *                 thousands in generations in a matter of seconds. 
 * in Creature.java:
 *  - MAXENERGY: currently set as some fraction (like 1/10th) less than the total number
 *               of steps. This is so that even if the no. of steps is changed, creatures
 *               will always start with slightly less energy than they need to survive
 *               a single generation
 *  - sustinence/potency: These values determine how much energy is gained by food, or
 *               taken by poison, respectively. If sustinence is set too high, creatures
 *               will get lazy and faff about on the edges to avoid monsters. Set too low,
 *               and they may never survive long enough to start really evolving.
 *  - mutation: A ratio between 0 and 1, determines how likely each gene has of mutating
 *               when a child creature is being born. Somewhere between .01 and .005 seems
 *               to work pretty well for a ~2000 generation simulation. A high mutation
 *               rate decreases the stability of a population, a low rate means it takes
 *               more generations to successfully adapt. 
 * in Environment.java:
 *  - numParents: How many successful creatures are placed into the parentList used to 
 *               generate children in successive generations. Similar to mutation rate, 
 *               too few parents is unstable: catastrophic mutations can destroy an entire
 *               population. Too many parents and positive mutations take longer to
 *               propagate through the population. 
 *  - monsterNum/monsterSpeed: the number and speed of the monsters on the map. If monsters
 *               pose too much of a threat (too fast, too many), creatures will risk
 *               starvation in order to run for the edges, where they are slightly safer
 *               from being eaten. 
 *  - creatureNum: Too many creatures can cause over-crowding, which seems to cause similar
 *               behaviour to having too many monsters: creatures will hide in the edges.
 * 
 *  - foodNum/poisonNum: Too much food and creatures get lazy. poison doesn't have a huge
 *               effect: creatures learn fairly quickly to avoid eating it. They may, 
 *               however, use poison to help navigate the map. 
 */
public class EvoApp {
  //generations: set the number of generations the simulation runs through.
  public static int generations = 2000;
  
/***MAIN***/
  public static void main(String[] args) { 
    //create simulation
    JFrame simulation = new JFrame("Evolution");
    Environment map = new Environment();
    simulation.getContentPane().add(map);
    simulation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    simulation.pack();
    simulation.setVisible(true);
    
    //create graph
    JFrame graphFrame = new JFrame("Energy over Generations");
    EnergyGraph graph = new EnergyGraph();
    graphFrame.getContentPane().add(graph);
    graphFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    graphFrame.pack();
    graphFrame.setVisible(true);
    
    //first run through, animated
    Environment.animate = true;
    map.run();
    //print out details of a single creature from first generation
    System.out.println("Sample creature, generation 1: ");
    System.out.println(Environment.creatureList[0]);
    System.out.println("Average Energy :" + Environment.getAverageEnergy() + "\n");

    //run simulation multiple times, not animated. 
    for (int i = 0; i < generations; i++){//500
      //generate new map
      map.generate(false);
      //run new map
      map.run();
      //add Average energy to list so it can be plotted
      Environment.energyList.add(Environment.getAverageEnergy());
      //plot energy graph
      graph.repaint();
    }
    //final run through, animated
    Environment.animate = true;
    map.generate(false);
    map.run();
    //print chromosome information for all craetures in final generation.
    for (int i = 0; i < Environment.creatureList.length; i++){
      System.out.println("Creature: " + i);
    System.out.println(Environment.creatureList[i]);
    }
    System.out.println("Average Energy :" + Environment.getAverageEnergy());

  }//end main
  
}//end class
