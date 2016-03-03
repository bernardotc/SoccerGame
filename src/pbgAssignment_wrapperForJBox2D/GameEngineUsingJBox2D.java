/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.World;

/**
 *
 * @author bernardot
 *
 * Based on BasicPhysicsEngineUsingBox2D by Michael Fairbank
 */
public class GameEngineUsingJBox2D {

    // frame dimensions
    public static final int SCREEN_HEIGHT = 768;
    public static final int SCREEN_WIDTH = 1360;
    public static final Dimension FRAME_SIZE = new Dimension(
            SCREEN_WIDTH, SCREEN_HEIGHT);
    public static final float WORLD_WIDTH = 10;//metres
    public static final float WORLD_HEIGHT = SCREEN_HEIGHT * (WORLD_WIDTH / SCREEN_WIDTH);// meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
    public static final float GRAVITY = 0f;
    public static final boolean ALLOW_MOUSE_POINTER_TO_DRAG_BODIES_ON_SCREEN = false;// There's a load of code in basic mouse listener to process this, if you set it to true

    public static World world; // Box2D container for all bodies and barriers 

    // sleep time between two drawn frames in milliseconds 
    public static final int DELAY = 10;
    public static final int NUM_EULER_UPDATES_PER_SCREEN_REFRESH = 10;
    // estimate for time between two frames in seconds 
    public static final float DELTA_T = DELAY / 1000.0f;

    public static int convertWorldXtoScreenX(float worldX) {
        return (int) (worldX / WORLD_WIDTH * SCREEN_WIDTH);
    }

    public static int convertWorldYtoScreenY(float worldY) {
        // minus sign in here is because screen coordinates are upside down.
        return (int) (SCREEN_HEIGHT - (worldY / WORLD_HEIGHT * SCREEN_HEIGHT));
    }

    public static float convertWorldLengthToScreenLength(float worldLength) {
        return (worldLength / WORLD_WIDTH * SCREEN_WIDTH);
    }

    public static float convertScreenXtoWorldX(int screenX) {
        return screenX * WORLD_WIDTH / SCREEN_WIDTH;
    }

    public static float convertScreenYtoWorldY(int screenY) {
        return (SCREEN_HEIGHT - screenY) * WORLD_HEIGHT / SCREEN_HEIGHT;
    }
    
    public SoccerGame soccer;

    public GameEngineUsingJBox2D() {
        world = new World(new Vec2(0, (float) -GRAVITY)); // create Box2D container for everything
        world.setContinuousPhysics(true);
        CollisionDetection listener = new CollisionDetection();
        world.setContactListener(listener);
        
        soccer = new SoccerGame();
    }

    public static void main(String[] args) throws Exception {
        final GameEngineUsingJBox2D game = new GameEngineUsingJBox2D();
        final GameView view = new GameView(game);
        JEasyFrame frame = new JEasyFrame(view, "Soccer Game");
        frame.addKeyListener(new KeyListener());
        view.addMouseMotionListener(new MouseListener());
        game.startThread(view);
    }

    private void startThread(final GameView view) throws InterruptedException {
        final GameEngineUsingJBox2D game = this;
        while (true) {
            game.update();
            view.repaint();

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
            }
        }
    }

    public void update() {
        int VELOCITY_ITERATIONS = NUM_EULER_UPDATES_PER_SCREEN_REFRESH;
        int POSITION_ITERATIONS = NUM_EULER_UPDATES_PER_SCREEN_REFRESH;
        soccer.update();
        world.step(DELTA_T, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }
}
