package de.hawhamburg.rmi;

/**
 * Created by Ole on 14.11.2015.
 */

        import de.hawhamburg.rmi.dice.DiceService;

        import java.net.MalformedURLException;
        import java.rmi.Naming;
        import java.rmi.RemoteException;
        import java.rmi.registry.LocateRegistry;
        import java.rmi.registry.Registry;

public class RMIServer {

    public static final String DICE_NAME = "DiceRMI";
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind(DICE_NAME, new DiceService());
        }
        catch (MalformedURLException ex) {
            System.out.println("MalformerURLException "+ex.getMessage());
            ex.printStackTrace();
        }
        catch (RemoteException ex) {
            System.out.println("Remote Exception "+ex.getMessage());
            ex.printStackTrace();
        }
    }
}