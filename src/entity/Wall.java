package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Objet qui bloque le personnage dans la map
 */
@objid ("a2b4389b-51ca-4184-90cc-023d05c1e3fb")
public class Wall extends Square {
	
	Warehouse warehouse;
	
	public Wall(Warehouse w) {
		super(w);
		warehouse = w;
	}
	
    /**
     * Le personnage ne peut pas se déplacer sur un mur
     * 
     * @return Boolean
     */
    @objid ("42c348cd-0bee-471a-a70b-202fcb1d0f37")
    public Boolean accept() {
    	return false;
    }

}
