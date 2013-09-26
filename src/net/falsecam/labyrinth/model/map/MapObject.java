package net.falsecam.labyrinth.model.map;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import net.falsecam.labyrinth.Game;

/**
 *
 * @author FalseCAM
 */
public class MapObject extends Node {

    private final MapType type;
    private final MapElement element;
    private RigidBodyControl control;

    public MapObject(MapElement element) {
        this.element = element;

        this.type = element.getType();
        this.name = type.getName();
        create();
        control = new RigidBodyControl(0);
        this.addControl(control);
        element.setMapObject(this);
    }

    private void create() {
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

    private void createCorners() {
        Box b1 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom1 = new Geometry("corner1", b1);
        geom1.setLocalTranslation(-1.5f, 0, -1.5f);
        Material mat1 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Red);
        geom1.setMaterial(mat1);
        attachChild(geom1);

        Box b2 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom2 = new Geometry("corner2", b2);
        geom2.setLocalTranslation(+1.5f, 0, -1.5f);
        Material mat2 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Red);
        geom2.setMaterial(mat2);
        attachChild(geom2);

        Box b3 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom3 = new Geometry("corner3", b3);
        geom3.setLocalTranslation(-1.5f, 0, +1.5f);
        Material mat3 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Red);
        geom3.setMaterial(mat3);
        attachChild(geom3);

        Box b4 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom4 = new Geometry("corner4", b4);
        geom4.setLocalTranslation(+1.5f, 0, +1.5f);
        Material mat4 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat4.setColor("Color", ColorRGBA.Red);
        geom4.setMaterial(mat4);
        attachChild(geom4);
    }

    private void createTop() {
        Box b = new Box(Vector3f.ZERO, 1f, 0.5f, 0.5f);
        Geometry geom = new Geometry("top", b);
        geom.setLocalTranslation(0, 0, 1.5f);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        geom.setMaterial(mat);
        attachChild(geom);
    }

    private void createBottom() {
        Box b = new Box(Vector3f.ZERO, 1f, 0.5f, 0.5f);
        Geometry geom = new Geometry("bottom", b);
        geom.setLocalTranslation(0, 0, -1.5f);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        attachChild(geom);
    }

    private void createLeft() {
        Box b = new Box(Vector3f.ZERO, 0.5f, 0.5f, 1f);
        Geometry geom = new Geometry("left", b);
        geom.setLocalTranslation(-1.5f, 0, 0);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        geom.setMaterial(mat);
        attachChild(geom);
    }

    private void createRight() {
        Box b = new Box(Vector3f.ZERO, 0.5f, 0.5f, 1f);
        Geometry geom = new Geometry("right", b);
        geom.setLocalTranslation(1.5f, 0, 0);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Pink);
        geom.setMaterial(mat);
        attachChild(geom);
    }

    public MapElement getMapElement() {
        return element;
    }
    
    public RigidBodyControl getControl(){
        return this.control;
    }
}
