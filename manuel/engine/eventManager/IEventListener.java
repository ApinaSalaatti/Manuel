package manuel.engine.eventManager;

/**
 *
 * @author Merioksan Mikko
 */
public interface IEventListener {
    public abstract void execute(IGameEvent event);
}
