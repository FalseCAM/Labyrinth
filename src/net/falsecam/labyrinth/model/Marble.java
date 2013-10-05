package net.falsecam.labyrinth.model;

import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import net.falsecam.labyrinth.Game;

/**
 *
 * @author FalseCAM
 */
public class Marble {

    Node node;
    RigidBodyControl physics;
    private final float moveSpeed = 5f;
    private boolean jump = true;

    public Marble() {
        this.node = new Node("Marble");
        create();
        createPhysics();
    }

    public Node getNode() {
        return node;
    }

    private void create() {
        Sphere mesh = new Sphere(16, 16, 0.3f);
        Geometry geom = new Geometry("Marble", mesh);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        mat.setTexture("DiffuseMap",
                Game.getAssetManager().loadTexture("Textures/Marble.jpg"));
        mat.setTexture("NormalMap",
                Game.getAssetManager().loadTexture("Textures/Marble_normal.png"));
        mat.setFloat("Shininess", 5f);
        geom.setMaterial(mat);
        node.attachChild(geom);
    }

    private void createPhysics() {
        SphereCollisionShape sphereShape =
                new SphereCollisionShape(0.3f);
        physics = new RigidBodyControl(sphereShape, 1f);
        node.addControl(physics);
        physics.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
        physics.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_03);
        physics.addCollideWithGroup(PhysicsCollisionObject.COLLISION_GROUP_04);
    }

    public RigidBodyControl getPhysics() {
        return physics;
    }

    public void giveJump() {
        this.jump = true;
    }

    public void jump() {
        if (this.jump == true) {
            this.jump = false;
            if (physics.getPhysicsLocation().getY() < 1) {
                physics.setLinearVelocity(new Vector3f(0, moveSpeed, 0));
            }
        }

    }

    public void moveUp() {
        physics.setLinearVelocity(new Vector3f(0, 0, -moveSpeed));
    }

    public void moveDown() {
        physics.setLinearVelocity(new Vector3f(0, 0, moveSpeed));
    }

    public void moveLeft() {
        physics.setLinearVelocity(new Vector3f(-moveSpeed, 0, 0));
    }

    public void moveRight() {
        physics.setLinearVelocity(new Vector3f(moveSpeed, 0, 0));
    }
}
