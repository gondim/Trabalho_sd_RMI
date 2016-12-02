package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Carro implements Serializable{
	private String nomeCarro;
	private int[][] carro = { { 0, 1, 0 }, { 1, 2, 1 }, { 0, 1, 0 }, { 1, 1, 1 } };
	private int lado;
	private boolean colidiu;
	public Rectangle coliderPlayer;
	private long pontuacao;
	private int dp;

	public Carro(String nomeCarro) {
		this.nomeCarro = nomeCarro;
		if (nomeCarro.equals("carro1")) {
			lado = 1;
		} else if (nomeCarro.equals("carro2")) {
			lado = 3;
		} else if (nomeCarro.equals("carro3")) {
			lado = 5;
		}
		colidiu = false;
		pontuacao = 0;
		dp = 10;
	}

	public boolean isColidiu() {
		return colidiu;
	}

	public void setColidiu(boolean colidiu) {
		if(colidiu)
			lado = 0;
		this.colidiu = colidiu;
	}

	public int isLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public long getPontuacao() {
		return pontuacao;
	}
	
	public String getNome(){
		return nomeCarro;
	}

	public void draw(Graphics g) {
		int size = 35;
		if (!isColidiu()) {
			for (int i = 0; i < carro.length; i++) {
				for (int j = 0; j < carro[0].length; j++) {
					if (carro[i][j] == 0)
						g.setColor(Color.gray);
					if (carro[i][j] == 1) {
						if (nomeCarro.equals("carro1")) {
							g.setColor(Color.blue);
						} else if (nomeCarro.equals("carro2")) {
							g.setColor(Color.red);
						} else if (nomeCarro.equals("carro3")) {
							g.setColor(Color.white);
						}

					}
					g.fillRect(j * size + (35 * pi(lado)), i * size + (35 * 16), size, size);
				}
			}
			coliderPlayer = new Rectangle(35 * pi(lado), 35 * 16, 35 * 3, 35 * 4 - 15);
		} else {
			coliderPlayer = new Rectangle();
		}
	}
	
	public void mover(){
		
	}

	public int pi(int lado) {
		return 1 + ((lado - 1) * 4);
	}

	public void pont() {
		if(!isColidiu())
			pontuacao = pontuacao + dp;
	}
}
