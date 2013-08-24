package manuel.engine.entities.components;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class BaseComponent {
    public long ownerID;
    
    public BaseComponent(long owner) {
        ownerID = owner;
    }
    public abstract String getType();
}
