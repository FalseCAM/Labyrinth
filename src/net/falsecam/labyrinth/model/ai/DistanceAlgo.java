package net.falsecam.labyrinth.model.ai;

import net.falsecam.labyrinth.model.map.AbstractMap;
import net.falsecam.labyrinth.model.map.MapElement;

/**
 *
 * @author FalseCAM
 */
public class DistanceAlgo {
    
    private final AbstractMap map;
    private final int[][] distances;
    private MapElement start;
    private MapElement target;
    
    public DistanceAlgo(AbstractMap map) {
        this.map = map;
        this.distances = new int[map.getHeight()][map.getWidth()];
    }
    
    public int distance(MapElement start, MapElement target) {
        this.start = start;
        this.target = target;
        initDistances();
        calculateDistances();
        for (int j = 0; j < distances.length; j++) {
            for (int i = 0; i < distances[j].length; i++) {
                if (map.get(i, j) == this.target) {
                    return distances[j][i];
                }
            }
        }
        return 0;
    }
    
    private void initDistances() {
        for (int j = 0; j < distances.length; j++) {
            for (int i = 0; i < distances[j].length; i++) {
                if (map.get(i, j) == start) {
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
                if (map.get(i, j) == start) {
                    calculateDistances(i, j);
                }
            }
        }
    }
    
    public void calculateDistances(int x, int y) {
        MapElement actual = map.get(x, y);
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
