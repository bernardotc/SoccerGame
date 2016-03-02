/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbgAssignment_wrapperForJBox2D;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

/**
 *
 * @author bernardot
 */
public class CollisionDetection implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        String aux = (String) contact.m_fixtureA.m_body.getUserData();
        String aux2 = (String) contact.m_fixtureB.m_body.getUserData();
        /*if (aux != null && aux.equals("ball") || aux2 != null && aux2.equals("ball")) {
            System.out.println("Collision");
        } else */if ((aux != null && aux.equals("ball") && aux2 != null && aux2.equals("wall")) || (aux != null && aux.equals("wall") && aux2 != null && aux2.equals("ball"))) {
            System.out.println("ball wall collision");
            System.out.println(contact.m_fixtureB.m_body.getPosition().x + " : " + contact.m_fixtureA.m_body.getPosition().y);
        }
    }

    @Override
    public void endContact(Contact contact) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
