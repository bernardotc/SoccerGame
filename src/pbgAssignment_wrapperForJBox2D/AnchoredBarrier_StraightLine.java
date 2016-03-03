package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class AnchoredBarrier_StraightLine extends AnchoredBarrier {
    /* Author: Michael Fairbank
     * Creation Date: 2016-02-05 (JBox2d version)
     * Significant changes applied: 
     */

    private Vec2 startPos, endPos;
    private final Color col;
    public final Body body;

    public AnchoredBarrier_StraightLine(float startx, float starty, float endx, float endy, Color col) {
        this(startx, starty, endx, endy, col, "");
    }

    public AnchoredBarrier_StraightLine(float startx, float starty, float endx, float endy, Color col, String userData) {
        this(startx, starty, endx, endy, col, userData, 0);
    }
    
    public AnchoredBarrier_StraightLine(float startx, float starty, float endx, float endy, Color col, String userData, int groupIndex) {
        startPos = new Vec2(startx, starty);
        endPos = new Vec2(endx, endy);

        World w = GameEngineUsingJBox2D.world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        bodyDef.position = new Vec2(startx, starty);
        bodyDef.userData = userData;
        Body body = w.createBody(bodyDef);
        this.body = body;
        Vec2[] vertices = new Vec2[]{new Vec2(), new Vec2(endx - startx, endy - starty)};
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(vertices, vertices.length);
        body.createFixture(chainShape, 0);
        this.col = col;
        body.m_fixtureList.m_filter.groupIndex = groupIndex;
    }

    @Override
    public void draw(Graphics2D g) {
        int x1 = GameEngineUsingJBox2D.convertWorldXtoScreenX(startPos.x);
        int y1 = GameEngineUsingJBox2D.convertWorldYtoScreenY(startPos.y);
        int x2 = GameEngineUsingJBox2D.convertWorldXtoScreenX(endPos.x);
        int y2 = GameEngineUsingJBox2D.convertWorldYtoScreenY(endPos.y);
        g.setColor(col);
        g.drawLine(x1, y1, x2, y2);
    }

}
