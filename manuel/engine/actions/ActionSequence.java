package manuel.engine.actions;

import java.util.ArrayDeque;

/**
 *
 * @author Merioksan Mikko
 */
public class ActionSequence extends Action {
    private ArrayDeque<Action> sequence;
    private boolean sequenceCreated;
    
    public ActionSequence() {
        sequence = new ArrayDeque<Action>();
        sequenceCreated = false;
    }
    public ActionSequence(Action ... actions) {
        sequence = new ArrayDeque<Action>();
        for(Action a : actions) {
            sequence.addLast(a);
        }
        sequenceCreated = true;
    }
    
    public void add(Action a) {
        sequence.addLast(a);
        sequenceCreated = true;
    }
    public void add(Action ... actions) {
        for(Action a : actions) {
            sequence.addLast(a);
        }
        sequenceCreated = true;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!sequence.isEmpty()) {
            sequence.peekFirst().update(deltaMs);
            if(sequence.peekFirst().finished()) {
                sequence.pollFirst();
            }
        }
    }
    
    @Override
    public boolean finished() {
        return sequence.isEmpty();
    }
}
