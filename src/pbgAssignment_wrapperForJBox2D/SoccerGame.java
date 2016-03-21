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
    private final Layout layout;
    private int leftScore;
    private int rightScore;
    private boolean leftTurn;
    private boolean piecesMoving;
    private boolean goal;
    private boolean leftSideGoal;
    private boolean firstTurn;
    private boolean slowTurn;
    private boolean start;
    private boolean end;
    private Timer endTimer;
    private Timer turnTimer;
    private Timer slowTimer;
    private Timer goalTimer;

    public SoccerGame() {
        layout = new Layout(Layout.LayoutMode.SOCCER_FIELD);
        start = true;
        end = false;
        leftSide = new SoccerTeam(true, Lineup.lineups.BALANCED, Color.BLUE);
        rightSide = new SoccerTeam(false, Lineup.lineups.BALANCED, Color.RED);
        ball = new SoccerBall(WORLD_WIDTH * 8 / 16, WORLD_HEIGHT * 7 / 16, 0, 0, .1f, Color.WHITE, 2.5f, 2f, "ball", false);
    }

    // reset the class to its initial settings.
    public final void reset() {
        leftSide.setPlayersToLineUp();
        rightSide.setPlayersToLineUp();
        ball.setToStartingPosition();
        leftScore = rightScore = 0;
        goal = piecesMoving = leftSideGoal = slowTurn = false;
        firstTurn = true;
        goalTimer = new Timer();
        turnTimer = new Timer();
        slowTimer = new Timer();
        endTimer = new Timer();
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

    // reset the game for the next turn (there was a goal or a player was too slow)
    public void eventGameReset(boolean leftSideGoal) {
        goal = piecesMoving = false;
        if (!firstTurn && !slowTurn) {
            if (leftSideGoal) {
                rightScore++;
                leftTurn = true;
            } else {
                leftScore++;
                leftTurn = false;
            }
        }
        if (leftScore >= 3 || rightScore >= 3) {
            end = true;
            endTimer.reset();
        }
        if (!slowTurn) {
            firstTurn = true;
            leftSide.setPlayersToLineUp();
            rightSide.setPlayersToLineUp();
            ball.setToStartingPosition();
        }
        slowTurn = false;
        setTurnToTeams();
        turnTimer.reset();
    }

    public void update() {
        if (!start && !end) {
            // update left player side
            if (!slowTurn && leftSide.playTurn(leftTurn)) {
                setTurnToFalse();
                piecesMoving = true;
                leftTurn = !leftTurn;
            }
            
            // update right player side
            if (!slowTurn && rightSide.playTurn(leftTurn)) {
                setTurnToFalse();
                piecesMoving = true;
                leftTurn = !leftTurn;
            }
            
            ball.modifyPositionAccordingToWall();
            
            // check if there was a goal
            if (!goal && ball.checkIfGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16) != null) {
                leftSideGoal = ball.checkIfGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
                goal = true;
                ball.body.setLinearDamping(8);
                goalTimer.reset();
            }

            // start the next turn.
            if (!goal && piecesMoving && leftSide.playersNotMoving() && rightSide.playersNotMoving() && ball.notMoving()) {
                //System.out.println("New turn");
                if (firstTurn) {
                    firstTurn = false;
                }
                piecesMoving = false;
                leftSide.checkPlayersNotInGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
                rightSide.checkPlayersNotInGoal(WORLD_WIDTH * 3 / 16, WORLD_WIDTH * 13 / 16);
                setTurnToTeams();
                turnTimer.reset();
            }

            // The goal timer has finished, reset game for the next turn
            if (goal && goalTimer.getMilliseconds() >= 3000) {
                //System.out.println("Reset beacuse of goal");
                eventGameReset(leftSideGoal);
            }

            if (KeyListener.isLeftScreenChangeLineupButtonPressed()) {
                KeyListener.setLeftScreenChangeLineupButtonPressed(false);
                leftSide.changeLineup();
                if (firstTurn && !piecesMoving && leftTurn) {
                    leftSide.setPlayersToLineUp();
                }
            }

            if (KeyListener.isRightScreenChangeLineupButtonPressed()) {
                KeyListener.setRightScreenChangeLineupButtonPressed(false);
                rightSide.changeLineup();
                if (firstTurn && !piecesMoving && !leftTurn) {
                    rightSide.setPlayersToLineUp();
                }
            }

            // the player was too slow. Set the timers.
            if (!piecesMoving && turnTimer.getMilliseconds() >= 10000) {
                slowTurn = true;
                MouseListener.setMouseButtonPressed(false);
                MouseListener.setPlayer(null);
                slowTimer.reset();
                turnTimer.reset();
            }

            // After the slow Timer has finished, change turn of the game.
            if (slowTurn && slowTimer.getMilliseconds() >= 1500) {
                leftTurn = !leftTurn;
                eventGameReset(false);
            }
        } else {
            if (!end && KeyListener.isStartGameButtonPressed()) {
                KeyListener.setStartGameButtonPressed(false);
                reset();
                start = false;
            }
            if (end && endTimer.getMilliseconds() >= 5000) {
                start = true;
                end = false;
            }
        }
    }

    private final String titleText = "SOCCER GAME!";
    private final String winCriteriaText = "First to score 3 goals wins!";
    private final String twoPlayerInstructionText = "Two player match";
    private final String startGameText = "Press 'Enter' to start the match";
    private final String leftTeamText = "Blue Team";
    private final String rightTeamText = "Red Team";
    private final String turn = "'s Turn";
    private final String versusText = "-";
    private final String goalText = "GOAL!";
    private final String foulText = "FOUL!";
    private final String preLineupText = "Current lineup: ";
    private final String leftPosLineupText = " (Change by pressing '1')";
    private final String rightPosLineupText = " (Change by pressing '0')";
    private final String timeLeftText = "Time left: ";
    private final String slowText = "TOO SLOW!";
    private final String winText = " WINS!";

    public void draw(Graphics2D g) {
        if (!start && !end) {
            for (AnchoredBarrier b : layout.getBarriers()) {
                b.draw(g);
            }
            for (SoccerPlayer p : leftSide.getPlayers()) {
                p.draw(g);
            }
            for (SoccerPlayer p : rightSide.getPlayers()) {
                p.draw(g);
            }
            ball.draw(g);

            // Draw all the infomartion from the top of the screen
            g.scale(3, 3);
            g.setColor(leftSide.getColor());
            g.drawChars(String.valueOf(leftScore).toCharArray(), 0, String.valueOf(leftScore).length(), SCREEN_WIDTH * 6 / 16 / 3, SCREEN_HEIGHT * 1 / 16 / 3);
            g.drawChars(leftTeamText.toCharArray(), 0, leftTeamText.length(), SCREEN_WIDTH * 2 / 16 / 3, SCREEN_HEIGHT * 1 / 16 / 3);
            g.scale(1 / 3.f, 1 / 3.f);
            g.drawChars((preLineupText + leftSide.getLineup().strategy.toString() + leftPosLineupText).toCharArray(), 0, (preLineupText + leftSide.getLineup().strategy.toString() + leftPosLineupText).length(), SCREEN_WIDTH * 2 / 16, SCREEN_HEIGHT * 3 / 32);

            g.scale(3, 3);
            if (leftTurn && !piecesMoving && !goal) {
                g.drawChars((leftTeamText + turn).toCharArray(), 0, (leftTeamText + turn).length(), SCREEN_WIDTH * 6 / 16 / 3 + 2, SCREEN_HEIGHT * 3 / 16 / 3);
            }

            g.setColor(Color.WHITE);
            g.drawChars(versusText.toCharArray(), 0, versusText.length(), SCREEN_WIDTH * 8 / 16 / 3 - 5, SCREEN_HEIGHT * 1 / 16 / 3);

            g.setColor(rightSide.getColor());
            g.drawChars(String.valueOf(rightScore).toCharArray(), 0, String.valueOf(rightScore).length(), SCREEN_WIDTH * 10 / 16 / 3 - 10, SCREEN_HEIGHT * 1 / 16 / 3);
            g.drawChars(rightTeamText.toCharArray(), 0, rightTeamText.length(), SCREEN_WIDTH * 12 / 16 / 3, SCREEN_HEIGHT * 1 / 16 / 3);
            g.scale(1 / 3.f, 1 / 3.f);
            g.drawChars((preLineupText + rightSide.getLineup().strategy.toString() + rightPosLineupText).toCharArray(), 0, (preLineupText + rightSide.getLineup().strategy.toString() + rightPosLineupText).length(), SCREEN_WIDTH * 10 / 16, SCREEN_HEIGHT * 3 / 32);

            // Draw the team that has the turn
            g.scale(3, 3);
            if (!leftTurn && !piecesMoving && !goal) {
                g.drawChars((rightTeamText + turn).toCharArray(), 0, (rightTeamText + turn).length(), SCREEN_WIDTH * 6 / 16 / 3 + 2, SCREEN_HEIGHT * 3 / 16 / 3);
            }

            // Draw the timer
            g.scale(2 / 3.f, 2 / 3.f);
            if (!piecesMoving && !slowTurn) {
                g.setColor(Color.LIGHT_GRAY);
                String time = String.valueOf(10 - (int) turnTimer.getMilliseconds() / (int) 1000);
                g.drawChars((timeLeftText + time).toCharArray(), 0, (timeLeftText + time).length(), SCREEN_WIDTH * 7 / 16 / 2, SCREEN_HEIGHT * 4 / 16 / 2 - 2);
            }

            // Draw the slow message
            if (slowTurn) {
                g.scale(6, 6);
                g.setColor(Color.LIGHT_GRAY);
                g.drawChars(slowText.toCharArray(), 0, slowText.length(), SCREEN_WIDTH * 1 / 16 / 5 + 5, SCREEN_HEIGHT * 4 / 16 / 5);
            }

            // Draw the goal message
            g.scale(3 / 2.f, 3 / 2.f);
            if (goal) {
                g.scale(6, 6);
                g.setColor(Color.LIGHT_GRAY);
                if (firstTurn) {
                    g.drawChars(foulText.toCharArray(), 0, foulText.length(), SCREEN_WIDTH * 1 / 16 / 5 + 1, SCREEN_HEIGHT * 3 / 16 / 5);
                } else {
                    g.drawChars(goalText.toCharArray(), 0, goalText.length(), SCREEN_WIDTH * 1 / 16 / 5 + 1, SCREEN_HEIGHT * 3 / 16 / 5);
                }
            }
        } else {
            // Start screen
            if (start) {
                g.scale(12, 12);
                g.setColor(Color.LIGHT_GRAY);
                g.drawChars(titleText.toCharArray(), 0, titleText.length(), SCREEN_WIDTH * 1 / 16 / 12, SCREEN_HEIGHT * 5 / 16 / 12);
                g.scale(1/2f, 1/2f);
                g.setColor(Color.CYAN);
                g.drawChars(twoPlayerInstructionText.toCharArray(), 0, twoPlayerInstructionText.length(), SCREEN_WIDTH * 4 / 16 / 6, SCREEN_HEIGHT * 8 / 16 / 6);
                g.drawChars(winCriteriaText.toCharArray(), 0, winCriteriaText.length(), SCREEN_WIDTH * 2 / 16 / 6, SCREEN_HEIGHT * 10 / 16 / 6);
                g.scale(1/3f, 1/3f);
                g.setColor(Color.YELLOW);
                g.drawChars(startGameText.toCharArray(), 0, startGameText.length(), SCREEN_WIDTH * 6 / 16 / 2, SCREEN_HEIGHT * 14 / 16 / 2);
            }
            // End screen
            if (end) {
                g.scale(12, 12);
                g.setColor(Color.LIGHT_GRAY);
                if (leftScore >= 3) {
                    g.drawChars((leftTeamText + winText).toCharArray(), 0, (leftTeamText + winText).length(), SCREEN_WIDTH * 1 / 16 / 12 - 2, SCREEN_HEIGHT * 8 / 16 / 12);
                } else {
                    g.drawChars((rightTeamText + winText).toCharArray(), 0, (rightTeamText + winText).length(), SCREEN_WIDTH * 1 / 16 / 12 - 2, SCREEN_HEIGHT * 8 / 16 / 12);
                }
            }
        }
    }
}
