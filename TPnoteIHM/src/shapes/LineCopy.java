package shapes;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineCopy extends Line {
	//represente copie d'une ligne 
	public LineCopy(Line l) {
		this.setStrokeWidth(l.getStrokeWidth());
		this.setStroke(l.getStroke());
		this.setFill(l.getFill());
		this.setStartX(l.getStartX() + 10);
		this.setStartY(l.getStartY()); 
		this.setEndX(l.getEndX() + 10);
		this.setEndY(l.getEndY()); 
		
	}



}
