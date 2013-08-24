package manuel.engine.eventManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class for managing events.
 * 
 * To create an event, just extends the GameEvent class. To listen for that event in your game, create listener classes that calls the appropriate
 * methods in your game classes and then pass that listener to the register method.
 * 
 * @author Merioksan Mikko
 */
public class EventManager {
    private static final long lastEventType = 13;
    /**
     * The two queues that the events are in turns read from and written to.
     */
    private ArrayList<ArrayDeque<IGameEvent>> eventQueues;
    private int currentQueue;
    
    /**
     * The event listeners
     */
    private HashMap<Long, ArrayList<IEventListener>> listeners;
    
    public EventManager() {
        eventQueues = new ArrayList<>();
        eventQueues.add(new ArrayDeque<IGameEvent>());
        eventQueues.add(new ArrayDeque<IGameEvent>());
        currentQueue = 0;
        
        listeners = new HashMap<>();
    }
    
    public void queueEvent(IGameEvent event) {
        eventQueues.get(currentQueue).addLast(event);
    }
    
    public void register(long eventType, IEventListener listener) {
        if(!listeners.containsKey(eventType)) {
            listeners.put(eventType, new ArrayList<IEventListener>());
        }
        listeners.get(eventType).add(listener);
    }
    
    public void executeEvent(IGameEvent e) {
        ArrayList<IEventListener> l = listeners.get(e.getEventType());
        
        if(l != null) {
            for(IEventListener listener : l) {
                listener.execute(e);
            }
        }
    }
    
    public void update(long deltaMs) {
        int current = currentQueue;
        currentQueue = (currentQueue + 1) % 2;
        eventQueues.get(currentQueue).clear();
        
        ArrayDeque<IGameEvent> toEmpty = eventQueues.get(current);
        
        while(!toEmpty.isEmpty()) {
            IGameEvent event = toEmpty.pollFirst();
            executeEvent(event);
        }
    }
}
