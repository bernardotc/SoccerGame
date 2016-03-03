/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

/**
 *
 * @author bernardot
 */
public class CollisionDetection implements ContactListener {

    public static boolean circleCollisioningWithBottomWall = false;
    public static boolean circleCollisioningWithLeftWall = false;
    public static boolean circleCollisioningWithRightWall = false;
    public static boolean circleCollisioningWithTopWall = false;
    public static Body circleCollidingBody = null;
    
    @Override
    public void beginContact(Contact contact) {
        String aux = (String) contact.m_fixtureA.m_body.getUserData();
        String aux2 = (String) contact.m_fixtureB.m_body.getUserData();
        /*if (aux != null && aux.equals("ball") || aux2 != null && aux2.equals("ball")) {
            System.out.println("Collision");
        } else */
        if (aux != null && aux.equals("bottom wall") || aux2 != null && aux2.equals("bottom wall")) {
            circleCollisioningWithBottomWall = true;
            if (aux != null && (aux.equals("player") || aux.equals("ball"))) {
                circleCollidingBody = contact.m_fixtureA.m_body;
            } else {
                circleCollidingBody = contact.m_fixtureB.m_body;
            }
        } else if ((aux != null && aux.equals("left wall") || aux2 != null && aux2.equals("left wall"))) {
            circleCollisioningWithLeftWall = true;
            if (aux != null && (aux.equals("player") || aux.equals("ball"))) {
                circleCollidingBody = contact.m_fixtureA.m_body;
            } else {
                circleCollidingBody = contact.m_fixtureB.m_body;
            }
        } else if ((aux != null && aux.equals("right wall") || aux2 != null && aux2.equals("right wall"))) {
            circleCollisioningWithRightWall = true;
            if (aux != null && (aux.equals("player") || aux.equals("ball"))) {
                circleCollidingBody = contact.m_fixtureA.m_body;
            } else {
                circleCollidingBody = contact.m_fixtureB.m_body;
            }
        } else if (aux != null && aux.equals("top wall") || aux2 != null && aux2.equals("top wall")) {
            circleCollisioningWithTopWall = true;
            if (aux != null && (aux.equals("player") || aux.equals("ball"))) {
                circleCollidingBody = contact.m_fixtureA.m_body;
            } else {
                circleCollidingBody = contact.m_fixtureB.m_body;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        String aux = (String) contact.m_fixtureA.m_body.getUserData();
        String aux2 = (String) contact.m_fixtureB.m_body.getUserData();
        if (aux != null && aux.equals("bottom wall") || aux2 != null && aux2.equals("bottom wall")) {
            circleCollisioningWithBottomWall = false;
        } else if ((aux != null && aux.equals("left wall") || aux2 != null && aux2.equals("left wall"))) {
            circleCollisioningWithLeftWall = false;
        } else if ((aux != null && aux.equals("right wall") || aux2 != null && aux2.equals("right wall"))) {
            circleCollisioningWithRightWall = false;
        } else if (aux != null && aux.equals("top wall") || aux2 != null && aux2.equals("top wall")) {
            circleCollisioningWithTopWall = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
