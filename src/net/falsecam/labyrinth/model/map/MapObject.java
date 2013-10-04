package net.falsecam.labyrinth.model.map;

import com.jme3.asset.TextureKey;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import net.falsecam.labyrinth.Game;

/**
 *
 * @author FalseCAM
 */
public class MapObject extends Node {

    private final MapType type;
    private final MapElement element;
    private RigidBodyControl physics;

    public MapObject(MapElement element) {
        this.element = element;

        this.type = element.getType();
        this.name = type.getName();
        create();
        physics = new RigidBodyControl(1f);
        this.addControl(physics);
        physics.setKinematic(true);
        physics.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_04);
        element.setMapObject(this);
    }

    private void create() {
        //createCorners();
        if (type.equals(MapType.TARGET)) {
            createTarget();
        }
        if (!type.isTopFree()) {
            createTop();
        }
        if (!type.isBottomFree()) {
            createBottom();
        }
        if (!type.isLeftFree()) {
            createLeft();
        }
        if (!type.isRightFree()) {
            createRight();
        }
    }
    private void createTop() {
        Box b = new Box(Vector3f.ZERO, 1f, 0.6f, 0.5f);
        Geometry geom = new Geometry("top", b);
        geom.setLocalTranslation(0, 0, -1.5f);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        geom.setMaterial(createMat());
        attachChild(geom);
    }

    private void createBottom() {
        Box b = new Box(Vector3f.ZERO, 1f, 0.6f, 0.5f);
        Geometry geom = new Geometry("bottom", b);
        geom.setLocalTranslation(0, 0, 1.5f);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        geom.setMaterial(createMat());
        attachChild(geom);
    }

    private void createLeft() {
        Box b = new Box(Vector3f.ZERO, 0.5f, 0.6f, 1f);
        Geometry geom = new Geometry("left", b);
        geom.setLocalTranslation(-1.5f, 0, 0);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        geom.setMaterial(createMat());
        attachChild(geom);
    }

    private void createRight() {
        Box b = new Box(Vector3f.ZERO, 0.5f, 0.6f, 1f);
        Geometry geom = new Geometry("right", b);
        geom.setLocalTranslation(1.5f, 0, 0);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Pink);
        geom.setMaterial(createMat());
        attachChild(geom);
    }

    private void createTarget() {
        Sphere s = new Sphere(16, 16, 1);
        Geometry geom = new Geometry("target", s);
        geom.setLocalTranslation(0, 1, 0);
        Material mat = new Material(Game.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        mat.setTexture("DiffuseMap",
                Game.getAssetManager().loadTexture("Textures/Map/Target.jpg"));
        mat.setFloat("Shininess", 5f);
        geom.setMaterial(mat);
        attachChild(geom);
    }

    private Material createMat() {
        Material mat = new Material(Game.getAssetManager(),
                "Common/MatDefs/Light/Lighting.j3md");
        TextureKey tKey = new TextureKey("Textures/Map/Wall.png");
        Texture tex = Game.getAssetManager().loadTexture(tKey);
        tex.setWrap(Texture.WrapMode.Repeat);
        mat.setTexture("DiffuseMap", tex);
        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Ambient", ColorRGBA.Orange);
        mat.setColor("Diffuse", ColorRGBA.Orange);
        mat.setColor("Specular", ColorRGBA.White);
        mat.setFloat("Shininess", 12);
        return mat;
    }

    public MapElement getMapElement() {
        return element;
    }

    public RigidBodyControl getPhysics() {
        return this.physics;
    }
}
