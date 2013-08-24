package manuel.engine.actions;

import manuel.engine.gui.ScreenItem;

/**
 *
 * @author Merioksan Mikko
 */
public class DisplayScreenItemAction extends Action {
    private ScreenItem item;
    private boolean display;
    private boolean done;
    
    public DisplayScreenItemAction(ScreenItem i, boolean d) {
        item = i;
        display = d;
        
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            if(display) {
                item.show();
            }
            else {
                item.hide();
            }
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
