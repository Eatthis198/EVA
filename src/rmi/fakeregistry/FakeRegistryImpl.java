package rmi.fakeregistry;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by David on 25-May-17.
 */
public class FakeRegistryImpl extends UnicastRemoteObject implements FakeRegistry {

    private Map<String, Remote> map;
    private Registry registry;

    public FakeRegistryImpl(int port) throws RemoteException {
        super();
        map = new Hashtable<>();
        registry = LocateRegistry.createRegistry(port);
        registry.rebind("FakeRegistry",this);
    }

    @Override
    public void rebind(String name, Remote obj) throws RemoteException {

        registry.rebind(name, obj);
        try {
            Remote o = registry.lookup(name);
            map.put(name, o);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Remote lookUp(String name) throws RemoteException {
        return map.get(name);
    }
}
