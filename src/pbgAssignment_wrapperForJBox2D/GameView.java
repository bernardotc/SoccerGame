/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author bernardot
 *
 * Based on BasicView by Michael Fairbank
 */
public class GameView extends JComponent {

    public static final Color BG_COLOR = Color.BLACK;

    private GameEngineUsingJBox2D game;

    public GameView(GameEngineUsingJBox2D game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        GameEngineUsingJBox2D game;
        synchronized (this) {
            game = this.game;
        }
        Graphics2D g = (Graphics2D) g0;
        // paint the background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (AnchoredBarrier b : game.barriers) {
            b.draw(g);
        }
        for (SoccerPlayer p : game.left.getPlayers()) {
            p.draw(g);
        }
        for (SoccerPlayer p : game.right.getPlayers()) {
            p.draw(g);
        }
        game.ball.draw(g);

    }

    @Override
    public Dimension getPreferredSize() {
        return GameEngineUsingJBox2D.FRAME_SIZE;
    }

    public synchronized void updateGame(GameEngineUsingJBox2D game) {
        this.game = game;
    }
}
