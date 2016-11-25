package api.peer;

import java.net.InetAddress;

import api.ConexaoListener;
import api.conexao.Conexao;

public abstract class Peer {
	

	protected int port;
	
	public static Peer getIntance(int port, String s){
	
		if(s.equalsIgnoreCase("tcp")){
			return new PeerTCP(port);
		}else{
			return new PeerUDP(port);
		}
		
	}
	
	
	public abstract Conexao getConexao(InetAddress inetAddress, int port);
	
	public abstract void iniciarRecebimentoConexao(ConexaoListener conexaoListener);
	
	public abstract void close();
	
}
