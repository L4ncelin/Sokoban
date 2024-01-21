package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Généralizattion de "Floor" et "Wall", c'est un objet dans la map
 */
@objid ("4de999e6-0fcc-40ae-b539-472e69e7c881")
public class Square {
	
    @objid ("3311b4a4-c543-4f69-831c-a8892fdc6a09")
    public Position position;

    @objid ("07fdce0a-d5f3-4a2f-8b91-8664fb42dddb")
    public Warehouse warehouse;
    
    public Square(Warehouse w) {
    	warehouse = w;
    }
}
