package manuel.engine.gui;

import java.util.ArrayList;
import java.util.Iterator;
import manuel.engine.actions.ActionSequence;

/**
 * A screen is a view shown to the player containing different gui items. Examples of screens are the main menu and the in-game gui.
 * 
 * @author Merioksan Mikko
 */
public abstract class Screen {
    /**
     * List of all the items this screen contains.
     */
    protected ArrayList<ScreenItem> items;
    
    /**
     * Actions that are taken during this screen.
     */
    protected ActionSequence actions;
    
    public Screen() {
        items = new ArrayList<>();
        actions = new ActionSequence();
    }
    
    public abstract void create();
    public abstract void destroy();
    
    public void addScreenItem(ScreenItem item) {
        items.add(item);
    }
    
    public void hideAll() {
        for(ScreenItem i : items) {
            i.hide();
        }
    }
    public void showAll() {
        for(ScreenItem i : items) {
            i.show();
        }
    }
    
    public void update(long deltaMs) {
        actions.update(deltaMs);
        
        Iterator<ScreenItem> it = items.iterator();
        while(it.hasNext()) {
            ScreenItem item = it.next();
            item.update(deltaMs);
        }
    }
    
    public void render() {
        Iterator<ScreenItem> it = items.iterator();
        while(it.hasNext()) {
            ScreenItem item = it.next();
            item.render();
        }
    }
    
    public boolean onKeyDown(int key) {
        Iterator<ScreenItem> it = items.iterator();
        while(it.hasNext()) {
            ScreenItem item = it.next();
            if(item.onKeyDown(key)) {
                return true;
            }
        }
        
        return false;
    }
    public boolean onKeyUp(int key) {
        Iterator<ScreenItem> it = items.iterator();
        while(it.hasNext()) {
            ScreenItem item = it.next();
            if(item.onKeyUp(key)) {
                return true;
            }
        }
        
        return false;
    }
    public boolean onMouseDown(int mX, int mY, int button) {
        Iterator<ScreenItem> it = items.iterator();
        while(it.hasNext()) {
            ScreenItem item = it.next();
            if(item.onMouseDown(mX, mY, button)) {
                return true;
            }
        }
        
        return false;
    }
    public boolean onMouseUp(int mX, int mY, int button) {
        Iterator<ScreenItem> it = items.iterator();
        while(it.hasNext()) {
            ScreenItem item = it.next();
            if(item.onMouseUp(mX, mY, button)) {
                return true;
            }
        }
        
        return false;
    }
    public boolean onPointerMove(int mX, int mY, int mDX, int mDY) {
        Iterator<ScreenItem> it = items.iterator();
        while(it.hasNext()) {
            ScreenItem item = it.next();
            if(item.onPointerMove(mX, mY, mDX, mDY)) {
                return true;
            }
        }
        
        return false;
    }
}
