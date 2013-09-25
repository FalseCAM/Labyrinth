package net.falsecam.labyrinth.model.map;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author FalseCAM
 */
public class MapObject extends Node {

    MapType type;

    MapObject(MapType type) {
        this.type = type;
        this.name = type.getName();

        create();
    }

    private void create() {
        createGround();
        createCorners();
        if (type.isTopFree()) {
            createTop();
        }
        if (type.isBottomFree()) {
            createBottom();
        }
        if (type.isLeftFree()) {
            createLeft();
        }
        if (type.isRightFree()) {
            createRight();
        }
    }

    private void createGround() {
        Box b = new Box(Vector3f.ZERO, 2f, 1f, 2f);
        Geometry geom = new Geometry("ground", b);
        geom.setLocalTranslation(0, -2, 0);
        attachChild(geom);
    }

    private void createCorners() {
        Box b1 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom1 = new Geometry("corner1", b1);
        geom1.setLocalTranslation(-1.5f, 0, -1.5f);
        attachChild(geom1);

        Box b2 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom2 = new Geometry("corner2", b2);
        geom2.setLocalTranslation(+1.5f, 0, -1.5f);
        attachChild(geom2);

        Box b3 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom3 = new Geometry("corner3", b3);
        geom3.setLocalTranslation(+1.5f, 0, -1.5f);
        attachChild(geom3);

        Box b4 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom4 = new Geometry("corner4", b4);
        geom4.setLocalTranslation(+1.5f, 0, -1.5f);
        attachChild(geom4);
    }

    private void createTop() {
        Box b = new Box(Vector3f.ZERO, 1f, 0.5f, 0.5f);
        Geometry geom = new Geometry("top", b);
        geom.setLocalTranslation(0, 0, 1);
        attachChild(geom);
    }

    private void createBottom() {
        Box b = new Box(Vector3f.ZERO, 1f, 0.5f, 0.5f);
        Geometry geom = new Geometry("bottom", b);
        geom.setLocalTranslation(0, 0, -1);
        attachChild(geom);
    }

    private void createLeft() {
        Box b = new Box(Vector3f.ZERO, 0.5f, 0.5f, 1f);
        Geometry geom = new Geometry("left", b);
        geom.setLocalTranslation(-1, 0, 0);
        attachChild(geom);
    }

    private void createRight() {
        Box b = new Box(Vector3f.ZERO, 0.5f, 0.5f, 1f);
        Geometry geom = new Geometry("right", b);
        geom.setLocalTranslation(1, 0, 0);
        attachChild(geom);
    }
}
