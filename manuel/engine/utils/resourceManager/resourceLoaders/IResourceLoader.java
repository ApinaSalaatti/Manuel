/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manuel.engine.utils.resourceManager.resourceLoaders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import manuel.engine.audioPlayer.SoundFile;
import org.w3c.dom.NodeList;

/**
 *
 * @author ApinaSalaatti
 */
public interface IResourceLoader {
    public BufferedImage loadImage(String ref) throws IOException;
    public NodeList loadData(String ref);
    public SoundFile loadSound(String ref, String type);
    public InputStream loadFileForReading(String ref);
    public OutputStream loadFileForWriting(String ref);
}
