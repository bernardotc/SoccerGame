/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.util.ArrayList;
import static pbgAssignment_wrapperForJBox2D.GameEngineUsingJBox2D.WORLD_HEIGHT;
import static pbgAssignment_wrapperForJBox2D.GameEngineUsingJBox2D.WORLD_WIDTH;

/**
 *
 * @author bernardot
 */
public class Layout {

    public static enum LayoutMode {

        SOCCER_FIELD, RECTANGLE
    };
    private ArrayList<AnchoredBarrier> barriers;

    // Defines the layout of the field
    public Layout(LayoutMode l) {
        barriers = new ArrayList<>();
        switch (l) {
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
                
                // bottom line, first right line from bottom to top
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 2 / 16, WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 2 / 16, Color.WHITE, "bottom wall"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 2 / 16, WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 5 / 16, Color.WHITE, "right wall"));

                // right goal
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 5 / 16, WORLD_WIDTH * 14 / 16, WORLD_HEIGHT * 5 / 16, Color.WHITE, "bottom wall"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 14 / 16, WORLD_HEIGHT * 5 / 16, WORLD_WIDTH * 14 / 16, WORLD_HEIGHT * 9 / 16, Color.WHITE, "right wall"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 14 / 16, WORLD_HEIGHT * 9 / 16, WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 9 / 16, Color.WHITE, "top wall"));

                // missing part of the right line, top line, the top part of the left line
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 9 / 16, WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 12 / 16, Color.WHITE, "right wall"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 12 / 16, WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 12 / 16, Color.WHITE, "top wall"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 12 / 16, WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 9 / 16, Color.WHITE, "left wall"));

                // left goal
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 9 / 16, WORLD_WIDTH * 2 / 16, WORLD_HEIGHT * 9 / 16, Color.WHITE, "top wall"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 2 / 16, WORLD_HEIGHT * 5 / 16, WORLD_WIDTH * 2 / 16, WORLD_HEIGHT * 9 / 16, Color.WHITE, "left wall"));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 2 / 16, WORLD_HEIGHT * 5 / 16, WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 5 / 16, Color.WHITE, "bottom wall"));

                // complete the rectangle field
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 5 / 16, WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 2 / 16, Color.WHITE, "left wall"));

                // left goal line
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 5 / 16, WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 9 / 16, Color.GRAY, "left goal", true));
                // right goal line
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 5 / 16, WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 9 / 16, Color.GRAY, "right goal", true));
                // middle field line
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 8 / 16, WORLD_HEIGHT * 12 / 16, WORLD_WIDTH * 8 / 16, WORLD_HEIGHT * 2 / 16, Color.GRAY, "middle line", true));

                // left small area
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 4 / 16, WORLD_HEIGHT * 9.5f / 16, WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 9.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 4 / 16, WORLD_HEIGHT * 4.5f / 16, WORLD_WIDTH * 4 / 16, WORLD_HEIGHT * 9.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 4.5f / 16, WORLD_WIDTH * 4 / 16, WORLD_HEIGHT * 4.5f / 16, Color.GRAY, "", true));
                
                // left big area
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 5 / 16, WORLD_HEIGHT * 10.5f / 16, WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 10.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 5 / 16, WORLD_HEIGHT * 3.5f / 16, WORLD_WIDTH * 5 / 16, WORLD_HEIGHT * 10.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 3 / 16, WORLD_HEIGHT * 3.5f / 16, WORLD_WIDTH * 5 / 16, WORLD_HEIGHT * 3.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH * 5 / 16, WORLD_HEIGHT * 7 / 16, .6f, -90, 180, Color.GRAY));
                
                // right small area
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 12 / 16, WORLD_HEIGHT * 9.5f / 16, WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 9.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 12 / 16, WORLD_HEIGHT * 4.5f / 16, WORLD_WIDTH * 12 / 16, WORLD_HEIGHT * 9.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 4.5f / 16, WORLD_WIDTH * 12 / 16, WORLD_HEIGHT * 4.5f / 16, Color.GRAY, "", true));
                
                // right big area
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 11 / 16, WORLD_HEIGHT * 10.5f / 16, WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 10.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 11 / 16, WORLD_HEIGHT * 3.5f / 16, WORLD_WIDTH * 11 / 16, WORLD_HEIGHT * 10.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH * 13 / 16, WORLD_HEIGHT * 3.5f / 16, WORLD_WIDTH * 11 / 16, WORLD_HEIGHT * 3.5f / 16, Color.GRAY, "", true));
                barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH * 11 / 16, WORLD_HEIGHT * 7 / 16, .6f, 90, 180, Color.GRAY));
                
                // middle circle
                barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH * 8 / 16, WORLD_HEIGHT * 7 / 16, .6f, 0, 360, Color.GRAY));
                barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH * 8 / 16, WORLD_HEIGHT * 7 / 16, .03f, 0, 360, Color.GRAY));
                break;                
            }
        }
    }

    public ArrayList<AnchoredBarrier> getBarriers() {
        return barriers;
    }
}
