package server.networking;

import domain.Board;
import domain.ColumnFullException;
import domain.InvalidColorTurnException;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Stubber;
import server.messages.ErrorMessage;
import server.messages.MaxPlayerReachedMessage;
import server.messages.PlayCommand;
import server.messages.RegistrationMessage;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.function.Supplier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServerWorkerTest {

    @InjectMocks
    private ServerWorker subject;

    @Mock
    private ObjectInput fakeInput;
    @Mock
    private ObjectOutput fakeOutput;
    @Mock
    private Board fakeBoard;
    @Mock
    private Server fakeServer;

    @Nested
    class WhenConnecting {

        Player playerStub;

        @Test
        void replyWithRegistrationAfterPlayerRegistration() throws IOException {
            registrationWillSucceed();
            subject.run();
            verify(fakeOutput, times(1)).writeObject(isA(RegistrationMessage.class));
        }

        @Test
        void replyWithMaxPlayerReachedAfterLastPlayerRegistration() throws IOException {
            boardWillBeFull();
            subject.run();
            verify(fakeOutput, times(1)).writeObject(isA(MaxPlayerReachedMessage.class));
        }

        @Test
        void broadCastBoardStateAfterLastPlayerRegistration() {
            registrationWillBeLastBeforeFullSucceed();
            subject.run();
            verify(fakeServer, times(1)).broadcastBoardstate();
        }

        private void registrationWillSucceed() {
            when(fakeBoard.hasMaxPlayers()).thenReturn(false);
            playerStub = mock(Player.class);
            when(fakeBoard.addNewPlayer()).thenReturn(playerStub);
        }

        private void registrationWillBeLastBeforeFullSucceed() {
            when(fakeBoard.hasMaxPlayers()).thenReturn(false, true);
            playerStub = mock(Player.class);
            when(fakeBoard.addNewPlayer()).thenReturn(playerStub);
        }

        private void boardWillBeFull() {
            when(fakeBoard.hasMaxPlayers()).thenReturn(true);
        }

    }

    @Nested
    class WhenPlaying {

        Player fakePlayer;

        @BeforeEach
        private void setUp() throws IOException {
            fakePlayer = mock(Player.class);
            when(fakeBoard.hasMaxPlayers()).thenReturn(false, true);
            when(fakeBoard.addNewPlayer()).thenReturn(fakePlayer);
        }

        @Test
        void InvalidTurnExceptionAreTransmittedAsErrorMessages() throws IOException, ClassNotFoundException, ColumnFullException, InvalidColorTurnException {
            nextPlayCommandWill(
                    () -> doThrow(InvalidColorTurnException.class)
            );

            subject.run();

            verify(fakeOutput, times(1)).writeObject(isA(ErrorMessage.class));
        }

        @Test
        void ColumnFullExceptionAreTransmittedAsErrorMessages() throws IOException, ClassNotFoundException, ColumnFullException, InvalidColorTurnException {
            nextPlayCommandWill(
                    () -> doThrow(ColumnFullException.class)
            );

            subject.run();

            verify(fakeOutput, times(1)).writeObject(isA(ErrorMessage.class));
        }

        @Test
        void ValidPlayCommandTriggerANewBroadcastOfTheState() throws ColumnFullException, InvalidColorTurnException, IOException, ClassNotFoundException {
            nextPlayCommandWill(
                    () -> doNothing()
            );

            subject.run();

            verify(fakeServer, times(2)).broadcastBoardstate();
        }

        private void nextPlayCommandWill(Supplier<Stubber> stubber) throws ColumnFullException, InvalidColorTurnException, IOException, ClassNotFoundException {
            PlayCommand fakeCommand = mock(PlayCommand.class);
            Stubber stub = stubber.get();
            stub.when(fakeCommand).applyForPlayer(fakePlayer);
            when(fakeInput.readObject()).thenReturn(fakeCommand, null);
        }

    }

    @Nested
    class WhenFinishing {

        Player fakePlayer;

        @BeforeEach
        private void setUp() throws IOException, ClassNotFoundException {
            fakePlayer = mock(Player.class);
            PlayCommand playCommand = mock(PlayCommand.class);

            when(fakeBoard.hasMaxPlayers()).thenReturn(false, true);
            when(fakeBoard.addNewPlayer()).thenReturn(fakePlayer);
            when(fakeInput.readObject()).thenReturn(playCommand, null)
            ;
        }

        @Test
        void serverIsShutWhenGameIsFinished() throws IOException {
            when(fakeBoard.isFinished()).thenReturn(true);
            subject.run();
            verify(fakeServer, times((1))).shutdown();
        }

    }
}
