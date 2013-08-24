package manuel.engine.physics.myPhysics;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Merioksan Mikko
 */
public class Shape {
    public class Rectangle {
        Vector2f min;
        Vector2f max;
    }
    public class Circle {
        float radius;
        Vector2f location;
    }
}
