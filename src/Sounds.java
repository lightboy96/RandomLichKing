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

    public void playClip()  {

        try {
            clip.open(audioIn);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();
        try {
            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clip.close();
        //Thread.sleep(clip.getMicrosecondLength());

    }
    private void losingMymind(){

    }


}

