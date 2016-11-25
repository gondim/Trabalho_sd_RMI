package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Mapa implements Serializable {
	public Carro carro1;
	public Carro carro2;
	public Carro carro3;
	List<CarroInimigo> array1 = new ArrayList<CarroInimigo>();
	List<CarroInimigo> array2 = new ArrayList<CarroInimigo>();
	List<CarroInimigo> array3 = new ArrayList<CarroInimigo>();
	private int cont;
	private int fase;
	public boolean jogar;

	public Mapa() {
		jogar = false;
		fase = 1;
		cont = 0;
		carro1 = new Carro("carro1");
		carro2 = new Carro("carro2");
		carro3 = new Carro("carro3");
		array1.add(new CarroInimigo(fase, 1));
		array1.add(new CarroInimigo(fase, 1));
		array2.add(new CarroInimigo(fase, 2));
		array2.add(new CarroInimigo(fase, 2));
		array3.add(new CarroInimigo(fase, 3));
		array3.add(new CarroInimigo(fase, 3));
	}

	public int[][] mapa = { { 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 },
			{ 0, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3 } };

	public void draw(Graphics g) {
		int size = 35;
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				if (mapa[i][j] == 0)
					g.setColor(Color.green);
				if (mapa[i][j] == 1)
					g.setColor(Color.gray);
				if (mapa[i][j] == 2)
					g.setColor(Color.yellow);
				if (mapa[i][j] == 3)
					g.setColor(Color.black);
				g.fillRect(j * size, i * size, size, size);
			}
		}
		placar(g);
		carro1.draw(g);
		carro2.draw(g);
		carro3.draw(g);
		array1.get(0).draw1(g);
		array2.get(0).draw2(g);
		array3.get(0).draw3(g);
		if (array1.get(0).getY() >= 400) {
			array1.get(1).draw1(g);
			array2.get(1).draw2(g);
			array3.get(1).draw3(g);
			if (array1.get(0).getY() >= 21 * 35) {
				array1.remove(0);
				array2.remove(0);
				array3.remove(0);
				carro1.pont();
				carro2.pont();
				carro3.pont();
				cont++;
				array1.add(new CarroInimigo(fase, 1));
				array2.add(new CarroInimigo(fase, 2));
				array3.add(new CarroInimigo(fase, 3));
				if (cont == 5) {
					cont = 0;
					fase++;
				}
			}
		}
		if (carro1.coliderPlayer.intersects(array1.get(0).coliderInimigo)
				|| carro1.coliderPlayer.intersects(array2.get(0).coliderInimigo)
				|| carro1.coliderPlayer.intersects(array3.get(0).coliderInimigo)) {
			carro1.setColidiu(true);
		}
		if (carro2.coliderPlayer.intersects(array1.get(0).coliderInimigo)
				|| carro2.coliderPlayer.intersects(array2.get(0).coliderInimigo)
				|| carro2.coliderPlayer.intersects(array3.get(0).coliderInimigo)) {
			carro2.setColidiu(true);
		}
		if (carro3.coliderPlayer.intersects(array1.get(0).coliderInimigo)
				|| carro3.coliderPlayer.intersects(array2.get(0).coliderInimigo)
				|| carro3.coliderPlayer.intersects(array3.get(0).coliderInimigo)) {
			carro3.setColidiu(true);
		}

	}

	public void placar(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));

		g.drawString("Carro 1", 25 * 35 + 15, 100);
		g.drawString(carro1.getPontuacao() + "", 26 * 35 + 15, 200);

		g.drawString("Carro 2", 25 * 35 + 15, 300);
		g.drawString(carro2.getPontuacao() + "", 26 * 35 + 15, 400);

		g.drawString("Carro 3", 25 * 35 + 15, 500);
		g.drawString(carro3.getPontuacao() + "", 26 * 35 + 15, 600);
	}

	public void mover(Key e) {
		if (e.left) {
			carro1.setLado(carro1.isLado() - 1);
			if (!move(carro1, carro2.isLado(), carro3.isLado()))
				carro1.setLado(carro1.isLado() + 1);
		}
		if (e.right) {
			carro1.setLado(carro1.isLado() + 1);
			if (!move(carro1, carro2.isLado(), carro3.isLado()))
				carro1.setLado(carro1.isLado() - 1);
		}
		if (e.a){
			carro2.setLado(carro2.isLado() - 1);
			if (!move(carro2, carro1.isLado(), carro3.isLado()))
				carro2.setLado(carro2.isLado() + 1);
		}
		if (e.d){
			carro2.setLado(carro2.isLado() + 1);
			if (!move(carro2, carro1.isLado(), carro3.isLado()))
				carro2.setLado(carro2.isLado() - 1);
		}
		if (e.g){
			carro3.setLado(carro3.isLado() - 1);
			if (!move(carro3, carro1.isLado(), carro2.isLado()))
				carro3.setLado(carro3.isLado() + 1);
		}
		if (e.j){
			carro3.setLado(carro3.isLado() + 1);
			if (!move(carro3, carro1.isLado(), carro2.isLado()))
				carro3.setLado(carro3.isLado() - 1);
		}
		e.off();
	}

	public boolean move(Carro carro, int lado1, int lado2) {
		if (carro.isLado() < 1)
			return false;
		if (carro.isLado() > 6)
			return false;
		if (carro.isLado() == lado1 || carro.isLado() == lado2)
			return false;
		return true;
	}
}
