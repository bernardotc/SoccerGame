/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author bernardot
 *
 * Based on BasicParticle by Michael Fairbank
 */
public class BasicCircle {

    public final int SCREEN_RADIUS;

    private final float mass;
    public final Color col;
    protected final Body body;

    public BasicCircle(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction) {
        this(sx, sy, vx, vy, radius, col, mass, rollingFriction, "");
    }
    
    public BasicCircle(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction, String data) {
        this(sx, sy, vx, vy, radius, col, mass, rollingFriction, "", false);
    }
    
    public BasicCircle(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction, String data, boolean collision) {
        World w = GameEngineUsingJBox2D.world; // a Box2D object
        BodyDef bodyDef = new BodyDef();  // a Box2D object
        bodyDef.type = BodyType.DYNAMIC; // this says the physics engine is to move it automatically
        bodyDef.position.set(sx, sy);
        bodyDef.linearVelocity.set(vx, vy);
        bodyDef.linearDamping = rollingFriction;
        bodyDef.userData = data;
        this.body = w.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();// This class is from Box2D
        circleShape.m_radius = radius;
        FixtureDef fixtureDef = new FixtureDef();// This class is from Box2D
        fixtureDef.shape = circleShape;
        fixtureDef.density = (float) (mass / (Math.PI * radius * radius));
        fixtureDef.friction = 0.0f;// this is surface friction;
        fixtureDef.restitution = 1.0f;
        body.createFixture(fixtureDef);
        body.m_fixtureList.m_isSensor = collision;
        this.mass = mass;
        this.SCREEN_RADIUS = (int) Math.max(GameEngineUsingJBox2D.convertWorldLengthToScreenLength(radius), 1);
        this.col = col;
    }

    public void draw(Graphics2D g) {
        int x = GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().x);
        int y = GameEngineUsingJBox2D.convertWorldYtoScreenY(body.getPosition().y);
        g.setColor(col);
        g.fillOval(x - SCREEN_RADIUS, y - SCREEN_RADIUS, 2 * SCREEN_RADIUS, 2 * SCREEN_RADIUS);
    }
    
    // Checks if the object is colliding with a wall. Then it moves it a little.
    public void modifyPositionAccordingToWall() {
        if (CollisionDetection.circleCollisioningWithBottomWall && CollisionDetection.circleCollidingBody == this.body) {
            body.setTransform(new Vec2(body.getPosition().x, body.getPosition().y+.01f), 0);
        } else if (CollisionDetection.circleCollisioningWithLeftWall && CollisionDetection.circleCollidingBody == this.body) {
            body.setTransform(new Vec2(body.getPosition().x + .02f, body.getPosition().y), 0);
        } else if (CollisionDetection.circleCollisioningWithRightWall && CollisionDetection.circleCollidingBody == this.body) {
            body.setTransform(new Vec2(body.getPosition().x - .02f, body.getPosition().y), 0);
        } else if (CollisionDetection.circleCollisioningWithTopWall && CollisionDetection.circleCollidingBody == this.body) {
            body.setTransform(new Vec2(body.getPosition().x, body.getPosition().y-.01f), 0);
        } 
    }
    
    public boolean notMoving() {
        return body.m_linearVelocity.length() <= 0.001f;
    }
}
