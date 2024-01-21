package view;

import java.util.Scanner;

import entity.Direction;

/**
 * Sert à afficher le jeu au joueur et à recevoir des informations de celui-ci qu'il enverra au Controller
 */
public class View {
	
	/**
	 * Joue un tour du jeu en affichant la carte et en récupérant l'action du joueur qui sera traitée par le modèle
	 * 
	 * @param map_string
	 * @return user_irection
	 */
	public Direction playRound(String map_string) {
		
		showMap(map_string); // On affiche la map
		
		Direction user_direction = getAction(); // On récupère le choix
		
		return user_direction; // On envoie l'action de l'utilisateur au controller 
	}
	
	/**
	 * Affiche la map dans le CMD
	 * 
	 * @param map_string
	 */
	public void showMap(String map_string) {
		System.out.println(map_string);
	}
	
	/**
	 * Récupère l'action de déplacement de l'utilisateur
	 * 
	 * @return user_direction
	 */
	public Direction getAction() {
		
		Direction user_direction = null;
		
	    Boolean user_direction_valid = false;
	    
	    while (!user_direction_valid) { // Tant que le choix n'est pas valide on redemande à l'utilisateur
    	Scanner userInput = new Scanner(System.in);
	    System.out.println("Quel action vas-tu faire ? (D/G/H/B) ou (Z/Q/S/D)");

	    String user_action = userInput.nextLine(); // On récupère le choix de l'utilisateur

	    switch(user_action) {
		case "G":
		case "g":
		case "Q":
		case "q":
			user_direction = Direction.LEFT;
			user_direction_valid = true;
			break;
		case "D":
		case "d":
			user_direction = Direction.RIGHT;
			user_direction_valid = true;
			break;
		case "H":
		case "h":
		case "Z":
		case "z":
			user_direction = Direction.UP;
			user_direction_valid = true;
			break;
		case "B":
		case "b":
		case "S":
		case "s":
			user_direction = Direction.DOWN;
			user_direction_valid = true;
			break;	
		default:
			System.out.println("### Erreur : Choix de direction non valide... ###\n");
    	}}
	    return user_direction;
	}
	
	/**
     * On demande au joueur s'il veut recommencer à jouer
     * 
     * @return Boolean
     */
    public Boolean getRestartChoice() {
    	
    	Boolean user_choice_valid = false;
    	
    	while (!user_choice_valid) {
    		
	    	Scanner user_input = new Scanner(System.in);
		    System.out.println("Voulez-vous rejouer ? (oui/non)");
	
		    String user_choice = user_input.nextLine(); // On récupère le choix de l'utilisateur
		    
		    switch(user_choice) {
				case "oui":
				case "OUI":
				case "o":
				case "yes":
				case "y":
					user_choice_valid = true;
					return true;
				case "non":
				case "NON":
				case "n":
				case "no":
					user_choice_valid = true;
					return false;
			default:
					System.out.println("### Erreur : Mauvais choix rentré... ###\n");
		    }
    	}
    	return false;
    }
}
