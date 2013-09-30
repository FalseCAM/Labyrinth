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

    public boolean isTopFree() {
        return type.isTopFree();
    }

    public boolean isBottomFree() {
        return type.isBottomFree();
    }

    public boolean isLeftFree() {
        return type.isLeftFree();
    }

    public boolean isRightFree() {
        return type.isRightFree();
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public void moveRight() {
        if (right != null) {
            top = right.getTop();
            if (top != null) {
                top.bottom = this;
            }
            bottom = right.getBottom();
            if (bottom != null) {
                bottom.top = this;
            }
            right.left = this;
        } else {
            if (left != null) {
                left.right = null;
            }
            top = null;
            bottom = null;
            left = null;
            right = null;
        }
    }

    public void moveLeft() {
        if (left != null) {
            top = left.getTop();
            if (top != null) {
                top.bottom = this;
            }
            bottom = left.getBottom();
            if (bottom != null) {
                bottom.top = this;
            }
            left.right = this;
        } else {
            if (right != null) {
                right.left = null;
            }
            top = null;
            bottom = null;
            left = null;
            right = null;
        }
    }

    public void moveUp() {
        if (top != null) {
            left = top.getLeft();
            if (left != null) {
                left.right = this;
            }
            right = top.getRight();
            if (right != null) {
                right.left = this;
            }
            top.bottom = this;
        } else {
            if (bottom != null) {
                bottom.top = null;
            }
            top = null;
            bottom = null;
            left = null;
            right = null;
        }
    }

    public void moveDown() {
        if (bottom != null) {
            left = bottom.getLeft();
            if (left != null) {
                left.right = this;
            }
            right = bottom.getRight();
            if (right != null) {
                right.left = this;
            }
            bottom.top = this;
        } else {
            if (top != null) {
                top.bottom = null;
            }
            top = null;
            bottom = null;
            left = null;
            right = null;
        }
    }
}
