package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Personnage qui peut se déplacer sur les carrés de type "Floor" et qui doit bouger les Box
 */
@objid ("307b4c41-869b-43ef-91f6-7cd4d1097716")
public class Man extends Moveable {
	
	public Position playerPosition;

	/**
	 * initialisation de la position du joueur
	 */
	public Man() {
		this.playerPosition = new Position(); //Initial position
	}
	
    /**
     * récupère le carré dans la direction voulus et si déplace si c'est possible
     * 
     * @param direction
     */
    @objid ("b4a2f7ee-d3f2-4f26-82c8-27a28786d7a5")
    public void move(final Direction direction) {
    	floor.getNeighborSquare(direction, playerPosition);
    	Boolean accepted = false;
    	
    	if (floor.neighbor_floor.position != null) { // Le carré d'à côté est de type "Floor"
    		accepted = floor.neighbor_floor.accept(this,direction, null);
    		
    	}else if (floor.neighbor_wall.position != null) { // Le carré d'à côté est de type "Wall"
    		accepted = floor.neighbor_wall.accept();
    	}
    	
    	if (accepted) { // Si rien n'empèche le déplacement, on se détache du "Floor" actuel et on se rattache à celui voulus
    		floor.leave(this);
    		this.floor = floor.neighbor_floor;
    	}
    }

}
