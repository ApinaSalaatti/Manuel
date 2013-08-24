package manuel.engine;

/**
 * A class that should be the basis of a game using this framework. Extend this and pass it to the Application class.
 * 
 * @author Merioksan Mikko
 */
public abstract class Game {

    /**
     * Called when the application is first created. Put initialization stuff here!
     */
    public abstract void create();
    /**
     * Called on every iteration through the main game loop. Put the logic of your game here!
     * 
     * @param deltaMs the amount of time in milliseconds elapsed since last update;
     */
    public abstract void update(long deltaMs);
    /**
     * Called every time the application is to be rendered.
     */
    public abstract void render();
    /**
     * Returns true if the game is requesting to quit the application.
     */
    public abstract boolean isQuitRequested();
    /**
     * Called when the application is closing. Free all your resources here!
     */
    public abstract void destroy();
    
    /**
     * Called when a keyboard key is pressed. Override in your game if you need to listen to keyboard events.
     * 
     * @param key keycode of the pressed key, see LWJGL documentation of Keyboard for more info.
     * 
     * @return true if we want to consume the event, false otherwise
     */
    public boolean onKeyDown(int key) {
        return false;
    }
    /**
     * Called when a keyboard key is released. Override in your game if you need to listen to keyboard events.
     * 
     * @param key keycode of the released key, see LWJGL documentation of Keyboard for more info.
     * 
     * @return true if we want to consume the event, false otherwise
     */
    public boolean onKeyUp(int key) {
        return false;
    }
    /**
     * Called when a mouse button is clicked. Override in your game if you need to listen to mouse events.
     * 
     * @param mX the x-coordinate of the mouse event
     * @param mY the y-coordinate of the mouse event
     * @param button code of the clicked button, see LWJGL documentation of Mouse for more info.
     * 
     * @return true if we want to consume the event, false otherwise
     */
    public boolean onMouseDown(int mX, int mY, int button) {
        return false;
    }
    /**
     * Called when a mouse button is released. Override in your game if you need to listen to mouse events.
     * 
     * @param mX the x-coordinate of the mouse event
     * @param mY the y-coordinate of the mouse event
     * @param button code of the released button, see LWJGL documentation of Mouse for more info.
     * 
     * @return true if we want to consume the event, false otherwise
     */
    public boolean onMouseUp(int mX, int mY, int button) {
        return false;
    }
    /**
     * Called when the mouse pointer moves. Override in your game if you need to listen to mouse events.
     * @param mX x-coordinate the mouse moved to
     * @param mY y-coordinate the mouse moved to
     * @param mDX the amount of movement on the x-axis since this event was last triggered
     * @param mDY the amount of movement on the x-axis since this event was last triggered
     * 
     * @return true if we want to consume the event, false otherwise
     */
    public boolean onPointerMove(int mX, int mY, int mDX, int mDY) {
        return false;
    }
}
