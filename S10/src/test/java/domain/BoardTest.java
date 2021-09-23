package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 * Board représente une partie de jeu, sa responsabilité est donc assez large et une instance de Board va souvent
 * déléguer à des objets plus spécifiques les détails de logique.
 * Cette classe de tests contient donc presqu'exclusivement des tests d'intégration : des qui valide la logique
 * d'une séquence d'appel.
 */
class BoardTest {

    private Board subject;

    @Nested
    class WhenEmpty {

        @BeforeEach
        void initEmptyBoard() {
            subject = new Board();
        }

        @Test
        void gameWillStartWithRed() {
            assertEquals(Color.RED, subject.getNextColorToPlay());
        }

        @Test
        void gameIsNotFinished() {
            assertFalse(subject.isFinished());
        }

    }

    @Nested
    class WhenInMiddleOfGame {

        private Player playerRed;
        private Player playerYellow;

        /**
         * Le jeu aura 2 jetons dans la colonne 0 (un rouge et un jaune) et un jeton rouge dans la colonne 1
         *
         * |   |   |   |   |   |   |   |
         * | J |   |   |   |   |   |   |
         * | R | R |   |   |   |   |   |
         * |---|---|---|---|---|---|---|
         *   0   1   2   3   4   5   6
         * C'est au jaune de jouer
         */
        @BeforeEach
        void initBoard() throws ColumnFullException, InvalidColorTurnException {
            subject = new Board();
            playerRed = subject.addNewPlayer();
            playerYellow = subject.addNewPlayer();

            subject.play(playerRed, 0);
            subject.play(playerYellow, 0);
            subject.play(playerRed, 1);
        }

        @Test
        void wrongPlayerCannotPlay() {
            assertThrows(InvalidColorTurnException.class, () -> subject.play(playerRed, 1));
        }

        /**
         * Exemple d'un assertAll plutot que d'écrire deux tests différents, en effet on ne teste ici qu'un seul aspect
         * de la logique.
         */
        @Test
        void playerCannotPlayOutOfBonds() {
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> subject.play(playerYellow, -1)),
                    () -> assertThrows(IllegalArgumentException.class, () -> subject.play(playerYellow, 7))
            );
        }

        /**
         * Ce test est très clairement un test d'intégration, pour qu'il soit vert il faut qu'une assez grande
         * proportion de code soit correct.
         */
        @Test
        void playerCannotAddColorToFullColumn() throws ColumnFullException, InvalidColorTurnException {
            int columnIndex = 5;
            // On a volontairement choisi de ne pas faire une boucle ni d'introduire de logique pour passer
            // d'un player à l'autre.
            // les tests ont pour objectif d'etre extremement explicite sur ce qui est fait afin de servir de
            // documentation par l'exemple. L'exemple ici est nettement plus clair à comprendre sans boucle ni logique.
            subject.play(playerYellow, columnIndex);
            subject.play(playerRed, columnIndex);
            subject.play(playerYellow, columnIndex);
            subject.play(playerRed, columnIndex);
            subject.play(playerYellow, columnIndex);
            subject.play(playerRed, columnIndex);

            Player nextPlayer = playerYellow;
            assertThrows(ColumnFullException.class, () -> subject.play(nextPlayer, columnIndex));
        }

    }

    @Nested
    class WhenGameCanBeFinished {

        private Player playerRed;
        private Player playerYellow;

        /**
         * Le jeu aura 3 jetons rouge dans la colonne 0 et 3 jetons jaunes dans la colonne 1
         * C'est au rouge de jouer
         * <p>
         * |   |   |   |   |   |   |   |
         * |   |   |   |   |   |   |   |
         * |   |   |   |   |   |   |   |
         * | R | J |   |   |   |   |   |
         * | R | J |   |   |   |   |   |
         * | R | J |   |   |   |   |   |
         * |---|---|---|---|---|---|---|
         * 0   1   2   3   4   5   6
         */
        @BeforeEach
        void initBoard() throws ColumnFullException, InvalidColorTurnException {
            subject = new Board();
            playerRed = subject.addNewPlayer();
            playerYellow = subject.addNewPlayer();

            for (int i = 0; i < 3; i++) {
                subject.play(playerRed, 0);
                subject.play(playerYellow, 1);
            }
        }

        /**
         * Ici on s'occupe bien de tester la logique entre le fait de jouer un coup victorieux et l'état de la partie.
         * C'est donc bien un test d'intégration.
         * Il est cependant nécessaire de vérifier qu'on est capable de détecter tous les coups victorieux possibles,
         * c'est la responsabilité du test unitaire d'isolation IsFinishMoveTest.
         */
        @Test
        void playerEndsTheGameByWinning() throws ColumnFullException, InvalidColorTurnException {
            subject.play(playerRed, 0);

            assertTrue(subject.isFinished());
            assertEquals(playerRed, subject.getWinner());
        }

        @Test
        void playerEndsTheGameByGivingUp(){
            subject.giveUp(playerRed);

            assertTrue(subject.isFinished());
            assertEquals(playerYellow, subject.getWinner());
        }
    }


}
