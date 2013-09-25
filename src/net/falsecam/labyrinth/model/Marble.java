package net.falsecam.labyrinth.model;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import net.falsecam.labyrinth.Game;

/**
 *
 * @author FalseCAM
 */
public class Marble {

    Node node;

    public Marble() {
        this.node = new Node("Marble");
        create();
        createPhysics();
    }

    public Node getNode() {
        return node;
    }

    private void create() {
        Sphere mesh = new Sphere(32, 32, 0.5f);
        Geometry geom = new Geometry("Marble", mesh);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        geom.setMaterial(mat);
        geom.setLocalTranslation(0, +5, 0);
        node.attachChild(geom);
    }

    private void createPhysics() {
        RigidBodyControl control = new RigidBodyControl(0.5f);
        node.addControl(control);
    }
}
