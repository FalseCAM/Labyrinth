package net.falsecam.labyrinth.model.ai;

import java.util.List;
import net.falsecam.labyrinth.model.map.MapElement;
import net.falsecam.labyrinth.model.map.MapType;

/**
 *
 * @author FalseCAM
 */
public class DistanceAlgo {

    private final MapElement[][] elements;
    private final int[][] distances;
    private MapElement start;
    private MapElement target;

    public DistanceAlgo(MapElement[][] elements) {
        this.elements = elements;
        this.distances = new int[elements.length][elements[0].length];
    }

    public int distance(MapElement start, MapElement target) {
        this.start = start;
        this.target = target;
        initDistances();
        calculateDistances();
        for (int j = 0; j < distances.length; j++) {
            for (int i = 0; i < distances[j].length; i++) {
                if (elements[j][i] == target) {
                    return distances[j][i];
                }
            }
        }
        return 0;
    }

    private void initDistances() {
        for (int j = 0; j < distances.length; j++) {
            for (int i = 0; i < distances[j].length; i++) {
                if (elements[j][i] == start) {
                    distances[j][i] = 0;
                } else {
                    distances[j][i] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private void calculateDistances() {
        for (int j = 0; j < distances.length; j++) {
            for (int i = 0; i < distances[j].length; i++) {
                if (elements[j][i] == start) {
                    calculateDistances(i, j);
                }
            }
        }
    }

    public void calculateDistances(int x, int y) {
        MapElement actual = elements[y][x];
        if (actual.getBottom() != null && actual.isBottomFree() && actual.getBottom().isTopFree() && (distances[y][x] < distances[y + 1][x])) {
            distances[y + 1][x] = distances[y][x] + 1;
            calculateDistances(x, y + 1);
        }
        if (actual.getTop() != null && actual.isTopFree() && actual.getTop().isBottomFree() && (distances[y][x] < distances[y - 1][x])) {
            distances[y - 1][x] = distances[y][x] + 1;
            calculateDistances(x, y - 1);
        }
        if (actual.getLeft() != null && actual.isLeftFree() && actual.getLeft().isRightFree() && (distances[y][x] < distances[y][x - 1])) {
            distances[y][x - 1] = distances[y][x] + 1;
            calculateDistances(x - 1, y);
        }
        if (actual.getRight() != null && actual.isRightFree() && actual.getRight().isLeftFree() && (distances[y][x] < distances[y][x + 1])) {
            distances[y][x + 1] = distances[y][x] + 1;
            calculateDistances(x + 1, y);
        }
    }
}
