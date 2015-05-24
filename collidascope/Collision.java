package collidascope;

/**
 * A representation of a prioritized collision between two objects.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public class Collision {
    /**
     * "one" of the colliders in the collision.
     */
    private ICollider a;
    /**
     * The "other" one in the collision.
     */
    private ICollider b;
    /**
     *  @see collidascope.Collider#getCollisionString(ICollider, ICollider)
     */
    private String collisionKey;
    /**
     * The collision's priority on a scale from 0-5. The default value
     * (lowest) = 5. If no priority is specified for any collision the
     * priority has no impact on the order in which the collision is
     * handled as all collision's priorities will be equal.
     */
    private int priority = 5;

    /**
     * Construct the collision with the colliders and a given priority.
     * @param a "one" of the colliders in the collision
     * @param b The "other" one in the collision.
     * @param priority The priority of the collision.
     */
    public Collision(ICollider a, ICollider b, int priority) {
        this(a, b);
        this.priority = priority;
    }

    /**
     * Returns the integer value used to determine the ordering the
     * collision should be handled.
     * @return An integer value between 0 and 5. 5 is the lowest and
     *         0 is the highest.
     */
    public int getPriority() { return priority; }

    /**
     *Constructs the collision with the colliders and default priority.
     * @param a "one" of the colliders in the collision.
     * @param b The "other" one in the collision.
     */
    public Collision(ICollider a, ICollider b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Gets "one" of the colliders in the collision.
     * @return "one" of the colliders.
     */
    public ICollider getOne() {
        return a;
    }

    /**
     * Gets the "other" one in the collision.
     * @param one "one" of the colliders in the collision.
     * @return The "other" one in the collision.
     */
    public ICollider getTheOther(ICollider one) {
        return (one == a) ? b : a;
    }

    /**
     * Returns the collision key generated from the concatenation
     * of "one" of the colliders with the "other"'s.
     * @return The collision key.
     */
    public String getCollisionKey() {
        return Collider.getCollisionString(a, b);
    }
}
