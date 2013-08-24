package manuel.engine.entities.components;

import org.lwjgl.util.vector.Vector2f;

/**
 * Component that stores the location of the actor.
 * 
 * @author Merioksan Mikko
 */
public class LocationComponent extends BaseComponent {
    public static final String type = "location";
    
    private Vector2f location;
    private Vector2f orientation;
    
    public LocationComponent(long owner) {
        this(owner, 0, 0);
    }
    public LocationComponent(long owner, float x, float y) {
        super(owner);
        location = new Vector2f(x, y);
        orientation = new Vector2f();
    }
    
    public Vector2f getLocation() {
        return location;
    }
    public void setLocation(Vector2f newLoc) {
        location.x = newLoc.x;
        location.y = newLoc.y;
    }
    
    public Vector2f getOrientation() {
        return orientation;
    }
    public void setOrientation(Vector2f newOri) {
        orientation.x = newOri.x;
        orientation.y = newOri.y;
        if(orientation.lengthSquared() != 0) {
            orientation.normalise();
        }
    }
    
    @Override
    public String getType() {
        return type;
    }
}
