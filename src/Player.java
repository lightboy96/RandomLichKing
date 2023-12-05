import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Player {

    private int playerNumber;
    private final Sounds sound;

    public Player(int playerIndex) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playerIndex =  playerIndex +1;
        String s = "/data/playerData/player" + playerIndex + ".wav";
        this.playerNumber = playerIndex;
        this.sound = new Sounds(s);

    }

    public void playPlayerSound() throws LineUnavailableException, IOException, InterruptedException {
        sound.playClip();
    }

}
