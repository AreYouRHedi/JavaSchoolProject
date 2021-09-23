package domain;

import domain.Color;
import domain.IsFinishMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Nous allons tester un mouvement final fait par un jeton rouge sur le haut d'une colonne
 * - Il existe 1 manière de gagner avec un segment Nord / Sud
 * - Il existe 4 manières de gagner avec un segment Ouest / Est
 * - Il existe 4 manières de gagner avec un segment Nord-Ouest / Sud-Est
 * - Il existe 4 manières de gagner avec un segment Nord-Est / Sud-Ouest
 *
 */
class IsFinishMoveTest {

    private Color[][] colorMatrix;

    @BeforeEach
    private void setup(){
        this.colorMatrix = new Color[7][6];
    }

    @Nested
    class WhenNorthSouth {
        @BeforeEach
        private void setup(){
            colorMatrix[0][0] = Color.RED;
            colorMatrix[0][1] = Color.RED;
            colorMatrix[0][2] = Color.RED;
            colorMatrix[0][3] = Color.RED;
        }

        @Test
        void testNorthSouth() {
            int columnIndex = 0;
            int rowIndex = 3;

            IsFinishMove subject = new IsFinishMove(colorMatrix, columnIndex, rowIndex);
            assertTrue(subject.verify());
        }
    }

    @Nested
    class WhenWestEast {

        @BeforeEach
        private void setup(){
            colorMatrix[0][0] = Color.RED;
            colorMatrix[1][0] = Color.RED;
            colorMatrix[2][0] = Color.RED;
            colorMatrix[3][0] = Color.RED;
        }

        @Test
        void testWinningOnPosition1() {
            assertWinningCoordinates(colorMatrix, 0, 0);
        }

        @Test
        void testWinningOnPosition2() {
            assertWinningCoordinates(colorMatrix, 1, 0);
        }

        @Test
        void testWinningOnPosition3() {
            assertWinningCoordinates(colorMatrix, 2, 0);
        }

        @Test
        void testWinningOnPosition4() {
            assertWinningCoordinates(colorMatrix, 3, 0);
        }
    }

    @Nested
    class WhenNorthWestSouthEast {

        @BeforeEach
        private void setup(){
            colorMatrix[0][3] = Color.RED;
            colorMatrix[1][2] = Color.RED;
            colorMatrix[2][1] = Color.RED;
            colorMatrix[3][0] = Color.RED;
        }

        @Test
        void testWinningOnPosition1() {
            assertWinningCoordinates(colorMatrix, 0, 3);
        }

        @Test
        void testWinningOnPosition2() {
            assertWinningCoordinates(colorMatrix, 1, 2);
        }

        @Test
        void testWinningOnPosition3() {
            assertWinningCoordinates(colorMatrix, 2, 1);
        }

        @Test
        void testWinningOnPosition4() {
            assertWinningCoordinates(colorMatrix, 3, 0);
        }
    }

    @Nested
    class WhenNorthEastSouthWest {

        @BeforeEach
        private void setup(){
            colorMatrix[0][0] = Color.RED;
            colorMatrix[1][1] = Color.RED;
            colorMatrix[2][2] = Color.RED;
            colorMatrix[3][3] = Color.RED;
        }

        @Test
        void testWinningOnPosition1() {
            assertWinningCoordinates(colorMatrix, 0, 0);
        }

        @Test
        void testWinningOnPosition2() {
            assertWinningCoordinates(colorMatrix, 1, 1);
        }

        @Test
        void testWinningOnPosition3() {
            assertWinningCoordinates(colorMatrix, 2, 2);
        }

        @Test
        void testWinningOnPosition4() {
            assertWinningCoordinates(colorMatrix, 3, 3);
        }
    }

    private void assertWinningCoordinates(Color[][] colorsMatrix,int columnIndex, int rowIndex){
        IsFinishMove subject = new IsFinishMove(colorsMatrix, columnIndex, rowIndex);
        assertTrue(
                subject.verify(),
                "Expected following position to be winning but was not : [" + columnIndex+ "," + rowIndex+"]"
        );
    }

}