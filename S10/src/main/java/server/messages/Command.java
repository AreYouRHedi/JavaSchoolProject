package server.messages;

import domain.ColumnFullException;
import domain.InvalidColorTurnException;
import domain.Player;

import java.io.Serializable;

abstract public class Command implements Serializable {
    private static final long serialVersionUID = 20191101L;
    abstract public void applyForPlayer(Player player)  throws ColumnFullException, InvalidColorTurnException;
}
