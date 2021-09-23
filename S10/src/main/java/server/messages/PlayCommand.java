package server.messages;

import domain.*;

public class PlayCommand extends Command {
    private static final long serialVersionUID = 20191101L;

    /**
     * L'index ( démarrant à 0) de la colonne dans laquelle le joueur souhaite placer son jeton
     */
    private Integer columnIndex;

    public PlayCommand(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public void applyForPlayer(Player player) throws ColumnFullException, InvalidColorTurnException {
        Board board = player.getBoard();

        board.play(player, columnIndex);
    }
}
