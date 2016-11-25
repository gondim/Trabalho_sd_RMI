package api.conexao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import api.MensagemListener;

public class ConexaoTCP extends Conexao{

	private Socket socket;
	private boolean run = true;
	
	public ConexaoTCP(InetAddress inetAddress, int port) {
		super(inetAddress, port);
		
	}
	
	public ConexaoTCP(Socket socket) {
		this.socket = socket;
		super.port = socket.getPort();
		super.inetAddress = socket.getInetAddress();
		
	}

	@Override
	public void send(String msg) {
		try {
			socket.getOutputStream().write((msg + "\n").getBytes());
			socket.getOutputStream().flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void iniciarRecebimento(final MensagemListener mensagemListener) {
		
		new Thread(new Runnable() {
			
			
			public void run() {
				
				BufferedReader br;
				try {
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String line = br.readLine();;
					//StringBuilder sb = new StringBuilder();
			
					while (line != null) {
						mensagemListener.recebido(line);
						line = br.readLine();	
					}
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		}).start();
		
	}

	@Override
	public void close() {
		run = false;
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
