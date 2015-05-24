package collidascope;

import java.awt.*;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Interface for objects which support the notion of colliding
 * with another collider.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public interface ICollider {

    /**
     * Returns the collision key used to create the map key for the
     * collision detection and handler functionality.
     * @return The collision key.
     */
    public String getCollisionKey();

    /**
     * Returns the bounding shape used for collision detection.
     * @return The bounding shape.
     */
    public Shape getBoundingShape();

    /**
     * Returns the handler functions for the collider.
     * @return The handlers.
     */
    public Map<String, BiConsumer<ICollider, ICollider>> getHandlers();

    /**
     * Returns the detectors functions for the collider.
     * @return The detectors.
     */
    public Map<String, BiFunction<ICollider, ICollider, Boolean>> getDetectors();
}
