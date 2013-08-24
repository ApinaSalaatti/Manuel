package manuel.engine.logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Merioksan Mikko
 */
public class Logger {
    public static int INFO = 1;
    public static int WARNING = 2;
    public static int ERROR = 3;
    
    private static boolean fileSet;
    private static String file;
    
    private static List<Message> logs = new ArrayList<>();
    
    public Logger() {

    }
    
    public static int amountOfLoggedMessages() {
        return logs.size();
    }
    
    public static Message getLoggedMessage(int indx) {
        if(indx < 0 || indx >= logs.size()) {
            return new Message(WARNING, System.currentTimeMillis(), "No such log index!");
        }
        
        return logs.get(indx);
    }
    
    public static void log(int level, String message) {
        logs.add(new Message(level, System.currentTimeMillis(), message));
        Collections.sort(logs, comp);
    }
    
    public static void setFile(String f) {
        file = f;
        fileSet = true;
    }
    public static void save() {
        if(fileSet) {
            
        }
    }

    private static TimeSorter ts = new TimeSorter();
    private static TypeSorter tys = new TypeSorter();
    private static Comparator<Message> comp = new TimeSorter(); 
    public static void sortByTime() {
        comp = ts;
        Collections.sort(logs, comp);
    }
    public static void sortByLevel() {
        comp = tys;
        Collections.sort(logs, comp);
    }
    
    private static class TimeSorter implements Comparator<Message> {
        @Override
        public int compare(Message m1, Message m2) {
            if(m2.time > m1.time) {
                return 1;
            }
            else if(m2.time < m1.time) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    
    private static class TypeSorter implements Comparator<Message> {
        @Override
        public int compare(Message m1, Message m2) {
            int comp = m2.type - m1.type;
            if(comp != 0) {
                return comp;
            }
            else {
                if(m2.time > m1.time) {
                    return 1;
                }
                else if(m2.time < m1.time) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }
}
