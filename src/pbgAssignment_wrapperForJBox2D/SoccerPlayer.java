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
public class SoccerPlayer extends BasicCircle {

    private boolean click = false;
    private int maxPosX = 0, maxPosY = 0;
    private int velX, velY;
    private boolean turn = false;

    public SoccerPlayer(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction, String data, boolean collision) {
        super(sx, sy, vx, vy, radius, col, mass, rollingFriction, data, collision);
        body.m_fixtureList.m_friction = 0f;
        body.setFixedRotation(true);
    }

    @Override
    public void draw(Graphics2D g) {
        // Check if it is your turn, the mouse is pressed and the click was done inside this object.
        if (MouseListener.isMouseButtonPressed() && click && turn) {
            //System.out.println(MouseListener.isMouseButtonPressed());
            int x = GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().x);
            int y = GameEngineUsingJBox2D.convertWorldYtoScreenY(body.getPosition().y);
            int endx, endy;
            // Adjust endx and endy depending on the side of the screen they are based on the center of the screen.
            if (x > GameEngineUsingJBox2D.SCREEN_WIDTH / 2) {
                endx = GameEngineUsingJBox2D.SCREEN_WIDTH + 2 * Math.abs(GameEngineUsingJBox2D.SCREEN_WIDTH / 2 - GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().x)) - MouseListener.getMouseX();
            } else {
                endx = GameEngineUsingJBox2D.SCREEN_WIDTH - 2 * Math.abs(GameEngineUsingJBox2D.SCREEN_WIDTH / 2 - GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().x)) - MouseListener.getMouseX();
            }
            if (y > GameEngineUsingJBox2D.SCREEN_HEIGHT / 2) {
                endy = GameEngineUsingJBox2D.SCREEN_HEIGHT + 2 * Math.abs(GameEngineUsingJBox2D.SCREEN_HEIGHT / 2 - GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().y)) - MouseListener.getMouseY();
            } else {
                endy = GameEngineUsingJBox2D.SCREEN_HEIGHT - 2 * Math.abs(GameEngineUsingJBox2D.SCREEN_HEIGHT / 2 - GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().y)) - MouseListener.getMouseY();

            }
            
            // If the distance from the body is less than 200 pixels
            if ((int) Math.hypot(endx - x, endy - y) <= 200) {
                //System.out.println((maxPosX - x) + ":" + (maxPosY - y));
                maxPosX = endx;
                maxPosY = endy;
                velX = endx - x;
                velY = y - endy;
            } else {
                //System.out.println((int) Math.hypot(endx - x, endy - y) + "/" + (endx - x) + ":" + (endy - y));
                maxPosX = (int) ((endx - x) * 200 / Math.hypot(endx - x, endy - y)) + x;
                maxPosY = (int) ((endy - y) * 200 / Math.hypot(endx - x, endy - y)) + y;
                velX = endx - x;
                velY = y - endy;
                //System.out.println((maxPosX - x) + "::::" + (maxPosY - y));
            }
            //System.out.println((velX) + "::::" + (velY));
            //System.out.println((endx) + "---" + (endy));
            g.setColor(col);
            g.fillOval(x - SCREEN_RADIUS, y - SCREEN_RADIUS, 2 * SCREEN_RADIUS, 2 * SCREEN_RADIUS);
            g.drawLine(x, y, maxPosX, maxPosY);
        } else {
            super.draw(g);
        }
    }

    public boolean checkMouseEvent() {
        // If it is this player's turn and the mouse button is pressed and there is not selected player yet
        if (turn && MouseListener.isMouseButtonPressed() && (MouseListener.getPlayer() == null || MouseListener.getPlayer() == this)) {
            Vec2 mouseCoordinates = MouseListener.getWorldCoordinatesOfMousePointer();
            // Check if the click was made inside the dimensions of this object
            if (body.m_fixtureList.testPoint(mouseCoordinates)) {
                click = true;
                MouseListener.setPlayer(this);
            }
        } else {
            click = false;
            // Not my turn or the mouse button is not loger pressed.
            // If it was my turn and the player pressed was this object then set the velocity
            if (MouseListener.getPlayer() == this && Math.hypot(velX, velY) > SCREEN_RADIUS) {
                //System.out.println(Math.hypot(velX, velY) + " / " + SCREEN_RADIUS);
                MouseListener.setPlayer(null);
                Vec2 velocity = new Vec2(GameEngineUsingJBox2D.convertScreenXtoWorldX(velX), GameEngineUsingJBox2D.convertScreenXtoWorldX(velY));
                velocity.mulLocal(5);
                body.setLinearVelocity(velocity);
                return true;
            // This if lets you to deselect the player and select another one
            } else if (MouseListener.getPlayer() == this) {
                MouseListener.setPlayer(null);
            }
        }
        return false;
    }
    
    public void checkPlayerNotInGoal(float leftLimit, float rightLimit) {
        if (body.getPosition().x < leftLimit) {
            body.setTransform(new Vec2(leftLimit * 1.5f, body.getPosition().y), 0);
        } else if (body.getPosition().x > rightLimit) {
            body.setTransform(new Vec2(rightLimit * 11.5f / 13f, body.getPosition().y), 0);
        }
    }
    
    public void setToStartingPosition(Vec2 position) {
        body.setAngularVelocity(0);
        body.setLinearVelocity(new Vec2(0, 0));
        body.setTransform(position, 0);
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

}
