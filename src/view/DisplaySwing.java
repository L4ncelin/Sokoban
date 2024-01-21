package view;


import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.Controller;

/**
 * Affiche la map dans la fenêtre Swing
 */
public class DisplaySwing extends JPanel{
	
	Controller controller;
	
	Image player_img;
	Image box_img;
	Image wall_img;
	Image floor_img;
	Image floor_goal_img;
	
	Integer width;
	Integer height;
	
	String map_string;
	
	/**
	 * Initialise toutes les images du jeu
	 * 
	 * @param c
	 */
	public DisplaySwing(Controller c) {
		controller = c;
		
		File player_file = new File("SokobanImages\\player.png");
		File box_file = new File("SokobanImages\\box.png");
		File wall_file = new File("SokobanImages\\wall.png");
		File floor_file = new File("SokobanImages\\floor.png");
		File floor_goal_file = new File("SokobanImages\\floor_goal.png");
		
		try {
			player_img = ImageIO.read(player_file);
			box_img = ImageIO.read(box_file);
			wall_img = ImageIO.read(wall_file);
			floor_img = ImageIO.read(floor_file);
			floor_goal_img = ImageIO.read(floor_goal_file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer new_width = 64;
		Integer new_height = 64;
		
		player_img = player_img.getScaledInstance(new_width, new_height, Image.SCALE_DEFAULT);
		box_img = box_img.getScaledInstance(new_width, new_height, Image.SCALE_DEFAULT);
		wall_img = wall_img.getScaledInstance(new_width, new_height, Image.SCALE_DEFAULT);
		floor_img = floor_img.getScaledInstance(new_width, new_height, Image.SCALE_DEFAULT);
		floor_goal_img = floor_goal_img.getScaledInstance(new_width, new_height, Image.SCALE_DEFAULT);
		
		width = player_img.getWidth(null);
		height = player_img.getHeight(null);
	}
	
	/**
	 * Affiche la "map_string" de l'entrepôt dans la fenêtre Swing
	 */
	@Override
	public void paint(Graphics g) {	
		
		map_string = controller.warehouse.map_string_swing;
		
		int line = 0;
    	int column = 0;

    	for (int i = 0; i < map_string.length(); i++) {
    		switch(map_string.charAt(i)) {
	    		case ' ': // Sol
    				g.drawImage(floor_img, column, line, null);
	    			column += width;
	    			break;
	    		case 'o': // Espace pour le bon affichage de la map
	    			column += width;
	    			break;
	    		case '#': // Mur
	    			g.drawImage(wall_img, column, line, null);
	    			column += width;
	    			break;
	    		case '$': // Boîte
	    			g.drawImage(box_img, column, line, null);
	    			column += width;
	    			break;
	    		case '@': // Personnage
	    			g.drawImage(floor_img, column, line, null);
	    			g.drawImage(player_img, column, line, null);
	    			column += width;
	    			break;
	    		case '.': // Objectif
	    			g.drawImage(floor_goal_img, column, line, this);
	    			column += width;
	    			break;
	    		case '\n': // Retour à la ligne
	    			line += height;
	    			column = 0;
	    			break;	
    		}
    	}
	}

}
