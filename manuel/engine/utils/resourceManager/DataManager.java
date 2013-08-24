package manuel.engine.utils.resourceManager;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Merioksan Mikko
 */
public class DataManager {
    private ResourceManager resourceManager;
    
    private HashMap<String, NodeList> data;
    
    public DataManager(ResourceManager rm) {
        resourceManager = rm;
        
        data = new HashMap<>();
    }
    
    public NodeList getData(String resource) {
        if(!data.containsKey(resource)) {
            loadData(resource);
        }
        
        return data.get(resource);
    }
    
    public void loadData(String resource) {
        NodeList nodes = resourceManager.resourceLoader.loadData(resource);
        data.put(resource, nodes);
    }
    
    public void preload(String root) {
        if(resourceManager.getMode() == ResourceManager.LOAD_FROM_JAR) {
            
        }
        else {
            File file = new File(root);
            File[] files = file.listFiles();
            for(File f : files) {
                if(f.isDirectory()) {
                    preload(f.getPath());
                }
                else if(f.isFile()) {
                    getData(f.getPath());
                }
            }
        }
    }
}
