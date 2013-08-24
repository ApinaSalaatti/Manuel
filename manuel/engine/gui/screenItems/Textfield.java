package manuel.engine.gui.screenItems;

import manuel.engine.Application;
import manuel.engine.graphics.Sprite;
import manuel.engine.gui.ScreenItem;
import manuel.engine.renderer.Renderer;
import manuel.engine.utils.resourceManager.ResourceManager;
import org.lwjgl.input.Keyboard;

/**
 * A basic textfield implementation. At this point can only hold as many characters as can be rendered.
 * 
 * @author Merioksan Mikko
 */
public class Textfield extends ScreenItem {
    private static final char INVALID_CHAR = (char)-1;
    
    private String input;
    private int cols;
    
    private char beingInputted;
    private boolean backspacing;
    private long inputSpeed, backspaced, inputted;
    
    public Textfield(int x, int y) {
        this(x, y, 10);
    }
    public Textfield(int x, int y, int cols) {
        super(
            new Sprite(Application.resourceManager.getTexture("assets/graphics/ui/textfield.png")),
            x, y, cols * 16, 32);
        this.cols = cols;
        input = "";
        backspacing = false;
        inputSpeed = 200;
        backspaced = inputted = 0;
        beingInputted = INVALID_CHAR;
    }
    
    public String getInput() {
        return input;
    }
    public void clear() {
        input = "";
    }
    
    @Override
    public void update(long deltaMs) {
        if(hasFocus) {
            if(backspacing) {
                backspaced += deltaMs;
                if(backspaced >= inputSpeed) {
                    if(input.length() > 0) {
                        input = input.substring(0, input.length()-1);
                    }
                    backspaced = 0;
                }
            }
            else if(beingInputted != INVALID_CHAR) {
                inputted += deltaMs;
                if(inputted >= inputSpeed) {
                    addChar(beingInputted);
                    inputted = 0;
                }
            }
        }
    }
    
    private void addChar(char c) {
        if(input.length() < cols) {
            input += c;
        }
    }
    
    @Override
    public void render() {
        super.render();
        Application.renderer.drawText(input, x-width/2, y-height/2);
    }
    
    @Override
    public boolean onKeyDown(int key) {
        if(hasFocus) {
            char c = getChar(key);
            if(key == Keyboard.KEY_BACK && input.length() > 0) {
                if(input.length() > 0) {
                    input = input.substring(0, input.length()-1);
                }
                backspacing = true;
                return true;
            }
            else if(c != INVALID_CHAR && input.length() < cols) {
                addChar(c);
                beingInputted = c;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean onKeyUp(int key) {
        if(hasFocus) {
            if(key == Keyboard.KEY_BACK) {
                backspacing = false;
                backspaced = 0;
                return true;
            }
            else if(getChar(key) == beingInputted) {
                beingInputted = INVALID_CHAR;
                inputted = 0;
            }
        }
        
        return false;
    }
    
    private char getChar(int key) {
        switch(key) {
            case Keyboard.KEY_A: return 'a';
            case Keyboard.KEY_B: return 'b';
            case Keyboard.KEY_C: return 'c';
            case Keyboard.KEY_D: return 'd';
            case Keyboard.KEY_E: return 'e';    
            case Keyboard.KEY_F: return 'f';    
            case Keyboard.KEY_G: return 'g'; 
            case Keyboard.KEY_H: return 'h';
            case Keyboard.KEY_I: return 'i';
            case Keyboard.KEY_J: return 'j';
            case Keyboard.KEY_K: return 'k';
            case Keyboard.KEY_L: return 'l';
            case Keyboard.KEY_M: return 'm';
            case Keyboard.KEY_N: return 'n';
            case Keyboard.KEY_O: return 'o';
            case Keyboard.KEY_P: return 'p';
            case Keyboard.KEY_Q: return 'q';
            case Keyboard.KEY_R: return 'r';
            case Keyboard.KEY_S: return 's';
            case Keyboard.KEY_T: return 't';
            case Keyboard.KEY_U: return 'u';
            case Keyboard.KEY_V: return 'v';
            case Keyboard.KEY_W: return 'w';
            case Keyboard.KEY_X: return 'x';
            case Keyboard.KEY_Y: return 'y';
            case Keyboard.KEY_Z: return 'z';
            case 27: return 'å';
            case 40: return 'ä';
            case 41: return 'ö';
            case Keyboard.KEY_1: return '1';
            case Keyboard.KEY_2: return '2';
            case Keyboard.KEY_3: return '3';
            case Keyboard.KEY_4: return '4';
            case Keyboard.KEY_5: return '5';
            case Keyboard.KEY_6: return '6';
            case Keyboard.KEY_7: return '7';
            case Keyboard.KEY_8: return '8';
            case Keyboard.KEY_9: return '9';
            case Keyboard.KEY_0: return '0';
            case Keyboard.KEY_SPACE: return ' ';
            case Keyboard.KEY_COMMA: return ',';
            case Keyboard.KEY_PERIOD: return '.';
            case 12: return '-';
        }
        return INVALID_CHAR;
    }
}
