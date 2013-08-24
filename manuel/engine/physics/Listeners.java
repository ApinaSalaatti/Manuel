package manuel.engine.physics;

import manuel.engine.entities.components.PhysicsComponent;
import manuel.engine.eventManager.IEventListener;
import manuel.engine.eventManager.IGameEvent;
import manuel.engine.eventManager.events.EntityEvents.EntityCreatedEvent;
import manuel.engine.eventManager.events.EntityEvents.EntityDestroyedEvent;

/**
 *
 * @author Merioksan Mikko
 */
public class Listeners {
    public static class EntityCreatedListener implements IEventListener {
        private MyPhysics physics;
        
        public EntityCreatedListener(MyPhysics phys) {
            physics = phys;
        }
        
        @Override
        public void execute(IGameEvent event) {
            EntityCreatedEvent ece = (EntityCreatedEvent)event;
            PhysicsComponent pc = (PhysicsComponent)ece.getEntity().getComponent(PhysicsComponent.type);
            if(pc != null) {
                physics.addObject(ece.getEntity(), pc.isSolid());
            }
        }
    }
    
    public static class EntityDestroyedListener implements IEventListener {
        private MyPhysics physics;
        
        public EntityDestroyedListener(MyPhysics phys) {
            physics = phys;
        }
        
        @Override
        public void execute(IGameEvent event) {
            EntityDestroyedEvent ece = (EntityDestroyedEvent)event;
            physics.removeObject(ece.getEntity());
        }
    }
}
