package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer implements Playable {
    @Override
    public void playSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("./" + soundFile);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL());
             Clip clip = AudioSystem.getClip()) {

            clip.open(audioInputStream);
            clip.start();

            // Wait for the clip to finish
            while (clip.isRunning()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            clip.stop();
        }
    }
}
