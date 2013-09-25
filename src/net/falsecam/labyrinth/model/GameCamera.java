package net.falsecam.labyrinth.model;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author FalseCAM
 */
public class GameCamera {

    private Camera camera;

    public GameCamera(Camera cam) {
        this.camera = cam;
        initCamera();
    }

    private void initCamera() {
        camera.setLocation(new Vector3f(0, 35, 40));
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
