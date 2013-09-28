/**
 *
 */
package net.falsecam.labyrinth.model.map;

import com.jme3.math.Vector3f;
import net.falsecam.labyrinth.Game;

/**
 * @author FalseCAM
 *
 */
public class AbstractMap {

    private MapElement[][] objects;
    private MapElement change;
    private MapElement target;
    private Integer width;
    private Integer height;

    public AbstractMap(MapElement[][] objects, MapElement change) {
        this.objects = objects;
        this.change = change;
        this.width = objects[0].length;
        this.height = objects.length;
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                if (objects[i][j].getType().equals(MapType.TARGET)) {
                    target = objects[i][j];
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
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
        return objects[y][x];
    }

    public MapElement getChange() {
        return change;
    }

    public MapElement getTarget() {
        return target;
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
}
