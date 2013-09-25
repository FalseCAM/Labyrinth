package net.falsecam.labyrinth.model;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import net.falsecam.labyrinth.Labyrinth;
import net.falsecam.labyrinth.model.map.AbstractMap;
import net.falsecam.labyrinth.model.map.Map;

/**
 *
 * @author Dev
 */
public class GameAppState extends AbstractAppState {

    private Labyrinth app;
    Node rootNode;
    AbstractMap abstractMap;
    Map map;
    GameCamera camera;
    public static final String mapFile = "Maps/Map.map.xml";

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (Labyrinth) app;
        this.rootNode = new Node();
        initMap();
        initCamera();
        this.app.getRootNode().attachChild(rootNode);
    }

    @Override
    public void update(float tpf) {
    }

    @Override
    public void cleanup() {
        super.cleanup();
        app.getRootNode().detachChild(this.rootNode);
    }

    private void initMap() {
        abstractMap = AbstractMap.loadMapFile(mapFile);
        map = new Map(abstractMap);
        rootNode.attachChild(map.getNode());
    }

    private void initCamera() {
        camera = new GameCamera(this.app.getCamera());
    }
}
