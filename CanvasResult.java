import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/*This class is used to draw the 2 canvas*/

public class CanvasResult  extends JPanel
{

  private JPanel titlePanel;
  private JLabel titleLabel ;

  private boolean DepthFirst;
  private CanvasRect canvas ;
  private int Dimension;
  public CanvasResult (boolean depthFirst)
  {
    DepthFirst = depthFirst;
    titleLabel = new JLabel ();
    if (depthFirst )
      titleLabel.setText( "Depth First using Stack Data Structures" );
    else
       titleLabel.setText( "Breadth First using Queue Data Structure") ;


    titlePanel = new JPanel ();
    titlePanel.add(titleLabel);    //sets the title of each canvas


    canvas = new CanvasRect( depthFirst );
    this.setLayout(new BorderLayout());
    this.add(canvas , BorderLayout.CENTER);
    this.add(titlePanel , BorderLayout.NORTH)  ;

    repaint();
  }

  /*Simply calls the generateImage() method of canvasRect*/
  public void generateImage ( int dimension)
  {
    canvas.generateImage(dimension);
  }


  public void setDimension(int dimension){
    Dimension=dimension;
  }
  
  public void labelBreadth(){
  canvas.setFirstEntry(false);
  canvas.labelBreadth();
  }

  public void labelDepth(){
   canvas.setFirstEntry(false);
  canvas.labelDepth();
  }

  
  private class CanvasRect extends Canvas
  {
    private int CANVAS_WIDTH = 600;
    private int CANVAS_HEIGHT = 400;

    private int dimension   ;

    private ICLLogic icllogic ;
    private boolean breadthBool = false;
    private boolean firstEntry = true;

    public CanvasRect( boolean depthFirst)
    {
       this.dimension = dimension ;
      icllogic = new ICLLogic (depthFirst);

      setBackground(Color.white);
      setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    /*This method is used to paint the squares generated in ICLLogic. It also
    calls the methods labelbreadth and labelDepth. These 2 methods will then
    label the graph.*/
    public void paint( Graphics g )
    {
      icllogic.draw(g);
        if(breadthBool && !firstEntry)
          icllogic.labelBreadth(g,dimension );
        if(!breadthBool && !firstEntry)
			try {
				icllogic.labelDepth(g,dimension );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
      firstEntry=true;
    }

    public void setFirstEntry(boolean value){
     firstEntry = value;
    }


    public void generateImage ( int Dimension )
    {
      dimension = Dimension;
      icllogic.generateImage(Dimension);

      repaint();
    }


    public void clear ()
    {
      repaint ();
    }

    public void labelBreadth(){
    breadthBool=true;
       repaint();
    }

    public void labelDepth(){
        breadthBool=false;
       repaint();
    }

  }


}