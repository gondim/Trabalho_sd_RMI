package api.peer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

import api.ConexaoListener;
import api.conexao.Conexao;
import api.conexao.ConexaoUDP;

public class PeerUDP extends Peer{

	public PeerUDP(int port) {
		super.port = port;
		try {
			datagramSocket = new DatagramSocket(port);
			hashConnections = new HashMap<>();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private DatagramSocket datagramSocket;
	private HashMap<String, ConexaoUDP> hashConnections;
	private boolean run = true;
	private final static String NEWM = "100new001";
	
	
	@Override
	public Conexao getConexao(InetAddress inetAddress, int port) {
		
		ConexaoUDP conexaoUDP = new ConexaoUDP(inetAddress, port, datagramSocket, this);
	
		conexaoUDP.send(NEWM);
		
		
		//System.out.println("CHAVE CRIADA" + inetAddress.getHostAddress()+port);
		hashConnections.put(inetAddress.getHostAddress()+port, conexaoUDP);
		return conexaoUDP;
	}

	@Override
	public void iniciarRecebimentoConexao(final ConexaoListener conexaoListener) {
		
		new Thread(new Runnable() {
			
			
			public void run() {
				
				try {
					while (run) {
						
						byte buffer[] = new byte[8192];
						DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
						datagramSocket.receive(datagramPacket);
						
						String chave = datagramPacket.getAddress().getHostAddress()+datagramPacket.getPort();
						//System.out.println("CHAVE RECEVBIDA " + chave);
						
						if(hashConnections.containsKey(chave)){
							
							ConexaoUDP conexaoUDP = hashConnections.get(chave);
							
							String msg = new String(datagramPacket.getData());
							
							if(!msg.trim().equalsIgnoreCase(NEWM)){
						
								conexaoUDP.notificarMensagem(msg.trim());								
							}
							
						}else{
										
							ConexaoUDP conexaoUDP = new ConexaoUDP(datagramPacket.getAddress(), datagramPacket.getPort(), datagramSocket, PeerUDP.this);
							hashConnections.put(datagramPacket.getAddress().getHostAddress()+datagramPacket.getPort(), conexaoUDP);
							conexaoListener.recebidoConexao(conexaoUDP );
						}
						

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
		
	}
	
	public void removerConexao(ConexaoUDP conexaoUDP){
		hashConnections.remove(conexaoUDP);
	}

	@Override
	public void close() {
		run = false;
		datagramSocket.close();
		
	}

}
