/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.falsecam.labyrinth.controller;

import com.jme3.math.Vector3f;
import net.falsecam.labyrinth.model.GameAppState;

/**
 *
 * @author FalseCAM
 */
public class GameController {

    private final float moveSpeed = 5f;
    private final GameAppState gameAppState;

    public GameController(GameAppState gameAppState) {
        this.gameAppState = gameAppState;
    }

    void moveUp() {
        gameAppState.getMarble().getPhysics().setLinearVelocity(new Vector3f(0, 0, -moveSpeed));
    }

    void moveDown() {
        gameAppState.getMarble().getPhysics().setLinearVelocity(new Vector3f(0, 0, moveSpeed));
    }

    void moveLeft() {
        gameAppState.getMarble().getPhysics().setLinearVelocity(new Vector3f(-moveSpeed, 0, 0));
    }

    void moveRight() {
        gameAppState.getMarble().getPhysics().setLinearVelocity(new Vector3f(moveSpeed, 0, 0));
    }
}
