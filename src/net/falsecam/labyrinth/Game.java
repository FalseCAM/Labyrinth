package net.falsecam.labyrinth;

import com.jme3.asset.AssetManager;

/**
 *
 * @author FalseCAM
 */
public class Game {

    public final static float scale = 1;
    static Game singleton;
    Labyrinth simpleApplication;

    private Game() {
    }

    static void init(Labyrinth simpleApplication) {
        Game game = new Game();
        singleton = game;
        game.simpleApplication = simpleApplication;
    }

    public static Game instance() {
        return singleton;
    }

    public static Labyrinth getMain() {
        return singleton.simpleApplication;
    }

    public static AssetManager getAssetManager() {
        return singleton.simpleApplication.getAssetManager();
    }
}
