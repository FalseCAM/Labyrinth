/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.falsecam.labyrinth.model.map;

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
public class Ground extends Node {

    public Ground(int width, int height) {
        Box b = new Box(Vector3f.ZERO, width, 1f, height);
        Geometry geom = new Geometry("ground", b);
        geom.setLocalTranslation(0, -1.5f, 0);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Gray);
        geom.setMaterial(mat);
        attachChild(geom);
    }
}
