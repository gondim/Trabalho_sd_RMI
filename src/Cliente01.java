
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import com.google.gson.Gson;

import api.conexao.Conexao;
import game.Mapa;
import game.Menu;

@SuppressWarnings("serial")
public class Cliente01 extends JFrame implements KeyListener {
	static SevidorInterface servidor;
	static Mapa mapa;
	static int jogador;
	static Menu menu;
	static Gson gson = new Gson();

	public Cliente01() {
		try {
			servidor = (SevidorInterface) Naming.lookup("rmi://172.18.9.37:3000/Servidor");
			System.out.println(servidor);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		jogador = 0;
		this.setSize(200, 200);
		addKeyListener(this);
		mapa = new Mapa();
		this.setTitle("Cliente");
		this.setSize(mapa.mapa[0].length * 35, mapa.mapa.length * 35);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.createBufferStrategy(2);
	}

	static Conexao c;

	public static void main(String[] args) throws UnknownHostException {

		Cliente01 cliente = new Cliente01();
		try {
			servidor.addJogador();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		cliente.setVisible(true);
		cliente.frames();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			try {
				servidor.mover("carro1d");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT){
			try {
				servidor.mover("carro1e");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				servidor.mover("restart");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void drawMenu(Graphics g) {
		menu.draw(g);
	}

	private void frames() {
		while (true) {
			buffer();
			try {
				mapa = gson.fromJson(servidor.atualizar(), Mapa.class);
				Thread.sleep(100);
			} catch (InterruptedException | RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void restart() {
		mapa = new Mapa();
	}

	public void jogar(Graphics g) {

		if (!mapa.jogar) {
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Inicio.png"));
			g.drawImage(image, 0, 0, mapa.mapa[0].length * 35, mapa.mapa.length * 35, null);
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("Espere os jogadores", 10, 680);
		} else {

			if (mapa.carro1.isColidiu() && mapa.carro2.isColidiu() && mapa.carro3.isColidiu()) {
				Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("GameOver.png"));
				g.drawImage(image, 0, 0, mapa.mapa[0].length * 35 - 4 * 35, mapa.mapa.length * 35, null);
				g.setColor(Color.white);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g.drawString("Aperte enter para jogar novamente", 10, 680);
			} else {
				mapa.draw(g);
				if(mapa.carro1.isColidiu()){
					try {
						servidor.colidiu(mapa.carro1.getNome());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(mapa.carro2.isColidiu()){
					try {
						servidor.colidiu(mapa.carro2.getNome());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				if(mapa.carro3.isColidiu()){
					try {
						servidor.colidiu(mapa.carro3.getNome());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void buffer() {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			jogar(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
}
