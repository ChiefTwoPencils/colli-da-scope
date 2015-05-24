package examples;

import collidascope.Collider;
import collidascope.Collision;
import collidascope.ICollider;

import java.util.*;

/**
 * Simple game world to use in a game.
 * @author Robert Wilk
 *         Created on 5/23/2015.
 */
public class GameWorld {
    /**
     * Enumerated game object types used in the game.
     */
    enum GameObjectType {
        FRIEND, ENEMY
    }

    /**
     * The system for detecting, tracking, and handling
     * game object collisions.
     */
    Collider collider;
    /**
     * The collection of game objects in the game.
     */
    Map<GameObjectType, List<GameObject>> gameObjects;

    /**
     * Constructs the game world and initializes it.
     */
    public GameWorld() {
        collider = new Collider();
        gameObjects = new HashMap<>();
        initialize();
    }

    /**
     * Initializes the game world to its beginning state.
     */
    public void initialize() {
        for (GameObjectType type : GameObjectType.values())
            gameObjects.put(type, new ArrayList<>());
        Friend f = new Friend();
        Enemy e = new Enemy();

        gameObjects.get(GameObjectType.FRIEND)
          .add(f);
        collider.addHandlers(f.getHandlers());
        collider.addDetectors(f.getDetectors());

        gameObjects.get(GameObjectType.ENEMY)
          .add(e);
        collider.addHandlers(e.getHandlers());
        collider.addDetectors(e.getDetectors());
    }

    /**
     * Advances the game clock by one "tick".
     */
    public void tick() {
        for (List<GameObject> gol : gameObjects.values())
            gol.forEach(GameObject::update);
        checkCollisions();
        collider.handleCollisions();
    }

    /**
     * Checks the current game objects for collisions.
     */
    private void checkCollisions() {
        Iterator<GameObject> friends = gameObjects.get(GameObjectType.FRIEND).iterator();
        Iterator<GameObject> enemies = gameObjects.get(GameObjectType.ENEMY).iterator();
        while (friends.hasNext()) {
            ICollider one = friends.next();
            while (enemies.hasNext()) {
                ICollider theOther = enemies.next();
                if (collider.detectedCollision(one, theOther)) {
                    collider.trackCollision(new Collision(one, theOther, 0));
                }
            }
        }
    }
}
