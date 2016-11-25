package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {

	public boolean left;
	public boolean right;
	public boolean a;
	public boolean d;
	public boolean g;
	public boolean j;
	public boolean enter;

	public Key() {
		left = false;
		right = false;
		enter = false;
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == e.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == e.VK_A) {
			a = true;
		}
		if (e.getKeyCode() == e.VK_D) {
			d = true;
		}
		if (e.getKeyCode() == e.VK_G) {
			g = true;
		}
		if (e.getKeyCode() == e.VK_J) {
			j = true;
		}
		if (e.getKeyCode() == e.VK_ENTER) {
			enter = true;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == e.VK_RIGHT) {
			right = false;
		}
		if (e.getKeyCode() == e.VK_A) {
			a = false;
		}
		if (e.getKeyCode() == e.VK_D) {
			d = false;
		}
		if (e.getKeyCode() == e.VK_G) {
			g = false;
		}
		if (e.getKeyCode() == e.VK_J) {
			j = false;
		}
		if (e.getKeyCode() == e.VK_ENTER) {
			enter = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void off() {
		left = false;
		right = false;
		a = false;
		d = false;
		g = false;
		j = false;
		enter = false;
	}

}
