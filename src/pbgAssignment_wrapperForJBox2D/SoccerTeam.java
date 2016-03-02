/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import pbgAssignment_wrapperForJBox2D.Lineup.lineups;

/**
 *
 * @author bernardot
 */
public class SoccerTeam {
    private ArrayList<SoccerPlayer> players;
    private Lineup lineup;
    private boolean leftSideOfScreen;
    private Color color; 
    
    public SoccerTeam(boolean leftSideOfScreen, lineups l, Color c) {
        players = new ArrayList<>();
        this.color = c;
        this.leftSideOfScreen = leftSideOfScreen;
        lineup = new Lineup(l, this.leftSideOfScreen);
        for (int x = 0; x < lineup.getPositions().size(); x++) {
            Vec2 pos = lineup.getPositions().get(x);
            players.add(new SoccerPlayer(pos.x, pos.y, 0, 0, .2f, color, 10, 2f));
        }
    }

    public ArrayList<SoccerPlayer> getPlayers() {
        return players;
    }
    
    public void setPlayersToLineUp() {
        for (int x = 0; x < lineup.getPositions().size(); x++) {
            Vec2 pos = lineup.getPositions().get(x);
            players.get(x).setToStartingPosition(pos);
        }
    }
}
