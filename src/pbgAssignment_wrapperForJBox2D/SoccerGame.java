/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Graphics2D;
import static pbgAssignment_wrapperForJBox2D.GameEngineUsingJBox2D.WORLD_HEIGHT;
import static pbgAssignment_wrapperForJBox2D.GameEngineUsingJBox2D.WORLD_WIDTH;

/**
 *
 * @author bernardot
 */
public class SoccerGame {

    private SoccerTeam leftSide;
    private SoccerTeam rightSide;
    private SoccerBall ball;
    private int leftScore;
    private int rightScore;
    private boolean leftTurn;
    private static int time;

    public SoccerGame() {
        reset();
    }

    public final void reset() {
        leftSide = new SoccerTeam(true, Lineup.lineups.BALANCED, Color.BLUE);
        rightSide = new SoccerTeam(false, Lineup.lineups.BALANCED, Color.RED);
        ball = new SoccerBall(WORLD_WIDTH / 2, WORLD_HEIGHT * 7 / 16, 0, 0, .1f, Color.WHITE, 2.5f, 2f, "ball");
        leftScore = rightScore = 0;
        leftTurn = Math.random() <= .49;
    }
    
    public void goalReset(boolean leftSideGoal) {
        if (leftSideGoal) {
            rightScore++;
            leftTurn = true;
        } else {
            leftScore++;
            leftTurn = false;
        }
        leftSide.setPlayersToLineUp();
        rightSide.setPlayersToLineUp();
        ball.setToStartingPosition();
    }

    public void update() {
        for (SoccerPlayer p : leftSide.getPlayers()) {
            p.checkMouseEvent();
            //p.checkPlayerNotInGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
        }
        for (SoccerPlayer p : rightSide.getPlayers()) {
            p.checkMouseEvent();
            //p.checkPlayerNotInGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
        }
        if (ball.checkIfGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16) != null) {
            goalReset(ball.checkIfGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16));
        }
    }

    public void draw(Graphics2D g) {
        for (SoccerPlayer p : leftSide.getPlayers()) {
            p.draw(g);
        }
        for (SoccerPlayer p : rightSide.getPlayers()) {
            p.draw(g);
        }
        ball.draw(g);
    }
}
