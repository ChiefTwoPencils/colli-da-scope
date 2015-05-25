package collidascope.collidatracka;

import collidascope.Collision;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * The collision tracker tracks and prioritizes <em>unique</em>
 * collisions between colliders. It will disregard duplicate
 * collisions. It is the client's responsibility to ensure priorities
 * are set and to understand collisions are <em>handled</em> uni-
 * directionally (A - B != B - A). The tracking mechanism does
 * not check for equivalent, but reversed, collisions. It is
 * assumed the <em>iteration</em> for collision detection is "smart"
 * and disallows the checking of B - A given A - B exists. In other
 * words, if the client does <em>not</em> prevent redundant/equivalent
 * collision detections neither does this code.
 *
 * <em>Note: The comparator used for prioritizing the collisions imposes
 * ordering inconsistent with 'equals'.</em>
 * @author Robert Wilk
 *         Created on 5/23/2015.
 */
public class Tracker
implements Iterable {

    /**
     * The set of prioritized collisions.
     */
    private Set<Collision> collisions;

    /**
     * Constructs the tracker with an empty collection of collisions.
     */
    public Tracker() {
        // Assigns the tree set and passes in an anonymous comparator for
        // prioritization.
        collisions = new TreeSet<>((Collision one, Collision theOther) ->
          theOther.getPriority() - one.getPriority()
        );
    }

    /**
     * Adds the collision to the collection.
     * @param collision The collision to be tracked.
     */
    public void track(Collision collision) {
        collisions.add(collision);
    }

    /**
     * Clears the collisions from the collection
     */
    public void clear() {
        collisions.clear();
    }

    @Override
    public Iterator iterator() {
        return collisions.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        collisions.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return collisions.spliterator();
    }
}
