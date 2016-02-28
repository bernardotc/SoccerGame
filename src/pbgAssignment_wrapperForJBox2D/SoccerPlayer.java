/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import java.awt.Color;

/**
 *
 * @author bernardot
 */
public class SoccerPlayer extends BasicCircle {

    public SoccerPlayer(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction) {
        super(sx, sy, vx, vy, radius, col, mass, rollingFriction);
    }
    
}
