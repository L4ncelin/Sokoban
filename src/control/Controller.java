package control;

import view.DisplaySwing;
import view.View;
import view.ViewSwing;

import java.util.Scanner;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.Direction;
import entity.Man;
//import entity.Square;
import entity.Warehouse;


/**
 * Initialise le jeu et fait le lien entre la vue et le modèle
 */
@objid ("f3841d40-6ee8-4ff5-b25e-d92c071d02b9")
public class Controller {
	@objid ("482e1178-bb69-47fb-ba69-fe5e66579c83")
    public Warehouse warehouse;

    @objid ("0ef703da-21ad-4c2e-878c-f438445b8965")
    public Man man;
    
	public boolean win; // Flag pour identifier si le joueur à gangner ou pas encore
	public Integer nb_box_on_goal; // nombre de boîte à bien placer pour gagner
	public Integer level; // Niveau choisit
	
	View view;
	ViewSwing view_swing;
	DisplaySwing display_swing;
	
	/**
	 * Mise en place du jeu : choix du niveau (1/2) et de l'interface (cmd/swing)
	 */
	public Controller() {	
		
		man = new Man();
		
		nb_box_on_goal = 0;
		
		Boolean level_selected = false;
		Boolean game_started = false;
		
		System.out.println(
				 "#############################\n"
				+"# Bienvenue dans le Sokoban #\n"
				+"#############################\n");
		
		while(!level_selected) {
			String level_choice = getLevelChoice();
			switch(level_choice) {
				case "1":
					warehouse = new Warehouse(man, 1);
					level = 1;
					level_selected = true;
					break;
				case "2":
					warehouse = new Warehouse(man, 2);
					level = 2;
					level_selected = true;
					break;
				default:
					System.out.println("### Erreur : Choix du niveau mal rensiegné... ###\n");
			}
		}
		
		while (!game_started) {
			String choice = getViewChoice();
			switch(choice) {
			case "cmd":
				game_started = true; 
				Play_CMD_Game();
				break;
				
			case "swing":
				game_started = true;
				System.out.println("Ouverture de l'interface Swing ...");
				Play_SWING_Game();
				break;
				
			default:
				System.out.println("### Erreur : Choix de l'inteface mal rensiegné... ###\n");
			}
		}
		
	}
	
    /**
     * Lancement d'une partie avec l'interface en cmd
     */
    public void Play_CMD_Game() {
    	
    	if (view == null) { // Si une vue existe déjà car on restart pas besoin de la recréer
    		view = new View();
    	}	
    	
		win = false;
		
		warehouse = new Warehouse(man, level); // On initialise l'entrepot avec le niveau choisit
		
		nb_box_on_goal = warehouse.UpdateMap();
		
		while(win == false) { // On continue la partie tant que le joueur n'a pas gagné
			Direction user_direction = view.playRound(warehouse.map_string);
			
			Action(user_direction); // On traite l'action du joueur à chaque tour

			System.out.println("Points marqués : " + nb_box_on_goal);
		}
		view.showMap(warehouse.map_string);
		
		System.out.println("Bravo vous avez gagné !!!"); // Fin de partie
		
		if (view.getRestartChoice()) { // possibilité de recommencer une partie
			Play_CMD_Game();
		}else {
			System.exit(0);
		}
    }
    
    /**
     * Lancement d'une partie avec une interface Swing
     */
    public void Play_SWING_Game() {
    	
		if (display_swing == null) { // Si une vue existe déjà car on restart pas besoin de la recréer
			display_swing = new DisplaySwing(this);
	    	view_swing = new ViewSwing(this, display_swing);
		}
    	
    	win = false;
		
		warehouse = new Warehouse(man, level); // On initialise l'entrepot avec le niveau choisit
		
		nb_box_on_goal = warehouse.UpdateMap();
    }

    /**
     * Traitement d'une action de l'utilisateur pour bouger son personnage
     * 
     * @param user_direction
     */
    @objid ("961b29d7-b040-4f14-aa65-31b629cc7289")
    public void Action(final Direction user_direction) {
    	
    	man.move(user_direction); // On traite le mouvement du joueur
    	
    	nb_box_on_goal = warehouse.UpdateMap(); // On met à jour la carte
    	
    	checkWin(nb_box_on_goal); // On vérifie si la partie est gagnée
    }
    
    /**
     * Vérifie si toutes les boîtes sont placées sur les cibles, dans ce cas la partie est gagnée
     * 
     * @param nb_box_on_goal
     * @return Boolean
     */
    public boolean checkWin(Integer nb_box_on_goal) {
    	if(nb_box_on_goal == warehouse.nb_goals) {
    		warehouse.UpdateMap();
    		win = true;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * On demande au joueur quel niveau il veut jouer (1/2)
     * 
     * @return user_choice
     */
    public String getLevelChoice() {
    	Scanner user_input = new Scanner(System.in);
	    System.out.println("Quel niveau veux tu jouer ? (1/2)");

	    String user_choice = user_input.nextLine(); // On récupère le choix de l'utilisateur
	    
	    return user_choice;
    }    
    
    /**
     * On demande au joueur quel interface il veut utiliser (cmd/swing)
     * 
     * @return user_choice
     */
    public String getViewChoice() {
    	Scanner user_input = new Scanner(System.in);
	    System.out.println("Quel interface veux-tu utiliser ? (cmd/swing)");

	    String user_choice = user_input.nextLine(); // On récupère le choix de l'utilisateur
	    
	    return user_choice;
    }   
}
