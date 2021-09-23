package server.messages;

import domain.*;

public class GiveUpCommand extends Command {
    private static final long serialVersionUID = 20191101L;

    @Override
    public void applyForPlayer(Player player) throws InvalidColorTurnException {
        Board board = player.getBoard();
        board.giveUp(player);
    }
}
