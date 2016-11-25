package api.conexao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import api.MensagemListener;
import api.peer.PeerUDP;

public class ConexaoUDP extends Conexao{
	
	private DatagramSocket datagramSocket;
	private PeerUDP peerUDP;
	private MensagemListener mensagemListener;
	
	public ConexaoUDP(InetAddress inetAddress, int port) {
		super(inetAddress, port);
		// TODO Auto-generated constructor stub
	}
	public ConexaoUDP(InetAddress inetAddress, int port, DatagramSocket datagramSocket, PeerUDP peerUDP) {
		this.peerUDP = peerUDP;
		this.datagramSocket = datagramSocket;
		super.port = port;
		super.inetAddress = inetAddress;
	}

	@Override
	public void send(String msg) {
		
		try {
			DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.length(), super.inetAddress, super.port);
			datagramSocket.send(datagramPacket);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarRecebimento(MensagemListener mensagemListener) {
	
		this.mensagemListener = mensagemListener;
		
	}
	
	public void notificarMensagem(String msg){
		
		if(mensagemListener!=null)mensagemListener.recebido(msg);
		
	}

	@Override
	public void close() {
		peerUDP.removerConexao(this);
		
	}

}
