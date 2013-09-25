/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.falsecam.labyrinth.model.map;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author FalseCAM
 */
public class Map {

    private final AbstractMap abstractMap;
    private Node node;

    public Map(AbstractMap abstractMap) {
        this.abstractMap = abstractMap;
        node = new Node("Map");
        createGround();
        createWalls();

        createPhysics();
    }

    private void createWalls() {
        float x = -abstractMap.getWidth() * 2+2;
        float z = -abstractMap.getHeight() * 2+2;
        for (int i = 0; i < abstractMap.getWidth(); i++) {
            for (int j = 0; j < abstractMap.getHeight(); j++) {
                MapType mapType = abstractMap.get(i, j);

                Node element = new MapObject(mapType);
                element.setLocalTranslation(x + 4 * i, 0, z + 4 * j);
                node.attachChild(element);
            }
        }
        node.updateModelBound();
    }

    private void createGround() {
        Ground ground = new Ground(abstractMap.getWidth() * 2, abstractMap.getHeight() * 2);
        node.attachChild(ground);

    }

    private void createPhysics() {
        BoxCollisionShape collision = new BoxCollisionShape(new Vector3f(abstractMap.getWidth() * 4, 1, abstractMap.getHeight() * 4));
        node.addControl(new RigidBodyControl(collision, 0.0f));
    }

    public void update(float tpf) {
    }

    public Node getNode() {
        return node;
    }
}
