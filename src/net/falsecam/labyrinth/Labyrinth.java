package net.falsecam.labyrinth;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import net.falsecam.labyrinth.model.GameAppState;

/**
 *
 * @author FalseCAM
 */
public class Labyrinth extends SimpleApplication {

    GameAppState gameAppState;

    public static void main(String[] args) {
        Labyrinth app = new Labyrinth();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Game.init(this);

        gameAppState = new GameAppState();
        stateManager.attach(gameAppState);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
