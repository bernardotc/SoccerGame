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
    public static final int SCREEN_WIDTH = 1024;
    public static final Dimension FRAME_SIZE = new Dimension(
            SCREEN_WIDTH, SCREEN_HEIGHT);
    public static final float WORLD_WIDTH = 10;//metres
    public static final float WORLD_HEIGHT = SCREEN_HEIGHT * (WORLD_WIDTH / SCREEN_WIDTH);// meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
    public static final float GRAVITY = 0f;
    public static final boolean ALLOW_MOUSE_POINTER_TO_DRAG_BODIES_ON_SCREEN = false;// There's a load of code in basic mouse listener to process this, if you set it to true

    public static World world; // Box2D container for all bodies and barriers 

    // sleep time between two drawn frames in milliseconds 
    public static final int DELAY = 20;
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

    public static enum LayoutMode {
        SOCCER_FIELD, RECTANGLE
    };

    public List<AnchoredBarrier> barriers;
    public SoccerTeam left, right;
    public SoccerBall ball;

    public GameEngineUsingJBox2D() {
        world = new World(new Vec2(0, (float) -GRAVITY)); // create Box2D container for everything
        world.setContinuousPhysics(true);
        LayoutMode layout = LayoutMode.SOCCER_FIELD;
        
        left = new SoccerTeam(true, Lineup.lineups.BALANCED, Color.BLUE);
        right = new SoccerTeam(false, Lineup.lineups.BALANCED, Color.RED);
        ball = new SoccerBall(WORLD_WIDTH / 2, WORLD_HEIGHT * 6 / 16, 0, 0, .1f, Color.WHITE, 1, 2);

        barriers = new ArrayList<AnchoredBarrier>();

        switch (layout) {
            case RECTANGLE: {
                // rectangle walls:
                // anticlockwise listing
                // These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
                barriers.add(new AnchoredBarrier_StraightLine(0, 0, WORLD_WIDTH, 0, Color.WHITE, "bottom"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE, "right"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE, "top"));
                barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE, "left"));
                break;
            }
            case SOCCER_FIELD: {
                // anticlockwise listing
                // These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH / 16, WORLD_HEIGHT / 16, WORLD_WIDTH * 15 / 16, WORLD_HEIGHT / 16, Color.WHITE));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 15 / 16, WORLD_HEIGHT / 16, WORLD_WIDTH * 15 / 16, WORLD_HEIGHT * 4 / 16, Color.WHITE));

                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 15 / 16, WORLD_HEIGHT * 4 / 16, WORLD_WIDTH * 63 / 64, WORLD_HEIGHT * 4 / 16, Color.WHITE));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 63 / 64, WORLD_HEIGHT * 4 / 16, WORLD_WIDTH * 63 / 64, WORLD_HEIGHT * 8 / 16, Color.WHITE));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 63 / 64, WORLD_HEIGHT * 8 / 16, WORLD_WIDTH * 15 / 16, WORLD_HEIGHT * 8 / 16, Color.WHITE));

                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 15 / 16, WORLD_HEIGHT * 8 / 16, WORLD_WIDTH * 15 / 16, WORLD_HEIGHT * 11 / 16, Color.WHITE));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 15 / 16, WORLD_HEIGHT * 11 / 16, WORLD_WIDTH / 16, WORLD_HEIGHT * 11 / 16, Color.WHITE));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH / 16, WORLD_HEIGHT * 11 / 16, WORLD_WIDTH / 16, WORLD_HEIGHT * 8 / 16, Color.WHITE));

                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH / 16, WORLD_HEIGHT * 8 / 16, WORLD_WIDTH / 64, WORLD_HEIGHT * 8 / 16, Color.WHITE));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH / 64, WORLD_HEIGHT * 4 / 16, WORLD_WIDTH / 64, WORLD_HEIGHT * 8 / 16, Color.WHITE));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH / 64, WORLD_HEIGHT * 4 / 16, WORLD_WIDTH / 16, WORLD_HEIGHT * 4 / 16, Color.WHITE));

                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH / 16, WORLD_HEIGHT * 4 / 16, WORLD_WIDTH / 16, WORLD_HEIGHT / 16, Color.WHITE));
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final GameEngineUsingJBox2D game = new GameEngineUsingJBox2D();
        final GameView view = new GameView(game);
        JEasyFrame frame = new JEasyFrame(view, "Basic Physics Engine");
        //frame.addKeyListener(new BasicKeyListener());
        //view.addMouseMotionListener(new BasicMouseListener());
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
        for (SoccerPlayer p:left.getPlayers()) {
            p.notificationOfNewTimestep();
        }
        for (SoccerPlayer p:right.getPlayers()) {
            p.notificationOfNewTimestep();
        }
        ball.notificationOfNewTimestep();
        world.step(DELTA_T, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }
}