package audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface Playable {
    void playSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException;
}
