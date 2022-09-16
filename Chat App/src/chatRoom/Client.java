package chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {

		try {
			 Socket client = new Socket("localhost", 4444);
			 ServerConnection serverConn = new ServerConnection(client);
			 BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			 PrintWriter output = new PrintWriter(client.getOutputStream(), true);
			 
			 System.out.println("Connected to Chat Server");
			 
			 new Thread(serverConn).start();
			 
			 while(true) {
				 String command = keyboard.readLine();
				 output.println(command);
				 if(command.equals("quit")) {
					 client.close();
					 break;
				 }
			 }
		} catch (IOException e) {			
			System.out.println("Socket Closed");
		}
	}

}
