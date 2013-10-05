package net.falsecam.labyrinth.model;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
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
    DirectionalLight sun = new DirectionalLight();
    AmbientLight al = new AmbientLight();
    AI ai;
    AbstractMap abstractMap;
    Map map;
    Marble marble;
    GameCamera camera;
    InputController inputController;
    GameController gameController;
    public static final String mapFile = "Maps/Map.map.xml";
    float timer = 0;
    private boolean start = false;

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
        initLights();
        ai = new AI(abstractMap);
        gameController = new GameController(this);
        inputController.initInput(app.getInputManager());
        inputController.setGameController(gameController);
        this.app.getRootNode().attachChild(rootNode);
        initPhysics();
    }

    @Override
    public void update(float tpf) {
        updateMarble(tpf);

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

    private void initLights() {
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f, -.5f, -.5f).normalizeLocal());
        rootNode.addLight(sun);
        al.setColor(ColorRGBA.White.mult(0.4f));
        rootNode.addLight(al);
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
        bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0, -8f, 0));
        // Debug Physics
        //bulletAppState.getPhysicsSpace().enableDebug(app.getAssetManager());
    }

    public Marble getMarble() {
        return marble;
    }

    private void updateMarble(float tpf) {

        int x = (int) (marble.getNode().getWorldTranslation().getX() / 4 + abstractMap.getWidth() / 2
                + (abstractMap.getWidth() % 2 == 1 ? 0.5 : 0));
        int z = (int) (marble.getNode().getWorldTranslation().getZ() / 4 + abstractMap.getHeight() / 2
                + (abstractMap.getHeight() % 2 == 1 ? 0.5 : 0));
        if (abstractMap.get(x, z) == abstractMap.getTarget()) {
            showWin();
        } else if (abstractMap.get(x, z).getType().equals(MapType.STAR)) {
            marble.giveJump();
        }
        this.timer += tpf;
        if (timer > 5 && start) {
            timer = 0;
            ai.doWork(x, z);
            map.updateWalls();
            try {
                MapElement mapElement = abstractMap.get(x, z);
                abstractMap.setMarble(mapElement);
            } catch (Exception e) {
            }
        } else {
            if (this.timer > 5) {
                this.start = true;
            }
        }
    }

    private void showWin() {
        app.getGuiNode().detachAllChildren();
        BitmapText hudText = new BitmapText(app.getGuiFont(), false);
        hudText.setSize(app.getGuiFont().getCharSet().getRenderedSize());
        hudText.setColor(ColorRGBA.White);
        hudText.setText("W I N !!!");
        hudText.setLocalTranslation(app.getContext().getSettings().getWidth() / 2, hudText.getLineHeight(), 0);
        app.getGuiNode().attachChild(hudText);
    }
}
