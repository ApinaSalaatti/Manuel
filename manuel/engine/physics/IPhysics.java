/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manuel.engine.physics;

/**
 * A wrapper for any physics implementation you would want in your game.
 * Just use your favourite physics engine and wrap it in a class that implements this.
 * 
 * @author ApinaSalaatti
 */
public interface IPhysics {
    public void update(long deltaMs);
    public void syncObjects();
}
