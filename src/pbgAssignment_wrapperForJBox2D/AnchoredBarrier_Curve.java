package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.common.Vec2;

public class AnchoredBarrier_Curve extends AnchoredBarrier {
    /* Author: Michael Fairbank
     * Creation Date: 2016-02-05 (JBox2d version)
     * Significant changes applied:  
     */

    private final Vec2 centreOfCircleBarrierArc;
    private final Color col;
    private final double deltaAngle;
    private final double startAngle;
    private final int radiusInScreenCoordinates;

    public AnchoredBarrier_Curve(float centrex, float centrey, float radiusOfBarrier, float startAngle, float deltaAngle, Color col) {
        centreOfCircleBarrierArc = new Vec2(centrex, centrey);
        this.deltaAngle = deltaAngle;
        this.startAngle = startAngle;
        this.radiusInScreenCoordinates = (int) GameEngineUsingJBox2D.convertWorldLengthToScreenLength(radiusOfBarrier);
        this.col = col;
    }

    @Override
    public void draw(Graphics2D g) {
        int x1 = GameEngineUsingJBox2D.convertWorldXtoScreenX(centreOfCircleBarrierArc.x);
        int y1 = GameEngineUsingJBox2D.convertWorldYtoScreenY(centreOfCircleBarrierArc.y);
        g.setColor(col);
        // g.drawArc arguments give dimensions of a rectangle (x,y,width,height) that contains the full ellipse that contains the arc
        g.drawArc(x1 - radiusInScreenCoordinates, y1 - radiusInScreenCoordinates,
                radiusInScreenCoordinates * 2, radiusInScreenCoordinates * 2, (int) startAngle, (int) deltaAngle);
    }

}
