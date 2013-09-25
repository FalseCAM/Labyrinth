/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.falsecam.labyrinth.model.map;

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
        create();
    }

    private void create() {
        float x = -abstractMap.getWidth() * 2;
        float z = -abstractMap.getHeight() * 2;
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

    public void update(float tpf) {
    }

    public Node getNode() {
        return node;
    }
}
