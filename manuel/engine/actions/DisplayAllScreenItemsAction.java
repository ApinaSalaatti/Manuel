package manuel.engine.actions;

import manuel.engine.gui.Screen;

/**
 *
 * @author Merioksan Mikko
 */
public class DisplayAllScreenItemsAction extends Action {
    private Screen screen;
    private boolean display;
    private boolean done;
    
    public DisplayAllScreenItemsAction(Screen s, boolean d) {
        screen = s;
        display = d;
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            if(display) {
                screen.showAll();
            }
            else {
                screen.hideAll();
            }
            
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
