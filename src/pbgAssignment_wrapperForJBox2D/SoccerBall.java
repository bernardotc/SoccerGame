/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;

/**
 *
 * @author bernardot
 */
public class SoccerBall extends BasicCircle {
    
    private float startingX, startingY;
    
    public SoccerBall(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction, String data, int groupIndex) {
        super(sx, sy, vx, vy, radius, col, mass, rollingFriction, data, groupIndex);
        startingX = sx;
        startingY = sy;
        body.setLinearDamping(rollingFriction);
        body.m_fixtureList.m_friction = 0f;
        body.setFixedRotation(true);
    }
    
    // Returns true if the ball passed the left goal, false if it was the right one. Null otherwise.
    public Boolean checkIfGoal(float leftGoal, float rightGoal) {
        if (body.getPosition().x < leftGoal)
            return true;
        else if (body.getPosition().x > rightGoal)
            return false;
        return null;
    }
    
    public void setToStartingPosition() {
        body.setAngularVelocity(0);
        body.setLinearVelocity(new Vec2(0, 0));
        body.setLinearDamping(2);
        body.setTransform(new Vec2(startingX, startingY), 0);
    }
    
    @Override
    public void draw(Graphics2D g) {
        int x = GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().x);
        int y = GameEngineUsingJBox2D.convertWorldYtoScreenY(body.getPosition().y);
        if (CollisionDetection.circleCollisioningWithBottomWall || CollisionDetection.circleCollisioningWithLeftWall || CollisionDetection.circleCollisioningWithRightWall || CollisionDetection.circleCollisioningWithTopWall) {
                    g.setColor(Color.YELLOW);
        } else {
        g.setColor(col);
        }
        g.fillOval(x - SCREEN_RADIUS, y - SCREEN_RADIUS, 2 * SCREEN_RADIUS, 2 * SCREEN_RADIUS);
    }
}
