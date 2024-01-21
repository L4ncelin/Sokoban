package view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.Controller;
import entity.Direction;

/**
 * Fenêtre Swing qui va contenir l'affichage de la map et aussi servir de "KeyListener"  
 */
public class ViewSwing extends JFrame implements KeyListener{
	
	Controller controller;
	DisplaySwing display_swing;
	
	/**
	 * Initialisation de la fenêtre Swing
	 * 
	 * @param c
	 * @param d
	 */
	public ViewSwing(Controller c, DisplaySwing d) {
		controller = c;
		display_swing = d;
				
		setTitle("Sokoban");
		
		addKeyListener(this);
		add(display_swing);
		
		setPreferredSize(new Dimension(600,600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    	
    	pack();
    	setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Lorsqu'une touche est préssée, il y a une action du joueur
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_LEFT:
			controller.Action(Direction.LEFT);
			this.repaint();
			break;
		case KeyEvent.VK_RIGHT:
			controller.Action(Direction.RIGHT);
			this.repaint();
			break;
		case KeyEvent.VK_UP:
			controller.Action(Direction.UP);
			this.repaint();
			break;
		case KeyEvent.VK_DOWN:
			controller.Action(Direction.DOWN);
			this.repaint();
			break;
		}
		
		if (controller.win) { // Si on a gagné on affiche une fenêtre proposant de recommencer 
			Integer restart_choice = JOptionPane.showConfirmDialog(this, "Vous Avez gagné ! \nVoulez-vous recommencer ?");
			if (restart_choice == 0) {
				controller.Play_SWING_Game();
				this.repaint();
			}else {
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
