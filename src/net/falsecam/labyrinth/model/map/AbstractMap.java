/**
 *
 */
package net.falsecam.labyrinth.model.map;

import net.falsecam.labyrinth.Game;

/**
 * @author FalseCAM
 *
 */
public class AbstractMap {

    private MapElement[][] objects;
    private MapType change;
    private Integer width;
    private Integer height;

    public AbstractMap(MapElement[][] objects, MapType change) {
        this.objects = objects;
        this.change = change;
        this.width = objects.length;
        this.height = objects[0].length;
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

    public MapElement get(Integer x, Integer y) {
        return objects[x][height - y - 1];
    }

    public MapType getChange() {
        return change;
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
