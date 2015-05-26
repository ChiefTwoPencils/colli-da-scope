package collidascope;

import collidascope.collidadetecta.Detector;
import collidascope.collidahandla.Handler;
import collidascope.collidatracka.Tracker;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * This class provides a flexible interface for collision detection,
 * tracking, and resolution. It allows the client to specify the
 * functionality of those individual areas but handles selecting the
 * runtime functionality for each automatically.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public class Collider {
    /**
     * The collision detector.
     */
    private Detector detector;
    /**
     * The collision handler.
     */
    private Handler handler;
    /**
     * The collision tracker.
     */
    private Tracker tracker;

    /**
     * Construct the collider with keys and functions for the detection and
     * handler maps.
     * @param detectionKeys Detection keys.
     * @param detectionFunctions Detection functions.
     * @param handlerKeys Handler keys.
     * @param handlerFunctions Handler functions.
     */
    public Collider(List<String> detectionKeys, List<BiFunction<ICollider, ICollider, Boolean>> detectionFunctions,
                    List<String> handlerKeys, List<BiConsumer<ICollider, ICollider>> handlerFunctions) {
        this();
        addDetectors(detectionKeys, detectionFunctions);
        addHandlers(handlerKeys, handlerFunctions);
    }

    /**
     * Construct the collider with the collision detection and handler maps.
     * @param detectors The map of detectors.
     * @param handlers The map of handlers.
     */
    public Collider(Map<String, BiFunction<ICollider, ICollider, Boolean>> detectors,
                    Map<String, BiConsumer<ICollider, ICollider>> handlers) {
        this();
        addDetectors(detectors);
        addHandlers(handlers);
    }

    /**
     * Constructs the collider with empty collision detection and handler maps.
     */
    public Collider() {
        detector = new Detector();
        handler = new Handler();
        tracker = new Tracker();
    }

    /**
     * Provides a one-way collision detection between two
     * colliders. It tries to find the detection algorithm
     * in the map based on @see getCollisionString.
     * @param a The first collider.
     * @param b The second collider.
     * @return Did they collide?
     */
    public boolean detectedCollision(ICollider a, ICollider b) {
        String key = getCollisionString(a, b);
        return detector.detectCollision(a, b, key);
    }

    /**
     * Tracks the collision in a prioritized set.
     * @param collision The collision to be tracked.
     */
    public void trackCollision(Collision collision) {
        tracker.track(collision);
    }

    /**
     * Resolves the collision between two colliders.
     * @param a "one" of the colliders.
     * @param b The "other" one in the collision.
     */
    public void handleCollision(ICollider a, ICollider b) {
        String key = getCollisionString(a, b);
        handler.handleCollision(a, b, key);
    }

    /**
     * Handles all the collisions which have been tracked.
     */
    public void handleCollisions() {
        handler.handleCollisions(tracker.iterator());
    }

    /**
     * Adds collision handler functionality to the collider.
     * @param keys The keys to map to.
     * @param handlers The handlers to be mapped.
     */
    public void addHandlers(List<String> keys, List<BiConsumer<ICollider, ICollider>> handlers) {
        checkSize(keys, handlers);
        handler.addHandlers(keys, handlers);
    }

    /**
     * Adds collision handler functionality to the collider.
     * @param handlers The handler functions.
     */
    public void addHandlers(Map<String, BiConsumer<ICollider, ICollider>> handlers) {
        handler.addHandlers(handlers);
    }

    /**
     * Adds collision detector functionality to the collider.
     * @param keys The keys to map to.
     * @param detectors The detectors to be mapped.
     */
    public void addDetectors(List<String> keys, List<BiFunction<ICollider, ICollider, Boolean>> detectors) {
        checkSize(keys, detectors);
        detector.addDetectors(keys, detectors);
    }

    /**
     * Adds collision detector functionality to the collider.
     * @param detectors The detector functions.
     */
    public void addDetectors(Map<String, BiFunction<ICollider, ICollider, Boolean>> detectors) {
        detector.addDetectors(detectors);
    }

    /**
     * Ensures the the lists are the same size.
     * @param a The first list.
     * @param b The second list.
     */
    private void checkSize(List a, List b) {
        if (a.size() != b.size())
            throw new IllegalArgumentException("keys.size() != values.size()");
    }

    /**
     * Gets the map key for collision detection and handler functionality.
     * @param a The first collider.
     * @param b The second collider.
     * @return The concatenation of the second key to the first.
     */
    public static String getCollisionString(ICollider a, ICollider b) {
        return a.getCollisionKey() + b.getCollisionKey();
    }
}
