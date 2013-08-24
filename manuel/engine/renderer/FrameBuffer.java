package manuel.engine.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

/**
 *
 * @author Merioksan Mikko
 */
public class FrameBuffer extends Texture {
    	public static boolean isSupported() {
    return GLContext.getCapabilities().GL_EXT_framebuffer_object;
    }

    /** The ID of the FBO in use */
    protected int id;
    protected Texture texture;
    protected boolean ownsTexture;

    FrameBuffer(Texture texture, boolean ownsTexture) throws LWJGLException {
        this.texture = texture;
        this.ownsTexture = ownsTexture;
        if (!isSupported()) {
            throw new LWJGLException("FBO extension not supported in hardware");
        }
        
        texture.bind();
        
        id = EXTFramebufferObject.glGenFramebuffersEXT();
        EXTFramebufferObject.glBindFramebufferEXT(GL30.GL_FRAMEBUFFER, id);
        EXTFramebufferObject.glFramebufferTexture2DEXT(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, texture.getTarget(), texture.getID(), 0);
        int result = EXTFramebufferObject.glCheckFramebufferStatusEXT(GL30.GL_FRAMEBUFFER);
        if (result != GL30.GL_FRAMEBUFFER_COMPLETE) {
            EXTFramebufferObject.glBindFramebufferEXT(GL30.GL_FRAMEBUFFER, 0);
            GL30.glDeleteFramebuffers(id);
            throw new LWJGLException("exception "+result+" when checking FBO status");
        }
        EXTFramebufferObject.glBindFramebufferEXT(GL30.GL_FRAMEBUFFER, 0);
    }

    /**
    * Advanced constructor which creates a frame buffer from a texture; the framebuffer
    * does not "own" the texture and thus calling dispose() on this framebuffer will not
    * destroy the texture.
    *
    * @param texture the texture to use
    * @throws LWJGLException if the framebuffer was not initialized correctly
    */
    public FrameBuffer(Texture texture) throws LWJGLException {
        this(texture, false);
    }

    /**
    *
    * @param width
    * @param height
    * @param filter
    * @param wrap
    * @throws LWJGLException
    */
    public FrameBuffer(int width, int height) throws LWJGLException {
        this(new Texture(width, height), true);
    }
    
    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getWidth() {
        return texture.getWidth();
    }

    @Override
    public int getHeight() {
        return texture.getHeight();
    }

    public Texture getTexture() {
        return texture;
    }

    /**
    * Binds the FBO and sets glViewport to the texture region width/height.
    */
    public void begin() {
        if (id == 0) {
            throw new IllegalStateException("can't use FBO as it has been destroyed..");
        }
        GL11.glViewport(0, 0, getWidth(), getHeight());
        EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, id);
        //glReadBuffer(GL_COLOR_ATTACHMENT0);
    }

    /**
    * Unbinds the FBO and resets glViewport to the display size.
    */
    public void end() {
        if (id==0)
            return;
        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, 0);
    }

    /**
    * Disposes this FBO without destroying the texture.
    */
    @Override
    public void dispose() {
        if (id==0)
        return;
        EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, 0);
        EXTFramebufferObject.glDeleteFramebuffersEXT(id);
        if (ownsTexture)
        texture.dispose();
        id = 0;
        //glReadBuffer(GL_BACK);
    }

    @Override
    public float getU() {
        return 0;
    }

    @Override
    public float getV() {
        return 1f;
    }

    @Override
    public float getU2() {
        return 1f;
    }

    @Override
    public float getV2() {
        return 0;
    }
}
