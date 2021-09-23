package domain;

import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Cette classe est un Method Object, un objet qui a pour unique responsabilité de représenter la logique d'une méthode.
 * La méthode en question est Board.isFinishMove() , mais la logique de cette méthode est trop riche pour tenir dans
 * la classe Board, c'est pourquoi la classe Board va instancier un objet de cette classe-ci et lui déléguer le calcul.
 *
 * Puisque les instances de cette classe sont capable de renvoyer un boolean sans avoir besoin de paramètre additionnels
 * à ceux passés dans le constructeur, nous pouvons implémenter Supplier<Boolean> afin de rendre cette classe compatible
 * avec tous les objets nécessitant une lambda de provider ou un provider.
 *
 */
class IsFinishMove implements Supplier<Boolean> {

    /**
     * La matrice de couleur représentant la partie en cours
     */
    private Color[][] colorsMatrix;
    /**
     * L'index de la colonne qui vient d'être jouée.
     */
    private int columnIndex;
    /**
     * La hauteur du jeton qui vient d'être joué sous la forme d'un index (démarre à 0)
     */
    private int rowIndex;

    /**
     * La couleur du dernier jeton sur la colonne.
     * L'objectif est de déterminer si ce jeton forme est connecté à 3 autres jetons de la même couleur.
     */
    private Color color;

    /**
     * Construit un objet en lui passant toutes ses dépendances.
     */
    public IsFinishMove(Color[][] colorsMatrix, int columnsIndex, int rowIndex) {
        this.colorsMatrix = colorsMatrix;
        this.columnIndex = columnsIndex;
        this.rowIndex = rowIndex;
        this.color = colorsMatrix[columnsIndex][rowIndex];
    }

    /**
     * Cette méthode délègue simplement à la vraie méthode, c'est un pattern Adapter afin de pouvoir implémenter
     * l'interface Supplier.
     * @see IsFinishMove#verify()
     */
    public Boolean get(){
        return verify();
    }

    public boolean verify(){
        if(this.verifyNorthSouth()) return true;
        if(this.verifyWestEast()) return true;
        if(this.verifyNorthWestSouthEast()) return true;
        if(this.verifyNorthEastSouthWest()) return true;
        return false;
    }

    private boolean verifyNorthSouth(){
        IntFunction<Optional<Color>> colorPicker = offset -> {
            return this.pick(columnIndex, rowIndex + offset);
        };
        Color[] colors = this.pickAlignedColors(-3,0, colorPicker);
        return this.check4SameColor(colors);
    }

    private boolean verifyWestEast(){
        return this.verifyWestEast(-3,0) ||
                this.verifyWestEast(-2,1) ||
                this.verifyWestEast(-1,2) ||
                this.verifyWestEast(0,3);
    }

    private boolean verifyWestEast(int before, int after){
        IntFunction<Optional<Color>> colorPicker = offset -> {
            return this.pick(columnIndex + offset, rowIndex);
        };
        Color[] colors = this.pickAlignedColors(before, after, colorPicker);
        return this.check4SameColor(colors);
    };

    private boolean verifyNorthWestSouthEast(){
        return this.verifyNorthWestSouthEast(-3,0) ||
                this.verifyNorthWestSouthEast(-2,1) ||
                this.verifyNorthWestSouthEast(-1,2) ||
                this.verifyNorthWestSouthEast(0,3);
    }

    private boolean verifyNorthWestSouthEast(int before, int after){
        IntFunction<Optional<Color>> colorPicker = offset -> {
            return this.pick(columnIndex - offset, rowIndex + offset);
        };
        Color[] colors = this.pickAlignedColors(before, after, colorPicker);
        return this.check4SameColor(colors);
    };


    private boolean verifyNorthEastSouthWest(){
        return this.verifyNorthEastSouthWest(-3,0) ||
                this.verifyNorthEastSouthWest(-2,1) ||
                this.verifyNorthEastSouthWest(-1,2) ||
                this.verifyNorthEastSouthWest(0,3);
    }

    private boolean verifyNorthEastSouthWest(int before, int after){
        IntFunction<Optional<Color>> colorPicker = offset -> {
            return this.pick(columnIndex + offset, rowIndex + offset);
        };
        Color[] colors = this.pickAlignedColors(before, after, colorPicker);
        return this.check4SameColor(colors);
    };


    private Color[] pickAlignedColors(int offsetMin, int offsetMax, IntFunction<Optional<Color>> colorPicker){
        Color[] colors = IntStream
                .rangeClosed(offsetMin, offsetMax)
                .mapToObj(colorPicker)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toArray(Color[]::new);

       return colors;
    }

    private boolean check4SameColor(Color[] colors){
        if(colors.length != 4)
            return false;

        return Stream
                .of(colors)
                .allMatch(this.color::equals);
    }

    private Optional<Color> pick(int columnIndex, int rowIndex){
        if(columnIndex < 0 || columnIndex >= this.colorsMatrix.length)
                return Optional.empty();
        Color[] column = colorsMatrix[columnIndex];
        if(rowIndex < 0 || rowIndex >= column.length)
            return Optional.empty();

        Color value = column[rowIndex];
        if(value == null)
            return Optional.empty();

        return Optional.of(value);
    }
}
