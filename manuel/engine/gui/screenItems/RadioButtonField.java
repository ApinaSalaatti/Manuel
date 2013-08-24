package manuel.engine.gui.screenItems;

import java.util.ArrayList;
import manuel.engine.Application;
import manuel.engine.gui.ScreenItem;
import manuel.engine.renderer.Renderer;
import manuel.engine.renderer.Texture;
import manuel.engine.utils.resourceManager.ResourceManager;

/**
 * A field of radio buttons, i.e. a selection where only one item can be selected at once.
 * 
 * @author Merioksan Mikko
 */
public class RadioButtonField extends ScreenItem {
    private ArrayList<RadioButton> buttons;
    
    private Texture button;
    
    private int selected;
    
    public RadioButtonField(int x, int y) {
        super(null, x, y, 0, 0);
        buttons = new ArrayList<RadioButton>();
        
        button = Application.resourceManager.getTexture("assets/graphics/ui/radiobutton.png");
    }
    
    public void addButton(String label) {
        buttons.add(new RadioButton(x, y+buttons.size()*20, 15, 15, label));
    }
    
    public String getSelected() {
        return buttons.get(selected).label;
    }
    
    @Override
    public boolean onMouseDown(int mX, int mY, int button) {
        if(button == 0) {
            for(int i = 0; i < buttons.size(); i++) {
                if(buttons.get(i).onMouseDown(mX, mY, button)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    public boolean onMouseUp(int mX, int mY, int button) {
        if(button == 0) {
            for(int i = 0; i < buttons.size(); i++) {
                if(buttons.get(i).onMouseUp(mX, mY, button)) {
                    selected = i;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    public void render() {
        super.render();
        for(int i = 0; i < buttons.size(); i++) {
            if(i == selected) {
                Application.renderer.draw(button, buttons.get(i).x, buttons.get(i).y, buttons.get(i).width, buttons.get(i).height, 0.5f, 0, 1, 1);
            }
            else {
                Application.renderer.draw(button, buttons.get(i).x, buttons.get(i).y, buttons.get(i).width, buttons.get(i).height, 0, 0, 0.5f, 1);
            }
            
            Application.renderer.drawText(buttons.get(i).label, buttons.get(i).x+20-8, buttons.get(i).y-8, 0.5f);
        }
    }
}
