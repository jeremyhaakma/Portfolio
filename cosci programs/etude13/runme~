/**
 *
 * Etude 13
 * Zombie Ants (Ant Class)
 *
 * Jeremy Haakma
 * Rory Mearns
 * Emma Stocker
 * Scott Wyngaarden
 *
 **/

public class Ant {
    private int xPos;
    private int yPos;
    private char lastDir;
    private boolean infected;
    private char[][] wDNA = new char[2][4];
    private char[][] bDNA = new char[2][4];
    

    public Ant (int x, int y, boolean infected, String wDNA, String bDNA){
        this.xPos = x;
        this.yPos = y;
        this.infected = infected;        
        this.lastDir = 'N';

        //split the wDNA string into the reaction and state change
        for (int i = 0; i < 4; i++) {
            this.wDNA[0][i] = wDNA.charAt(i);
            this.wDNA[1][i] = wDNA.charAt(i+5);
        }
        //split the bDNA string into reaction and state change
        for (int i = 0; i < 4; i++) {
            this.bDNA[0][i] = bDNA.charAt(i);
            this.bDNA[1][i] = bDNA.charAt(i+5);
        }
    }

    public int getX() {
        return this.xPos;
    }

    public int getY() {
        return this.yPos;        
    }

    public char getLastDir() {
        return this.lastDir;
    }

    public boolean isInfected() {
        return this.infected;
    }

    public void infect() {
        this.infected = true;
    }

    
    public void move(char[][][] plane, boolean[][] lPlane){            
        if(this.infected){
            this.moveZombie(plane, lPlane);
        }else{
            this.moveAnt(plane, lPlane);
        }
    }
    

    private void moveZombie(char[][][] plane, boolean[][] lPlane) {
        char[] cell = plane[this.yPos][this.xPos];
        
        //if on a ant trail
        if(cell[0]=='a'){   
            switch(cell[1]){
                case 'N':
                    if (this.yPos == 0) {
                        this.yPos = plane.length-1;
                    } else {
                        this.yPos -=1;
                    }
                    break;
                case 'E':
                    if (this.xPos == plane[0].length-1) {
                        this.xPos = 0;
                    } else {
                        this.xPos += 1;
                    }
                    break;
                case 'S':
                    if (this.yPos == plane.length-1) {
                        this.yPos = 0;
                    } else {
                        this.yPos += 1;
                    }
                    break;
                case 'W':
                    if (this.xPos == 0) {
                        this.xPos = plane[0].length-1;
                    } else {
                        this.xPos -= 1;
                    }
                    break;
                
            }
        }
        //else defaultDNA
        else{
            //movement based off the hidden black/white state grid

            /**
             * Hidden plane / 'lPlane' is for 'langtons ant' default behavior 
             * Plane cells are booleans:
             * False  =  "White"
             * True   =  "Black"
             **/
            char move = 'N';

            // Find the move direction, and set the hidden plane cell to black or white
            switch(this.lastDir){
                case 'N':

                    // Set the new move direction
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][0] : this.wDNA[0][0];

                    // Change the lPlane color (black / white)
                    // If it's white:
                    if (lPlane[this.yPos][this.xPos]) {
                        // Change it to whats in the dna
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][0] == 'b') ? false : true;
                    }
                    // else if it's black:
                    else {
                        // Change it to whats in the dna
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][0] == 'b') ? false : true;
                    }
                    break;

                case 'E':
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][1] : this.wDNA[0][1];
                    if (lPlane[this.yPos][this.xPos]) {
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][1] == 'b') ? false : true;
                    } else {
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][1] == 'b') ? false : true;
                    }
                    break;

                case 'S':
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][2] : this.wDNA[0][2];
                    if (lPlane[this.yPos][this.xPos]) {
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][2] == 'b') ? false : true;
                    } else {
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][2] == 'b') ? false : true;
                    }
                    break;

                case 'W':
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][3] : this.wDNA[0][3];
                    if (lPlane[this.yPos][this.xPos]) {
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][3] == 'b') ? false : true;
                    } else {
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][3] == 'b') ? false : true;
                    }
                    break;
            }

            // Update the X & Y co-ordinates of the ant
            switch(move) {
                case 'N':
                    if (this.yPos == 0) {
                        this.yPos = plane.length-1;
                    } else {
                        this.yPos -=1;
                    }
                    break;
                case 'E':
                    if (this.xPos == plane[0].length-1) {
                        this.xPos = 0;
                    } else {
                        this.xPos += 1;
                    }
                    break;
                case 'S':
                    if (this.yPos == plane.length-1) {
                        this.yPos = 0;
                    } else {
                        this.yPos += 1;
                    }
                    break;
                case 'W':
                    if (this.xPos == 0) {
                        this.xPos = plane[0].length-1;
                    } else {
                        this.xPos -= 1;
                    }
                    break;
            }

            // Set the lastDir to move
            this.lastDir = move;
        }
    }
    
    private void moveAnt (char[][][] plane, boolean[][] lPlane) {
        char[] cell = plane[this.yPos][this.xPos];
        
        //if on a zombie trail
        if(cell[0] == 'z'){ 
            switch(cell[1]){
                case 'N':
                    if (this.yPos == plane.length-1) {
                        this.yPos = 0;
                    } else {
                        this.yPos += 1;
                    }
                    break;
                case 'E':
                    if (this.xPos == 0) {
                        this.xPos = plane[0].length-1;
                    } else {
                        this.xPos -= 1;
                    }
                    break;
                case 'S':
                    if (this.yPos == 0) {
                        this.yPos = plane.length-1;
                    } else {
                        this.yPos -=1;
                    }
                    break;
                case 'W':
                    if (this.xPos == plane[0].length-1) {
                        this.xPos = 0;
                    } else {
                        this.xPos += 1;
                    }
                    break;                    
            }         
        }        
        //else defaultDNA
        else{
            char move = 'N';

            // Find the move direction, and set the hidden plane cell to black or white
            switch(this.lastDir){
                case 'N':
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][0] : this.wDNA[0][0];
                    if (lPlane[this.yPos][this.xPos]) {
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][0] == 'b') ? false : true;
                    }
                    else {
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][0] == 'b') ? false : true;
                    }
                    break;

                case 'E':
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][1] : this.wDNA[0][1];
                    if (lPlane[this.yPos][this.xPos]) {
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][1] == 'b') ? false : true;
                    } else {
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][1] == 'b') ? false : true;
                    }
                    break;

                case 'S':
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][2] : this.wDNA[0][2];
                    if (lPlane[this.yPos][this.xPos]) {
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][2] == 'b') ? false : true;
                    } else {
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][2] == 'b') ? false : true;
                    }
                    break;

                case 'W':
                    move = lPlane[this.yPos][this.xPos] ? this.bDNA[0][3] : this.wDNA[0][3];
                    if (lPlane[this.yPos][this.xPos]) {
                        lPlane[this.yPos][this.xPos] = (this.wDNA[1][3] == 'b') ? false : true;
                    } else {
                        lPlane[this.yPos][this.xPos] = (this.bDNA[1][3] == 'b') ? false : true;
                    }
                    break;
            }

            // Update the X & Y co-ordinates of the ant
            switch(move) {
                case 'N':
                    if (this.yPos == 0) {
                        this.yPos = plane.length-1;
                    } else {
                        this.yPos -=1;
                    }
                    break;
                case 'E':
                    if (this.xPos == plane[0].length-1) {
                        this.xPos = 0;
                    } else {
                        this.xPos += 1;
                    }
                    break;
                case 'S':
                    if (this.yPos == plane.length-1) {
                        this.yPos = 0;
                    } else {
                        this.yPos += 1;
                    }
                    break;
                case 'W':
                    if (this.xPos == 0) {
                        this.xPos = plane[0].length-1;
                    } else {
                        this.xPos -= 1;
                    }
                    break;
            }

            // Set the lastDir to move
            this.lastDir = move;
        }

    }


}
