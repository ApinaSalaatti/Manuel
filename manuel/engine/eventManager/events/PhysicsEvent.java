package manuel.engine.eventManager.events;

import manuel.engine.eventManager.IGameEvent;
import manuel.engine.physics.myPhysics.PhysicsObject;

/**
 *
 * @author Merioksan Mikko
 */
public class PhysicsEvent {
    public static class NewCollisionEvent implements IGameEvent {
        public static long eventType = 3;
        
        public PhysicsObject obj1;
        public PhysicsObject obj2;
        
        public NewCollisionEvent(PhysicsObject obj1, PhysicsObject obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }
        
        @Override
        public long getEventType() {
            return eventType;
        }
    }
    
    public static class CollisionEndedEvent implements IGameEvent {
        public static long eventType = 4;
        
        public PhysicsObject obj1;
        public PhysicsObject obj2;
        
        public CollisionEndedEvent(PhysicsObject obj1, PhysicsObject obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }
        
        @Override
        public long getEventType() {
            return eventType;
        }
    }
}
