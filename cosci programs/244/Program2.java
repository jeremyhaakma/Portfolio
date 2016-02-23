import java.io.*;
import java.net.*;
import java.util.*;

public class Program2 {

    private PrintWriter output;
    private BufferedReader input;

    public Program2(Socket socket) throws Exception {
        output = new PrintWriter(socket.getOutputStream(), true);
        input =
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void startReceiving() throws Exception {
        Scanner stdin = new Scanner (System.in);
        String line;
        System.err.println("Please enter data here");
        while(true) {
            if(stdin.hasNextLine()){
                output.println(stdin.nextLine());
            }
            if ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        }  
    }

    public void startSending() throws Exception {
        Scanner stdin = new Scanner (System.in);
        String line;
        System.err.println("Please enter data here");
        while(true) {
            if(stdin.hasNextLine()){
            output.println(stdin.nextLine());
            }
            if ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        }  
    }

    public static void main(String[] args) {
        Socket socket = null;
        try {
            int port = Integer.parseInt(args[0]);
            if (args.length > 1) {
                //Client
                socket = new Socket(args[1], port);
                System.err.println("Connected to " + args[1] + " on port " + port);
                Program2 example = new Program2(socket);
                example.startSending();
            } else {
                //Server
                ServerSocket serverSocket = new ServerSocket(port);
                System.err.println("Waiting for someone to connect");
                socket = serverSocket.accept();
                System.err.println("Accepted connection on port " + port);
                Program2 example = new Program2(socket);
                example.startReceiving();
            }
        } catch (Exception e){
            e.printStackTrace();
            System.err.println("\nUsage: java TCPExample <port> [host]");
        }
    }
    
}
