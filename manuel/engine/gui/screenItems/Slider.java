package manuel.engine.gui.screenItems;

import manuel.engine.Application;
import manuel.engine.graphics.Sprite;
import manuel.engine.gui.ScreenItem;
import manuel.engine.renderer.Renderer;
import manuel.engine.renderer.Texture;
import manuel.engine.utils.resourceManager.ResourceManager;

/**
 *
 * @author Merioksan Mikko
 */
public class Slider extends ScreenItem {
    private Texture slide;
    
    private float min, max;
    private float current;
    
    private float minX, maxX;
    
    private boolean beingClicked;
    
    public Slider(int x, int y, float min, float max) {
        super(new Sprite(Application.resourceManager.getTexture("assets/graphics/ui/slider.png")), x, y, 200, 50);
        slide = Application.resourceManager.getTexture("assets/graphics/ui/slide.png");
        
        this.min = min;
        this.max = max;
        current = 0.5f;
        beingClicked = false;
        
        minX = x - width / 2 + 10;
        maxX = x + width / 2 - 10;
    }
    
    public float getValue() {
        return min + (max - min) * current;
    }
    
    @Override
    public void update(long deltaMs) {
        super.update(deltaMs);
    }
    
    @Override
    public boolean onMouseDown(int mX, int mY, int button) {
        super.onMouseDown(mX, mY, button);
        
        if(button == 0) {
            if(mX < x + width / 2 && x > x - width / 2 && mY < y + height / 2 && mY > y - height / 2) {
                beingClicked = true;
                float x = mX;
                
                if(x < minX) {
                    x = minX;
                }
                else if(x > maxX) {
                    x = maxX;
                }
                
                float length = maxX - minX;
                float relX = x - minX;
                
                float prcnt = relX/length;
                setCurrent(prcnt);
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
            float x = mX;
                
            if(x < minX) {
                x = minX;
            }
            else if(x > maxX) {
                x = maxX;
            }

            float length = maxX - minX;
            float relX = x - minX;

            float prcnt = (float)(relX)/(float)(length);
            setCurrent(prcnt);
        }
        
        return false;
    }
    
    private void setCurrent(float prcnt) {
        if(prcnt < 0) {
            prcnt = 0;
        }
        else if(prcnt > 1) {
            prcnt = 1;
        }
        current = prcnt;
    }
    
    @Override
    public void render() {
        super.render();
        float length = maxX - minX;
        Application.renderer.draw(slide, (minX + length * current), y);
    }
}
