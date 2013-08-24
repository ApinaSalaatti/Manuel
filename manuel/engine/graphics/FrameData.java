package manuel.engine.graphics;

import manuel.engine.renderer.TextureRegion;

/**
 *
 * @author Merioksan Mikko
 */
public class FrameData {
    /**
     * How big this frame is to be rendered
     */
    public float width, height;
    /**
     * The reqion of the current texture to render (the texture must be correctly set, of course).
     */
    public TextureRegion region;
    
    public FrameData(float w, float h, TextureRegion region) {
        this.width = w;
        this.height = h;
        this.region = region;
    }
}
