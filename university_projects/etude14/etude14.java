import java.util.*;

public class etude14 {

  public static void main(String[] args) {
    byX();
    System.out.print("\n");
    byZ();
  }
  
  public static void byX(){
    long x = 3;
    long y = 3; 
    long z = 1;
    
    int i = 1;
    while (i < 71){
      z = 1;
      
      while(z < x && i < 71){
        
          //caluclate y
          double calcY = Math.sqrt( 1 + z*z*z*z - x*x );
          //check if y is > x and has no factors in common with x and z
          if ( calcY % 1 == 0){
            //System.out.println("rounded");
            y = Math.round(calcY);
            if (y > x && gcd(x, y) == 1 && gcd(z, y) == 1 && gcd(z, x) == 1){
              //if it meets criteria, print x, y and z and increment i
              System.out.println(x + " " + y + " " + z);
              i++;
            }
          }//end if calcY rounded
       
        z++;
      }//end while z < x
      x++;
    }//end while i < 70
  }
  
  public static void byZ() {
    long x = 3;
    long y = 2; 
    long z = 1;
    
    int i = 1;
    while (i < 71){
      x = z+1;
      while(x < Math.sqrt(z*z*z*z - z*z) && i < 71){

          //caluclate y
          double calcY = Math.sqrt( 1 + z*z*z*z - x*x );
          //check if y is > x and has no factors in common with x and z
          if ( calcY % 1 == 0){
            //System.out.println("rounded");
            y = Math.round(calcY);
            if (y > x && gcd(x, y) == 1 && gcd(z, y) == 1 && gcd(z, x) == 1){
              //if it meets criteria, print x, y and z and increment i
              System.out.println(x + " " + y + " " + z);
              i++;
            }
          }//end if calcY rounded'

        x++;
      }
      z++;
    }
  }
  
  public static long gcd(long a, long b){
   if(a == 0) return b;
   if(b == 0) return a;
   if(a > b) return gcd(b, a % b);
   return gcd(a, b % a);
}
  
}