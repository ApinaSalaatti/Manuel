package manuel.engine.Scenes;

import manuel.engine.eventManager.IEventListener;
import manuel.engine.eventManager.IGameEvent;
import manuel.engine.eventManager.events.EntityEvents.EntityDestroyedEvent;
import manuel.engine.eventManager.events.EntityEvents.SceneActorCreatedEvent;

/**
 *
 * @author Merioksan Mikko
 */
public class Listeners {
    public static class SceneActorCreatedListener implements IEventListener {
        private Scene scene;
        
        public SceneActorCreatedListener(Scene s) {
            scene = s;
        }
        
        @Override
        public void execute(IGameEvent event) {
            SceneActorCreatedEvent sace = (SceneActorCreatedEvent)event;
            scene.addActor(sace.getActor());
        }
    }
    
    public static class EntityDestroyedListener implements IEventListener {
        private Scene scene;
        
        public EntityDestroyedListener(Scene s) {
            scene = s;
        }
        
        @Override
        public void execute(IGameEvent event) {
            EntityDestroyedEvent ede = (EntityDestroyedEvent)event;
            scene.removeActor(ede.getEntity().getID());
        }
    }
}
