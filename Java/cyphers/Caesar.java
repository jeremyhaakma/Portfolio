import java.util.Scanner;
import java.io.*;

public class Caesar{
    // 8 bits for character set
    private static InputStream inStream = System.in;
    private static OutputStream outStream = System.out;
    public static void main(String[] args){
        int shift = Integer.parseInt(args[0]);
        int b;
        try{
            while ( (b = inStream.read()) != -1){
                outStream.write((b+shift)%255);
                System.out.flush();
            }
        }catch(IOException e){
        System.out.println(e);
        }
    }
}
