import java.awt.*;
import java.applet.*;

public class ICLLogic extends Applet
{
  private  int dimension = 2 ;
  /*This is the 2D array where the numbers are going to be saved. You will use
  this array in both labelBreadth and labelDepth*/
  private  int [][] pixels ;

  private final static int PIXELDIM = 18;

  private final static int  CURRENTPIXEL = 1 ;
  private final static int  CHECKPIXEL = 2   ;
  private final static int  ADDDATASTRUCTURE = 3;

  boolean depthFirst ;   //Is it depth first or breadth first?

  private int delay = 250 ;

  private int state = 0 ;
  //This is the queue to be used in labelBreadth
  ArrayQueue q = new ArrayQueue();
  //This is the stack to be used in labelDepth
  ArrayStack s = new ArrayStack(255);  

  public ICLLogic (boolean depthFirst)
  {
    this.depthFirst = depthFirst ;
  }
  
  private void setDimension ( int dimension )
  {
    this.dimension = dimension ;
  }

  /*This method is responsible of drawing the squares*/
  public void generateImage (int dimension)
  {
    this.setDimension(dimension);
    pixels = new int [dimension + 2][dimension + 2];
    for ( int i = 0 ; i < dimension + 2 ; i ++ )
    {
      pixels[0][i] = pixels[dimension + 1][i] = 0 ;  // bottom and top
      pixels[i][0] = pixels[i][dimension + 1] = 0 ;  // left and right

    }

    for ( int i = 1 ; i <= dimension ;  i++ )
    {
      for ( int j = 1 ; j <= dimension ; j++ )
      {
        pixels[i][j] = (int) (Math.random() *2) ;
      }
    }
  }

  public void draw (Graphics g )
  {
    if ( pixels != null )
    {
      for ( int i = 1 ; i <= dimension ;  i++ )
      {
        for ( int j = 1 ; j <= dimension ; j++ )
        {

          drawPixel (g, i , j  , pixels[i][j], Color.gray);

        }
      }
    }

  }

  /*This method is responsible for coloring the square and labelling each one
  with a number. You will need this method also in labelDepth and labelbreadth
  since it also sets the color of each square.*/
  public void drawPixel (Graphics  g , int x , int y , int number, Color color)//I added one more parametr - "Color color"
  {
  /*The color is not fully random..Re-implement it so it becomes fully random*/
    Color current ;     
    current = color;       

      g.setColor(current);
      g.fillRect(x*PIXELDIM , y*PIXELDIM , PIXELDIM , PIXELDIM);

      current = Color.black;
      g.setColor(current);


      g.drawRect(x*PIXELDIM , y*PIXELDIM , PIXELDIM , PIXELDIM);
      String str= new String(""+number); //writes the number on the square
      g.drawString(str,  x*PIXELDIM + PIXELDIM/4 ,
       y*PIXELDIM + 2*PIXELDIM /3 );
  }	  

  public void labelDepth(Graphics g,int dimension) throws Exception{
   
   /*Enter here the code for the Depth First method. This method will later
   be called in class CanvasResult. The Dimension attribute it takes is the
   dimension value set by the user. You do not need to worry about how this
   value will be set.*/	  
	  int startRow;
	  int startCol;	 //
	  int numberForLaybling =1;
	  Color colorForLabling;
	 
	  //initialize offsets 
	  Position [] offset = new Position [4]; 
	  offset[0] = new Position(0, 1); // right 
	  offset[1] = new Position(1, 0); // down 
	  offset[2] = new Position(0, -1); // left 
	  offset[3] = new Position(-1, 0); // up 
	  
	  //start scan 	  
	  for (int k = 1; k < dimension + 1; k++)
	  	for (int n = 1; n < dimension + 1; n++){	  
			  if (pixels[n][k]==1){// new step
				  
				  startRow = n;
				  startCol = k;
				  //number and color for labeling
				  numberForLaybling++;
				  colorForLabling = new Color((int)(Math.random()*256),(int)
						  (Math.random()*256), (int)(Math.random()*256));	  
				  
				  Position here = new Position(startRow, startCol); 
				  pixels[n][k] = 100; // prevent return to entrance 
				  int option = 0; // next move 
				  int lastOption = 3;
				  
				  //lable the first pixel				  
				  drawPixel (g,here.row, here.col, numberForLaybling, colorForLabling);
	  
				  // search for other parts of element 				 
				  while (here.row != dimension || here.col != dimension) {
					  // not at exit find a neighbor to move to won't compile without explicit initialization 
						  int r = 0, c = 0; // row and column of neighbor 
						  while (option <= lastOption) {	   
							  r = here.row + offset[option].row; 
							  c = here.col + offset[option].col; 
							  if (pixels[r][c] == 1)								  
								  break; //we fount an element
							  option++; // next option 
						  } 
	  
					  // was a neighbor found? 
					  if (option <= lastOption){ // yes 	  
						  //lable this box with a number a color						  
						  drawPixel (g, r, c, numberForLaybling, colorForLabling);
						  s.push(here);//push in the stack
						  		  		
						  here = new Position(r, c); 
						  // set to 0 to prevent revisit 
						  pixels[r][c] = 111; 
						  option = 0; 
					  } 
		  	
					  else {
						  // no neighbor to move to, back up 
						  if (s.empty()) //return false; // no place to 
							  break;// from the step 
						  Position next = (Position) s.pop(); 
						  if (next.row == here.row) 
							  option = 2 + next.col - here.col; 
						  else option = 3 + next.row - here.row; 
						  here = next; 
					  } 
					  if (s.empty()) 
						  break;// from the step 
				  }
			  }
		  } 	   
   }

   public void labelBreadth(Graphics g,int Dimension){
  
  /*Enter here the code for the breadth first method. This method will later
   be called in class CanvasResult. The Dimension attribute it takes is the
   dimension value set by the user. You do not need to worry about how this
   value will be set.*/
	   	  int startRow;
		  int startCol;	 
		  int numberForLaybling =1;
		  Color colorForLabling;
		 
		  //initialize offsets 
		  Position [] offset = new Position [4]; 
		  offset[0] = new Position(0, 1); // right 
		  offset[1] = new Position(1, 0); // down 
		  offset[2] = new Position(0, -1); // left 
		  offset[3] = new Position(-1, 0); // up 
		  
		  //start scan 
		  for (int k = 1; k < dimension + 1; k++)
			  	for (int n = 1; n < dimension + 1; n++){
				  if (pixels[n][k]==1){// new step					  
					  startRow = n;
					  startCol = k;
					  //number and color for laybling
					  numberForLaybling++;
					  colorForLabling = new Color((int)(Math.random()*256),(int)
							  (Math.random()*256), (int)(Math.random()*256));
		  
					  Position here2 = new Position(startRow, startCol);					  
					  //lable the first pixel				  
					  drawPixel (g, here2.row ,here2.col, numberForLaybling, colorForLabling);
					  
					  pixels[n][k] = 100; // prevent return to entrance 
					  int numOfNbrs = 4; // neighbors of a grid position
					  // label reachable grid positions					  
					  Position nbr = new Position(0, 0);					  
					  while(here2.row != dimension || here2.col != dimension){ // label neighbors of ‘here’						  
						  for (int i = 0; i < numOfNbrs; i++){
							  // check out neighbors of ‘here’				  
							  nbr.row = here2.row + offset[i].row;
							  nbr.col = here2.col + offset[i].col;
							  if (pixels[nbr.row][nbr.col] == 1){ // unlabeled nbr, label it												  
								  drawPixel (g,nbr.row, nbr.col, numberForLaybling, colorForLabling);
								  pixels[nbr.row][nbr.col] = 111;								  
								  q.put(new Position(nbr.row, nbr.col));
							  }
						  }					  
						  if ( q.isEmpty() == false){					  
							  here2 = (Position) q.remove();							  
						  }
						  else
							  break;
					  } 
					
				  }
			  }
   }
			  

}