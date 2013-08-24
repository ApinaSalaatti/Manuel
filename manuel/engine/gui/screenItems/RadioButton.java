package manuel.engine.gui.screenItems;

/**
 *
 * @author Merioksan Mikko
 */
public class RadioButton {
    public int x, y;
    public int width, height;
    
    private boolean beingClicked;
    
    public String label;
    
    public RadioButton(int x, int y, int w, int h, String l) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.label = l;
        
        beingClicked = false;
    }
    
    public boolean onMouseDown(int mX, int mY, int button) {
        if(mX < x + width / 2 && x > x - width / 2 && mY < y + height / 2 && mY > y - height / 2) {
            beingClicked = true;
            return true;
        }
        return false;
    }
    public boolean onMouseUp(int mX, int mY, int button) {
        boolean r = false;
        
        if(mX < x + width / 2 && x > x - width / 2 && mY < y + height / 2 && mY > y - height / 2) {
            if(beingClicked) {
                r = true;
            }
        }
        
        beingClicked = false;
        
        return r;
    }
}
