import java.io.*;

public class Program2{

    
    public static void main(String args[]){
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
        try{
            String input = reader.readLine();
            String output = "";
            while(input != null){
                for (int i = input.length()-1; i >= 0; i--){
                    output = output + input.charAt(i);
                }
                System.out.println(output);
                output = "";
                input = reader.readLine();
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage() );
        }
    }
}
