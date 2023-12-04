package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer implements Playable{
    @Override
    public void playSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("./" + soundFile);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}
