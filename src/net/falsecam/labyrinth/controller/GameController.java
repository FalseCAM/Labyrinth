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

    
    private final GameAppState gameAppState;

    public GameController(GameAppState gameAppState) {
        this.gameAppState = gameAppState;
    }

    void moveUp() {
        gameAppState.getMarble().moveUp();
    }

    void moveDown() {
        gameAppState.getMarble().moveDown();
    }

    void moveLeft() {
        gameAppState.getMarble().moveLeft();
    }

    void moveRight() {
        gameAppState.getMarble().moveRight();
    }

    void jump() {
        gameAppState.getMarble().jump();
    }
}
