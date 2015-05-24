package collidascope.collidahandla;

import collidascope.Collision;
import collidascope.ICollider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

/**
 * A class used to handle collisions between two colliders. The handler
 * relies on the client's programmed functions to provide the logic to
 * carry out the collision response for the specific colliders.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public class Handler {
    /**
     * Map of collision handling functions.
     */
    private Map<String, BiConsumer<ICollider, ICollider>> handlers;

    /**
     * Constructs the handler with an empty container for client
     * specified resolution functionality.
     */
    public Handler() {
        handlers = new HashMap<>();
    }
    /**
     * Adds collision handler functionality to the collider.
     * @param keys The keys to map to.
     * @param handlers The handlers to be mapped.
     */
    public void addHandlers(List<String> keys, List<BiConsumer<ICollider, ICollider>> handlers) {
        IntStream.range(0, keys.size())
          .forEach(i -> this.handlers.put(keys.get(i), handlers.get(i)));
    }

    /**
     * Adds the mapped handler functions to the Handler.
     * @param handlers The map
     */
    public void addHandlers(Map<String, BiConsumer<ICollider, ICollider>> handlers) {
        this.handlers.putAll(handlers);
    }

    /**
     * Handles a collision between two colliders immediately.
     * @param a The first collider.
     * @param b The second collider.
     * @param key The key for the handler function.
     */
    public void handleCollision(ICollider a, ICollider b, String key) {
        BiConsumer<ICollider, ICollider> handler = handlers.get(key);
        if (handler == null)
            return;
        handler.accept(a, b);
    }

    /**
     * Applies the handler functionality to each collision in the iterator.
     * @param iterator The collision iterator.
     */
    public void handleCollisions(Iterator<Collision> iterator) {
        while (iterator.hasNext()) {
            Collision collision = iterator.next();
            ICollider one = collision.getOne();
            handlers.get(collision.getCollisionKey())
              .accept(one, collision.getTheOther(one));
        }
    }
}
