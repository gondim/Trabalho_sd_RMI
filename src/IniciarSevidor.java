import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class IniciarSevidor {
	
public static void main(String[] args) {
		
	
    try {
    	System.out.println("chamando servidor...");
        LocateRegistry.createRegistry(3000);
    	System.setProperty("java.rmi.server.hostname", "172.18.9.37");
        Servidor s = new Servidor();
		Naming.rebind("rmi://172.18.9.37:3000/Servidor", s);
		s.frames();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}