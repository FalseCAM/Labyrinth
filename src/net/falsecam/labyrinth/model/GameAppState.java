package net.falsecam.labyrinth.model;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import net.falsecam.labyrinth.Labyrinth;
import net.falsecam.labyrinth.controller.GameController;
import net.falsecam.labyrinth.controller.InputController;
import net.falsecam.labyrinth.model.ai.AI;
import net.falsecam.labyrinth.model.map.AbstractMap;
import net.falsecam.labyrinth.model.map.Map;
import net.falsecam.labyrinth.model.map.MapElement;
import net.falsecam.labyrinth.model.map.MapType;

/**
 *
 * @author FalseCAM
 */
public class GameAppState extends AbstractAppState {

    private Labyrinth app;
    Node rootNode;
    BulletAppState bulletAppState;
    AI ai;
    AbstractMap abstractMap;
    Map map;
    Marble marble;
    GameCamera camera;
    InputController inputController;
    GameController gameController;
    public static final String mapFile = "Maps/Map.map.xml";

    public GameAppState(InputController inputController) {
        super();
        this.inputController = inputController;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (Labyrinth) app;
        this.rootNode = new Node();
        initMap();
        initMarble();
        initCamera();
        gameController = new GameController(this);
        inputController.initInput(app.getInputManager());
        inputController.setGameController(gameController);
        this.app.getRootNode().attachChild(rootNode);
        initPhysics();
    }

    @Override
    public void update(float tpf) {
        updateMarble();
    }

    @Override
    public void cleanup() {
        this.bulletAppState.cleanup();
        app.getStateManager().detach(this.bulletAppState);
        app.getRootNode().detachChild(this.rootNode);
    }

    private void initMap() {
        abstractMap = AbstractMap.loadMapFile(mapFile);
        map = new Map(abstractMap);
        rootNode.attachChild(map.getNode());

        System.out.println(abstractMap.isAcceptable());
    }

    private void initCamera() {
        camera = new GameCamera(this.app.getCamera());
    }

    private void initMarble() {
        this.marble = new Marble();
        rootNode.attachChild(marble.getNode());
        float x = -abstractMap.getWidth() * 2 + 2;
        float z = -abstractMap.getHeight() * 2 + 2;
        for (int i = 0; i < abstractMap.getWidth(); i++) {
            for (int j = 0; j < abstractMap.getHeight(); j++) {
                MapElement mapElement = abstractMap.get(i, j);
                if (mapElement.getType().equals(MapType.START)) {
                    Vector3f position = new Vector3f(x + 4 * i, 2, z + 4 * j);
                    marble.getPhysics().setPhysicsLocation(position);
                }
            }
        }
    }

    private void initPhysics() {
        bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        app.getStateManager().attach(bulletAppState);
        bulletAppState.getPhysicsSpace().addAll(rootNode);
        //bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0, -9.81f, 0));
        // Debug Physics
        //bulletAppState.getPhysicsSpace().enableDebug(app.getAssetManager());
    }

    public Marble getMarble() {
        return marble;
    }

    private void updateMarble() {

        int x = (int) (marble.getNode().getWorldTranslation().getX() / 4 + abstractMap.getWidth() / 2
                + (abstractMap.getWidth() % 2 == 1 ? 0.5 : 0));
        int z = (int) (marble.getNode().getWorldTranslation().getZ() / 4 + abstractMap.getHeight() / 2
                + (abstractMap.getHeight() % 2 == 1 ? 0.5 : 0));

        try {
            MapElement mapElement = abstractMap.get(x, z);
            abstractMap.setMarble(mapElement);
        } catch (Exception e) {
        }
    }
}
