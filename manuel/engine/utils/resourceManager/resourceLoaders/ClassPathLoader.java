package manuel.engine.utils.resourceManager.resourceLoaders;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import manuel.engine.audioPlayer.SoundFile;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Merioksan Mikko
 */
public class ClassPathLoader implements IResourceLoader {
    /** 
     * Load a given resource as a buffered image
     * 
     * @param ref The location of the resource to load
     * @return The loaded buffered image
     * @throws IOException Indicates a failure to find a resource
     */
    @Override
    public BufferedImage loadImage(String ref) throws IOException { 
        BufferedImage bufferedImage =  ImageIO.read(new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(ref))); 

        return bufferedImage;
    }
    
    @Override
    public NodeList loadData(String resource) {
        File xmlFile = new File(resource);
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.getClass().getClassLoader().getResourceAsStream(resource));
            
            Node root = doc.getDocumentElement();
            return root.getChildNodes();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public SoundFile loadSound(String res, String type) {
        IntBuffer b = BufferUtils.createIntBuffer(1);
        AL10.alGenBuffers(b);
        
        SoundFile sf = new SoundFile();
        
        WaveData waveFile = WaveData.create(this.getClass().getClassLoader().getResource(res));
            
        AL10.alBufferData(b.get(0), waveFile.format, waveFile.data, waveFile.samplerate);
        waveFile.dispose();
        
        return sf;
    }
    
    /**
     * Returns a file's content in a BufferedReader. Remember to close the reader when not needed anymore!
     * @param ref
     * @return 
     */
    @Override
    public InputStream loadFileForReading(String ref) {
        
        return null;
    }
    
    @Override
    public OutputStream loadFileForWriting(String ref) {
        return null;
    }
}
