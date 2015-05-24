package examples;

import collidascope.ICollider;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * A simple game object for the purposes of demonstrating the
 * collision behavior of ColliDaScope. The class includes an
 * GameObject#update method which would be invoked upon each
 * tick of the game clock for example.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public abstract class GameObject
implements ICollider {

    /**
     * The size of the game object.
     */
    private final int SIZE;
    /**
     * The bounding shape used in collision detection.
     */
    private Shape boundingShape;
    /**
     * The detector functions for the game object.
     */
    private Map<String, BiFunction<ICollider, ICollider, Boolean>> detectors;
    /**
     * The handler functions for the game object.
     */
    private Map<String, BiConsumer<ICollider, ICollider>> handlers;
    /**
     * The point of location of the GameObject.
     */
    private Point2D.Double location;

    /**
     * Constructs the game object at a arbitrary default
     * location.
     */
    public GameObject() {
        this(new Point2D.Double(250, 250));
    }

    /**
     * Constructs the game object at the given location.
     * @param location The initial location of the object.
     */
    public GameObject(Point2D.Double location) {
        this(location, 100);
    }

    /**
     * Constructs the game object at the given location and size.
     * @param location The initial location of the game object.
     * @param size The size of the object.
     */
    public GameObject(Point2D.Double location, int size) {
        boundingShape = new Rectangle((int) location.getX(), (int) location.getY(), size, size);
        this.location = location;
        SIZE = size;
        detectors = new HashMap<>();
        handlers = new HashMap<>();
    }

    /**
     * Update the representation of the game object to reflect its
     * internal state.
     */
    abstract public void update();

    /**
     * Returns the location of the game object.
     * @return The location.
     */
    public Point2D.Double getLocation() {
        return location;
    }

    /**
     * Returns the size of the game object.
     * @return The size.
     */
    public int getSize() { return SIZE; }

    /**
     * Maps the key and handler in the collision handler.
     * @param key The key to map the handler to.
     * @param handler The handler to be mapped.
     */
    protected void addHandler(String key, BiConsumer<ICollider, ICollider> handler) {
        handlers.put(key, handler);
    }

    /**
     * Maps the key and handler in the collision detector.
     * @param key The key to map the detector to.
     * @param detector The detector to be mapped.
     */
    protected void addDetector(String key, BiFunction<ICollider, ICollider, Boolean> detector) {
        detectors.put(key, detector);
    }

    @Override
    public Map<String, BiConsumer<ICollider, ICollider>> getHandlers() {
        return handlers;
    }

    @Override
    public Map<String, BiFunction<ICollider, ICollider, Boolean>> getDetectors() {
        return detectors;
    }

    @Override
    public Shape getBoundingShape() {
        return boundingShape;
    }
}
