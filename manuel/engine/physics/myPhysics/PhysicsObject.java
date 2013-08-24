package manuel.engine.physics.myPhysics;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Merioksan Mikko
 */
public class PhysicsObject {
    public Vector2f location, velocity, acceleration, force;
    public float mass;
    public boolean doesCollide;
    
    // hitbox stuf
    public AABB aabb;
    
    public PhysicsObject(boolean collides) {
        location = new Vector2f();
        velocity = new Vector2f();
        acceleration = new Vector2f();
        force = new Vector2f();
        mass = 0;
        
        doesCollide = collides;
        
        aabb = new AABB();
    }
    
    public void step() {
        // objects with infinite mass don't move
        if(mass == 0) {
            return;
        }
        
        acceleration.x += force.x / mass;
        acceleration.y += force.y / mass;
        
        velocity.x += acceleration.x * Physics.TIMESTEP;
        velocity.y += acceleration.y * Physics.TIMESTEP;
        
        velocity.x *= 0.98; // friction
        velocity.y *= 0.98;
        if(velocity.x <= 3 && velocity.x >= -3 && acceleration.x == 0) {
            velocity.x = 0;
        }
        if(velocity.y <= 3 && velocity.y >= -3 && acceleration.y == 0) {
            velocity.y = 0;
        }
        
        location.x += velocity.x * Physics.TIMESTEP;
        location.y += velocity.y * Physics.TIMESTEP;
        
        aabb.max.x += velocity.x * Physics.TIMESTEP;
        aabb.max.y += velocity.y * Physics.TIMESTEP;
        aabb.min.x += velocity.x * Physics.TIMESTEP;
        aabb.min.y += velocity.y * Physics.TIMESTEP;
    }
}
