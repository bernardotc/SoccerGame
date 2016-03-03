/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Graphics2D;
import org.jbox2d.common.Timer;
import static pbgAssignment_wrapperForJBox2D.GameEngineUsingJBox2D.SCREEN_HEIGHT;
import static pbgAssignment_wrapperForJBox2D.GameEngineUsingJBox2D.SCREEN_WIDTH;
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
    private boolean piecesMoving;
    private boolean goal;
    private boolean leftSideGoal;
    private static int time;
    private Timer goalTimer;

    public SoccerGame() {
        reset();
    }

    public final void reset() {
        leftSide = new SoccerTeam(true, Lineup.lineups.BALANCED, Color.BLUE);
        rightSide = new SoccerTeam(false, Lineup.lineups.BALANCED, Color.RED);
        ball = new SoccerBall(WORLD_WIDTH / 2, WORLD_HEIGHT * 7 / 16, 0, 0, .1f, Color.WHITE, 2.5f, 2f, "ball", -1);
        leftScore = rightScore = 0;
        goal = piecesMoving = leftSideGoal = false;
        goalTimer = new Timer();
        leftTurn = Math.random() <= .49;
        setTurnToTeams();
    }

    public void setTurnToTeams() {
        leftSide.setTurn(leftTurn);
        rightSide.setTurn(!leftTurn);
    }

    public void setTurnToFalse() {
        leftSide.setTurn(false);
        rightSide.setTurn(false);
    }

    public void goalReset(boolean leftSideGoal) {
        goal = false;
        if (leftSideGoal) {
            rightScore++;
            leftTurn = true;
        } else {
            leftScore++;
            leftTurn = false;
        }
        leftSide.setPlayersToLineUp();
        rightSide.setPlayersToLineUp();
        setTurnToTeams();
        ball.setToStartingPosition();
    }

    public void update() {
        if (leftSide.playTurn(leftTurn)) {
            setTurnToFalse();
            piecesMoving = true;
            leftTurn = !leftTurn;
        }
        if (rightSide.playTurn(leftTurn)) {
            setTurnToFalse();
            piecesMoving = true;
            leftTurn = !leftTurn;
        }
        ball.modifyPositionAccordingToWall();
        if (!goal && ball.checkIfGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16) != null) {
            leftSideGoal = ball.checkIfGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
            goal = true;
            ball.body.setLinearDamping(8);
            goalTimer.reset();
        }

        if (piecesMoving && leftSide.playersNotMoving() && rightSide.playersNotMoving() && ball.notMoving()) {
            //System.out.println("New turn");
            piecesMoving = false;
            leftSide.checkPlayersNotInGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
            rightSide.checkPlayersNotInGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
            setTurnToTeams();
        }

        if (goal && goalTimer.getMilliseconds() >= 3000) {
            System.out.println("Reset beacuse of goal");
            goalReset(leftSideGoal);
        }
    }

    private String leftTeamText = "Blue Team";
    private String rightTeamText = "Red Team";
    private String turn = "'s Turn";
    private String versusText = "-";
    private String goalText = "GOAL!";

    public void draw(Graphics2D g) {
        for (SoccerPlayer p : leftSide.getPlayers()) {
            p.draw(g);
        }
        for (SoccerPlayer p : rightSide.getPlayers()) {
            p.draw(g);
        }
        ball.draw(g);

        g.scale(3, 3);

        g.setColor(leftSide.getColor());
        g.drawChars(String.valueOf(leftScore).toCharArray(), 0, String.valueOf(leftScore).length(), SCREEN_WIDTH * 6 / 16 / 3, SCREEN_HEIGHT * 1 / 16 / 3);
        g.drawChars(leftTeamText.toCharArray(), 0, leftTeamText.length(), SCREEN_WIDTH * 2 / 16 / 3, SCREEN_HEIGHT * 1 / 16 / 3);
        if (leftTurn && !piecesMoving && !goal) {
            g.drawChars((leftTeamText + turn).toCharArray(), 0, (leftTeamText + turn).length(), SCREEN_WIDTH * 6 / 16 / 3 + 2, SCREEN_HEIGHT * 3 / 16 / 3);
        }

        g.setColor(Color.WHITE);
        g.drawChars(versusText.toCharArray(), 0, versusText.length(), SCREEN_WIDTH * 8 / 16 / 3 - 5, SCREEN_HEIGHT * 1 / 16 / 3);

        g.setColor(rightSide.getColor());
        g.drawChars(String.valueOf(rightScore).toCharArray(), 0, String.valueOf(rightScore).length(), SCREEN_WIDTH * 10 / 16 / 3 - 10, SCREEN_HEIGHT * 1 / 16 / 3);
        g.drawChars(rightTeamText.toCharArray(), 0, rightTeamText.length(), SCREEN_WIDTH * 12 / 16 / 3, SCREEN_HEIGHT * 1 / 16 / 3);
        if (!leftTurn && !piecesMoving && !goal) {
            g.drawChars((rightTeamText + turn).toCharArray(), 0, (rightTeamText + turn).length(), SCREEN_WIDTH * 6 / 16 / 3 + 2, SCREEN_HEIGHT * 3 / 16 / 3);
        }

        if (goal) {
            g.scale(6, 6);
            g.setColor(Color.LIGHT_GRAY);
            g.drawChars(goalText.toCharArray(), 0, goalText.length(), SCREEN_WIDTH * 1 / 16 / 5 + 1, SCREEN_HEIGHT * 3 / 16 / 5);
        }
    }
}
