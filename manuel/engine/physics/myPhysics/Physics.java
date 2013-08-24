package manuel.engine.physics.myPhysics;

import manuel.engine.physics.myPhysics.World;
import manuel.engine.physics.myPhysics.CollisionDetection;
import org.lwjgl.util.vector.Vector2f;
/**
 *
 * @author Merioksan Mikko
 */
public class Physics {
    public static float TIMESTEP = 1.0f / 60.0f; // one time step in seconds
    private World world;
    private CollisionDetection collisions;
    
    private IMyPhysicsCallback callback;
    
    public Physics(World w, CollisionDetection cd) {
        world = w;
        collisions = cd;
    }
    
    public void setCallback(IMyPhysicsCallback cb) {
        callback = cb;
    }
    
    public void step() {
        world.step();
        collisions.check(world.getDynamicObjects(), world.getStaticObjects());
        collisions.resolveCollisions();
        
        if(callback != null) {
            callback.physicsStepCallback();
        }
    }
}
