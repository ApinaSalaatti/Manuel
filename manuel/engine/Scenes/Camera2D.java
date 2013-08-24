package manuel.engine.Scenes;

import manuel.engine.Application;
import manuel.engine.entities.Entity;
import manuel.engine.entities.components.LocationComponent;
import manuel.engine.utils.MathUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Merioksan Mikko
 */
public class Camera2D {
    private long followedEntityID;
    private Scene scene;
    
    private Vector2f location;
    private float maxZoom = 0.5f;
    private float minZoom = 2.0f;
    private float zoom;
    
    public Camera2D() {
        location = new Vector2f();
        zoom = 1.0f;
    }
    
    public void setOnScene(Scene s) {
        scene = s;
    }
    
    public void followEntity(long id) {
        followedEntityID = id;
    }
    public void stopFollowing() {
        followedEntityID = -1;
    }
    
    public void setLocation(Vector2f newLoc) {
        location.x = newLoc.x;
        location.y = newLoc.y;
    }
    public Vector2f getLocation() {
        return location;
    }
    
    public void setZoom(float z) {
        zoom = z;
        if(zoom > minZoom) {
            zoom = minZoom;
        }
        if(zoom < maxZoom) {
            zoom = maxZoom;
        }
    }
    public float getZoom() {
        return zoom;
    }
    
    private Vector2f invLoc = new Vector2f();
    public void update(long deltaMs) {
        Entity e = Application.baseLogic.getEntity(followedEntityID);
        if(e != null) {
            LocationComponent lc = (LocationComponent)e.getComponent(LocationComponent.type);
            location.x = lc.getLocation().x;
            location.y = lc.getLocation().y;
        }
        
        if(scene != null) {
            if(location.x < scene.minX + (Display.getWidth() / 2)  * zoom) {
                location.x = scene.minX + (Display.getWidth() / 2)  * zoom;
            }
            if(location.x > scene.maxX - (Display.getWidth() / 2) * zoom) {
                location.x = scene.maxX - (Display.getWidth() / 2) * zoom;
            }
            if(location.y < scene.minY + (Display.getHeight() / 2)  * zoom) {
                location.y = scene.minY + (Display.getHeight() / 2)  * zoom;
            }
            if(location.y > scene.maxY - (Display.getHeight() / 2) * zoom) {
                location.y = scene.maxY - (Display.getHeight() / 2) * zoom;
            }
        }

        MathUtil.toOrtho2D(Application.renderer.getSpriteBatch().getProjectionMatrix(), 0, 0, Display.getWidth() * zoom, Display.getHeight() * zoom);

        invLoc = new Vector2f(-location.x + (Display.getWidth() / 2) * zoom, -location.y + (Display.getHeight() / 2) * zoom);
        Application.renderer.getSpriteBatch().getViewMatrix().setIdentity();
        Application.renderer.getSpriteBatch().getViewMatrix().translate(invLoc);
    }
}
