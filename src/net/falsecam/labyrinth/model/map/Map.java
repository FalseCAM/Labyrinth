/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.falsecam.labyrinth.model.map;

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
    private Ground ground;

    public Map(AbstractMap abstractMap) {
        this.abstractMap = abstractMap;
        node = new Node("Map");
        createGround();
        createWalls();
    }

    private void createWalls() {
        float x = -abstractMap.getWidth() * 2 + 2;
        float z = -abstractMap.getHeight() * 2 + 2;
        for (int i = 0; i < abstractMap.getWidth(); i++) {
            for (int j = 0; j < abstractMap.getHeight(); j++) {
                MapElement mapElement = abstractMap.get(i, j);
                MapObject element = new MapObject(mapElement);
                node.attachChild(element);
            }
        }
        node.updateModelBound();
        updateWalls();
    }

    private void createGround() {
        ground = new Ground(abstractMap.getWidth() * 2, abstractMap.getHeight() * 2);
        node.attachChild(ground);
    }
    
    public void updateWalls(){
        float x = -abstractMap.getWidth() * 2 + 2;
        float z = -abstractMap.getHeight() * 2 + 2;
        for (int i = 0; i < abstractMap.getWidth(); i++) {
            for (int j = 0; j < abstractMap.getHeight(); j++) {
                Vector3f position = new Vector3f(x + 4 * i, 0, z + 4 * j);
                MapElement mapElement = abstractMap.get(i, j);
                mapElement.getMapObject().getControl().setPhysicsLocation(position);
            }
        }
    }

    public void update(float tpf) {
    }

    public Node getNode() {
        return node;
    }
}
