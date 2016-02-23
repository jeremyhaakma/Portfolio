import java.io.*;

class Program1{

    public static void main(String args[]){
        try{
            int input = System.in.read();
            while(input != -1){
                System.out.write(input);
                input = System.in.read();
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage() );
        }

        
    }
}
