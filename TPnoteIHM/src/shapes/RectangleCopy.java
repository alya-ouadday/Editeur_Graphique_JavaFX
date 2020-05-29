package shapes;

import javafx.scene.shape.Rectangle;

public class RectangleCopy extends Rectangle{

	public RectangleCopy(Rectangle r) {
		this.setFill(r.getFill());
		this.setX(r.getX() + 10);
		this.setY(r.getY()+10);
		this.setWidth(r.getWidth());
		this.setHeight(r.getHeight());
	}

}
