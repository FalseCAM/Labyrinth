package net.falsecam.labyrinth.model.map;

/**
 *
 * @author FalseCAM
 */
public class MapElement {

    private final MapType type;
    MapElement top;
    MapElement bottom;
    MapElement left;
    MapElement right;
    private MapObject mapObject;

    public MapElement(MapType type) {
        this.type = type;
    }

    public MapType getType() {
        return type;
    }

    public void setTop(MapElement top) {
        this.top = top;
    }

    public MapElement getTop() {
        return top;
    }

    public void setBottom(MapElement bottom) {
        this.bottom = bottom;
    }

    public MapElement getBottom() {
        return bottom;
    }

    public void setLeft(MapElement left) {
        this.left = left;
    }

    public MapElement getLeft() {
        return left;
    }

    public void setRight(MapElement right) {
        this.right = right;
    }

    public MapElement getRight() {
        return right;
    }

    public void setMapObject(MapObject mapObject) {
        this.mapObject = mapObject;
    }

    public MapObject getMapObject() {
        return mapObject;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
