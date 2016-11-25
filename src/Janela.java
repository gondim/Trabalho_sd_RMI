import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import game.Key;
import game.Mapa;
import game.Menu;

@SuppressWarnings("serial")
public class Janela extends JFrame {
	Key key;
	Mapa mapa;
	Menu menu;
	boolean jogar;

	public Janela() {
		jogar = false;
		key = new Key();
		mapa = new Mapa();
		menu = new Menu();
		this.addKeyListener(key);
		this.setSize(mapa.mapa[0].length * 35, mapa.mapa.length * 35);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.createBufferStrategy(2);
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
		if (!jogar) {
			drawMenu(g);
			if (key.enter)
				jogar = true;
		} else {
			if (mapa.carro1.isColidiu() && mapa.carro2.isColidiu() && mapa.carro3.isColidiu()) {
				Image image = Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("GameOver.png"));
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

	public static void main(String[] args) {
		Janela janela = new Janela();
		janela.frames();
	}
}
