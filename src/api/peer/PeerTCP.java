package api.peer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import api.ConexaoListener;
import api.conexao.Conexao;
import api.conexao.ConexaoTCP;

public class PeerTCP extends Peer{

	private ServerSocket serverSocket;
	private boolean run = true;
	
	public PeerTCP(int port) {
		super.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public Conexao getConexao(InetAddress inetAddress, int port) {
		try {
			Socket socket = new Socket(inetAddress, port);
			return new ConexaoTCP(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public void iniciarRecebimentoConexao(final ConexaoListener conexaoListener) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
				while(run){
					
					Socket socket = serverSocket.accept();
					Conexao c = new ConexaoTCP(socket);
					conexaoListener.recebidoConexao(c);
						
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
				
			}
		}).start();
		
	}

	
	public void close() {
		try {
			run = false;
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
