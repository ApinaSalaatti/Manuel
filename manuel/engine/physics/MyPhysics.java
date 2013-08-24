package manuel.engine.physics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import manuel.engine.Application;
import manuel.engine.entities.Entity;
import manuel.engine.entities.components.LocationComponent;
import manuel.engine.entities.components.PhysicsComponent;
import manuel.engine.eventManager.events.EntityEvents.EntityCreatedEvent;
import manuel.engine.eventManager.events.EntityEvents.EntityDestroyedEvent;
import manuel.engine.eventManager.events.PhysicsEvent.CollisionEndedEvent;
import manuel.engine.eventManager.events.PhysicsEvent.NewCollisionEvent;
import manuel.engine.physics.Listeners.EntityCreatedListener;
import manuel.engine.physics.Listeners.EntityDestroyedListener;
import manuel.engine.physics.myPhysics.*;
import manuel.engine.processManager.Process;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Merioksan Mikko
 */
public class MyPhysics extends Process implements IPhysics, IMyPhysicsCallback {
    private class CollisionPair {
        public PhysicsObject obj1, obj2;
        
        public CollisionPair(PhysicsObject o1, PhysicsObject o2) {
            obj1 = o1;
            obj2 = o2;
        }
        
        @Override
        public boolean equals(Object obj) {
            if(super.equals(obj)) {
                return true;
            }
            if(obj instanceof CollisionPair) {
                CollisionPair col = (CollisionPair)obj;
                if(col.obj1 == this.obj1 && col.obj2 == this.obj2) {
                    return true;
                }
                if(col.obj1 == this.obj2 && col.obj2 == this.obj1) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private ArrayList<CollisionPair> lastUpdateCollisions;
    
    private Map<Long, PhysicsObject> logicToPhysicsMap;
    
    private final static long fps = 60;
    private final static long timeStep = 1000 / fps;
    private long accumulated;
    
    private Physics physics;
    private CollisionDetection collisions;
    private World world;

    public MyPhysics() {
        lastUpdateCollisions = new ArrayList<>();
        
        logicToPhysicsMap = new HashMap<>();
        
        accumulated = 0;
        
        collisions = new CollisionDetection();
        world = new World();
        
        physics = new Physics(world, collisions);
        physics.setCallback(this);
        
        Application.eventManager.register(EntityCreatedEvent.eventType, new EntityCreatedListener(this));
        Application.eventManager.register(EntityDestroyedEvent.eventType, new EntityDestroyedListener(this));
    }
    
    public void addObject(Entity entity, boolean doesCollide) {
        LocationComponent locComp = (LocationComponent)entity.getComponent(LocationComponent.type);
        PhysicsComponent physComp = (PhysicsComponent)entity.getComponent(PhysicsComponent.type);
        
        if(physComp == null) {
            return;
        }
        
        Vector2f loc = locComp.getLocation();
        float width = physComp.getWidth();
        float height = physComp.getHeight();
        float mass = physComp.getMass();
        
        PhysicsObject obj = new PhysicsObject(doesCollide);
        obj.location.x = loc.x;
        obj.location.y = loc.y;
        obj.aabb.min = new Vector2f(loc.x - width / 2, loc.y - height / 2);
        obj.aabb.max = new Vector2f(loc.x + width / 2, loc.y + height / 2);
        obj.mass = mass;
        
        logicToPhysicsMap.put(entity.getID(), obj);
        world.addObject(obj, World.TYPE_DYNAMIC_OBJECT);
    }
    
    public void removeObject(Entity entity) {
        PhysicsObject obj = logicToPhysicsMap.remove(entity.getID());
        world.removeObject(obj);
    }
    
    @Override
    public void update(long deltaMs) {
        accumulated += deltaMs;
        
        while(accumulated > timeStep) {
            physics.step();
            accumulated -= timeStep;
        }
        
        syncObjects();
    }
    
    @Override
    public void physicsStepCallback() {
        ArrayList<CollisionPair> thisUpdateCollisions = new ArrayList<>();
        for(int i = 0; i < collisions.getManifoldAmount(); i++) {
            Manifold m = collisions.getManifoldByIndex(i);
            
            CollisionPair cp = new CollisionPair(m.obj1, m.obj2);
            thisUpdateCollisions.add(cp);
            if(!lastUpdateCollisions.contains(cp)) {
                Application.eventManager.queueEvent(new NewCollisionEvent(cp.obj1, cp.obj2));
            }
        }

        Iterator<CollisionPair> it = lastUpdateCollisions.iterator();
        while(it.hasNext()) {
            CollisionPair cp = it.next();
            if(!thisUpdateCollisions.contains(cp)) {
                Application.eventManager.queueEvent(new CollisionEndedEvent(cp.obj1, cp.obj2));
            }
        }
        
        lastUpdateCollisions.clear();
        lastUpdateCollisions.addAll(thisUpdateCollisions);
    }
    
    /**
     * Returns the physics object attached to the given entity id, if any
     * @param id
     * @return 
     */
    public PhysicsObject getObject(long id) {
        return logicToPhysicsMap.get(id);
    }
    
    @Override
    public void syncObjects() {
        for(Entry<Long, PhysicsObject> entry : logicToPhysicsMap.entrySet()) {
            long id = entry.getKey();
            PhysicsObject obj = entry.getValue();
            Entity e = Application.baseLogic.getEntity(id);
            LocationComponent lc = (LocationComponent)e.getComponent(LocationComponent.type);

            lc.getLocation().x = obj.location.x;
            lc.getLocation().y = obj.location.y;
        }
    }
    
    public void applyForce(long actorID, Vector2f force) {
        PhysicsObject obj = logicToPhysicsMap.get(actorID);
        if(obj != null) {
            obj.force.x += force.x;
            obj.force.y += force.y;
        }
    }
    public void addAcceleration(long actorID, Vector2f acc) {
        PhysicsObject obj = logicToPhysicsMap.get(actorID);
        if(obj != null) {
            obj.acceleration.x += acc.x;
            obj.acceleration.y += acc.y;
        }
    }
    public void applyAcceleration(long actorID, Vector2f acc) {
        PhysicsObject obj = logicToPhysicsMap.get(actorID);
        if(obj != null) {
            obj.acceleration.x = acc.x;
            obj.acceleration.y = acc.y;
        }
    }
    public void applyVelocity(long actorID, Vector2f vel) {
        PhysicsObject obj = logicToPhysicsMap.get(actorID);
        if(obj != null) {
            System.out.println(obj.velocity);
            obj.velocity.x = vel.x;
            obj.velocity.y = vel.y;
        }
    }
}
