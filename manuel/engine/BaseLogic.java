package manuel.engine;

import java.util.HashMap;
import manuel.engine.Listeners.EntityCreatedListener;
import manuel.engine.Listeners.EntityDestroyedListener;
import manuel.engine.entities.Entity;
import manuel.engine.entities.EntityFactory;
import manuel.engine.eventManager.events.EntityEvents.EntityCreatedEvent;
import manuel.engine.eventManager.events.EntityEvents.EntityDestroyedEvent;

/**
 *
 * @author Merioksan Mikko
 */
public class BaseLogic implements IGameLogic {
    private EntityFactory entityFactory;
    private HashMap<Long, Entity> entities;
    
    public BaseLogic() {
        entityFactory = new EntityFactory();
        entities = new HashMap<>();
        
        Application.eventManager.register(EntityCreatedEvent.eventType, new EntityCreatedListener(this));
        Application.eventManager.register(EntityDestroyedEvent.eventType, new EntityDestroyedListener(this));
    }
    
    @Override
    public void update(long deltaMs) {
        
    }
    
    /**
     * Creates the data in a given file, but does not add it to the entity list.
     * @param file the xml-file to read the entity data from
     * @return the created entity
     */
    public Entity createEntity(String file) {
        return entityFactory.newEntity(file);
    }
    /**
     * Adds an entity to the Entity list.
     * @param e entity to add
     */
    public void addEntity(Entity e) {
        if(!entities.containsKey(e.getID())) { // no two Entities with the same ID allowed!
            entities.put(e.getID(), e);
        }
    }
    /**
     * Returns the Entity with the given id.
     * @param id id of the Entity to be searched
     * @return the found Entity or null if entity not found
     */
    public Entity getEntity(long id) {
        return entities.get(id);
    }
    /**
     * Removes and returns the Entity with the given id from the Entity list.
     * @param id
     * @return 
     */
    public Entity removeEntity(long id) {
        return entities.remove(id);
    }
}
