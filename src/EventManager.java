import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {
    public boolean exiting;
    public boolean starting;
    public  boolean newYear;
    private final List<Events> eventList = new ArrayList<>();
    private PlayerManager playerManager;

    public EventManager(int numberOfPlayers) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playerManager =  new PlayerManager(numberOfPlayers);
        Events e1  = new Events("The Lich King yells: Frostmourne hungers!","data/FrostmourneHungers.wav",false,false,playerManager); //Basic Event everyone drinks
        Events e2  = new Events("It is time for a random person to drink","noData",true,false,playerManager);

        eventList.add(e1);
        eventList.add(e2);
       }
    private void randomEvent() throws LineUnavailableException, IOException, InterruptedException {
        int listLength = eventList.size();
        int randomNumber;
        int randomPlayer;
        boolean selected = false;
        Random random  =  new Random();
        Events currentEvent;

        while(!selected){
           randomNumber = random.nextInt(listLength);
           currentEvent = eventList.get(randomNumber);

           if(currentEvent.isHourly()){
               currentEvent.hasLastRan();


           } else if (currentEvent.isPlayerDependant()) {
               currentEvent.playEvent();
               selected = true;
           }
        }
    }
}
