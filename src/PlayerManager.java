import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private final int currentPLayers;
    private final int maxNumber = 10;
    private final List<Player> players = new ArrayList<>();


    public PlayerManager(int numberOfPlayers) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        validateNumberOfPlayers(numberOfPlayers);
        this.currentPLayers = numberOfPlayers;
        initPlayers();

    }
    private void initPlayers() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        for(int i = 0; i < this.currentPLayers; i++) {
            Player player =  new Player(i);
            players.add(player);
        }
    }
    public void playPlayerSound(int index) throws LineUnavailableException, IOException, InterruptedException {
        Player currentPlayer = players.get(index-1);
        currentPlayer.playPlayerSound();

    }



    private void validateNumberOfPlayers(int i) {
        if (i < 1 || i > this.maxNumber) {
            throw new IllegalArgumentException("Player number must be between 1 and " + this.maxNumber);
        }
    }
}
