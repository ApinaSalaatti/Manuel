package manuel.engine.physics.myPhysics;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Merioksan Mikko
 */
public class Manifold {
    public PhysicsObject obj1, obj2;
    public float penetration;
    public Vector2f normal;
    
    public Manifold(PhysicsObject o1, PhysicsObject o2) {
        obj1 = o1;
        obj2 = o2;
    }
    
    public void resolve() {
        if(!obj1.doesCollide || !obj2.doesCollide) {
            return;
        }
        
        float velAlongNormal = velAlongNormal();
        
        if(velAlongNormal <= 0) {
            float restitution = 0.5f;
            float impulse = -(1 + restitution) * velAlongNormal;
            
            float invMass1, invMass2;
            if(obj1.mass == 0) {
                invMass1 = 0;
            }
            else {
                invMass1 = 1.0f / obj1.mass;
            }
            if(obj2.mass == 0) {
                invMass2 = 0;
            }
            else {
                invMass2 = 1.0f / obj2.mass;
            }
            
            impulse /= invMass1 + invMass2;
            Vector2f imp = new Vector2f(normal.x, normal.y);
            imp.x *= impulse;
            imp.y *= impulse;
            
            obj1.velocity.x -= invMass1 * imp.x;
            obj1.velocity.y -= invMass1 * imp.y;
            
            obj2.velocity.x += invMass2 * imp.x;
            obj2.velocity.y += invMass2 * imp.y;
        }
    }
    
    public float velAlongNormal() {
        Vector2f relVel = new Vector2f();
        relVel.x = obj2.velocity.x - obj1.velocity.x;
        relVel.y = obj2.velocity.y - obj1.velocity.y;
        
        return Vector2f.dot(relVel, normal);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)) {
            return true;
        }
        if(obj instanceof Manifold) {
            Manifold col = (Manifold)obj;
            
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
