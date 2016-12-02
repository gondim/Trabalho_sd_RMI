import java.rmi.Remote;
import java.rmi.RemoteException;

import game.Carro;

public interface SevidorInterface extends Remote{
	public void mover(String massage) throws RemoteException;
	public String atualizar() throws RemoteException;
	public void addJogador() throws RemoteException;
	public void colidiu(String carro) throws RemoteException;
}
