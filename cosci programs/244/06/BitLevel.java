import java.io.*;
import java.util.Scanner;

public class BitLevel{

    private static FileInputStream inFile;
    private static FileOutputStream outFile;
    private static String key;

    public static void main(String[] args){
        try{
            
            Scanner s = new Scanner(System.in);
            inFile = new FileInputStream(args[0]);
            outFile = new FileOutputStream(args[1]);
            
            /* ask user to input a key */
            System.out.println("Enter key:");
            
            /* store input as String, turn string into byte array */
            key = s.nextLine();
            byte[] b = key.getBytes();
            
            /* While there is data to encrypt, XOR with the key,
               returning to beginning of key when it reaches the end */
            int index = 0;
            while (inFile.available() > 0){
                if (index >= b.length){
                    index=0;
                }
                outFile.write( b[index++]  ^ inFile.read() );
            }
            
            /* close the output stream */
            outFile.close();

        } catch(IOException e){
            System.out.println(e);
        }
    }
}
