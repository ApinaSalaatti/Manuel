package manuel.engine.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Merioksan Mikko
 */
public class EntityFactory {
    private long lastID;
    
    public EntityFactory() {
        lastID = 1;
    }
    
    public Entity newEntity(String file) {
        Entity e = new Entity(lastID++);
        
        return e;
    }
}
