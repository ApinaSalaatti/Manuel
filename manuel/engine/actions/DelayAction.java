package manuel.engine.actions;

/**
 *
 * @author Merioksan Mikko
 */
public class DelayAction extends Action {
    private long toDelay, delayed;
    
    public DelayAction(int delay) {
        toDelay = delay;
        delayed = 0;
    }
    
    @Override
    public void update(long deltaMs) {
        delayed += deltaMs;
    }
    
    @Override
    public boolean finished() {
        return delayed >= toDelay;
    }
}
