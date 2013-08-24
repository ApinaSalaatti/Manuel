package manuel.engine;

/**
 *  A wrapper for all sorts of Application-level options, such as title, fullscreen mode, resolution and so on.
 * An instance of AppOptions must be passed to the application when created.
 * 
 * @author Merioksan Mikko
 */
public class AppOptions {
    public int windowWidth = 800;
    public int windowHeight = 640;
    public boolean fullscreen = false;
    public boolean vsync = true;
    public String title = "My Game!";
}
