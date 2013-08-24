package manuel.engine.renderer;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Merioksan Mikko
 */
public class Texture {
    public int target;
    public int id;
    private int width;
    private int height;

    public Texture(int target, int id) {
        this.target = target;
        this.id = id;
    }
    
    public Texture() {
        
    }
    
    public boolean valid() {
        return id != 0;
    }
    
    public void create(int target, int id) {
        this.target = target;
        this.id = id;
    }
    public void dispose() {
        if(valid()) {
            GL11.glDeleteTextures(id);
            id = 0;
        }
    }

    public void bind() {
        GL11.glBindTexture(target, id);
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    public int getWidth() {
        return width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }
    
    public int getID() {
        return id;
    }
    public int getTarget() {
        return target;
    }
    
    public float getU() {
        return 0f;
    }
    public float getV() {
        return 0f;
    }
    public float getU2() {
        return 1f;
    }
    public float getV2() {
        return 1f;
    }
}
