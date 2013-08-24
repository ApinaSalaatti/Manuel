package manuel.engine.Scenes;

import manuel.engine.Application;
import manuel.engine.entities.components.LocationComponent;
import manuel.engine.graphics.IRenderable;
import org.lwjgl.util.vector.Vector2f;

/**
 * A scene actor is a renderable entity. It contains information about what to render and where.
 * 
 * @author Merioksan Mikko
 */
public class SceneActor {
    /**
     * The Entity this actor is associated with
     */
    public long entityID;
    
    protected IRenderable renderable;
    protected Vector2f location;
    
    /**
     * An optional identifier that can be given for the actor, so it can be easily found. No checks are made to ensure the id is unique.
     */
    public String name;
    /**
     * A special identifier that should not be given to an actor.
     */
    public static final String NO_NAME_GIVEN = "unidentified";
    
    public SceneActor(long entity, IRenderable r, Vector2f l) {
        entityID = entity;
        renderable = r;
        location = l;
        name = NO_NAME_GIVEN;
    }
    
    public long getEntity() {
        return entityID;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public void update(long deltaMs) {
        LocationComponent lc = (LocationComponent)Application.baseLogic.getEntity(entityID).getComponent(LocationComponent.type);
        location.x = lc.getLocation().x;
        location.y = lc.getLocation().y;
        renderable.update(deltaMs);
    }
    
    public void render() {
        Application.renderer.draw(renderable.getTexture(), location.x, location.y, renderable.getWidth(), renderable.getHeight(), location.x, location.y, 0, renderable.getU(), renderable.getV(), renderable.getU2(), renderable.getV2());
    }
    
    public void setLocation(Vector2f newLoc) {
        location.x = newLoc.x;
        location.y = newLoc.y;
    }
}
