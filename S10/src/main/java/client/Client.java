package client;

import domain.Color;
import server.messages.*;


import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

class Client {

    /**
     * Factory method principale, bien que le constructeur attende des input et output stream, ce qu'on a sous la main
     * dans le serveur c'est une socket. Cette méthode s'occupe donc de construire les streams sur base de la socket.
     *
     * @param socket la socket représentant la connexion vers un server
     * @return une instance de Client
     * @throws IOException
     */
    public static Client fromSocket(
            Socket socket
    ) throws IOException {
        ObjectInputStream networkInput;
        ObjectOutputStream networkOutput;
        Scanner keyboard = new Scanner(System.in);

        // TODO
       networkOutput=new ObjectOutputStream(socket.getOutputStream());
       networkOutput.flush();
       networkInput=new ObjectInputStream(socket.getInputStream());

        return new Client(networkInput, networkOutput, keyboard);
    }

    private ObjectInput networkInput;
    private ObjectOutput networkOutput;
    private Scanner keyboard;
    private Color myColor;
    private BoardState currentBoardState;

    public Client(ObjectInput networkInput, ObjectOutput networkOutput, Scanner keyboard) {
        this.networkInput = networkInput;
        this.networkOutput = networkOutput;
        this.keyboard = keyboard;
    }

    void run() throws ClientException{
        startGame();
        readBoardStateOrError();
        displayBoardState();
        while(gameIsNotFinished()){
            if ( isMyTurn() ){
              sendCommand();
            }
            readBoardStateOrError();
            displayBoardState();
        }
        displayBoardState();
        displayWinner();
    }

    /**
     * On commence par lire le message initial du serveur nous informant de la réussite de notre ajout
     * à la partie. En cas de non réussite à cause du nombre maximzl atteint, on s'arrete.
     * En cas de réussite, on mémorise la couleur du joueur que l'on représente et on affiche un message à ce sujet.
     *
     * On peut s'appuyer sur la méthode readMessage à écrire plus bas
     *
     * @throws ClientException
     */
    private void startGame() throws ClientException {
    	// TODO ADD CODE HERE
    	Object message = readMessage();
    	if(message instanceof MaxPlayerReachedMessage) {
    		throw new ClientException("max players");
    	}
    	RegistrationMessage regis = (RegistrationMessage) message;
    	this.myColor=regis.getPlayerColor();
    	System.out.println("connected :"+this.myColor);
    }

    /**
     * On attend de pouvoir lire le nouvel état de la partie que le serveur doit nous renvoyer.
     * Lorsqu'on le recoit, on met à jour currentBoardState
     *
     * On peut s'appuyer sur la méthode readMessage à écrire plus bas
     *
     * @throws ClientException
     */
    private void readBoardStateOrError() throws ClientException {
    	// TODO ADD CODE HERE
			Object message = (BoardState) readMessage();
			if(message instanceof ErrorMessage) {
				ErrorMessage error = (ErrorMessage) message;
				throw new ClientException(error.getErrorMessage());
			}
			currentBoardState=(BoardState) message;
    }

    private boolean gameIsNotFinished(){
        return !this.currentBoardState.gameIsFinished();
    }

    private void displayBoardState() {
        Color[][] boardColors = this.currentBoardState.getBoardColors();
        StringBuilder result = new StringBuilder();
        int columnCount = boardColors.length;
        int rowCount = boardColors[0].length;

        result.append("<< YOUR COLOR IS : ").append(myColor.name()).append("\n\n");

        for (int rowIndex = rowCount - 1; rowIndex >= 0; rowIndex--) {
            result.append("|");
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                Color cellColor = boardColors[columnIndex][rowIndex];
                String colorName = cellColor == null ? " " : ""+cellColor.name().charAt(0);
                result
                        .append(" ")
                        .append(colorName)
                        .append(" |");
            }
            result.append("\n");
        }
        result.append(" ");
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            result
                    .append(" ")
                    .append(columnIndex)
                    .append("  ");
        }
        System.out.println(result);
    }

    private void displayWinner(){
        System.out.println("WINNER IS : " + this.currentBoardState.getWinnerColor());
    }

    private boolean isMyTurn(){
        return this.currentBoardState.getNextColorToPlay() == this.myColor;
    }

    /**
     * On demande à l'utilisateur sur quelle colonne il veut jouer
     * On fabrique une PlayCommand avec cette info et on l'envoie au serveur.
     * @throws ClientException
     */
    private void sendCommand() throws ClientException {
    	// TODO ADD CODE HERE
    	System.out.println("Where do you want to play ?");
    	int index =keyboard.nextInt();
    	PlayCommand command = new PlayCommand(index);
    	try {
			networkOutput.writeObject(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ClientException(e);
		}
    }

    /**
     * On lit un message recu du serveur.
     * En cas de problème on emballe l'exception recue dans un ClientException
     * @return le message recu
     */
    private Object readMessage() throws ClientException {
    	Object message=null;
		try {
			message = networkInput.readObject();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClientException();
		}
		return message;
		
    	
    }



}
