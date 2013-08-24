package manuel.engine.utils.resourceManager;

import java.nio.IntBuffer;
import manuel.engine.renderer.Texture;
import manuel.engine.utils.resourceManager.resourceLoaders.FileLoader;
import manuel.engine.utils.resourceManager.resourceLoaders.IResourceLoader;
import org.w3c.dom.NodeList;

/**
 *
 * @author Merioksan Mikko
 */
public class ResourceManager {
    public static final int LOAD_FROM_DISC = 1;
    public static final int LOAD_FROM_JAR = 2;
    private int mode;
    
    public IResourceLoader resourceLoader;
    
    private TextureManager texManager;
    private AudioManager audioManager;
    private DataManager dataManager;
    
    public ResourceManager() {
        texManager = new TextureManager(this);
        audioManager = new AudioManager(this);
        dataManager = new DataManager(this);
        
        resourceLoader = new FileLoader();
        
        mode = LOAD_FROM_DISC;
    }
    
    public TextureManager getTextureManager() {
        return texManager;
    }
    public Texture getTexture(String tex) {
        return texManager.getTexture(tex);
    }
    
    public AudioManager getAudioManager() {
        return audioManager;
    }
    public IntBuffer getSound(String sound) {
        return audioManager.getSound(sound);
    }
    
    public DataManager getDataManager() {
        return dataManager;
    }
    public NodeList getData(String resource) {
        return dataManager.getData(resource);
    }
    
    public int getMode() {
        return mode;
    }
    
    public void destroy() {
        audioManager.killALData();
    }
    
    public void preloadResources() {
        /*
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Renderer.get().begin();
        Renderer.get().drawText("LOADING STUFF...", 200, 300);
        Renderer.get().drawText("GRAPHICS", 200, 350);
        Renderer.get().end();
        Display.update();
        texManager.preload("assets/graphics/");
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Renderer.get().begin();
        Renderer.get().drawText("LOADING STUFF...", 200, 300);
        Renderer.get().drawText("AUDIO", 200, 350);
        Renderer.get().end();
        Display.update();
        audioManager.preload("assets/audio/");
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Renderer.get().begin();
        Renderer.get().drawText("LOADING STUFF...", 200, 300);
        Renderer.get().drawText("DATA", 200, 350);
        Renderer.get().end();
        Display.update();
        dataManager.preload("assets/data/");
        * 
        */
    }
}
