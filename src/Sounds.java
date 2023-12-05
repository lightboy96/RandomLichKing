import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    private final AudioInputStream audioIn;
    private final Clip clip;

    public Sounds(String input) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("./" + input);
        audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
        clip = AudioSystem.getClip();
    }

    public void playClip() throws LineUnavailableException, IOException, InterruptedException {
        clip.open(audioIn);
        clip.start();
        try {
            Thread.sleep(clip.getMicrosecondLength());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

