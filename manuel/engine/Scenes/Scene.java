package manuel.engine.Scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import manuel.engine.Application;
import manuel.engine.Scenes.Listeners.EntityDestroyedListener;
import manuel.engine.Scenes.Listeners.SceneActorCreatedListener;
import manuel.engine.eventManager.events.EntityEvents.EntityDestroyedEvent;
import manuel.engine.eventManager.events.EntityEvents.SceneActorCreatedEvent;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

/**
 * A scene is the renderable part of the game. When a scene is rendered, all the actors currently on it are rendered.
 * 
 * @author Merioksan Mikko
 */
public class Scene {
    private List<SceneActor> actors;
    
    private Camera2D camera;
    
    /**
     * Boundaries of the scene.
     */
    public float minX, minY, maxX, maxY;
    
    public Scene(Camera2D c) {
        this(c, 0, 0, 1000, 1000);
    }
    public Scene(Camera2D c, float minX, float minY, float maxX, float maxY) {
        actors = new ArrayList<>();
        
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        
        camera = c;
        camera.setOnScene(this);
        
        Application.eventManager.register(SceneActorCreatedEvent.eventType, new SceneActorCreatedListener(this));
        Application.eventManager.register(EntityDestroyedEvent.eventType, new EntityDestroyedListener(this));
    }
    
    public void update(long deltaMs) {
        camera.update(deltaMs);
        for(SceneActor actor : actors) {
            actor.update(deltaMs);
        }
    }
    
    public void render() {
        for(SceneActor actor : actors) {
            actor.render();
        }
    }
    
    public Camera2D getCamera() {
        return camera;
    }
    
    /**
     * Removes the scene actor associated with the given entity
     * @param entity
     * @return true if an actor was removed
     */
    public boolean removeActor(long entity) {
        Iterator<SceneActor> it = actors.iterator();
        while(it.hasNext()) {
            SceneActor actor = it.next();
            if(actor.entityID == entity) {
                it.remove();
                return true;
            }
        }
        return false;
    }
    /**
     * Only removes the first actor found with the given name.
     * @param name
     * @return true if an actor was removed
     */
    public boolean removeActor(String name) {
        if(name.equals(SceneActor.NO_NAME_GIVEN)) {
            return false;
        }
        
        Iterator<SceneActor> it = actors.iterator();
        while(it.hasNext()) {
            SceneActor actor = it.next();
            if(actor.name.equals(name)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
    public boolean removeActor(SceneActor actor) {
        return actors.remove(actor);
    }
    public boolean addActor(SceneActor actor) {
        return actors.add(actor);
    }
    public void clear() {
        actors.clear();
    }
    
    private Vector2f screenToWorld = new Vector2f();
    /**
     * Transforms the given vectors screen coordinates into game world coordinates. Meaning: This method will modify the vector passed as a parameter!
     * @param x
     * @param y
     * @return 
     */
    public Vector2f screenToWorldCoordinate(Vector2f screenCoords) {
        screenCoords.x = (screenCoords.x * camera.getZoom()) + camera.getLocation().x - ((Display.getWidth() / 2) * camera.getZoom());
        screenCoords.y = (screenCoords.y * camera.getZoom()) + camera.getLocation().y - ((Display.getHeight() / 2) * camera.getZoom());
        return screenCoords;
    }
    
    /**
     * Returns the first SceneActor found when a "raycast" is made from the given screen coordinates.
     * @param screenX
     * @param screenY
     * @return null if no SceneActor was found
     */
    public SceneActor pickActor(int screenX, int screenY) {
        float x = (screenX * camera.getZoom()) + camera.getLocation().x - ((Display.getWidth() / 2) * camera.getZoom());
        float y = (screenY * camera.getZoom()) + camera.getLocation().y - ((Display.getHeight() / 2) * camera.getZoom());
        for(SceneActor actor : actors) {
            Vector2f loc = actor.location;
            float w = actor.renderable.getWidth();
            float h = actor.renderable.getHeight();
            if(x > loc.x - w / 2 && x < loc.x + w / 2 && y > loc.y - h / 2 && y < loc.y + h / 2) {
                return actor;
            }
        }
        
        return null;
    }
}
