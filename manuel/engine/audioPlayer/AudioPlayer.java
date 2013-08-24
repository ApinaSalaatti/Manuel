package manuel.engine.audioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import manuel.engine.Application;
import manuel.engine.utils.resourceManager.ResourceManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Merioksan Mikko
 */
public class AudioPlayer {
    private static AudioPlayer instance = new AudioPlayer();
    
    private float soundVolume;
    private float musicVolume;
    private IntBuffer currentMusic;
    
    private ArrayList<IntBuffer> sources;
    
    /*
    IntBuffer buffer;
    IntBuffer source;
    
    FloatBuffer sourcePos;
    FloatBuffer sourceVel;
    * 
    */
    
    FloatBuffer listenerPos;
    FloatBuffer listenerVel;
    FloatBuffer listenerOri;
    
    public AudioPlayer() {
        soundVolume = 1.0f;
        musicVolume = 1.0f;
        
        sources = new ArrayList<IntBuffer>();
        
        /*
        buffer = BufferUtils.createIntBuffer(1);
        source = BufferUtils.createIntBuffer(1);

        sourcePos = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
        sourcePos.flip();
        sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
        sourceVel.flip();
        * 
        */
        listenerPos = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
        listenerPos.flip();
        listenerVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
        listenerVel.flip();
        listenerOri = BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f});
        listenerOri.flip();
        
        AL10.alListener(AL10.AL_POSITION, listenerPos);
        AL10.alListener(AL10.AL_VELOCITY, listenerVel);
        AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
    }
    
    public static AudioPlayer get() {
        return instance;
    }
    
    public void moveListener(float x, float y) {
        float relX = x / (Display.getWidth() * 3); // piece of shit way of normalizing the coordinates
        float relY = y / (Display.getHeight() * 3);
        
        listenerPos.put(relX).put(relY).put(0.0f);
        listenerPos.flip();
        
        AL10.alListener(AL10.AL_POSITION, listenerPos);
    }
    
    public IntBuffer addSource() {
        IntBuffer sourceBuf = BufferUtils.createIntBuffer(1);
        AL10.alGenSources(sourceBuf);
        sources.add(sourceBuf);
        return sourceBuf;
    }
    
    private IntBuffer playSound(String sound, float x, float y, float volume, boolean loop) {
        IntBuffer source = addSource();
        
        IntBuffer buf = Application.resourceManager.getSound(sound);
        float X = x / (Display.getWidth() * 3);
        float Y = y / (Display.getHeight() * 3);
        float Z = 0.0f;
        
        FloatBuffer sourcePos = BufferUtils.createFloatBuffer(3).put(X).put(Y).put(Z);
        FloatBuffer sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });

        sourcePos.flip();
        sourceVel.flip();
        
        AL10.alSourcei(source.get(0), AL10.AL_BUFFER, buf.get(0));
        AL10.alSourcef(source.get(0), AL10.AL_PITCH, 1.0f);
        AL10.alSourcef(source.get(0), AL10.AL_GAIN, volume);
        AL10.alSource(source.get(0), AL10.AL_POSITION, sourcePos);
        AL10.alSource(source.get(0), AL10.AL_VELOCITY, sourceVel);
        
        if(loop) {
            AL10.alSourcei(source.get(0), AL10.AL_LOOPING, AL10.AL_TRUE);
        }
        else {
            AL10.alSourcei(source.get(0), AL10.AL_LOOPING, AL10.AL_FALSE);
        }
        
        AL10.alSourcePlay(source.get(0));
        
        return source;
    }
    
    public IntBuffer playSound(String s, boolean loop) {
        return playSound(s, 0, 0, soundVolume, loop);
    }
    
    public void stop(IntBuffer source) {
        if(source != null)
            if(AL10.alIsSource(source.get(0)))
                AL10.alSourceStop(source);
    }
    public void stopMusic() {
        stop(currentMusic);
    }
    
    public void update(long deltaMs) {
        Iterator<IntBuffer> it = sources.iterator();
        while(it.hasNext()) {
            IntBuffer source = it.next();
            if(AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED) {
                AL10.alDeleteSources(source.get(0));
                it.remove();
            }
        }
    }
    
    public void setMusicVolume(float v) {
        musicVolume = v;
        if(currentMusic != null) {
            AL10.alSourcef(currentMusic.get(0), AL10.AL_GAIN, musicVolume);
        }
    }
    public void setSoundVolume(float v) {
        soundVolume = v;
        for(IntBuffer s : sources) {
            if(s != currentMusic) {
                AL10.alSourcef(s.get(0), AL10.AL_GAIN, soundVolume);
            }
        }
    }
}
