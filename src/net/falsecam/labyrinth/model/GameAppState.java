package net.falsecam.labyrinth.model;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;
import net.falsecam.labyrinth.Labyrinth;
import net.falsecam.labyrinth.controller.GameController;
import net.falsecam.labyrinth.controller.InputController;
import net.falsecam.labyrinth.model.map.AbstractMap;
import net.falsecam.labyrinth.model.map.Map;

/**
 *
 * @author FalseCAM
 */
public class GameAppState extends AbstractAppState {

    private Labyrinth app;
    Node rootNode;
    BulletAppState bulletAppState;
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
    }

    private void initCamera() {
        camera = new GameCamera(this.app.getCamera());
    }

    private void initMarble() {
        this.marble = new Marble();
        rootNode.attachChild(marble.getNode());
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
}
