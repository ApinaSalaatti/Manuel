package manuel.engine.gui.screenItems;

import manuel.engine.Application;
import manuel.engine.graphics.Sprite;
import manuel.engine.gui.ScreenItem;
import manuel.engine.renderer.FrameBuffer;
import manuel.engine.renderer.Renderer;
import manuel.engine.renderer.Texture;
import manuel.engine.utils.resourceManager.ResourceManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Merioksan Mikko
 */
public class ScrollBar extends ScreenItem {
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    private int type;
    
    private Texture slide;
    
    private float minX, minY, maxX, maxY;
    private float slideX, slideY;
    
    private boolean beingClicked;
    
    public ScrollBar(int x, int y, int length, int t) {
        super(new Sprite(Application.resourceManager.getTexture("assets/graphics/ui/textField.png")), x, y);
        
        if(t == VERTICAL) {
            super.setHeight(length);
            super.setWidth(10);
            type = t;
            slideX = minX = x;
            slideY = minY = y - height / 2;
        }
        else {
            super.setHeight(10);
            super.setWidth(length);
            type = HORIZONTAL;
            slideX = minX = x - width / 2;
            slideY = minY = y;
        }
        
        slide = Application.resourceManager.getTexture("assets/graphics/ui/slide.png");
        slide.setHeight(15);
        slide.setWidth(15);
        
        maxX = minX + length;
        maxY = minY + length;
    }
    
    @Override
    public void render() {
        super.render();
        Application.renderer.draw(slide, slideX, slideY);
    }
    
    @Override
    public boolean onMouseDown(int mX, int mY, int button) {
        super.onMouseDown(mX, mY, button);
        
        if(button == 0) {
            if(mX < x + width / 2 && mX > x - width / 2 && mY < y + height / 2 && mY > y - height / 2) {
                beingClicked = true;
                moveSlide(mX, mY);
            }
        }
        
        return false;
    }
    
    @Override
    public boolean onMouseUp(int mX, int mY, int button) {
        beingClicked = false;
        
        return false;
    }
    
    @Override
    public boolean onPointerMove(int mX, int mY, int mDX, int mDY) {
        if(beingClicked) {
            moveSlide(mX, mY);
        }
        
        return false;
    }
    
    public float getXOffset() {
        return slideX - minX;
    }
    public float getYOffset() {
        return slideY - minY;
    }
    
    public void moveSlide(float X, float Y) {
        if(type == VERTICAL) {
            if(Y < minY) {
                Y = minY;
            }
            else if(Y > maxY) {
                Y = maxY;
            }
            slideY = Y;
        }
        else {
            if(X < minX) {
                X = minX;
            }
            else if(X > maxX) {
                X = maxX;
            }
            slideX = X;
        }
    }
}
