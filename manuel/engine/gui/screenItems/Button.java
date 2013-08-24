package manuel.engine.gui.screenItems;

import manuel.engine.graphics.IRenderable;
import manuel.engine.gui.ScreenItem;
import manuel.engine.gui.screenItems.listeners.ButtonListener;

/**
 * A basic button implementation. To use the basic button behaviour, just add a custon ButtonListener.
 * 
 * @author Merioksan Mikko
 */
public class Button extends ScreenItem {
    private ButtonListener listener;
    private boolean beingClicked;
    
    public Button(IRenderable spr, int x, int y, int w, int h) {
        super(spr, x, y, w, h);
        
        beingClicked = false;
    }
    
    public void addButtonListener(ButtonListener bl) {
        listener = bl;
    }
    
    @Override
    public boolean onMouseDown(int mX, int mY, int button) {
        super.onMouseDown(mX, mY, button);
        
        if(button == 0 && mX < x + width / 2 && mX > x - width / 2 && mY < y + height / 2 && mY > y - height / 2) {
            beingClicked = true;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean onMouseUp(int mX, int mY, int button) {
        super.onMouseUp(mX, mY, button);
        
        boolean r = false;
        
        if(button == 0 && mX < x + width / 2 && mX > x - width / 2 && mY < y + height / 2 && mY > y - height / 2) {
            if(beingClicked && listener != null) {
                listener.onClick();
            }
            
            r = true;
        }
        
        beingClicked = false;
        return r;
    }
}
