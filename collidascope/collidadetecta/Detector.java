package collidascope.collidadetecta;

import collidascope.ICollider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * A class used to detect collisions between two colliders. The detector
 * relies on the client's programmed functions to provide the logic to
 * determine if a collision occurs based on the specific colliders.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public class Detector {
    /**
     * Map of collision detection functions.
     */
    private Map<String, BiFunction<ICollider, ICollider, Boolean>> detectors;

    /**
     * Constructs the detector with an empty container for client
     * specified detection functionality.
     */
    public Detector() {
        detectors = new HashMap<>();
    }

    /**
     * Adds collision detector functionality to the collider.
     * @param keys The keys to map to.
     * @param detectors The detectors to be mapped.
     */
    public void addDetectors(List<String> keys, List<BiFunction<ICollider, ICollider, Boolean>> detectors) {
        IntStream.range(0, keys.size())
          .forEach(i -> this.detectors.put(keys.get(i), detectors.get(i)));
    }

    /**
     * Adds the mapped detector functions to the Detector.
     * @param detectors The detectors.
     */
    public void addDetectors(Map<String, BiFunction<ICollider, ICollider, Boolean>> detectors) {
        this.detectors.putAll(detectors);
    }

    /**
     * Provides a one-way collision detection between two
     * colliders. It tries to find the detection algorithm
     * in the map based on @see getCollisionString.
     * @param a The first collider.
     * @param b The second collider.
     * @param key The key to the detection function.
     * @return Did they collide?
     */
    public boolean detectCollision(ICollider a, ICollider b, String key) {
        BiFunction<ICollider, ICollider, Boolean> d = detectors.get(key);
        if (d == null)
            return false;
        return d.apply(a, b);
    }
}
