package examples;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Simple enemy to be used in a game.
 * @author Robert Wilk
 *         Created on 5/22/2015.
 */
public class Enemy
extends GameObject {

    /**
     * The key used in collision detection, tracking, and resolution.
     */
    public static final String COLLISION_KEY = "Enemy";
    /**
     * Constructs the enemy with the default collision key
     * "Enemy".
     */
    public Enemy() {
        this(new Point2D.Double(240, 230));
    }

    /**
     * Constructs the enemy at the given location.
     * @param location The initial location of the enemy.
     */
    public Enemy(Point2D.Double location) {
        this(location, 100);
    }

    /**
     * Constructs the enemy at the given location and size.
     * @param location The initial location of the enemy.
     * @param size The size of the enemy.
     */
    public Enemy(Point2D.Double location, int size) {
        super(location, size);
        addHandler(getCollisionKey() + Friend.COLLISION_KEY, (a, b) ->
            System.out.println(
              "Performing the collision responce for an enemy and a friend."
            )
        );
        addDetector(getCollisionKey() + Friend.COLLISION_KEY, (a, b) ->
            a.getBoundingShape().intersects((Rectangle) b.getBoundingShape())
        );
    }

    @Override
    public void update() {
        System.out.println(
          "Enemy updates itself...\n" +
            "Perhaps it moves/animates, switches strategies, or similar..."
        );
    }

    @Override
    public String getCollisionKey() {
        return COLLISION_KEY;
    }
}
