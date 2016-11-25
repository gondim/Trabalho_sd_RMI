
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.google.gson.Gson;

import api.ConexaoListener;
import api.MensagemListener;
import api.conexao.Conexao;
import api.peer.Peer;
import game.Key;
import game.Mapa;
import game.Menu;

public class Servidor extends JFrame {

	static Key key;
	static int jogador;
	static Mapa mapa;
	static Menu menu;
	static Gson gson = new Gson();

	public Servidor() {
		jogador = 0;
		key = new Key();
		mapa = new Mapa();
		menu = new Menu();
		this.setTitle("Servidor");
		this.addKeyListener(key);
		this.setSize(mapa.mapa[0].length * 35, mapa.mapa.length * 35);
		// this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.createBufferStrategy(2);
	}

	public static void main(String[] args) {

		Peer peer = Peer.getIntance(9500, "UDP");
		System.out.println("servidor iniciado");
		peer.iniciarRecebimentoConexao(new ConexaoListener() {

			@Override
			public void recebidoConexao(Conexao conexao) {

				gerenciarConexao(conexao);

			}
		});

		Servidor s = new Servidor();
		s.frames();

	}

	public static void gerenciarConexao(final Conexao conexao) {
		conexao.iniciarRecebimento(new MensagemListener() {

			public void recebido(String mensagem) {

				if (mensagem.equals("carro1d")) {
					key.right = true;
					mapa.mover(key);
				} else if (mensagem.equals("carro1e")) {
					key.left = true;
					mapa.mover(key);
				} else if (mensagem.equals("carro2d")) {
					key.d = true;
					mapa.mover(key);
				} else if (mensagem.equals("carro2e")) {
					key.a = true;
					mapa.mover(key);
				} else if (mensagem.equals("carro3d")) {
					key.j = true;
					mapa.mover(key);
				} else if (mensagem.equals("carro3e")) {
					key.g = true;
					mapa.mover(key);
				} else if (mensagem.equals("inicio")) {
					jogador++;
					System.out.println(jogador);
				} else if (mensagem.equals("enter")){
					key.enter = true;
					mapa.mover(key);
				}
				String json = gson.toJson(mapa);
				conexao.send(json);

				// Teste teste = (Teste) mensagem;
				// System.out.println("RECEBIDO> " + teste.getTeste());

				// conexao.send(mensagem);

			}
		});
	}

	public void drawMenu(Graphics g) {
		menu.draw(g);
	}

	private void frames() {
		while (true) {
			buffer();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void restart() {
		mapa = new Mapa();
	}

	public void jogar(Graphics g) {

		if (jogador < 3) {
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Inicio.png"));
			g.drawImage(image, 0, 0, mapa.mapa[0].length * 35, mapa.mapa.length * 35, null);
			g.setColor(Color.black);
			g.fillRect(0, 0, mapa.mapa[0].length * 35, mapa.mapa.length * 35);
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("Espere os jogadores", 10, 680);
		} else {
			mapa.jogar = true;
			if (mapa.carro1.isColidiu() && mapa.carro2.isColidiu() && mapa.carro3.isColidiu()) {
				Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("GameOver.png"));
				g.drawImage(image, 0, 0, mapa.mapa[0].length * 35 - 4 * 35, mapa.mapa.length * 35, null);
				g.setColor(Color.white);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g.drawString("Aperte enter para jogar novamente", 10, 680);
				if (key.enter) {
					restart();
				}
			} else {
				mapa.draw(g);
				mapa.mover(key);
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
