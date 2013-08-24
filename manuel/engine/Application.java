package manuel.engine;

import manuel.engine.eventManager.EventManager;
import manuel.engine.processManager.ProcessManager;
import manuel.engine.renderer.Renderer;
import manuel.engine.utils.resourceManager.ResourceManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Merioksan Mikko
 */
public class Application {
    private long lifetime;
    
    private Game game;
    
    public static ResourceManager resourceManager;
    public static EventManager eventManager;
    public static ProcessManager processManager;
    public static BaseLogic baseLogic;
    public static Renderer renderer;
    
    public Application() {
        
    }
    
    public void create(Game g, AppOptions options) {
        try {
            // initiate the window
            if(options.fullscreen) {
                Display.setFullscreen(options.fullscreen);
            }
            else {
                Display.setDisplayMode(new DisplayMode(options.windowWidth, options.windowHeight));
            }
            Display.setTitle(options.title);
            Display.setResizable(true); //whether our window is resizable
            Display.setVSyncEnabled(options.vsync); //whether hardware VSync is enabled
            //Mouse.setGrabbed(true);

            //create and show our display
            Display.create();

            resize();
        } catch(LWJGLException e) {
            System.exit(1);
        }
        
        resourceManager = new ResourceManager();
        eventManager = new EventManager();
        processManager = new ProcessManager();
        baseLogic = new BaseLogic();
        renderer = new Renderer();
        
        game = g;
        game.create();
    }
    
    public void run() {
        lifetime = 0;
        
        long timeWas, timeIs = (Sys.getTime() * 1000) / Sys.getTimerResolution();
        long deltaMs;
        while(!game.isQuitRequested() && !Display.isCloseRequested()) {
            handleInput();
            
            timeWas = timeIs;
            timeIs = (Sys.getTime() * 1000) / Sys.getTimerResolution();
            deltaMs = timeIs - timeWas;

            lifetime += deltaMs;
            
            processManager.update(deltaMs);
            eventManager.update(deltaMs);
            baseLogic.update(deltaMs);
            game.update(deltaMs);
            
            renderer.begin();
            game.render();
            renderer.end();
            
            // If the window was resized, we need to update our projection
            if (Display.wasResized()) {
                resize();
            }
            
            Display.update();
            Display.sync(60);
        }
        
        destroy();
    }
    
    public boolean handleInput() {
        while(Keyboard.next()) {
	    if(Keyboard.getEventKeyState()) {
                game.onKeyDown(Keyboard.getEventKey());
	    } else {
	        game.onKeyUp(Keyboard.getEventKey());
	    }
	}
        float mDX = Mouse.getDX();
        float mDY = -Mouse.getDY();
        float mouseX = Mouse.getX();
        float mouseY = Display.getHeight() - Mouse.getY();
        
        while(Mouse.next()) {
            int button = Mouse.getEventButton();
            int mX = Mouse.getEventX();
            int mY = Display.getHeight() - Mouse.getEventY(); // invert the y-axis to match all our coordinates
            if(button != -1) { // check that a state of a mouse button has changed
                if(Mouse.getEventButtonState()) {
                    game.onMouseDown((int)mX, (int)mY, button);
                }
                else {
                    game.onMouseUp((int)mX, (int)mY, button);
                }
            }
        }
        
        if(mDX != 0 || mDY != 0) {
            game.onPointerMove((int)mouseX, (int)mouseY, (int)mDX, (int)mDY);
        }
        
        return false;
    }
    
    /**
     * Resize the window.
     */
    public void resize() {
        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        if(renderer != null) {
            renderer.getSpriteBatch().resize(Display.getWidth(), Display.getHeight());
        }
    }
    
    public void destroy() {
        game.destroy();
    }
}
