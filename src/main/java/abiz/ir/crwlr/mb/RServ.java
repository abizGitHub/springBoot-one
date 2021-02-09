package abiz.ir.crwlr.mb;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RServ {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        GameRImpl gameR = new GameRImpl();
        Remote remote = UnicastRemoteObject.exportObject(gameR, 1099);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("gamer", remote);
        //for (;;){}

    }

}
