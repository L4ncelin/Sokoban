package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Objet qui doit être bougé par le joueur sur les cibles 
 */
@objid ("20154d55-4ee2-4705-a864-160ccf34ceb1")
public class Box extends Moveable {
	
	/**
	 * récupère le carré dans la direction voulus et si déplace si c'est possible
	 * 
	 * @param direction
	 * @return Boolean
	 */
	public Boolean moveBox(final Direction direction) {
    	floor.getNeighborSquare(direction, floor.position);
    	Boolean accepted = false;
    	if (floor.neighbor_floor.position != null) { // Le carré d'à côté est de type "Floor"
    		accepted = floor.neighbor_floor.accept(null,direction,this);
    		
    	}else if (floor.neighbor_wall.position != null) { // Le carré d'à côté est de type "Wall"
    		accepted = floor.neighbor_wall.accept();
    	}
    	
    	
    	if (accepted) { // Si rien n'empèche le déplacement, on se détache du "Floor" actuel et on se rattache à celui voulus
    		floor.leave(null);
    		this.floor = floor.neighbor_floor;
    		return true;
    	}else {
    		return false;
    	}
    }
}
