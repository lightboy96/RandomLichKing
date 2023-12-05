import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Events {
    //This class adds the events where an event will have multiple properties
    private final boolean isPlayerDependant;
    private final boolean isHourly;
    private int lastRan;
    private String message;
    private Sounds sounds;
    private PlayerManager playerManager;



    public Events(String inputMessage, String sound, boolean playerDependant, boolean hourly, PlayerManager playerManager) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (playerDependant) {
            this.isPlayerDependant = true;
            this.playerManager = playerManager;
        } else {
            initNonPlayerDependant(inputMessage, sound);
            this.isPlayerDependant = false;

        }
        this.isHourly = hourly;

    }

    private void initNonPlayerDependant(String inputMessage, String sound) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.message = inputMessage;
        this.sounds = new Sounds(sound);

    }
    public void playEvent() throws LineUnavailableException, IOException, InterruptedException {
        if(this.isPlayerDependant){
         playerManager.randomPlayerEvent();
        }
        if(this.isHourly){
            //Needs to be filled with time logic
        }
    }


    public boolean isPlayerDependant() {
        return isPlayerDependant;
    }

    public boolean isHourly() {
        return isHourly;
    }

    public int hasLastRan() {
        return lastRan;
    }
}
