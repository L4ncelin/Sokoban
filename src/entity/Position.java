package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Définit le positionnement d'un objet dans la map
 */
@objid ("4133d0cb-0345-4558-b92f-c426fdcecaba")
public class Position {
	
    @objid ("9223830f-8ec8-4a85-a310-2c2759e941cc")
    public int line;

    @objid ("a2d21e0b-efd7-4e77-8e22-9815bff448f6")
    public int column;

    @objid ("2d9d16cf-d068-4eb6-9c0b-c678f5cf97eb")
    public Warehouse warehouse;

    @objid ("bc37bc81-a044-4b6a-9e3d-1fd9064949b5")
    public Square square;

    /**
     * Récupère la position du carré d'à côté dasn la direction voulue
     * 
     * @param direction
     * @param positionPlayer
     * @return neighbor_position
     */
    @objid ("d9deed9c-215c-41fd-ba5a-870144adb790")
    public Position getNeighborPosition(final Direction direction, Position position_player) {
    	Position pos = new Position();
    	pos.line = position_player.line;
    	pos.column = position_player.column;
    	
    	switch(direction) {
	    	case Direction.LEFT:
	    		pos.column -= 1;
	    		break;
	    	case Direction.RIGHT:
	    		pos.column += 1;
	    		break;
	    	case Direction.UP:
	    		pos.line -= 1;
	    		break;
	    	case Direction.DOWN:
	    		pos.line += 1;
	    		break;
    	}

    	return pos;
    }

}
