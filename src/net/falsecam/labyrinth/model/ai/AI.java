package net.falsecam.labyrinth.model.ai;

import net.falsecam.labyrinth.model.map.AbstractMap;

/**
 *
 * @author FalseCAM
 */
public class AI {

    private final AbstractMap map;
    private int marbleX;
    private int marbleY;

    public AI(AbstractMap map) {
        this.map = map;
    }

    public void doWork(int marbleX, int marbleY) {
        this.marbleX = marbleX;
        this.marbleY = marbleY;
        for (int i = 0; i < map.getWidth(); i++) {
            findWay(i, 0);
        }
        for (int j = 1; j < map.getHeight() - 1; j++) {
        }
    }

    private void findWay(int x, int y) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
