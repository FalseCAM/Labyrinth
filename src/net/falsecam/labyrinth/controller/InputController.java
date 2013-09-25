/**
 *
 */
package net.falsecam.labyrinth.controller;

import com.jme3.input.InputManager;

/**
 * @author FalseCAM
 *
 */
public interface InputController {

    public void initInput(InputManager inputManager);

    public void setGameController(GameController gameController);

    public void cleanup();
}
