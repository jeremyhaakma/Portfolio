import java.util.Scanner;
import java.io.File;

public class testfile{

    public static void main(String[] args){
        File file = new File("letter-frequencies.txt");
        Scanner sc = new Scanner(file);
        System.out.println(sc.next());
    }


}
