/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.util.ArrayList;
import org.jbox2d.common.Vec2;

/**
 *
 * @author bernardot
 */
public class Lineup {

    ArrayList<Vec2> positions;
    lineups strategy;

    public static enum lineups {
        BALANCED, WING_ATTACK, THREE_DEFENSE
    }

    Lineup(lineups l, boolean leftSideOfScreen) {
        strategy = l;
        resetPositions(leftSideOfScreen);
    }

    public ArrayList<Vec2> getPositions() {
        return positions;
    }
    
    public final void resetPositions(boolean leftSideOfScreen) {
        positions = new ArrayList<>();
        switch (strategy) {
            case BALANCED: {
                if (leftSideOfScreen) {
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 7 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 5 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 10 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 5 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 4 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 8 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 6 / 16));
                } else {
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 7 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 5 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 10 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 5 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 4 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 8 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 6 / 16));
                }
                break;
            }
            case WING_ATTACK: {
                if (leftSideOfScreen) {
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 7 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 7 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 11 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 9 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 5 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 7 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 3 / 16));
                } else {
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 7 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 7 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 11 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 9 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 5 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 7 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 3 / 16));
                }
                break;
            }
            case THREE_DEFENSE: {
                if (leftSideOfScreen) {
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 9 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 7 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 5 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 8 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 6 / 16));
                } else {
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 9 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 7 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 4 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 5 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 8 / 16));
                    positions.add(new Vec2(GameEngineUsingJBox2D.WORLD_WIDTH - GameEngineUsingJBox2D.WORLD_WIDTH * 6 / 16, GameEngineUsingJBox2D.WORLD_HEIGHT * 6 / 16));
                }
                break;
            }
        }
    }
    
}
