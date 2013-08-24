package manuel.engine.physics.myPhysics;

import manuel.engine.physics.myPhysics.PhysicsObject;
import java.util.ArrayList;
import java.util.List;

/**
 * A world is where all the physics objects live and collide.
 * 
 * @author Merioksan Mikko
 */
public class World {
    public static int TYPE_DYNAMIC_OBJECT;
    public static int TYPE_STATIC_OBJECT;
    public float friction = 0.5f;
    
    private List<PhysicsObject> dynamicObjects;
    private List<PhysicsObject> staticObjects;
    
    public World() {
        dynamicObjects = new ArrayList<>();
        staticObjects = new ArrayList<>();
    }
    
    public List<PhysicsObject> getDynamicObjects() {
        return dynamicObjects;
    }
    public List<PhysicsObject> getStaticObjects() {
        return staticObjects;
    }
    public void addObject(PhysicsObject obj, int type) {
        if(type == TYPE_DYNAMIC_OBJECT) {
            dynamicObjects.add(obj);
        }
        else if(type == TYPE_STATIC_OBJECT) {
            staticObjects.add(obj);
        }
    }
    public void removeObject(PhysicsObject obj) {
        dynamicObjects.remove(obj);
        staticObjects.remove(obj);
    }
    
    public void step() {
        for(PhysicsObject obj : dynamicObjects) {
            obj.step();
        }
    }
}
