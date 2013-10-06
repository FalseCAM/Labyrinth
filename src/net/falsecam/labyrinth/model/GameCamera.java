package net.falsecam.labyrinth.model;

import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;

/**
 *
 * @author FalseCAM
 */
public class GameCamera {
    
    private InputManager inputManager;
    private Camera camera;
    ChaseCamera chaseCam;
    
    public GameCamera(Camera cam) {
        this.camera = cam;
        initCamera();
    }
    
    public GameCamera(Camera cam, InputManager inputManager, Spatial spatial) {
        this.inputManager = inputManager;
        this.camera = cam;
        chaseCam = new ChaseCamera(camera, spatial, inputManager);
        initCamera();
        initChaseCam();
    }
    
    private void initChaseCam() {
        chaseCam.setDefaultDistance(30);
        chaseCam.setLookAtOffset(new Vector3f(0, 5, 0));
        chaseCam.setDragToRotate(false);
        chaseCam.setDefaultHorizontalRotation((float) (Math.PI / 2.0));
        chaseCam.setDefaultVerticalRotation((float) (Math.PI / 4.5));
        chaseCam.setSmoothMotion(true);
        chaseCam.setRotationSpeed(0f); // disable mouse rotation
        chaseCam.setEnabled(true);
    }
    
    private void initCamera() {
        camera.setLocation(new Vector3f(0, 40, 50));
        camera.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
    }
    
    public void up() {
        camera.setLocation(camera.getLocation().add(new Vector3f(0, 0, -1)));
    }
    
    public void down() {
        camera.setLocation(camera.getLocation().add(new Vector3f(0, 0, +1)));
    }
    
    public void left() {
        camera.setLocation(camera.getLocation().add(new Vector3f(-1, 0, 0)));
    }
    
    public void right() {
        camera.setLocation(camera.getLocation().add(new Vector3f(+1, 0, 0)));
    }
    
    public void in() {
        camera.setLocation(camera.getLocation().add(new Vector3f(0, +1, 0)));
    }
    
    public void out() {
        camera.setLocation(camera.getLocation().add(new Vector3f(0, -1, 0)));
    }
}
