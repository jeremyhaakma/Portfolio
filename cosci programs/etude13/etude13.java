/**
 *
 * Etude 13
 * Zombie Ants
 *
 * Jeremy Haakma
 * Rory Mearns
 * Emma Stocker
 * Scott Wyngaarden
 *
 **/

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class etude13 {

    static ArrayList<Ant> antList;
    static ArrayList<Ant> zombieList;
    static char[][][] plane;
    static int globalStep = 0;

    static JFrame frame;
    static JLabel stepCount;

    /**
     * Hidden plane is for 'langtons ant' behavior 
     * Plane cells are booleans:
     * False  =  "White"
     * True   =  "Black"
     **/
    static boolean[][] hiddenPlane;

    public static void main(String[] args){
        String input;
        char[] DNA;
        boolean infected;
        int xPos;
        int yPos;
        int width;
        int length;
        int duration = 0;
        String wDNA;
        String bDNA;

        antList = new ArrayList<Ant>();
        zombieList = new ArrayList<Ant>();



        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            input = s.next().toLowerCase();
            
            // Scanning in the platform
            if (input.equals("plane")) {                
                input = s.next(); // Throw away "Length"
                length = s.nextInt();
                input = s.next(); // Throw away "Width"                 
                width = s.nextInt();
                input = s.next(); //Throw away "Duration"
                duration = s.nextInt();               
                
                //make platform 2D array and initialise all to false 
                plane = new char[length][width][2];
                hiddenPlane = new boolean[length][width];
                
            }
            //Scanning in ants
            else {
                input = s.next(); // Throw away "xPos"
                xPos = s.nextInt();
                input = s.next(); // Throw away "yPos"
                yPos = s.nextInt();                
                input = s.next(); //Throw away infected
                infected = Boolean.parseBoolean(s.next());
                wDNA = s.next(); // wDNA
                bDNA = s.next(); // bDNA
                                            

                //add the ant to the appropriate list
                if(infected){
                    //add an infected ant to the list of zombies
                    zombieList.add(new Ant(xPos, yPos, infected, wDNA, bDNA));
                }else{
                    //add an uninfected ant to the list of ants
                    antList.add(new Ant(xPos, yPos, infected, wDNA, bDNA));
                }
            }
        } //end while 
        frame = new JFrame("Zombie Ants");
        stepCount= new JLabel("Step "+globalStep);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new AntScenario());
        frame.pack();
        frame.setVisible(true);
        frame.add(stepCount, BorderLayout.SOUTH);

        runScenario(duration);
        
    }//end main

    public static void runScenario (int duration) {
        //runs indefinitely, add conditions to stop        
       
        for(int step = 0; step<duration; step++){
            globalStep++;
            for(int a = 0; a<2 ;a++){
                for(Ant ant : antList){
                
                    int oldX = ant.getX();
                    int oldY = ant.getY();
                    //char lastDir = ant.getLastDir();
                 
                    ant.move(plane, hiddenPlane);                
                    plane[oldY][oldX] = new char[] {'a', ant.getLastDir()};
                    checkCollision(ant);
                }

                //System.out.println(antList.get(0).getX()+" "+antList.get(0).getY());
                printScenario();
                wait(20);
            }

            for(int z = 0; z<3; z++){
                for(Ant zombie : zombieList){
                    int oldX = zombie.getX();
                    int oldY = zombie.getY();
                    //char lastDir = zombie.getLastDir();

                    zombie.move(plane, hiddenPlane);             
                    plane[oldY][oldX] = new char[] {'z', zombie.getLastDir()};             
                    checkCollision(zombie);
                }
                printScenario();
                wait(20);
          
                if(antList.size()>0){
                    //move infected ants into the list of zombie
                    for(int i = antList.size()-1; i>=0; i--){
                        Ant ant = antList.get(i);
                        if(ant.isInfected()){
                            antList.remove(ant);
                            zombieList.add(ant);
                        }
                    }
                    if(antList.size()==0){
                        duration = step+28;
                    }
                }
            }
            
        }
    }//end runScenario

    public static void checkCollision (Ant a) {
        if(a.isInfected()){
            //zombie moves onto an ant
            for(int i = 0; i<antList.size(); i++){
                Ant ant = antList.get(i);                
                if(ant.getX() == a.getX() && ant.getY() == a.getY()){
                    ant.infect();
                }
            }
        }else{
            //ant moves onto a zombie
            for(int i = 0; i<zombieList.size(); i++){
                Ant zombie = zombieList.get(i);                
                if(zombie.getX() == a.getX() && zombie.getY() == a.getY()){
                    a.infect();
                }

            }
        }
    }//end checkCollision

    public static void printScenario() {
        stepCount.setText("Days since the 'incident'(steps): "+globalStep);
        frame.repaint();
    }

    public static void wait(int n){
        try{
            Thread.sleep(n);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }


            
}//end etude13

