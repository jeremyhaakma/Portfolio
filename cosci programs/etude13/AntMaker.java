import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;

public class AntMaker{

  private static int antNum;
  private static int zombieNum;
  private static int length;
  private static int width;
  
  public static void main (String[] args){
    
    String output = "";
    Scanner scan = new Scanner(System.in);
    
    System.out.println("Press 1 to generate random ants with default settings\n" + 
                       "Press 2 to change settings and generate random ants");
    int selection = scan.nextInt();
    switch(selection) {
      case 1: 
        length = 100;
        width = 100;
        antNum = 30;
        zombieNum = 1;
        break;
      case 2:
        System.out.println("Enter Platform length (recommended: 100)");
        length = scan.nextInt();
        
        
        System.out.println("Enter Platform width (recommended: 100)");
        width = scan.nextInt();
        
        
        System.out.println("Enter number of ants (recommended: 30)");
        antNum = scan.nextInt();
        System.out.println("Enter number of zombies (recommended: 1)");
        zombieNum = scan.nextInt();
        break;
    }
    output = "Plane\nLength " + length;
    output = output + "\nWidth " + width + 
          "\nDuration 800\n\n";
    
    //System.out.println("Enter seed");
    //int seed = scan.nextInt();
    Random r = new Random();
    
    for(int j = 0; j < antNum; j++){
    //ant black DNA
    String blackDNA = "";
    for(int i = 0; i < 4; i++){
      blackDNA = blackDNA + (r.nextBoolean() ? (r.nextBoolean() ? 'N' : 'S') : (r.nextBoolean() ? 'E' : 'W') );
    }
    blackDNA = blackDNA + '_';
    for(int i = 0; i < 4; i++){
      blackDNA = blackDNA + (r.nextBoolean() ? 'b' : 'w' );
    }
    
    //ant white DNA
    String whiteDNA = "";
    for(int i = 0; i < 4; i++){
      whiteDNA = whiteDNA + (r.nextBoolean() ? (r.nextBoolean() ? 'N' : 'S') : (r.nextBoolean() ? 'E' : 'W') );
    }
    whiteDNA = whiteDNA + '_';
    for(int i = 0; i < 4; i++){
      whiteDNA = whiteDNA + (r.nextBoolean() ? 'b' : 'w' );
    }
    
    
      output = output + 
        "Ant\n" + 
        "xPos " + r.nextInt(width) + 
        "\nyPos " + r.nextInt(length) + 
        "\ninfected false\n" + 
        blackDNA + "\n" + 
        whiteDNA + "\n\n";
    }
    
    //zombie black DNA
    for(int j = 0; j < zombieNum; j++){
    //ant black DNA
    String zombieBlackDNA = "";
    for(int i = 0; i < 4; i++){
      zombieBlackDNA = zombieBlackDNA + (r.nextBoolean() ? (r.nextBoolean() ? 'N' : 'S') : (r.nextBoolean() ? 'E' : 'W') );
    }
    zombieBlackDNA = zombieBlackDNA + '_';
    for(int i = 0; i < 4; i++){
      zombieBlackDNA = zombieBlackDNA + (r.nextBoolean() ? 'b' : 'w' );
    }
    
    //ant white DNA
    String zombieWhiteDNA = "";
    for(int i = 0; i < 4; i++){
      zombieWhiteDNA = zombieWhiteDNA + (r.nextBoolean() ? (r.nextBoolean() ? 'N' : 'S') : (r.nextBoolean() ? 'E' : 'W') );
    }
    zombieWhiteDNA = zombieWhiteDNA + '_';
    for(int i = 0; i < 4; i++){
      zombieWhiteDNA = zombieWhiteDNA + (r.nextBoolean() ? 'b' : 'w' );
    }
    
    
      output = output + 
        "Ant\n" + 
        "xPos " + r.nextInt(width) + 
        "\nyPos " + r.nextInt(length) + 
        "\ninfected true\n" + 
        zombieBlackDNA + "\n" + 
        zombieWhiteDNA + "\n\n";
    }
    try{
    PrintWriter writer = new PrintWriter("generatedAnts.txt");
    writer.println(output);
    writer.close();
    } catch (Exception e){
    
    }

    
    
  }//end main
  
}//end class