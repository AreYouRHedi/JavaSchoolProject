package server.networking;

import domain.Board;
import domain.ColumnFullException;
import domain.InvalidColorTurnException;
import domain.Player;
import server.messages.*;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

class ServerWorker extends Thread {
	private ObjectInput input;
	private ObjectOutput output;

	private Server server;
	private Board board;
	private Player player;

	/**
	 * Factory method principale, bien que le constructeur attende des input et
	 * output stream, ce qu'on a sous la main dans le serveur c'est une socket.
	 * Cette méthode s'occupe donc de construire les input/output streams sur base
	 * de la socket.
	 *
	 * @param socket la socket représentant la connexion vers un client
	 * @param server l'instance du serveur auxquel les autres clients sont connectés
	 * @param board  la partie sur laquelle on va jouer
	 * @return une instance de ServerWorker, un thread qui va permettre d'agir en
	 *         tant qu'un des joueurs de la partie.
	 * @throws IOException
	 */
	static ServerWorker fromSocket(Socket socket, Server server, Board board) throws IOException {
		ObjectInputStream input;
		ObjectOutputStream output;

		// TODO ADD CODE HERE
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());

		return new ServerWorker(input, output, server, board);
	}

	/**
	 * Constructeur principal, il recoit toutes ses dépendances afin de pouvoir etre
	 * facilement testé en isolation
	 * 
	 * @param input  le stream d'entrée sur lequel on va recevoir les commandes de
	 *               jeu
	 * @param output le stream de sortie sur lequel on va principalement envoyer
	 *               l'état de la partie
	 * @param server l'instance du serveur auxquel les autres clients sont connectés
	 * @param board  la partie sur laquelle on va jouer
	 */
	ServerWorker(ObjectInput input, ObjectOutput output,

			Server server, Board board) {
		super();
		this.input = input;
		this.output = output;
		this.server = server;
		this.board = board;
	}

	/**
	 * Corps du thread : - on enregistre le joueur sur la board - on démarre la
	 * boucle d'attente et d'exécution des commandes
	 *
	 */
	@Override
	public void run() {
		/*
		 * Gardez cette méthode lisible, utilisez les méthodes privées suivantes : -
		 * boardIsFull - registerPlayer - handleCommands - threadIsAlive
		 */

		// TODO ADD CODE HERE
	}

	/**
	 * Communique l'état de la partie au client
	 */
	void sendBoardState() {
		BoardState message = BoardState.fromBoard(board);
		sendMessage(message);
	}

	/**
	 * Cloture le worker en nettoyant les ressource qu'il a créé. On ne clot pas la
	 * socket ici car elle n'a pas été créée par le worker, mais par le serveur. Ce
	 * sera donc au server de cloturer la socket.
	 *
	 */
	void shutdown() {
		// TODO ADD CODE HERE
		try {
			this.input.close();
			this.output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// private below this

	/**
	 * @return true si la partie ne peut plus accepter de nouveaux joueur
	 */
	private boolean boardIsFull() {
		// TODO ADD CODE HERE
		return board.hasMaxPlayers();
	}

	/**
	 * demande a la board d'ajouter un joueur et stocke le joueur dans le worker.
	 * renvoie à travers le réseau l'information recue de la board
	 * (RegistrationMessage) si la partie est prête à commencer, on envoie à tout le
	 * monde l'etat de la board afin qu'un des joueurs sache que c'est son tour.
	 */
	private void registerPlayer() {
		// TODO ADD CODE HERE
		this.player = board.addNewPlayer();
		RegistrationMessage message = RegistrationMessage.fromPlayer(player);
		sendMessage(message);
		if (board.hasMaxPlayers()) {
			server.broadcastBoardstate();
		}
	}

	/**
	 * Boucle qui va lire une commande depuis le réseau, l'exécuter, envoyer le
	 * nouvel état de la partie. Lorsque la partie est finie, il faut notifier le
	 * serveur qu'il peut nettoyer les workers et s'éteindre.
	 * 
	 * Cette méthode peut se reposer sur : readCommand handleCommand
	 */

	private void handleCommands() {
		try {
			while (threadIsAlive()) {

				Optional<Command> optCommand;
				optCommand = readCommand();
				if (optCommand.isPresent()) {
					Command command = optCommand.get();
					System.out.println("Command received from " + player.getColor() + " : " + command);
					handleCommand(command);
					server.broadcastBoardstate();
					if (board.isFinished()) {
						server.shutdown();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Méthode qui va gérer une commande de jeu (un mouvement ou un abandon)
	 * 
	 * @param command
	 */
	private void handleCommand(Command command) {
		try {
			command.applyForPlayer(player);
		} catch (ColumnFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidColorTurnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui va lire une commande depuis le réseau. Plutot que de renvoyer
	 * null en cas de pépin, elle renverra un Optional vide.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Optional<Command> readCommand() throws ClassNotFoundException {
		try {
			Object received = input.readObject();
			if (received == null) {
				return Optional.empty();
			}
			Command command = (Command) received;
			return Optional.of(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		}

	}

	/**
	 * envoie au réseau une réponse informant que la partie est pleine et que la
	 * connexion ne peut donc pas se faire. utilise sendMessage
	 */
	private void rejectBecauseMaxPlayers() {
		MaxPlayerReachedMessage message = new MaxPlayerReachedMessage();
		sendMessage(message);
	}

	/**
	 * Envoie au réseau un message qui informe que la commande précédente s'est mal
	 * passée. utilise sendMessage
	 */
	private void sendErrorMessage(Exception e) {
		// TODO ADD CODE HERE
		ErrorMessage message = new ErrorMessage(e.getMessage());
		sendMessage(message);
	}

	/**
	 * Envoie un message au réseau.
	 */
	private void sendMessage(Object message) {
		System.out.println("Ecriture d'un message"+message);
		try {
			output.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Permet de savoir si le Thread actuel n'a pas été interrompu par le serveur.
	 * 
	 * @return true si le thread n'a pas été interrompu
	 */
	private boolean threadIsAlive() {
		return !Thread.currentThread().isInterrupted();
	}
}