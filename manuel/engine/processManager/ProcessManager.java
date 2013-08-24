package manuel.engine.processManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import manuel.engine.processManager.IProcess.ProcessState;

/**
 *
 * @author Merioksan Mikko
 */
public class ProcessManager {
    private List<IProcess> processes;
    private List<IProcess> toAdd;
    
    public ProcessManager() {
        processes = new ArrayList<>();
        toAdd = new ArrayList<>();
    }
    
    public void update(long deltaMs) {
        Iterator<IProcess> it = toAdd.iterator();
        while(it.hasNext()) {
            IProcess p = it.next();
            processes.add(p);
            it.remove();
        }
        
        it = processes.iterator();
        while(it.hasNext()) {
            IProcess p = it.next();
            
            if(p.getState() == ProcessState.UNINITIALIZED) {
                p.init();
            }
            
            if(p.getState() == ProcessState.RUNNING) {
                p.update(deltaMs);
            }
            
            if(p.isDead()) {
                switch(p.getState()) {
                    case SUCCEEDED:
                        p.onSuccess();
                        IProcess child = p.removeChild();
                        if(child != null) {
                            attachProcess(child);
                        }
                        break;
                    case FAILED:
                        p.onFail();
                        break;
                    case ABORTED:
                        p.onAbort();
                        break;
                }
                
                it.remove();
            }
        }
    }
    
    public void attachProcess(IProcess pr) {
        toAdd.add(pr);
    }
    
    public void abortAllProcesses() {
        Iterator<IProcess> it = processes.iterator();
        while(it.hasNext()) {
            IProcess p = it.next();
            p.abort();
        }
    }
    public List<IProcess> getProcesses() {
        return processes;
    }
}
