package client;

import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Client client = null;
        try (Socket socket = new Socket("localhost", 12345)) {
            client = Client.fromSocket(socket);
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
