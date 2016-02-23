import java.util.Scanner;
public class Program3 {

    public static void main (String args[]){
        Scanner scan = new Scanner(System.in);
        String output = "";
        while (scan.hasNextLine()){
            output = scan.nextLine().toUpperCase();
            System.out.println(output);
        }
    }
}
