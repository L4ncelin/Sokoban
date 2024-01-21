package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Sol permet au joueur de se déplacer dessus, ou de poser des boîtes dessus. Certains sols sont les objectifs
 */
@objid ("485a9672-fb44-4519-85b8-f06e3220a0ee")
public class Floor extends Square {
	
    public Floor(Warehouse w) {
		super(w);
		warehouse = w;
	}

	@objid ("f0d7e105-097a-483c-9596-6f3177648d85")
    public boolean isGoal = false;

    public Boolean player_here = false;
    
    public Boolean box_here = false;
    
    public Boolean not_floor = false;
    
    @objid ("90171f41-3f58-409b-bf9d-d6295b26940b")
    public Moveable moveable;
    
    public Floor neighbor_floor;
    public Wall neighbor_wall;

    @objid ("8b0dd9de-c8b2-40a3-badb-a9d6a8b239ab")
    public void getNeighborSquare(final Direction direction, Position positionPlayer) {
    	Position positionNeighborSquare = this.position.getNeighborPosition(direction, positionPlayer);
    
    	warehouse.getSquare(positionNeighborSquare);
    	
    	neighbor_floor = warehouse.neighbor_floor;
    	neighbor_wall = warehouse.neighbor_wall;
    	
    }

    @objid ("25ce7916-7cfb-4885-aa54-a70d632ae552")
    public void leave(final Man man) {
    	if (man != null) {
        	player_here = false;    		
    	}else {
    		box_here = false;
    	}
    }

    @objid ("5020c59a-5d05-4b31-b74b-0dfbc8c94499")
    public Boolean accept(final Man man,final Direction direction, Box box) {
    	if (box_here && man != null) { // il y a une caisse à côté du joueur
    		Box bx = new Box();
    		bx.floor = this;
    		Boolean box_moved = bx.moveBox(direction);
    		
    		if (box_moved) { // la boîte à bougée
    			man.playerPosition.line = this.position.line;
            	man.playerPosition.column = this.position.column;
            	
            	player_here = true;
            	return true;
    		}else { // la boîte n'a pas bougée
    			return false;
    		}   		
    	}else if (box_here && man == null) { // On ne peut pas pousser plusieurs caisses
    		return false;
    	}
    	else if(box == null){ // il n'y a pas de caisses à côté du joueur
    		man.playerPosition.line = this.position.line;
        	man.playerPosition.column = this.position.column;
        	
        	player_here = true;
        	return true;
    	}else {  // Il n'y a pas de caisse à côté de la caisse qui est elle même à côté du joueur  		
    		box_here = true;
    		return true;
    	}
    }

}
