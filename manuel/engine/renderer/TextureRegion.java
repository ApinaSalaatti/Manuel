package manuel.engine.renderer;

/**
 * A helper class for rendering a specific region of a texture.
 * 
 * @author Merioksan Mikko
 */
public class TextureRegion {
    public float u, v, u2, v2;
    
    public TextureRegion(float texWidth, float texHeight, float x, float y, float width, float height) {
        this.u = x / texWidth;
        this.v = y / texHeight;
        this.u2 = (x + width) / texWidth;
        this.v2 = (y + height) / texHeight;
    }
    public TextureRegion(float u, float v, float u2, float v2) {
        this.u = u;
        this.v = v;
        this.u2 = u2;
        this.v2 = v2;
    }
}
