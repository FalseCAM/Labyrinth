package net.falsecam.labyrinth.model;

import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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
    
    public Marble() {
        this.node = new Node("Marble");
        create();
        createPhysics();
    }
    
    public Node getNode() {
        return node;
    }
    
    private void create() {
        Sphere mesh = new Sphere(8, 8, 0.3f);
        Geometry geom = new Geometry("Marble", mesh);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
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
}
