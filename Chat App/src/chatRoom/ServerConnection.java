package chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable {
	
	private Socket serverConnect;
	private BufferedReader in;
	BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	
	public ServerConnection(Socket s) throws IOException {
		this.serverConnect = s;
		in = new BufferedReader(new InputStreamReader ( serverConnect.getInputStream()));		
	}
	
	public void run(){
		String serverResponse;
		try {
			while(true){
				 serverResponse = in.readLine();
				 System.out.println(serverResponse);	 		 
			}
		} catch (IOException e) {
			System.out.println("Socket Closed");
		}
		
	}

	
}

