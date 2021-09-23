package domain;


import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Board est une classe Facade qui cache l'existence d'autres classe en exposant un accès restreint à celles-ci
 * au travers d'un nombre limité de méthodes.
 * Une instance de Board représente une partie.
 */
public class Board {

    private int COLUMNS_COUNT = 7;

    private Column[] columns;
    private Color nextColorToPlay;
    private Player winner;

    private Player playerRed;
    private Player playerYellow;

    public Board() {
        this.nextColorToPlay = Color.RED;
        this.columns = IntStream
                        .range(0, COLUMNS_COUNT)
                        .mapToObj(index -> new Column())
                        .toArray(Column[]::new)
        ;
    }

    public Color getNextColorToPlay() {
        return nextColorToPlay;
    }

    public Column[] getColumns() {
        return columns;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isFinished(){
        return this.winner != null;
    }

    public void registerPlayer(Player player){
        if(player.getColor() == Color.RED){
            this.playerRed = player;
        } else {
            this.playerYellow = player;
        }
    }

    public void play(Player player, int index) throws ColumnFullException, InvalidColorTurnException {
        Color color = player.getColor();
        assertValidColorTurn(color);
        assertValidColumnIndex(index);
        Column column = columns[index];
        assertColumnNotFull(column);

        column.play(color);
        if(this.lastMoveIsFinishing(index)) {
            this.winner = player;
            return;
        }

        this.nextColorToPlay = this.nextColorToPlay.nextColor();

    }

    public void giveUp(Player player)  {
        Player otherPlayer = player == playerRed ? playerYellow : playerRed;
        this.winner = otherPlayer;
    }

    public boolean hasMaxPlayers() {
        return playerRed != null && playerYellow != null;
    }

    public Player addNewPlayer(){
        if(playerRed == null){
            return new Player(this, Color.RED);
        } else {
            return new Player(this, Color.YELLOW);
        }

    }

    public Color[][] buildColorsMatrix(){
        return Stream
                .of(this.getColumns())
                .map(Column::getTokenStack)
                .map(Color[]::clone)
                .toArray(Color[][]::new);
    }

    private boolean lastMoveIsFinishing(int columnIndex){
        Color[][] colorsMatrix = buildColorsMatrix();
        Column column = this.columns[columnIndex];
        int rowIndex = column.getCurrentStackSize() - 1;
        IsFinishMove isFinishMove = new IsFinishMove(colorsMatrix, columnIndex, rowIndex);
        return isFinishMove.verify();
    }

    private void assertColumnNotFull(Column column) throws ColumnFullException {
        if(column.isFull())
            throw new ColumnFullException();
    }

    private void assertValidColumnIndex(int index){
        if(index >= COLUMNS_COUNT || index < 0)
            throw new IllegalArgumentException("Invalid column index : "+ index);
    }

    private void assertValidColorTurn(Color color) throws InvalidColorTurnException {
        if(color != this.nextColorToPlay)
            throw new InvalidColorTurnException();
    }

}
