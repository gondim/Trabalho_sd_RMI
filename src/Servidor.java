

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import com.google.gson.Gson;

import api.MensagemListener;
import api.conexao.Conexao;
import game.Key;
import game.Mapa;
import game.Menu;

public class Servidor extends UnicastRemoteObject implements SevidorInterface {

	static Key key;
	static int jogador;
	static Mapa mapa;
	static Menu menu;
	static Gson gson = new Gson();

	public Servidor() throws RemoteException {
		super();
		jogador = 0;
		key = new Key();
		mapa = new Mapa();
		menu = new Menu();
		System.out.println("shark boy");
		// this.setTitle("Servidor");
		// this.addKeyListener(key);
		// this.setSize(mapa.mapa[0].length * 35, mapa.mapa.length * 35);
		// this.setUndecorated(true);
		// this.setLocationRelativeTo(null);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setResizable(false);
		// this.setVisible(true);
		// this.createBufferStrategy(2);
	}

	public static void main(String[] args) {

		try {
			System.out.println("chamando servidor...");
			LocateRegistry.createRegistry(3000);
			System.setProperty("java.rmi.server.hostname", "172.18.9.37");
			Servidor s = new Servidor();
			Naming.rebind("rmi://172.18.9.37:3000/Servidor", s);
			s.frames();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
				} else if (mensagem.equals("enter")) {
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

	public void frames() {
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

	public void jogar() {
		/*
		 * if (jogador < 3) { Image image =
		 * Toolkit.getDefaultToolkit().getImage(getClass().getResource(
		 * "Inicio.png")); g.drawImage(image, 0, 0, mapa.mapa[0].length * 35,
		 * mapa.mapa.length * 35, null); g.setColor(Color.black); g.fillRect(0,
		 * 0, mapa.mapa[0].length * 35, mapa.mapa.length * 35);
		 * g.setColor(Color.white); g.setFont(new Font("TimesRoman", Font.PLAIN,
		 * 30)); g.drawString("Espere os jogadores", 10, 680); } else {
		 * mapa.jogar = true; if (mapa.carro1.isColidiu() &&
		 * mapa.carro2.isColidiu() && mapa.carro3.isColidiu()) { Image image =
		 * Toolkit.getDefaultToolkit().getImage(getClass().getResource(
		 * "GameOver.png")); g.drawImage(image, 0, 0, mapa.mapa[0].length * 35 -
		 * 4 * 35, mapa.mapa.length * 35, null); g.setColor(Color.white);
		 * g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); g.drawString(
		 * "Aperte enter para jogar novamente", 10, 680); if (key.enter) {
		 * restart(); } } else {
		 */
		if (jogador < 3) {
		} else {
			if (mapa.carro1.isColidiu() && mapa.carro2.isColidiu() && mapa.carro3.isColidiu()) {
				
			} else {
				mapa.jogar = true;
				mapa.atualizar();
				mapa.mover(key);
			}
		}

		/*
		 * } }
		 */
	}

	private void buffer() {

		jogar();

	}

	@Override
	public void mover(String massage) throws RemoteException {
		if (massage.equals("carro1d")) {
			key.right = true;
			mapa.mover(key);
		} else if (massage.equals("carro1e")) {
			key.left = true;
			mapa.mover(key);
		} else if (massage.equals("carro2d")) {
			key.d = true;
			mapa.mover(key);
		} else if (massage.equals("carro2e")) {
			key.a = true;
			mapa.mover(key);
		} else if (massage.equals("carro3d")) {
			key.j = true;
			mapa.mover(key);
		} else if (massage.equals("carro3e")) {
			key.g = true;
			mapa.mover(key);
		} else if (massage.equals("restart")) {
			if(mapa.carro1.isColidiu() && mapa.carro2.isColidiu() && mapa.carro3.isColidiu())
				restart();
		}
	}

	@Override
	public String atualizar() throws RemoteException {
		String json = gson.toJson(Servidor.mapa);
		return json;
	}

	@Override
	public void addJogador() throws RemoteException {
		jogador++;
		System.out.println(jogador);
	}

	@Override
	public void colidiu(String carro) throws RemoteException {
		if (carro.equals(mapa.carro1.getNome())) {
			mapa.carro1.setColidiu(true);
		} else if (carro.equals(mapa.carro2.getNome())) {
			mapa.carro2.setColidiu(true);
		} else if (carro.equals(mapa.carro3.getNome())) {
			mapa.carro3.setColidiu(true);
		}
	}

}
