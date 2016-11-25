package api.conexao;

import java.net.InetAddress;

import api.MensagemListener;

public abstract class  Conexao {
	
	protected InetAddress inetAddress;
	protected int port;
	
	public Conexao(InetAddress inetAddress, int port){
		this.inetAddress = inetAddress;
		this.port = port;
	}
	
	public Conexao(){
		
	}
	
	public abstract void send(String msg);
	
	public abstract void iniciarRecebimento(MensagemListener mensagemListener);
	
	public abstract void close();
	
}
