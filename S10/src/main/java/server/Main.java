package server;

import server.networking.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Server server = null;
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
			server = new Server(serverSocket);
			server.run();
		} catch (Exception e) {
			e.printStackTrace();
			server.shutdown();
			System.exit(1);
		}
	}	

}