package manuel.engine.entities;

import java.util.ArrayList;
import java.util.List;
import manuel.engine.entities.components.BaseComponent;

/**
 *
 * @author Merioksan Mikko
 */
public class Entity {
    /**
     * The ID of this entity. These should be unique application-wide.
     */
    private long id;
    
    private List<BaseComponent> components;
    
    public Entity(long id) {
        this.id = id;
        this.components = new ArrayList<>();
    }
    
    public long getID() {
        return id;
    }
    
    public void addComponent(BaseComponent comp) {
        components.add(comp);
    }
    public BaseComponent getComponent(String type) {
        for(BaseComponent comp : components) {
            if(comp.getType().equals(type)) {
                return comp;
            }
        }
        
        return null;
    }
}
