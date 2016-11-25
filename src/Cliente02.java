
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;

import com.google.gson.Gson;

import api.ConexaoListener;
import api.MensagemListener;
import api.conexao.Conexao;
import api.peer.Peer;
import game.Mapa;
import game.Menu;

@SuppressWarnings("serial")
public class Cliente02 extends JFrame implements KeyListener {

	static Mapa mapa;
	static int jogador;
	static Menu menu;
	static Gson gson = new Gson();

	public Cliente02() {
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

		Peer peer = Peer.getIntance(9701, "UDP");

		peer.iniciarRecebimentoConexao(new ConexaoListener() {

			public void recebidoConexao(Conexao conexao) {

			}
		});

		System.out.println("Cliente iniciado");

		c = peer.getConexao(InetAddress.getByName("172.18.9.37"), 9500);

		c.iniciarRecebimento(new MensagemListener() {

			@Override
			public void recebido(String mensagem) {

				// if (mensagem instanceof Mapa) {
				mapa = gson.fromJson(mensagem, Mapa.class);

				// }
				// Teste teste = (Teste) mensagem;
				// System.out.println("Servidor me disse: " + teste.getTeste());
			}
		});
		
		c.send("inicio");

		Cliente02 cliente = new Cliente02();
		cliente.setVisible(true);
		cliente.frames();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			c.send("carro2d");
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT)
			c.send("carro2e");
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
				c.send("mapa");
				Thread.sleep(250);
			} catch (InterruptedException e) {
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
				// if (key.enter) {
				// restart();
				// }
			} else {
				mapa.draw(g);
			}
		}
	}

	private void buffer() {
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			c.send("mapa");
			g = bf.getDrawGraphics();
			jogar(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
}
