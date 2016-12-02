import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SevidorInterface extends Remote{
	public void mover(String massage) throws RemoteException;
	public String atualizar() throws RemoteException;
	public void addJogador() throws RemoteException;
}
