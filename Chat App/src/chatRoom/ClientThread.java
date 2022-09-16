package chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread{
	
	private Socket client = null ;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<ClientThread> clients;
	public int cliNumber;
	BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

	public ClientThread(Socket client, ArrayList<ClientThread> clients , int clientCount) throws IOException {
		this.clients = clients;
		this.client = client;
		this.cliNumber = clientCount;
	    in = new BufferedReader(new InputStreamReader ( client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);	
	}
	
	public void run() {
		
		while(true) {
			String request;
						try {
							while(true){
							request = in.readLine();
							if(request.equals("quit")){
								clients.remove(this);
								client.close();
								broadcastMessage("Client " + cliNumber + " disconnected.");
							}
							else {
								String address = "[ Client " + cliNumber + " ] : ";
								broadcastMessage(address + request );
								System.out.println(address + request);
							}						
						}
						} catch (IOException e) {
							System.out.println("Client " + cliNumber + " disconnected.");
							break;
						}			
					}
	}
	
	
	public void broadcastMessage(String message) throws IOException {
		 for(ClientThread c : clients) {	
			 if(!c.equals(this)) {	
				 c.out.println(message);
			 }
		 }
	}
	

}
