/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import org.jbox2d.common.Vec2;

/**
 *
 * @author bernardot
 */
public class MouseListener extends MouseInputAdapter {

    private static int mouseX, mouseY;
    private static boolean mouseButtonPressed;
    private static SoccerPlayer player = null;

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseButtonPressed = false;
        //System.out.println("Move event: " + mouseX + "," + mouseY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseButtonPressed = true;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public static boolean isMouseButtonPressed() {
        return mouseButtonPressed;
    }

    public static Vec2 getWorldCoordinatesOfMousePointer() {
        return new Vec2(GameEngineUsingJBox2D.convertScreenXtoWorldX(mouseX), GameEngineUsingJBox2D.convertScreenYtoWorldY(mouseY));
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static void setMouseX(int mouseX) {
        MouseListener.mouseX = mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static void setMouseY(int mouseY) {
        MouseListener.mouseY = mouseY;
    }

    public static SoccerPlayer getPlayer() {
        return player;
    }

    public static void setPlayer(SoccerPlayer player) {
        MouseListener.player = player;
    }

    public static void setMouseButtonPressed(boolean mouseButtonPressed) {
        MouseListener.mouseButtonPressed = mouseButtonPressed;
    }
}
