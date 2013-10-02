package net.falsecam.labyrinth.model.map;

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
        MapObject changeElement = new MapObject(abstractMap.getChange());
        node.attachChild(changeElement);
        for (int i = 0; i < abstractMap.getWidth(); i++) {
            for (int j = 0; j < abstractMap.getHeight(); j++) {
                MapElement mapElement = abstractMap.get(i, j);
                MapObject element = new MapObject(mapElement);
                node.attachChild(element);
            }
        }
        updateWalls();
    }

    private void createGround() {
        ground = new Ground(abstractMap.getWidth(), abstractMap.getHeight());
        node.attachChild(ground);
    }

    public void updateWalls() {
        MapObject element = abstractMap.getChange().getMapObject();
        element.getPhysics().setPhysicsLocation(new Vector3f(0, -5, 0));
        float x = -abstractMap.getWidth() * 2 + 2;
        float z = -abstractMap.getHeight() * 2 + 2;
        for (int j = 0; j < abstractMap.getHeight(); j++) {
            for (int i = 0; i < abstractMap.getWidth(); i++) {
                Vector3f position = new Vector3f(x + 4 * i, 0, z + 4 * j);
                MapElement mapElement = abstractMap.get(i, j);
                mapElement.getMapObject().setLocalTranslation(position);
                //mapElement.getMapObject().getPhysics().setPhysicsLocation(position);
            }
        }
    }

    public AbstractMap getAbstractMap() {
        return abstractMap;
    }

    public void update(float tpf) {
    }

    public Node getNode() {
        return node;
    }
}
