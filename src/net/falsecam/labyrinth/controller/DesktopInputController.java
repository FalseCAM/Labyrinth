package net.falsecam.labyrinth.controller;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;

/**
 *
 * @author FalseCAM
 */
public class DesktopInputController implements InputController, AnalogListener {

    private InputManager inputManager;
    private GameController gameController;

    public DesktopInputController() {
    }

    @Override
    public void initInput(InputManager inputManager) {
        this.inputManager = inputManager;
        inputManager.addMapping("Up",
                new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Down",
                new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Left",
                new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Right",
                new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Jump",
                new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, new String[]{"Up", "Down", "Left", "Right", "Jump"});
    }

    public void cleanup() {
        inputManager.removeListener(this);
        inputManager.removeListener(this);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void onAnalog(String name, float value, float tpf) {

        if (name.equals("Up")) {
            if (gameController != null) {
                gameController.moveUp();
            }
        } else if (name.equals("Down")) {
            if (gameController != null) {
                gameController.moveDown();
            }
        } else if (name.equals("Left")) {
            if (gameController != null) {
                gameController.moveLeft();
            }
        } else if (name.equals("Right")) {
            if (gameController != null) {
                gameController.moveRight();
            }
        } else if (name.equals("Jump")) {
            if (gameController != null) {
                gameController.jump();
            }
        }
    }

    public String getJumpText() {
        return "Press <SPACE> to jump";
    }
}
