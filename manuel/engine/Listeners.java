package manuel.engine;

import manuel.engine.eventManager.IEventListener;
import manuel.engine.eventManager.IGameEvent;
import manuel.engine.eventManager.events.EntityEvents.EntityCreatedEvent;
import manuel.engine.eventManager.events.EntityEvents.EntityDestroyedEvent;

/**
 * Application level and baselogic level listeners.
 * 
 * @author Merioksan Mikko
 */
public class Listeners {
    public static class EntityCreatedListener implements IEventListener {
        private BaseLogic logic;
        
        public EntityCreatedListener(BaseLogic l) {
            logic = l;
        }
        
        @Override
        public void execute(IGameEvent event) {
            EntityCreatedEvent ece = (EntityCreatedEvent)event;
            logic.addEntity(ece.getEntity());
        }
    }
    
    public static class EntityDestroyedListener implements IEventListener {
        private BaseLogic logic;
        
        public EntityDestroyedListener(BaseLogic l) {
            logic = l;
        }
        
        @Override
        public void execute(IGameEvent event) {
            EntityDestroyedEvent ede = (EntityDestroyedEvent)event;
            logic.removeEntity(ede.getEntity().getID());
        }
    }
}
