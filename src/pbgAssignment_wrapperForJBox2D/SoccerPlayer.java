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

    public SoccerPlayer(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction) {
        super(sx, sy, vx, vy, radius, col, mass, rollingFriction);
    }

    @Override
    public void draw(Graphics2D g) {
        if (MouseListener.isMouseButtonPressed() && click) {
            //System.out.println(MouseListener.isMouseButtonPressed());
            int x = GameEngineUsingJBox2D.convertWorldXtoScreenX(body.getPosition().x);
            int y = GameEngineUsingJBox2D.convertWorldYtoScreenY(body.getPosition().y);
            int endx, endy;
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
            if ((int) Math.hypot(endx - x, endy - y) <= 300) {
                //System.out.println((maxPosX - x) + ":" + (maxPosY - y));
                maxPosX = endx;
                maxPosY = endy;
                velX = endx - x;
                velY = y - endy;
            } else {
                //System.out.println((int) Math.hypot(endx - x, endy - y) + "/" + (endx - x) + ":" + (endy - y));
                maxPosX = (int) ((endx - x) * 300 / Math.hypot(endx - x, endy - y)) + x;
                maxPosY = (int) ((endy - y) * 300 / Math.hypot(endx - x, endy - y)) + y;
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

    public void checkMouseEvent() {
        if (MouseListener.isMouseButtonPressed() && (MouseListener.getPlayer() == null || MouseListener.getPlayer() == this)) {
            Vec2 mouseCoordinates = MouseListener.getWorldCoordinatesOfMousePointer();
            if (body.m_fixtureList.testPoint(mouseCoordinates)) {
                click = true;
                MouseListener.setPlayer(this);
            }
        } else {
            if (MouseListener.getPlayer() == this) {
                MouseListener.setPlayer(null);
                body.setLinearVelocity(new Vec2(velX / 25, velY / 25));
            }
            click = false;
        }
    }

}
