package week03;

/**
 *  A recursive representation of a tower of blocks.
 *
 * @author Michael Albert
 */
public class Tower{

    /** The top block. */
    private char top;
    
    /** The rest of the tower. */
    private Tower rest;

    /**
     * Creates a new empty Tower.
     */
    public Tower() {
        this.top = ' ';
        this.rest = null;
    }
    
    /**
     *  External classes can only create empty towers and manipulate
     *  them using public methods, because this constructor is
     *  private.
     * @param top the top block in this tower
     * @param rest the rest of the tower
     */
    private Tower(char top, Tower rest) {
        this.top = top;
        this.rest = rest;
    }

    /**
     *  Returns true if this tower is empty, otherwise false.  Empty
     *  towers are represented with the top block being a space
     *  character.
     * @return whether the tower is empty or not.
     */
    public boolean isEmpty() {
        return top == ' ';
    }
        
    /**
     *  Creates a new tower by adding the given block to the top of
     *  this tower.
     * @param block a block to add to the top of this tower.
     * @return a new tower created by adding a block to the top of
     * this tower.
     */
    public Tower add(char block) {
        return new Tower(block, this);
    }

    /** Checks the height of a given tower.
     * @return how many blocks in tower
     */
    public int height(){
        int h = 0;
        if (!isEmpty()){
            h = 1;
        }
        if (rest != null){
            return h + rest.height();
        }
        return h;
    }

    /** Counts the number of blocks in a tower equal to parameter.
     * @param c character to be checked
     * @return number of blocks equal to c
     */
    public int count(char c){
        int cCount= 0;
        if (top == c){
            cCount = 1;
        }
        if (rest != null){
            return cCount + rest.count(c);
        }
        return cCount;
    }
}
