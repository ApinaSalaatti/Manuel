package manuel.engine.processManager;

/**
 *
 * @author Merioksan Mikko
 */
public class Process implements IProcess {
    private ProcessState currentState;
    
    private IProcess child;
    
    public Process() {
        currentState = ProcessState.UNINITIALIZED;
    }
    
    @Override
    public boolean isAlive() {
        return currentState == ProcessState.RUNNING || currentState == ProcessState.PAUSED;
    }
    
    @Override
    public void pause() {
        if(getState() == ProcessState.RUNNING) {
            setState(ProcessState.PAUSED);
        }
    }
    
    @Override
    public void unpause() {
        if(getState() == ProcessState.PAUSED) {
            setState(ProcessState.RUNNING);
        }
    }
    
    @Override
    public boolean isPaused() {
        return currentState == ProcessState.PAUSED;
    }
    
    @Override
    public boolean isDead() {
        return currentState == ProcessState.ABORTED || currentState == ProcessState.FAILED || currentState == ProcessState.SUCCEEDED;
    }
    
    @Override
    public void update(long deltaMs) {
        
    }
    
    @Override
    public void init() {
        currentState = ProcessState.RUNNING;
    }
    
    @Override
    public void succeed() {
        setState(ProcessState.SUCCEEDED);
    }
    
    @Override
    public void fail() {
        setState(ProcessState.FAILED);
    }
    
    @Override
    public void abort() {
        setState(ProcessState.ABORTED);
    }
    
    @Override
    public void onAbort() {
        
    }
    
    @Override
    public void onFail() {
        
    }
    
    @Override
    public void onSuccess() {
        
    }
    
    @Override
    public ProcessState getState() {
        return currentState;
    }
    
    private void setState(ProcessState newState) {
        currentState = newState;
    }
    
    @Override
    public IProcess removeChild() {
        IProcess c = child;
        child = null;
        return c;
    }
    
    @Override
    public IProcess getChild() {
        return child;
    }
    
    @Override
    public void attachChild(IProcess c) {
        child = c;
    }
}
