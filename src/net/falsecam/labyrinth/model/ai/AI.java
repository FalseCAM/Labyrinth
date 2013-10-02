package net.falsecam.labyrinth.model.ai;

import java.util.Random;
import net.falsecam.labyrinth.model.map.AbstractMap;

/**
 *
 * @author FalseCAM
 */
public class AI {

    private enum Type {

        UP, DOWN, LEFT, RIGHT
    };
    private final AbstractMap map;
    private final DistanceAlgo distanceAlgo;
    private final Random rnd = new Random();
    private int marbleX;
    private int marbleY;
    private int best_distance = 0;
    int best_position;
    private Type best_direction;
    private int timer = 0;

    public AI(AbstractMap map) {
        this.map = map;
        this.distanceAlgo = new DistanceAlgo(map);
    }

    public void doWork(int marbleX, int marbleY) {
        timer = (++timer) % 3;
        this.marbleX = marbleX;
        this.marbleY = marbleY;

        best_position = 0;
        best_distance = 0;
        best_direction = Type.UP;
        if (timer == 0) {
            for (int i = 2; i < map.getWidth() - 1; i++) {
                if (i == marbleX) {
                    continue;
                }
                testMove(i, Type.UP);
                testMove(i, Type.DOWN);
            }
            for (int j = 2; j < map.getHeight() - 1; j++) {
                if (j == marbleY) {
                    continue;
                }
                testMove(j, Type.LEFT);
                testMove(j, Type.RIGHT);
            }
        } else {
            doRandomMove(marbleX, marbleY);
        }

        doMove(best_position, best_direction);
    }

    private void doRandomMove(int marbleX, int marbleY) {
        best_direction = Type.values()[rnd.nextInt(Type.values().length)];
        if (best_direction.equals(Type.UP) || best_direction.equals(Type.DOWN)) {
            do {
                best_position = rnd.nextInt(map.getWidth());
            } while (best_position == marbleX);
        } else {
            do {
                best_position = rnd.nextInt(map.getHeight());
            } while (best_position == marbleY);
        }

    }

    private void testMove(int position, Type direction) {
        doMove(position, direction);
        int distance = distanceAlgo.distance(map.get(marbleX, marbleY), map.getTarget());
        if (distance != Integer.MAX_VALUE && distance > best_distance && map.getTarget() != map.getChange()) {
            best_distance = distance;
            best_direction = direction;
            best_position = position;
        }
        undoMove(position, direction);
    }

    private void doMove(int position, Type direction) {
        switch (direction) {
            case UP:
                map.changeToTop(position);
                break;
            case DOWN:
                map.changeToBottom(position);
                break;
            case LEFT:
                map.changeToLeft(position);
                break;
            case RIGHT:
                map.changeToRight(position);
                break;
            default:
                break;
        }
    }

    private void undoMove(int position, Type direction) {
        switch (direction) {
            case UP:
                map.changeToBottom(position);
                break;
            case DOWN:
                map.changeToTop(position);
                break;
            case LEFT:
                map.changeToRight(position);
                break;
            case RIGHT:
                map.changeToLeft(position);
                break;
            default:
                break;
        }
    }
}
