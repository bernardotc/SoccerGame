/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author bernardot
 */
public class KeyListener extends KeyAdapter {
    private static boolean leftScreenChangeLineupButtonPressed;
    private static boolean rightScreenChangeLineupButtonPressed;
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_1:
                leftScreenChangeLineupButtonPressed = true;
                break;
            case KeyEvent.VK_0:
                rightScreenChangeLineupButtonPressed = true;
                break;
        }
    }

    public static boolean isLeftScreenChangeLineupButtonPressed() {
        return leftScreenChangeLineupButtonPressed;
    }

    public static boolean isRightScreenChangeLineupButtonPressed() {
        return rightScreenChangeLineupButtonPressed;
    }

    public static void setLeftScreenChangeLineupButtonPressed(boolean leftScreenChangeLineupButtonPressed) {
        KeyListener.leftScreenChangeLineupButtonPressed = leftScreenChangeLineupButtonPressed;
    }

    public static void setRightScreenChangeLineupButtonPressed(boolean rightScreenChangeLineupButtonPressed) {
        KeyListener.rightScreenChangeLineupButtonPressed = rightScreenChangeLineupButtonPressed;
    }
}
