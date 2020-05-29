package shapes;

import javafx.scene.shape.Ellipse;

public class EllipseCopy extends Ellipse { 

	public EllipseCopy(Ellipse e){
		this.setFill(e.getFill());
		this.setCenterX(e.getCenterX()+10);
		this.setCenterY(e.getCenterY()+10);
		this.setRadiusX(e.getRadiusX());
		this.setRadiusY(e.getRadiusY());
		
	}

}
