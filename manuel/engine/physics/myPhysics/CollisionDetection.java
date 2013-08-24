package manuel.engine.physics.myPhysics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Merioksan Mikko
 */
public class CollisionDetection {
    private ArrayList<Manifold> manifolds;
    
    public CollisionDetection() {
        manifolds = new ArrayList<>();
    }
    
    public int getManifoldAmount() {
        return manifolds.size();
    }
    
    public Manifold getManifoldByIndex(int indx) {
        return manifolds.get(indx);
    }

    public void check(List<PhysicsObject> dynObjects, List<PhysicsObject> statObjects) {
        manifolds.clear();
        
        Iterator<PhysicsObject> it1 = dynObjects.iterator();
        while(it1.hasNext()) {
            PhysicsObject obj1 = it1.next();
            
            Iterator<PhysicsObject> it2 = dynObjects.iterator();
            while(it2.hasNext()) {
                PhysicsObject obj2 = it2.next();
                if(obj1 != obj2) {
                    Manifold m = new Manifold(obj1, obj2);
                    if(checkCollision(m)) {
                        if(!manifolds.contains(m)) { // check to avoid duplicate manifolds
                            manifolds.add(m);
                        }
                    }
                }
            }
            
            it2 = statObjects.iterator();
            while(it2.hasNext()) {
                PhysicsObject obj2 = it2.next();
                if(obj1 != obj2) {
                    Manifold m = new Manifold(obj1, obj2);
                    if(checkCollision(m)) {
                        if(!manifolds.contains(m)) { // check to avoid duplicate manifolds
                            manifolds.add(m);
                        }
                    }
                }
            }
        }
    }
    
    /*
    public boolean checkTerrainCollision(PhysicsObject obj, TiledTerrain terrain) {
        float minX = obj.aabb.min.x;
        float minY = obj.aabb.min.y;
        float maxX = obj.aabb.max.x;
        float maxY = obj.aabb.max.y;

        // left side
        int x = (int)minX;
        for(int y = (int)minY; y <= (int)maxY; y++) {
            if(terrain.isSolid(x, y)) {
                Manifold m = new Manifold(obj, terrainObject);
                m.normal = terrain.normal(x, y);
                if(m.velAlongNormal() <= 0) {
                    manifolds.add(m);
                }
                return true;
                
            }
        }
        // right side
        x = (int)maxX;
        for(int y = (int)minY; y <= (int)maxY; y++) {
            if(terrain.isSolid(x, y)) {
                Manifold m = new Manifold(obj, terrainObject);
                m.normal = terrain.normal(x, y);
                if(m.velAlongNormal() <= 0) {
                    manifolds.add(m);
                }
                return true;
            }
        }
        // top side
        int y = (int)minY;
        for(x = (int)minX; x <= (int)maxX; x++) {
            if(terrain.isSolid(x, y)) {
                Manifold m = new Manifold(obj, terrainObject);
                m.normal = terrain.normal(x, y);
                if(m.velAlongNormal() <= 0) {
                    manifolds.add(m);
                }
                return true;
            }
        }
        // bottom side
        y = (int)maxY;
        for(x = (int)minX; x <= (int)maxX; x++) {
            if(terrain.isSolid(x, y)) {
                Manifold m = new Manifold(obj, terrainObject);
                m.normal = terrain.normal(x, y);
                if(m.velAlongNormal() <= 0) {
                    manifolds.add(m);
                }
                return true;
            }
        }
        
        return false;
    }
    */
    
    public boolean checkCollision(Manifold m) {
        PhysicsObject a = m.obj1;
        PhysicsObject b = m.obj2;
        Vector2f n = new Vector2f();
        
        n.x = b.location.x - a.location.x;
        n.y = b.location.y - a.location.y;
        
        AABB abox = a.aabb;
        AABB bbox = b.aabb;
        
        float a_extent = (abox.max.x - abox.min.x) / 2;
        float b_extent = (bbox.max.x - bbox.min.x) / 2;
        
        float x_overlap = a_extent + b_extent - Math.abs(n.x);

        if(x_overlap > 0) {
            a_extent = (abox.max.y - abox.min.y) / 2;
            b_extent = (bbox.max.y - bbox.min.y) / 2;
            
            float y_overlap = a_extent + b_extent - Math.abs(n.y);

            if(y_overlap > 0) {
                if(x_overlap < y_overlap) {
                    if(n.x < 0) {
                        m.normal = new Vector2f(-1, 0);
                    }
                    else {
                        m.normal = new Vector2f(1, 0);
                    }
                    m.penetration = x_overlap;
                    return true;
                }
                else {
                    if(n.y < 0) {
                        m.normal = new Vector2f(0, -1);
                    }
                    else {
                        m.normal = new Vector2f(0, 1);
                    }
                    m.penetration = y_overlap;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void resolveCollisions() {
        Iterator<Manifold> it = manifolds.iterator();
        while(it.hasNext()) {
            Manifold m = it.next();
            m.resolve();
        }
    }
}
