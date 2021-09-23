package server.messages;

import domain.Color;
import domain.Player;

import java.io.Serializable;

public class RegistrationMessage implements Serializable {
    private static final long serialVersionUID = 20191101L;

    private Color playerColor;

    public static RegistrationMessage fromPlayer(Player player) {
        return new RegistrationMessage(player.getColor());
    }

    public RegistrationMessage(Color playerColor) {
        this.playerColor = playerColor;
    }

    public Color getPlayerColor() {
        return playerColor;
    }
}
