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

    public static enum lineups {

        BALANCED
    }

    Lineup(lineups l, boolean leftSideOfScreen) {
        positions = new ArrayList<>();
        switch (l) {
            case BALANCED: {
                if (leftSideOfScreen) {
                    float factor = 0;
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
        }
    }

    public ArrayList<Vec2> getPositions() {
        return positions;
    }
    
}
