package com.gdn.randomlichking.audio;

import com.gdn.randomlichking.service.logger.ConsoleLogger;
import com.gdn.randomlichking.service.logger.Logger;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayerImpl implements SoundPlayer {
    private final Logger logger = new ConsoleLogger();

    @Override
    public void playSound(String soundFile) {
        File file = new File("./" + soundFile);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL())) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.logError(e.getMessage());
        }
    }
}
