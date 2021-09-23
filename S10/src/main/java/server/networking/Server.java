package server.networking;

import domain.Board;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Server {
	
	private ServerSocket socket;
	private Map<Socket,ServerWorker> workers;
	private Board board;


	public Server(ServerSocket socket) {
		this.board = new Board();
		this.socket = socket;
		this.workers = new HashMap<>();
	}
	
	public void run() throws IOException {
		System.out.println("Server started on port : "+this.socket.getLocalPort());
		while ( workers.size()<2 ) {
			// Lorsqu'un client se connecte, un socket est créé pour ce client.
			// Il s'agit d'un tuyau ouvert entre le serveur et le client, ils peuvent tous les deux envoyer des messages l'un à l'autre.
			Socket socketClient = this.socket.accept();
			System.out.println("New client connected");
			createServerWorker(socketClient);
			
			
		}
		System.out.println("Max players reached, not accepting connections anymore");
	}
	
	public void shutdown() {
		// Il faut interrompre chaque Thread et fermer chaque socket. Il faut ensuite fermer la socket d'ecoute du serveur
		System.out.println("Shutting Down Server");
		workers.entrySet()
			.stream()
			.parallel()
			.forEach(entry -> {
				Socket socket = entry.getKey();
				ServerWorker worker = entry.getValue();
				try {
					worker.shutdown();
					socket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			});
		
	}

	public void broadcastBoardstate(){
		// Il faut demander à chaque worker de transmettre l'état de la board via sa socket
		workers.values()
		.stream()
		.parallel()
		.forEach(ServerWorker::sendBoardState);
	}
	

	private void createServerWorker(Socket clientSocket) throws IOException {
		// Il faut créer un nouveau thread qui va s'occuper uniquement de ce socket. N'oubliez pas de démarrer ce thread.
		
		
		ServerWorker worker =ServerWorker.fromSocket(clientSocket, this, board);
		workers.put(clientSocket,worker);
		worker.start();
	}

}
