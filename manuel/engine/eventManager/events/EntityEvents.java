package manuel.engine.eventManager.events;

import manuel.engine.Scenes.SceneActor;
import manuel.engine.entities.Entity;
import manuel.engine.eventManager.IGameEvent;

/**
 *
 * @author Merioksan Mikko
 */
public class EntityEvents {
    public static class EntityCreatedEvent implements IGameEvent {
        public static long eventType = 5;
        
        private Entity entity;
        
        public EntityCreatedEvent(Entity e) {
            entity = e;
        }
        
        @Override
        public long getEventType() {
            return eventType;
        }
        
        public Entity getEntity() {
            return entity;
        }
    }
    
    public static class EntityDestroyedEvent implements IGameEvent {
        public static long eventType = 9;
        
        private Entity entity;
        
        public EntityDestroyedEvent(Entity e) {
            entity = e;
        }
        
        @Override
        public long getEventType() {
            return eventType;
        }
        
        public Entity getEntity() {
            return entity;
        }
    }
    
    public static class SceneActorCreatedEvent implements IGameEvent {
        public static long eventType = 8;
        
        private SceneActor actor;
        
        public SceneActorCreatedEvent(SceneActor a) {
            actor = a;
        }
        
        @Override
        public long getEventType() {
            return eventType;
        }
        
        public SceneActor getActor() {
            return actor;
        }
    }
}
