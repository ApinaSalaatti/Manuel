/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manuel.engine.processManager;

/**
 *
 * @author ApinaSalaatti
 */
public interface IProcess {
    public enum ProcessState { UNINITIALIZED, RUNNING, PAUSED, SUCCEEDED, ABORTED, FAILED };
    
    public void init();
    public void update(long deltaMs);
    public void fail();
    public void succeed();
    public void abort();
    
    public void onFail();
    public void onSuccess();
    public void onAbort();
    
    public boolean isDead();
    public boolean isAlive();
    
    public void pause();
    public void unpause();
    public boolean isPaused();
    
    public void attachChild(IProcess child);
    public IProcess getChild();
    public IProcess removeChild();
    
    public ProcessState getState();
}
