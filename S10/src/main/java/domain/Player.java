package domain;

import domain.Board;
import domain.Color;

public class Player {

    private Board board;
    private Color color;

    public Player(Board board, Color color) {
        this.board = board;
        this.color = color;
        board.registerPlayer(this);
    }

    public Board getBoard() {
        return board;
    }

    public Color getColor() {
        return color;
    }
}
