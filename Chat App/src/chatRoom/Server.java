package chatRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

	public static ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	static int clientCount = 0;

	public static void main(String[] args) {

			try (ServerSocket serverSocket = new ServerSocket(4444)) {
				System.out.println("SERVER is waiting for client connection..");
				
				while(true) {	
					clientCount++;
					Socket clientListener = serverSocket.accept();	
					ClientThread client = new ClientThread(clientListener, clients, clientCount);
					clients.add(client);	
					System.out.println("SERVER is connected to Client " + clientCount);
					client.start();
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
