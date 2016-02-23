package week05;

/**
 * Skeleton code for an array based implementation of Young's tableau.
 *
 * @author Iain Hewson
 */
public class TableauAppBackup {

    /**
     * The main method is just used for testing.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
    }

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        return rowValuesIncrease(t) &&
            columnValuesIncrease(t) && isSetOf1toN(t);
    } 

    /**
     *  Returns a string representation of an array based tableau.
     *
     * @param t a two-dimensional array which represents a tableau.
     *
     * @return a string representation of an array based tableau.
     */
    public static String toString(int[][] t) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                result.append(String.format("%-4s", t[i][j]));
            }
            if (i < t.length-1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     *  Returns true if no row is longer than preceding row, otherwise false.
     *
     * @param t A 2-D Array which represents a tableau.
     *
     * @return returns true if paramater contains no rows longer than
     *         preceding rows.
     */
    public static boolean rowLengthsDecrease(int[][] t){
        for (int i = 1; i < t.length; i++){
            if (t[i].length > t[i-1].length){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if integers always increase from left to right.
     *
     * @param t A 2-D Array which represents a tableau
     *
     * @return returns true if, from left to right, all integers increase,
     *         otherwise returns false.
     */
    public static boolean rowValuesIncrease(int[][] t){
        for (int row = 0; row < t.length; row++){
            for (int col = 1; col < t[row].length; col++){
                if (t[row][col] < t[row][col-1]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if integers always increase from top to bottom
     * in any column.
     *
     * @param t A 2-D Array which represents a tableau
     *
     * @return returns true if, from top to bottom, all integers increase,
     *         otherwise returns false.
     */
    public static boolean columnValuesIncrease(int[][] t){
        if (rowLengthsDecrease(t)){
            for (int row = 1; row < t.length; row++){
                for (int col = 0; col < t[row].length; col++){

                    if (t[row][col] < t[row-1][col]){
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
    /**
     * Returns true if only includes values 1 t N.
     *
     * @param t A 2-D array which represents a tableau
     *
     * @return returns true if t contains only values 1 to N
     */
    public static boolean isSetOf1toN(int[][] t){
        boolean[] nArray = new boolean[t.length*t[0].length +1];
        int cells = 1;
        for (int[] row : t){
            for(int i : row){
                if (i <= 0 || i > nArray.length || nArray[i]){
                    return false;
                }else {
                    nArray[i] = true; 
                }
                cells++;
            } 
        }
        for (int i = cells; i < nArray.length; i++){
            if (nArray[i]){
                return false;
            }
        }
        
        return true;
    }



}
