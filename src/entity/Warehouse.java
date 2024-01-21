package entity;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Entrepôt : Il va contneir tous les objets présents en jeu
 */
@objid ("ae22fb5b-7a80-4243-b27c-42464bf416da")
public class Warehouse {
	
	public Floor neighbor_floor;
    public Wall neighbor_wall;
    
    public int line_number = 0;
    public int nb_goals = 0;
	
    public String level1 = 
			 "#######\n"
			+"#     #\n"
			+"#     #\n"
			+"#. #  #\n"
			+"#. $$ #\n"
			+"#.$$  #\n"
			+"#.#  @#\n"
			+"#######\n";
    
    public String level2 = 
    		  "oooo#####\n"
			+ "oooo#   #\n"
			+ "oooo#$  #\n"
			+ "oo###  $##\n"
			+ "oo#  $ $ #\n"
			+ "### # ## #ooo######\n"
			+ "#   # ## #####  ..#\n"
			+ "# $  $          ..#\n"
			+ "##### ### #@##  ..#\n"
			+ "oooo#     #########\n"
			+ "oooo#######\n";
    
	public String map_string = "";

	public String map_string_swing = "";

    @objid ("c021e994-490f-4ee6-92f1-e00b92a567f1")
    public ArrayList<Square> square_array = new ArrayList<Square>();
    
    /**
     * Initialisation de l'entrepôt avec la map choisit
     * 
     * @param man
     * @param level
     */
    public Warehouse(Man man, Integer level) {
    	if (level == 1) {
    		map_string = level1;
    	}else if (level == 2) {
    		map_string = level2;
    	}
    	else {
    		System.out.println("### Erreur : choix du niveau mal enregistré... ###\n");
    	}
    	readMap(man);
    }
    

    /**
     * Parcourt tous les objets de square_array et renvoit le "Floor" ou "Wall" correspondant à la position
     * 
     * @param position_square
     */
    @objid ("b0ecbc06-2ecf-4b83-a9a8-a5c1ee94f744")
    public void getSquare(final Position position_square) {
    	for(int i = 0; i < square_array.size(); i++) {
    		if (square_array.get(i).position.line == position_square.line) {
    			if (square_array.get(i).position.column == position_square.column) {
    				
    				if(square_array.get(i) instanceof Floor) { //Si l'objet est de type "Floor"
    					neighbor_floor = (Floor)square_array.get(i);
    					neighbor_wall = new Wall(this);
    				}
    				else if (square_array.get(i) instanceof Wall) { // si l'objet est de type "Wall"
    					neighbor_wall = (Wall)square_array.get(i);
    					neighbor_floor = new Floor(this);
    				}
    				else {
    					System.out.println("### Erreur : Objet de la map non reconnut... ###\n");
    				}
    			}
    		}
    	}
    }
    
    
    /**
     * Initialisation de tous les objets présents dans la map par lecture de la map_string
     * 
     * @param man
     */
    public void readMap(Man man) {
    	
    	int line = 0;
    	int column = 0;

    	for (int i = 0; i < map_string.length(); i++) {
    		switch(map_string.charAt(i)) {
	    		case ' ': // Sol
    				Floor floor = new Floor(this);
    				
    				floor.position = new Position();
    				
    				floor.position.line = line;
    				floor.position.column = column;
    				
    				floor.warehouse = this;
	    			
	    			square_array.add(floor);
	    			column++;
	    			break;
	    		case 'o': // Ni sol Ni mur, seulement pour le bon affichage dans Swing
	    			Floor not_floor = new Floor(this);
    				
	    			not_floor.position = new Position();
    				
	    			not_floor.position.line = line;
	    			not_floor.position.column = column;
    				
	    			not_floor.warehouse = this;
	    			
	    			not_floor.not_floor = true;
	    			
	    			square_array.add(not_floor);
	    			column++;
	    			break;
	    		case '#': // Mur
	    			Wall wall = new Wall(this);
    				
	    			wall.position = new Position();
	    			wall.position.warehouse = this;
    				wall.position.line = line;
    				wall.position.column = column;
    				
	    			square_array.add(wall);

	    			column++;
	    			break;
	    		case '$': // Boîte
	    			Floor box = new Floor(this);
	    			
	    			box.position = new Position();
	    			
	    			box.position.line = line;
	    			box.position.column = column;
    				
	    			box.box_here = true;
    				
	    			box.warehouse = this;  			
	    			
	    			square_array.add(box);
	    			column++;
	    			break;
	    		case '@': // Joueur
	    			Floor player = new Floor(this);
	    			
	    			player.position = new Position();
    				
	    			player.position.line = line;
	    			player.position.column = column;
    				
	    			player.player_here = true;
    				
	    			player.warehouse = this;
	    			
	    			square_array.add(player);
	    			
	    			man.floor = player;
	    			man.playerPosition.line = line;
	    			man.playerPosition.column = column;
	    			column++;
	    			break;
	    		case '.': // Objectif
    				Floor goal = new Floor(this);
    				
    				goal.position = new Position();
    				
    				goal.position.line = line;
    				goal.position.column = column;
    				
    				goal.warehouse = this;
    				
    				goal.isGoal = true;
    				nb_goals++;
	    			
	    			square_array.add(goal);
	    			column++;
	    			break;
	    		case '\n': // Retour à la ligne
	    			line ++;
	    			column = 0;
	    			line_number++;
	    			break;	
    		}
    	}
    }
    
    /**
     * Met à jour la map_string après l'action du joueur dans la map
     * 
     * @return nb_box_on_goal
     */
    public Integer UpdateMap() {
    	
    	Integer nb_box_on_goal = 0;
    	
    	map_string = new String();
    	map_string_swing = new String();
    	
    	ArrayList<String> string_array = new ArrayList<String>();
    	ArrayList<String> string_array_swing = new ArrayList<String>();
    	
    	for(int i = 0; i <= line_number;i++) { // Initilisation de toutes les lignes qui seront affichées
    		string_array.add("");
    		string_array_swing.add("");
    	}
    	
    	for(Square sqr : square_array) { // on parcourt la list et on ajoute le caractère correspondant à l'objet à la bonne position
    		if (sqr instanceof Floor) {
    	
    			Floor flr = (Floor)sqr;
    			if (flr.player_here) {
    				// "set" pour remplacer la ligne par une nouvelle composée de l'ancienne plus un nouveau caractère
    				string_array.set(sqr.position.line, string_array.get(sqr.position.line) + '@');
    				string_array_swing.set(sqr.position.line, string_array_swing.get(sqr.position.line) + '@');
    			}else if (flr.box_here) {
    				string_array.set(sqr.position.line, string_array.get(sqr.position.line) + '$');
    				string_array_swing.set(sqr.position.line, string_array_swing.get(sqr.position.line) + '$');
    				if (flr.isGoal) {
    					nb_box_on_goal++;
    				}
    			}
    			else if(flr.isGoal) {
    				string_array.set(sqr.position.line, string_array.get(sqr.position.line) + '.');
    				string_array_swing.set(sqr.position.line, string_array_swing.get(sqr.position.line) + '.');
    			}
    			else {
    				string_array.set(sqr.position.line, string_array.get(sqr.position.line) + ' ');
    				if (flr.not_floor) {
    					string_array_swing.set(sqr.position.line, string_array_swing.get(sqr.position.line) + 'o');
    				}else {
    					string_array_swing.set(sqr.position.line, string_array_swing.get(sqr.position.line) + ' ');
    				}
    			}
    			
    			
    		}else if (sqr instanceof Wall) {
    			string_array.set(sqr.position.line, string_array.get(sqr.position.line) + '#');
    			string_array_swing.set(sqr.position.line, string_array_swing.get(sqr.position.line) + '#');
			}else {
    		}
    	}

    	// On concatène toutes les lignes de la liste string_array
    	for (String string : string_array) {
    		map_string = map_string + string + "\n";
    	}
    	
    	for (String string : string_array_swing) {
    		map_string_swing = map_string_swing + string + "\n";
    	}
    	
    return nb_box_on_goal;	
    }
    	
}
