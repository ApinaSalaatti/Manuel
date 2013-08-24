package manuel.engine.renderer;

import manuel.engine.graphics.IRenderable;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Merioksan Mikko
 */
public class Renderer {
    private SpriteBatch batch;
    
    private BitmapFont font;
    
    public Renderer() {
        try {
            batch = new SpriteBatch();
            font = new BitmapFont("assets/graphics/characters.png");
            init();
        } catch(Exception e) {
            System.out.println("Renderer creation failed!");
            e.printStackTrace();
        }
    }
    
    private void init() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        // Enable blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // Set clear to opaque white
        GL11.glClearColor(1f, 1f, 1f, 1f);
    }
    
    public BitmapFont getFont() {
        return font;
    }
    
    public void begin() {
        batch.begin();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
    
    public void end() {
        batch.end();
    }
    
    public SpriteBatch getSpriteBatch() {
        return batch;
    }
 
    public void draw(Texture tex, float x, float y) {
        draw(tex, x, y, tex.getWidth(), tex.getHeight());
    }
    
    public void draw(Texture tex, float x, float y, float w, float h) {
        draw(tex, x, y, w, h, 0, 0, 1, 1);
    }
    
    public void draw(Texture tex, float x, float y, float w, float h, float u, float v, float u2, float v2) {
        draw(tex, x, y, w, h, x, y, 0, u, v, u2, v2);
    }
    
    public void draw(Texture tex, float x, float y, float w, float h, float originX, float originY, float angle, float u, float v, float u2, float v2) {
        x = x - w / 2;
        y = y - h / 2;
        batch.draw(tex, x, y, w, h, originX, originY, angle, u, v, u2, v2);
    }
    
    public void draw(IRenderable renderable, float x, float y) {
        x = x - renderable.getWidth() / 2;
        y = y - renderable.getHeight() / 2;
        
        batch.draw(renderable.getTexture(), x, y, renderable.getWidth(), renderable.getHeight(), renderable.getU(), renderable.getV(), renderable.getU2(), renderable.getV2());
    }
    
    public void draw(Texture tex, float[] vertices, int offset) {
        batch.draw(tex, vertices, offset);
    }
    
    public void drawText(String text, float x, float y) {
        drawText(text, x, y, 1.0f);
    }
    public void drawText(String text, float x, float y, float scale) {
        font.renderText(text, x, y, scale);
    }
}
