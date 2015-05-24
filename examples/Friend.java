package examples;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Simple friend to be used in a game.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public class Friend
extends GameObject {

    /**
     * The key used in collision detection, tracking, and resolution.
     */
    public static final String COLLISION_KEY = "Friend";
    /**
     * Constructs the friend with the default collision key "Friend".
     */
    public Friend() {
        this(new Point2D.Double(225, 245));
    }

    /**
     * Constructs the friend at the given location.
     * @param location The initial location of the friend.
     */
    public Friend(Point2D.Double location) {
        this(location, 50);
    }

    /**
     * Constructs the friend at the given location and size.
     * @param location The initial location of the friend.
     * @param size The size of the friend.
     */
    public Friend(Point2D.Double location, int size) {
        super(location, size);
        addHandler(getCollisionKey() + Enemy.COLLISION_KEY, (a, b) ->
            System.out.println(
              "Performing the collision response for a friend an an enemy")
        );
        addDetector(getCollisionKey() + Enemy.COLLISION_KEY, (a, b) ->
            a.getBoundingShape().intersects((Rectangle) b.getBoundingShape())
        );
    }

    @Override
    public void update() {
        System.out.println(
          "Friend updates itself...\n" +
            "Perhaps it moves/animates, switches strategies, or similar..."
        );
    }

    @Override
    public String getCollisionKey() {
        return COLLISION_KEY;
    }
}
