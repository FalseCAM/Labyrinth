package net.falsecam.labyrinth;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.renderer.RenderManager;
import net.falsecam.labyrinth.controller.DesktopInputController;
import net.falsecam.labyrinth.model.GameAppState;

/**
 *
 * @author FalseCAM
 */
public class Labyrinth extends SimpleApplication {

    GameAppState gameAppState;
    DesktopInputController desktopInputController;

    public static void main(String[] args) {
        Labyrinth app = new Labyrinth();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Game.init(this);
        flyCam.setEnabled(false);
        desktopInputController = new DesktopInputController();
        gameAppState = new GameAppState(desktopInputController);
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

    public BitmapFont getGuiFont() {
        return this.guiFont;
    }
}
