package audio;

import service.logger.ConsoleLogger;
import service.logger.Logger;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer implements Playable {
    Logger logger = new ConsoleLogger();

    @Override
    public void playSound(String soundFile) {
        File file = new File("./" + soundFile);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL())) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            while (clip.isRunning()) {
                Thread.sleep(clip.getMicrosecondLength());
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            logger.logError(e.getMessage());
        }
    }
}
