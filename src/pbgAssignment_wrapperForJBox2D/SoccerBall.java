/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import org.jbox2d.common.Vec2;

/**
 *
 * @author bernardot
 */
public class SoccerBall extends BasicCircle {
    
    private float startingX, startingY;
    
    public SoccerBall(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction, String data) {
        super(sx, sy, vx, vy, radius, col, mass, rollingFriction, data);
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
        body.setTransform(new Vec2(startingX, startingY), 0);
    }
}
