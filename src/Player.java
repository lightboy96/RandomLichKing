import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Player {

    private int playerNumber;
    private final Sounds sound;
    private String playerMessage;

    public Player(int playerIndex, String message) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playerIndex =  playerIndex +1;
        String s = "/data/playerData/player" + playerIndex + ".wav";
        this.playerNumber = playerIndex;
        this.sound = new Sounds(s);
        this.playerMessage = message;

    }
    public void writePlayer(){
        //
    }

    public void playPlayerSound() throws LineUnavailableException, IOException, InterruptedException {
        sound.playClip();
    }
    public void writeMessage(){
        System.out.println(this.playerMessage);
    }

}
