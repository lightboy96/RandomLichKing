import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {
    private final List<Events> eventList = new ArrayList<>();
    public boolean exiting;
    public boolean starting;
    public boolean newYear;
    private final PlayerManager playerManager;

    public EventManager(int numberOfPlayers) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playerManager = new PlayerManager(numberOfPlayers);
        Events e1 = new Events("The Lich King yells: Frostmourne hungers!", "data/FrostmourneHungers.wav", false, false, playerManager); //Basic Event everyone drinks
        Events e2 = new Events("It is time for a random person to drink", "noData", true, false, playerManager);

        eventList.add(e1);
        eventList.add(e2);
    }

    public void randomEvent() throws LineUnavailableException, IOException, InterruptedException {
        int listLength = eventList.size();
        int randomNumber;
        int randomPlayer;
        boolean selected = false;
        Random random = new Random();
        Events currentEvent;
        int i= 0;
        while (!selected) {
            i++;
            System.out.println(i);
            randomNumber = random.nextInt(listLength);
            currentEvent = eventList.get(randomNumber);

            System.out.println("Number:" + randomNumber);


            if (currentEvent.isHourly()) {
                selected  = false;


            } else if (currentEvent.isPlayerDependant()) {
                currentEvent.playEvent();
                selected = true;
            } else {
                currentEvent.playEvent();
                selected = true;
            }
        }
    }
}

