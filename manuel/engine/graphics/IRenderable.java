package manuel.engine.graphics;

import manuel.engine.renderer.Texture;

/**
 *
 * @author Merioksan Mikko
 */
public interface IRenderable {
    public void update(long deltaMs);
    public void reset();
    public Texture getTexture();
    public float getWidth();
    public float getHeight();
    public float getU();
    public float getV();
    public float getU2();
    public float getV2();
}
