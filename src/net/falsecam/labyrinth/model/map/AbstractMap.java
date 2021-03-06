/**
 *
 */
package net.falsecam.labyrinth.model.map;

import java.util.LinkedList;
import java.util.List;
import net.falsecam.labyrinth.Game;

/**
 * @author FalseCAM
 *
 */
public class AbstractMap {

    private MapElement[][] objects;
    private MapElement change;
    private MapElement target;
    private MapElement marble;
    private Integer width;
    private Integer height;

    public AbstractMap(MapElement[][] objects, MapElement change) {
        this.objects = objects;
        this.change = change;
        this.width = objects[0].length;
        this.height = objects.length;
        updateObjects();

    }

    private void updateObjects() {
        change.bottom = null;
        change.left = null;
        change.right = null;
        change.top = null;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (objects[j][i].getType().equals(MapType.TARGET)) {
                    target = objects[j][i];
                }
                if (objects[j][i].getType().equals(MapType.START)) {
                    marble = objects[j][i];
                }

                if (i > 0) {
                    objects[j][i].setLeft(objects[j][i - 1]);
                } else {
                    objects[j][i].setLeft(null);
                }
                if (j > 0) {
                    objects[j][i].setTop(objects[j - 1][i]);
                } else {
                    objects[j][i].setTop(null);
                }
                if (i < width - 1) {
                    objects[j][i].setRight(objects[j][i + 1]);
                } else {
                    objects[j][i].setRight(null);
                }
                if (j < height - 1) {
                    objects[j][i].setBottom(objects[j + 1][i]);
                } else {
                    objects[j][i].setBottom(null);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Change: ");
        builder.append(change.toString());
        builder.append("\n");
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                builder.append(objects[j][i].toString());
            }
            if (j < height - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public MapElement get(int x, int y) {
        try {
            return objects[y][x];
        } catch (Exception e) {
            return null;
        }
    }

    public MapElement getChange() {
        return change;
    }

    public MapElement getTarget() {
        return target;
    }

    public void setMarble(MapElement marble) {
        this.marble = marble;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public static AbstractMap loadMapFile(String file) {
        Game.getAssetManager().registerLoader(MapLoader.class, "map.xml");
        return (AbstractMap) Game.getAssetManager().loadAsset(file);
    }

    public boolean isAcceptable() {
        return isAcceptable(marble, null, new LinkedList<MapElement>());
    }

    public boolean isAcceptable(MapElement actual, MapElement before, List<MapElement> beforeList) {
        if (actual == null) {
            return false;
        }
        if (beforeList.contains(actual)) {
            return false;
        } else {
            beforeList.add(actual);
        }
        if (actual.getType().equals(MapType.TARGET)) {
            return true;
        }
        if (actual.getBottom() != null && before != actual.getBottom() && actual.isBottomFree() && actual.getBottom().isTopFree()
                && isAcceptable(actual.getBottom(), actual, beforeList)) {
            return true;
        }
        if (actual.getLeft() != null && before != actual.getLeft() && actual.isLeftFree() && actual.getLeft().isRightFree()
                && isAcceptable(actual.getLeft(), actual, beforeList)) {
            return true;
        }
        if (actual.getRight() != null && before != actual.getRight() && actual.isRightFree() && actual.getRight().isLeftFree()
                && isAcceptable(actual.getRight(), actual, beforeList)) {
            return true;
        }
        if (actual.getTop() != null && before != actual.getTop() && actual.isTopFree() && actual.getTop().isBottomFree()
                && isAcceptable(actual.getTop(), actual, beforeList)) {
            return true;
        }
        return false;
    }

    public MapElement[][] getElements() {
        return objects;
    }

    public void changeToRight(int pos) {
        MapElement temp = change;
        temp.right = objects[pos][0];
        change = objects[pos][width - 1];
        change.left = null;
        change.bottom = null;
        change.right = null;
        change.top = null;
        for (int i = width - 1; i > 0; i--) {
            objects[pos][i] = objects[pos][i - 1];
        }
        objects[pos][0] = temp;
        updateObjects();
    }

    public void changeToLeft(int pos) {
        MapElement temp = change;
        temp.left = objects[pos][width - 1];
        change = objects[pos][0];
        change.left = null;
        change.bottom = null;
        change.right = null;
        change.top = null;
        for (int i = 0; i < width - 1; i++) {
            objects[pos][i] = objects[pos][i + 1];
        }
        objects[pos][width - 1] = temp;
        updateObjects();
    }

    public void changeToTop(int pos) {
        MapElement temp = change;
        temp.top = objects[height - 1][pos];
        change = objects[0][pos];
        change.left = null;
        change.bottom = null;
        change.right = null;
        change.top = null;
        for (int i = 0; i < height - 1; i++) {
            objects[i][pos] = objects[i + 1][pos];
        }
        objects[height - 1][pos] = temp;
        updateObjects();
    }

    public void changeToBottom(int pos) {
        MapElement temp = change;
        temp.bottom = objects[0][pos];
        change = objects[height - 1][pos];
        change.left = null;
        change.bottom = null;
        change.right = null;
        change.top = null;
        for (int i = height - 1; i > 0; i--) {
            objects[i][pos] = objects[i - 1][pos];
        }
        objects[0][pos] = temp;
        updateObjects();
    }
}
