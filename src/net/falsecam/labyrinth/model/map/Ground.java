/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.falsecam.labyrinth.model.map;

import com.jme3.asset.TextureKey;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import net.falsecam.labyrinth.Game;

/**
 *
 * @author FalseCAM
 */
public class Ground extends Node {

    private final int width;
    private final int height;

    public Ground(int width, int height) {
        this.width = width;
        this.height = height;
        createGround();
        createCorners();
        RigidBodyControl physics = new RigidBodyControl(0.0f);
        physics.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_03);
        addControl(physics);
        physics.setKinematic(false);
    }

    private void createGround() {
        Box b = new Box(Vector3f.ZERO, width * 2, 0.5f, height * 2);
        Geometry geom = new Geometry("ground", b);
        geom.setLocalTranslation(0, -1f, 0);

        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Gray);
        TextureKey tKey = new TextureKey("Textures/Map/Floor.png");
        tKey.setGenerateMips(true);
        Texture tex = Game.getAssetManager().loadTexture(tKey);
        tex.setWrap(Texture.WrapMode.Repeat);
        mat.setTexture("ColorMap", tex);
        geom.setMaterial(mat);
        attachChild(geom);
    }

    private void createCorners() {
        float x = -width * 2 + 2;
        float z = -height * 2 + 2;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Vector3f position = new Vector3f(x + 4 * i, 0, z + 4 * j);
                Node corners = createCornersNode();
                attachChild(corners);
                corners.setLocalTranslation(position);

            }
        }
    }

    private Node createCornersNode() {
        Node node = new Node("corners");
        Box b1 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom1 = new Geometry("corner1", b1);
        geom1.setLocalTranslation(-1.5f, 0, -1.5f);
        Material mat1 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Red);
        geom1.setMaterial(mat1);
        node.attachChild(geom1);

        Box b2 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom2 = new Geometry("corner2", b2);
        geom2.setLocalTranslation(+1.5f, 0, -1.5f);
        Material mat2 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Red);
        geom2.setMaterial(mat2);
        node.attachChild(geom2);

        Box b3 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom3 = new Geometry("corner3", b3);
        geom3.setLocalTranslation(-1.5f, 0, +1.5f);
        Material mat3 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Red);
        geom3.setMaterial(mat3);
        node.attachChild(geom3);

        Box b4 = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f);
        Geometry geom4 = new Geometry("corner4", b4);
        geom4.setLocalTranslation(+1.5f, 0, +1.5f);
        Material mat4 = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat4.setColor("Color", ColorRGBA.Red);
        geom4.setMaterial(mat4);
        node.attachChild(geom4);
        return node;
    }
}
