package net.falsecam.labyrinth.model.map;

/**
 *
 * @author FalseCAM
 */
public class MapElement {

    MapElement top;
    MapElement bottom;
    MapElement left;
    MapElement right;

    public MapElement() {
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
}
