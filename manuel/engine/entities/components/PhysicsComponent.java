package manuel.engine.entities.components;

/**
 *
 * @author Merioksan Mikko
 */
public class PhysicsComponent extends BaseComponent {
    public static final String type = "physics";
    
    private float mass;
    private float width, height;
    
    /**
     * Boolean flag that tells if this entity is supposed to collide (i.e. is solid) with other entities.
     */
    private boolean solid;
    
    public PhysicsComponent(long owner, float w, float h, float m) {
        this(owner, w, h, m, true);
    }
    public PhysicsComponent(long owner, float w, float h, float m, boolean collides) {
        super(owner);
        
        width = w;
        height = h;
        mass = m;
        
        solid = collides;
    }
    
    public float getMass() {
        return mass;
    }
    
    public float getWidth() {
        return width;
    }
    
    public float getHeight() {
        return height;
    }
    
    public boolean isSolid() {
        return solid;
    }
    
    @Override
    public String getType() {
        return type;
    }
}
