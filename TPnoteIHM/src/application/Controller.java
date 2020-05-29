package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import shapes.EllipseCopy;
import shapes.LineCopy;
import shapes.RectangleCopy;

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
    
    
    double xDep; //coordonee x au debut du dessin (pointage)
    double yDep; // coordonnee y au debut du dessin (pointage)
    double xEnd; // x lorsqu'on fini le dessin 
    double yEnd; // y lorsqu'on fini le dessin 
    boolean begin = false; //true lorsqu'on commence a dessiner 
	ArrayList<Line> listeLine = new ArrayList<Line>();//liste de lignes à l'ecran 
	ArrayList<Rectangle> listeRectangle = new ArrayList<Rectangle>();
	ArrayList<Ellipse> listeEllipse= new ArrayList<Ellipse>();
	ArrayList<Shape> listeShape = new ArrayList<Shape>();
	Shape selectedShape =null; //la figure selectionnee 
	


    @FXML
    public void initialize() {

 	
    	//Créer un EventListener pour le clic sur le Bouton Line 
    	radioBtnLine.setOnAction(event -> {//lorsqu'on quitte le mode select 
    		if(selectedShape != null) { 
    			selectedShape.setOpacity(1); //l'opacite de la cible selectionee revient a la normale 
    	}	
    	});
    	
    	radioBtnRectangle.setOnAction(event -> {
    		if(selectedShape != null) {
    			selectedShape.setOpacity(1);
    	}	
    	});
    	
    	radioBtnEllipse.setOnAction(event -> {
    		if(selectedShape != null) {
    			selectedShape.setOpacity(1);
    	}	
    	});
    	
    	
    	btnDelete.setOnAction(event -> {
    		if(radioBtnSelect.isSelected()) {
    			if(selectedShape != null) {
    				anchor.getChildren().remove(selectedShape);
    			}
    		}
    	});
    	
    	//Créer un EventListener pour le clic sur le Bouton Clone 
    	btnClone.setOnAction(event -> { 
    		if(radioBtnSelect.isSelected()) {// si on est en mode select 
    			if(selectedShape != null) {// si une cible a bien ete selectionee 
    				if(selectedShape instanceof Line) {// si la cible est une ligne 
    					Line l = (Line) selectedShape;
    					LineCopy copyL = new LineCopy(l);//on copie la ligne 
    					addLine(copyL); //on l'ajoute a nos formes 
    				}
    				
    				else if(selectedShape instanceof Rectangle) {// si c'est un rectangle
    					Rectangle r = (Rectangle)selectedShape; 
    					RectangleCopy copyR = new RectangleCopy(r); 
    					addRectangle(copyR); 
    				}
    				else if(selectedShape instanceof Ellipse) {// si c'est une ellipse
    					Ellipse e = (Ellipse)selectedShape; 
    					EllipseCopy copyE = new EllipseCopy(e); 
    					addEllipse(copyE); 
    				}
    				
    			}
    		}
    	});
    	
    	
    	//Créer un EventHandler pour la pression sur l'anchorPane 
    
    	  anchor.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                  new EventHandler<MouseEvent>(){

              @Override
              public void handle(MouseEvent event) {
            	  //on recupere les coordonee du point de depart du dessin 
                  xDep = event.getX(); //x pointe 
                  yDep = event.getY(); //y pointe

              }
              
          });
    	  

    	//Créer un EventHandler pour le drag sur le anchorPane
    	   anchor.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	
            	  if(radioBtnLine.isSelected()) {// si on dessine une ellipse 
            	   if(!begin ) {// si on a pas commence le dessin 
            	   addLine(event.getX(), event.getY()); //on ajoute la ligne a l'anchorPane
            	   begin = true;  //dessin commence 
            	   }
            	   else {// on a deja commence le dessin
            		   int nbL = listeLine.size(); 
            		   listeLine.get(nbL-1).setEndX(event.getX());
            		   //on recupere la derniere ligne ajoutee et on change sa coordonee de fin
            		   //la ou la souris pointe 
                       listeLine.get(nbL-1).setEndY(event.getY());
                       listeLine.get(nbL-1).setStroke(colorPicker.getValue());
                       //on change la couleur de la ligne a celle du color Picker  
                       
            	   }
            
               }
            	  
            	  else if(radioBtnEllipse.isSelected()){// si c'est le bouton ellipse qui est selectionne
            		 
            		  if(! begin) {
            			  addEllipse(0, 0); 
            			  begin = true; 
            		  }
            		  else {
            			  int nbE = listeEllipse.size(); 
            			  listeEllipse.get(nbE -1).setRadiusX(Math.abs((event.getX() - xDep) )); 
            			  //on change les rayons a l'offset entre l'endroit pointee actuellement et l'endoit pointe au debut
            			  listeEllipse.get(nbE-1).setRadiusY(Math.abs((event.getY() - yDep) )); 
            			  listeEllipse.get(nbE-1).setFill(colorPicker.getValue());
            			//on change la couleur de l'ellipse a celle du color Picker  
            		  }
            	  }
            	  
            	  else if(radioBtnRectangle.isSelected()) {//si c'est le bouton rectangle qui est selectionne
            		  if(! begin) {
            			  addRectangle(0, 0);
            			  begin = true; 
            		  }
            		  else {
            			  int nbR = listeRectangle.size(); 
            			  listeRectangle.get(nbR-1).setWidth(Math.abs((event.getX() - xDep) ));
            			  listeRectangle.get(nbR-1).setHeight(Math.abs((event.getY()- yDep)));
            			  listeRectangle.get(nbR-1).setFill(colorPicker.getValue());
            			  
            		  }
            	  }
     
               }
           });
    	   
    	 //On creer un eventHandler lorsqu'on relache l'anchor pane  
    	   anchor.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	   begin = false;// lorsqu'on relache, le dessin est fini 
            	   
               }}); 
    	   
    	   
    	 //On creer un eventListener pour le ColorPicker   
    	   colorPicker.setOnAction(event -> {
    		   if(radioBtnSelect.isSelected()) { // si on est en mode select 
    		   if(selectedShape != null) {//qu'on a selectionne une forme 
    			   selectedShape.setFill(colorPicker.getValue());
    			   //on change la couleur de la forme a celle du color picker 
    			   if(selectedShape instanceof Line) { // si c'est une ligne
    				   Line l = (Line)selectedShape; 
    				   l.setStroke(colorPicker.getValue());// on change la couleur du contour 
    			   }
    		   }
    	   }
    		   
    	   });
    
    	 
    }
    
	   private Line addLine(Line l ) { // la fonction pour ajouter une ligne deja dessinee 
		   
	        anchor.getChildren().add(l); // on ajoute la ligne a l'anchor pane
	        listeLine.add(l);// a la liste de ligne
	        listeShape.add(l); // a la liste de formes 
	        
	        //------Creer EventHandler pour les lignes------ 

	        l.addEventHandler(MouseEvent.MOUSE_RELEASED, // lorsque la ligne est relachee
	                   new EventHandler<MouseEvent>(){
	    		   
	               @Override
	               public void handle(MouseEvent event) {
	            	   if(radioBtnSelect.isSelected()) {// si elle est selectionnee 
		            		double dx = event.getX() - xDep; 
		            		double dy  = event.getY() - yDep;
		            	 
		            	   l.setStartX(l.getStartX() + dx);
		            	   // on la deplace a l'endroit ou on veut la relacher 
		            	   l.setStartY(l.getStartY() + dy); 
		            	   l.setEndX(l.getEndX() + dx);
		            	   l.setEndY(l.getEndY() + dy) ;

		            	   }
	               }}); 
	        
	        
	        
	        l.addEventHandler(MouseEvent.MOUSE_PRESSED, 
	                   new EventHandler<MouseEvent>(){// lorsqu'on appuie sur la ligne 
	    		   
	               @Override
	               public void handle(MouseEvent event) {
	            	   if(radioBtnSelect.isSelected()) {// si elle est selectionnee 
	                     	// feedback en changement d'opacite pour notifier de la selection 
	            		   	l.setOpacity(0.25);
	                     	 selectedShape = l; 
	                     	 for(Shape shape: listeShape) {
	                     		if(shape != l) shape.setOpacity(1);
	                     		//on remet l'opacite a normal pour les autres formes non selectionnees
	                     	 }
	            	   }
    
                   
	               }});
	       
	        return l;

	    }

	   private Line addLine(double x, double y) { // pour ajouter une ligne pas encore dessinee 
	        Line l = new Line(xDep, yDep, x, y);
	        l.setStroke(colorPicker.getValue());//on change sa couleur a celle du color picker 
	        l.setStrokeWidth(7);// on change son epaisseur 
	        return addLine(l);
	        
	   }
	   
	   private Ellipse addEllipse(Ellipse e) {// on ajoute une ellipse deja dessinee
		   
		   anchor.getChildren().add(e);// on les ajoute a l'anchor pane
		   listeEllipse.add(e); //a la liste d'ellipse
		   listeShape.add(e); // liste de formes 
		   
		   // ------------Creer event handler pour les ellipses ------
		   e.addEventHandler(MouseEvent.MOUSE_DRAGGED, //lorsque l'ellipse est deplacee 
                   new EventHandler<MouseEvent>(){
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) { // si on est en mode select 
            	   e.setCenterX(event.getX());; 
            	   //on deplace le centre de l'ellipse a l'endroit de la souris 
                   e.setCenterY(event.getY());}
                   
               }}); 
        
        e.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                   new EventHandler<MouseEvent>(){// si on appuie sur l'ellipse 
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) {// si on est en mode select 
                     	 e.setOpacity(0.5);
                     	 // on donne un feedback pour notifier de la selection 
            	   		selectedShape = e;
            	   	 for(Shape shape: listeShape) {
            	   		 if(shape != e) shape.setOpacity(1); // on remet les opacites des autres a normal
                 	 }
            	   		
            	   }
            }});
           
		   return e; 
		   
	   }

	   private Ellipse addEllipse(double x, double y) {// on ajoute une ellipse non dessinee 
		   Ellipse e= new Ellipse(xDep, yDep, x, y);
		   e.setFill(colorPicker.getValue());
		   return addEllipse(e); 
	   }
	   
	   private Rectangle addRectangle(Rectangle r) {
		   anchor.getChildren().add(r);
		   listeRectangle.add(r); 
		   listeShape.add(r); 
		 
		// ------------Creer event handler pour les rectangles ------
		   
		   r.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                   new EventHandler<MouseEvent>(){ // lorsque le rectangle est deplace 
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) {
            		   
            		   r.setX(event.getX()); // on deplace son point de reference la ou la souris est 
            		   r.setY(event.getY());
            		   
            	   }
                   
               }}); 
        
        r.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                   new EventHandler<MouseEvent>(){ // si on appuie sur le rectangle 
    		   
               @Override
               public void handle(MouseEvent event) {
            	   if(radioBtnSelect.isSelected()) {// si on est en mode select 
                     	 r.setOpacity(0.5);// on notifie de a selection 
            	   		selectedShape = r;
            	   	 for(Shape shape: listeShape) {
            	   		 if(shape != r) shape.setOpacity(1); //on remet les opacites a normal 
                 	 }
            	   		
            	   }
          
                
               }});
        
		   return r; 
	   }
	   private Rectangle addRectangle(double x, double y) { //on ajoute un rectangle non dessine encore
		   Rectangle r= new Rectangle(xDep, yDep, x, y);
		   r.setFill(colorPicker.getValue()); 
        
		   return addRectangle(r); 
	   }
}
