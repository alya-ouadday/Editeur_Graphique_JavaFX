package shapes;

import javafx.scene.shape.Ellipse;

public class EllipseCopy extends Ellipse { 
	//represent copy of an ellipse 
	public EllipseCopy(Ellipse e){
		this.setFill(e.getFill());
		this.setCenterX(e.getCenterX()+10);//decalage pour voir le clonage
		this.setCenterY(e.getCenterY()+10);
		this.setRadiusX(e.getRadiusX());
		this.setRadiusY(e.getRadiusY());
		
	}

}
