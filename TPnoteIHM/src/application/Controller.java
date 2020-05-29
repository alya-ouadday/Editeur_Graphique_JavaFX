package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {

    @FXML
	private  RadioButton radioBtnSelect;

    @FXML
 	private  RadioButton radioBtnEllipse;
    @FXML
 	private  RadioButton radioBtnRectangle;
    @FXML
 	private  RadioButton radioBtnLine;

    @FXML
 	private  ColorPicker colorPicker;
    
	@FXML
    private Button btnDelete;

    @FXML
    private Button btnClone;
    @FXML 
    private AnchorPane anchor; 
    
    
    double xDep; 
    double yDep; 
    double xEnd; 
    double yEnd; 
    boolean begin = false; 
	ArrayList<Line> listeLine = new ArrayList<Line>(); 
	ArrayList<Rectangle> listeRectangle = new ArrayList<Rectangle>();
	ArrayList<Ellipse> listeEllipse= new ArrayList<Ellipse>();
	ArrayList<Shape> listeShape = new ArrayList<Shape>();
	int nbLine = -1; 
	int nbRectangle = -1; 
	int nbEllipse = -1; 
	Shape selectedShape =null; 
	


    @FXML
    public void initialize() {

    	
    	double xDepart = 0;
    	double yDepart = 0;
    	
    	btnDelete.setOnAction(event -> {
    		if(radioBtnSelect.isSelected()) {
    			if(selectedShape != null) {
    				anchor.getChildren().remove(selectedShape);
    			}
    		}
    	});
    	
    	btnClone.setOnAction(event -> {
    		if(radioBtnSelect.isSelected()) {
    			if(selectedShape != null) {
    				if(selectedShape instanceof Line) {
    					Line tocopy = (Line)selectedShape;
    					Line copy = new Line(tocopy.getStartX() + 10, tocopy.getStartY(), tocopy.getEndX()+10, tocopy.getEndY());
    					copy.setStrokeWidth(tocopy.getStrokeWidth());
    					copy.setStroke(colorPicker.getValue());
    					addLine(copy); 
    				}
    				
    			}
    		}
    	});
    
    	  anchor.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                  new EventHandler<MouseEvent>(){

              @Override
              public void handle(MouseEvent event) {
           
                 
                  xDep = event.getX(); 
                  yDep = event.getY(); 

              }
              
          });
    	  

    	   
    	   anchor.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	
            	  if(radioBtnLine.isSelected()) {
            	   if(!begin ) {
            	   addLine(event.getX(), event.getY());
            	   begin = true; 
            	   }
            	   else {
            		   listeLine.get(nbLine).setEndX(event.getX());
                       listeLine.get(nbLine).setEndY(event.getY());
                       listeLine.get(nbLine).setStroke(colorPicker.getValue());
                       
            	   }
            
               }
            	  
            	  else if(radioBtnEllipse.isSelected()){
            		 
            		  if(! begin) {
            			  addEllipse(0, 0); 
            			  begin = true; 
            		  }
            		  else {
            			    
            			  listeEllipse.get(nbEllipse).setRadiusX(Math.abs((event.getX() - xDep) )); 
            			  listeEllipse.get(nbEllipse).setRadiusY(Math.abs((event.getY() - yDep) )); 
            			  listeEllipse.get(nbEllipse).setFill(colorPicker.getValue());
            			  
            		  }
            	  }
            	  
            	  else if(radioBtnRectangle.isSelected()) {
            		  if(! begin) {
            			  addRectangle(0, 0);
            			  begin = true; 
            		  }
            		  else {
            			  listeRectangle.get(nbRectangle).setWidth(Math.abs((event.getX() - xDep) ));
            			  listeRectangle.get(nbRectangle).setHeight(Math.abs((event.getY()- yDep)));
            			  listeRectangle.get(nbRectangle).setFill(colorPicker.getValue());
            			  
            		  }
            	  }
               
               
               
               }
           });
    	   
    	   anchor.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	   begin = false;
            	   
               }}); 
    	   
    	   colorPicker.setOnAction(event -> {
    		   if(radioBtnSelect.isSelected()) {
    		   if(selectedShape != null) {
    			   selectedShape.setFill(colorPicker.getValue());
    			   if(selectedShape instanceof Line) {
    				   Line l = (Line)selectedShape; 
    				   l.setStroke(colorPicker.getValue());
    				   l.setOpacity(0.25); 
    			   }
    		   }
    	   }
    		   
    	   });
    
    	 
    }
    
    
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	   private Line addLine(Line l ) {
	        
	        anchor.getChildren().add(l); 
	        listeLine.add(l);
	        listeShape.add(l); 
	      
	        
	        l.addEventHandler(MouseEvent.MOUSE_RELEASED, 
	                   new EventHandler<MouseEvent>(){
	    		   
	               @Override
	               public void handle(MouseEvent event) {
	            	   if(radioBtnSelect.isSelected()) {
		            		double dx = event.getX() - xDep; 
		            		double dy  = event.getY() - yDep;
		            	 
		            	   l.setStartX(l.getStartX() + dx);
		            	   l.setStartY(l.getStartY() + dy); 
		            	   l.setEndX(l.getEndX() + dx);
		            	   l.setEndY(l.getEndY() + dy) ;
		            		
        	   
		            	   }
	            	
	               }}); 
	        
	        
	        
	        l.addEventHandler(MouseEvent.MOUSE_PRESSED, 
	                   new EventHandler<MouseEvent>(){
	    		   
	               @Override
	               public void handle(MouseEvent event) {
	            	   if(radioBtnSelect.isSelected()) {
	                     	 l.setOpacity(0.25);
	                     	 selectedShape = l; 
	                     	 for(Shape shape: listeShape) {
	                     		if(shape != l) shape.setOpacity(1);
	                     	 }
	            	   }
    
                   
	               }});
	        
	    
	        nbLine++;
	        return l;

	    }

	   private Line addLine(double x, double y) {
	        Line l = new Line(xDep, yDep, x, y);
	        l.setStroke(colorPicker.getValue());
	        l.setStrokeWidth(10);
	        return addLine(l);
	        
	   }
	   
	   private Ellipse addEllipse(double x, double y) {
		   Ellipse e= new Ellipse(xDep, yDep, x, y);
		   e.setFill(colorPicker.getValue());
		   anchor.getChildren().add(e);
		   listeEllipse.add(e); 
		   listeShape.add(e); 
		   nbEllipse++;   
		   e.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) {
            	   e.setCenterX(event.getX());; 
                   e.setCenterY(event.getY());}
                   
               }}); 
        
        e.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) {
                     	 e.setOpacity(0.5);
            	   		selectedShape = e;
            	   	 for(Shape shape: listeShape) {
            	   		 if(shape != e) shape.setOpacity(1);
                 	 }
            	   		
            	   }
          
                
               }});
        
       
		   
		   return e; 
		   
	   }
	   
	   private Rectangle addRectangle(double x, double y) {
		   Rectangle r= new Rectangle(xDep, yDep, x, y);
		   r.setFill(colorPicker.getValue());
		   anchor.getChildren().add(r);
		   listeRectangle.add(r); 
		   listeShape.add(r); 
		   nbRectangle ++;
		   
		   r.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) {
            		   
            		   r.setX(event.getX());
            		   r.setY(event.getY());
            	   //deplacement 
            		   
            	   }
                   
               }}); 
        
        r.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) {
                     	 r.setOpacity(0.5);
            	   		selectedShape = r;
            	   	 for(Shape shape: listeShape) {
            	   		 if(shape != r) shape.setOpacity(1);
                 	 }
            	   		
            	   }
          
                
               }});
        
		   return r; 
	   }
}
