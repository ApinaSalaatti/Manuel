package manuel.engine.physics.myPhysics;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Merioksan Mikko
 */
public class AABB {
    public Vector2f min, max;
    
    public AABB() {
        min = new Vector2f();
        max = new Vector2f();
    }
}
