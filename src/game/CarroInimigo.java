package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Random;

public class CarroInimigo implements Serializable {
	private int[][] carro = { { 1, 1, 1 }, { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
	private int y = -100;
	private boolean lado;
	public Rectangle coliderInimigo;
	public int cont;
	public int velocidade;
	int array;

	public CarroInimigo(int fase, int array) {
		this.array = array;
		speed(fase);
		cont = 0;
		Random r = new Random();
		int i = r.nextInt(2);
		if (i == 0)
			lado = true;
		else
			lado = false;
	}
	
	public void atualizar(){
		y++;
	}

	public void draw1(Graphics g) {
		int size = 35;
		if (lado) {
			for (int i = 0; i < carro.length; i++) {
				for (int j = 0; j < carro[0].length; j++) {
					if (carro[i][j] == 0)
						g.setColor(Color.gray);
					if (carro[i][j] == 1)
						g.setColor(Color.black);

					g.fillRect(j * size + p1(array), i * size + y, size, size);

				}
			}
			coliderInimigo = new Rectangle(p1(array), y, 35 * 3, 35 * 4);
		} else {
			for (int i = 0; i < carro.length; i++) {
				for (int j = 0; j < carro[0].length; j++) {
					if (carro[i][j] == 0)
						g.setColor(Color.gray);
					if (carro[i][j] == 1)
						g.setColor(Color.black);
					g.fillRect(j * size + p1(array), i * size + y, size, size);
				}
			}
			coliderInimigo = new Rectangle(p1(array), y, 35 * 3, 35 * 4);
		}
	}
	
	public void draw2(Graphics g) {
		int size = 35;
		if (lado) {
			for (int i = 0; i < carro.length; i++) {
				for (int j = 0; j < carro[0].length; j++) {
					if (carro[i][j] == 0)
						g.setColor(Color.gray);
					if (carro[i][j] == 1)
						g.setColor(Color.black);

					g.fillRect(j * size + p2(array), i * size + y, size, size);

				}
			}
			coliderInimigo = new Rectangle(p2(array), y, 35 * 3, 35 * 4);
		} else {
			for (int i = 0; i < carro.length; i++) {
				for (int j = 0; j < carro[0].length; j++) {
					if (carro[i][j] == 0)
						g.setColor(Color.gray);
					if (carro[i][j] == 1)
						g.setColor(Color.black);
					g.fillRect(j * size + p2(array), i * size + y, size, size);
				}
			}
			coliderInimigo = new Rectangle(p2(array), y, 35 * 3, 35 * 4);
		}
	}
	
	public void draw3(Graphics g) {
		int size = 35;
		if (lado) {
			for (int i = 0; i < carro.length; i++) {
				for (int j = 0; j < carro[0].length; j++) {
					if (carro[i][j] == 0)
						g.setColor(Color.gray);
					if (carro[i][j] == 1)
						g.setColor(Color.black);

					g.fillRect(j * size + p3(array), i * size + y, size, size);

				}
			}
			coliderInimigo = new Rectangle(p3(array), y, 35 * 3, 35 * 4);
		} else {
			for (int i = 0; i < carro.length; i++) {
				for (int j = 0; j < carro[0].length; j++) {
					if (carro[i][j] == 0)
						g.setColor(Color.gray);
					if (carro[i][j] == 1)
						g.setColor(Color.black);
					g.fillRect(j * size + p3(array), i * size + y, size, size);
				}
			}
			coliderInimigo = new Rectangle(p3(array), y, 35 * 3, 35 * 4);
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public void speed(int fase) {
		if (fase == 1)
			velocidade = 4;
		if (fase == 2)
			velocidade = 5;
		if (fase == 3)
			velocidade = 6;
		if (fase == 4)
			velocidade = 7;
		if (fase == 5)
			velocidade = 8;
		if (fase == 6)
			velocidade = 9;
		if (fase == 7)
			velocidade = 10;
		if (fase == 8)
			velocidade = 11;
		if (fase == 9)
			velocidade = 12;
		if (fase == 10)
			velocidade = 13;
		if (fase == 11)
			velocidade = 14;
		if (fase == 12)
			velocidade = 15;
		if (fase == 13)
			velocidade = 16;
		if (fase == 14)
			velocidade = 17;
		if (fase == 15)
			velocidade = 18;
		if (fase == 16)
			velocidade = 19;
		if (fase >= 17)
			velocidade = 20;
	}

	public int p1(int array) {
		if (lado) {
			return 5 * 35;
		} else {
			return 35;
		}
	}

	public int p2(int array) {
		if (lado) {
			return 13 * 35;
		} else {
			return 9 * 35;
		}
	}

	public int p3(int array) {
		if (lado) {
			return 21 * 35;
		} else {
			return 17 * 35;
		}
	}

}
