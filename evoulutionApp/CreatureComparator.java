import java.util.Comparator;
/** CreatureComparator
  * This class is used to sort an ArrayList of creatures by their energy levels
  */
class CreatureComparator implements Comparator<Creature>{
   //@Override
 public int compare(Creature o1, Creature o2) {
  return o1.energy - o2.energy;
 }
}//end class