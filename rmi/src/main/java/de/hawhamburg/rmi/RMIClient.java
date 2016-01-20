package de.hawhamburg.rmi;


import de.hawhamburg.rmi.dice.DiceRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by abe180 on 04.11.2015.
 */
public class RMIClient {

    public static void main(String[] args) {
        try {
            DiceRMI dice = (DiceRMI) Naming.lookup("//127.0.0.1/" + RMIServer.DICE_NAME);
//            System.setSecurityManager(new RMISecurityManager());
            for (int ii = 0; ii < 10; ii++) {
                System.out.println("Number " + dice.roll().getNumber());
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
