package manuel.engine.graphics;

import manuel.engine.Application;
import manuel.engine.renderer.Texture;
import manuel.engine.renderer.TextureRegion;

/**
 *
 * @author Merioksan Mikko
 */
public class Sprite implements IRenderable {
    private Texture tex;
    private float width, height;
    private TextureRegion region;
    
    public Sprite(Texture t) {
        this(t, t.getWidth(), t.getHeight(), new TextureRegion(0, 0, 1, 1));
    }
    public Sprite(String t) {
        this(Application.resourceManager.getTexture(t));
    }
    public Sprite(Texture t, float width, float height) {
        this(t, width, height, new TextureRegion(0, 0, 1, 1));
    }
    public Sprite(Texture t, float width, float height, TextureRegion region) {
        this.tex = t;
        this.width = width;
        this.height = height;
        this.region = region;
    }
    
    @Override
    public void update(long deltaMs) {
        
    }
    
    @Override
    public void reset() {
        
    }
    
    @Override
    public Texture getTexture() {
        return tex;
    }
    
    @Override
    public float getWidth() {
        return width;
    }
    @Override
    public float getHeight() {
        return height;
    }
    
    @Override
    public float getU() {
        return region.u;
    }
    @Override
    public float getV() {
        return region.v;
    }
    @Override
    public float getU2() {
        return region.u2;
    }
    @Override
    public float getV2() {
        return region.v2;
    }
}
