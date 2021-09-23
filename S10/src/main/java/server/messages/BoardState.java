package server.messages;

import domain.Board;
import domain.Color;

import java.io.Serializable;

public class BoardState implements Serializable {

	private static final long serialVersionUID = 20191101L;

    private Color nextColorToPlay;
    private Color winnerColor;
    private Color[][] boardColors;

    public static BoardState fromBoard(Board board) {
        Color[][] boardColors = board.buildColorsMatrix();
        Color nextColorToPlay = board.getNextColorToPlay();
        Color winnerColor = board.isFinished() ? board.getWinner().getColor() : null;
        return new BoardState(boardColors, nextColorToPlay, winnerColor);
    }

    public BoardState(Color[][] boardColors, Color nextColorToPlay, Color winnerColor) {
        this.boardColors = boardColors;
        this.nextColorToPlay = nextColorToPlay;
        this.winnerColor = winnerColor;
    }

    public Color getNextColorToPlay() {
        return nextColorToPlay;
    }

    public Color getWinnerColor() {
        return winnerColor;
    }

    public boolean gameIsFinished(){
        return this.winnerColor != null;
    }

    public Color[][] getBoardColors() {
        return boardColors;
    }
}
