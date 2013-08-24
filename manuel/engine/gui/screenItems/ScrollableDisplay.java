package manuel.engine.gui.screenItems;

import manuel.engine.gui.ScreenItem;
import manuel.engine.renderer.Renderer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Merioksan Mikko
 */
public class ScrollableDisplay extends ScreenItem {
    private ScrollBar bar;
    private ScreenItem item;
    
    public ScrollableDisplay(int x, int y, int w, int h, ScreenItem i) {
        super(null, x, y, w, h);
        
        item = i;
        bar = new ScrollBar(x+width/2, y, height, ScrollBar.VERTICAL);
        
        item.setX(x);
        item.setY(y);
    }
    
    @Override
    public void render() {
        item.render();

        bar.render();
    }
    
    @Override
    public void update(long deltaMs) {
        item.setY((int)(y - bar.getYOffset()));
    }
    
    @Override
    public boolean onMouseDown(int mX, int mY, int button) {
        super.onMouseDown(mX, mY, button);
        if(bar.onMouseDown(mX, mY, button)) {
            return true;
        }
        else if(item.onMouseDown(mX, mY, button)) {
            return true;
        }
        
        return false;
    }
    @Override
    public boolean onMouseUp(int mX, int mY, int button) {
        super.onMouseUp(mX, mY, button);
        if(item.onMouseUp(mX, mY, button)) {
            return true;
        }
        else if(bar.onMouseUp(mX, mY, button)) {
            return true;
        }
        
        return false;
    }
    @Override
    public boolean onPointerMove(int mX, int mY, int mDX, int mDY) {
        super.onPointerMove(mX, mY, mDX, mDY);
        if(item.onPointerMove(mX, mY, mDX, mDY)) {
            return true;
        }
        else if(bar.onPointerMove(mX, mY, mDX, mDY)) {
            return true;
        }
        
        return false;
    }
}
