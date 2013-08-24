package manuel.engine.logging;

/**
 *
 * @author Merioksan Mikko
 */
public class Message {
    public int type;
    public long time;
    public String message;
    
    public Message(int t, long tim, String m) {
        type = t;
        time = tim;
        message = m;
    }
}
