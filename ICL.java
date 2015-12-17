/*********************************************************************
Project: Image Component Labeling

*********************************************************************/
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class ICL extends JFrame implements Runnable
{
 static ICL icl;
 static ICL icl2;
  private JLabel chooseDimensionLabel ;
  private JTextField chooseDimensionText ;


  private GridBagConstraints c;

  private JPanel choicepannel ;
  private JPanel actionpannel ;
  private JPanel controlpannel ;
  private JPanel canvaspannel ;
  private JPanel app ;

  private JButton generateImage ;
  private JButton labelComponents ;

  private JButton quit ;

  private Container container;
  // Set up the Canvas
  private CanvasResult  canvasBreadthFirst;
  private CanvasResult  canvasDepthFirst ;

  private static final  int initialDimension = 10 ;    // Initial Time for an activity
  private int dimension;

  static Thread breadth;
  static Thread depth;

  // Set up a window listener
  // initialize the components
  // add action events to them

  public ICL()
  {

     super ( "Task Manager");
     this.setSize(500,700);
     this.setResizable(false);

     addWindowListener(
        new WindowAdapter() {
            public void windowClosing (WindowEvent e)
            {
                System.exit(0);
            }
        }
        );



    choicepannel = new JPanel(new BorderLayout());
    choicepannel.setLayout( new GridBagLayout());

    c = new GridBagConstraints();

    c.anchor = GridBagConstraints.NORTHWEST;
    c.insets = new Insets ( 2,5,2,5 );
    c.fill = GridBagConstraints.NONE;

    c.gridx = 0;
    c.gridy = 0 ;
    c.weightx = 0;


    chooseDimensionLabel = new JLabel ( "Enter Dimension " );
    chooseDimensionLabel.setForeground(Color.black);
    chooseDimensionText = new JTextField("" + initialDimension
    , 5 );

    c.gridx = 0;
    c.gridy = 2 ;
    c.insets = new Insets ( 2,5,2,5 );
    choicepannel.add(chooseDimensionLabel,c);

    c.gridx = 1;
    c.gridy = 2 ;
    c.insets = new Insets ( 2,5,2,5 );
    c.weightx = 1 ;
    choicepannel.add(chooseDimensionText,c);



    generateImage = new JButton ("Generate Image") ;
    labelComponents = new JButton ("Label Components");

    actionpannel = new JPanel(new BorderLayout());
    actionpannel.setLayout( new GridBagLayout());

    c = new GridBagConstraints();

    c.anchor = GridBagConstraints.NORTHWEST;
    c.insets = new Insets ( 2,5,2,5 );
    c.fill = GridBagConstraints.NONE;

    c.gridx = 0;
    c.gridy = 0 ;
    actionpannel.add(generateImage, c);

    c.gridy = 1 ;
    actionpannel.add(labelComponents , c);

    quit = new JButton ("Quit") ;


    controlpannel = new JPanel(new BorderLayout());
    controlpannel.setLayout( new GridBagLayout());

    c = new GridBagConstraints();

    c.anchor = GridBagConstraints.NORTHWEST;
    c.insets = new Insets ( 2,5,2,5 );
    c.fill = GridBagConstraints.NONE;

    c.gridx = 0;
    c.gridy = 0 ;
    c.weightx = 1;
    controlpannel.add(quit , c);


    app = new JPanel(new BorderLayout());
    app.setLayout( new GridBagLayout());

    c = new GridBagConstraints();

    c.anchor = GridBagConstraints.WEST;
    c.insets = new Insets ( 30,5,2,5 );
    c.fill = GridBagConstraints.NONE;

    c.gridx = 0;
    c.gridy = 0 ;
    c.weightx = 1;

    app.add(choicepannel , c );

    c.weighty = 0.5;
    c.gridy = 1 ;
    c.gridheight = 2 ;
    app.add(actionpannel , c);

    c.weighty = 0.5;
    c.gridy = 3 ;
    c.gridheight = 1;
    app.add(controlpannel , c);

    canvasDepthFirst = new CanvasResult(true);
    canvasBreadthFirst = new CanvasResult(false);

    canvaspannel = new JPanel ();
    canvaspannel.setLayout(new GridLayout(2,1,0,30));
    canvaspannel.add( canvasDepthFirst)  ;
    canvaspannel.add( canvasBreadthFirst)  ;
    canvaspannel.setBorder((BorderFactory.createLineBorder(Color.black)));

    generateImage.addActionListener(new theChoice());
    quit.addActionListener(new theChoice());
    labelComponents.addActionListener(new theChoice());

    container = this.getContentPane();
    container.setBounds(new Rectangle(5,5));
    container.add(app,BorderLayout.WEST);
    container.add(canvaspannel,BorderLayout.CENTER);


    show();
  }



  public static void main ( String [] args ) throws StackFullException, StackEmptyException
  {
     icl = new ICL() ;     //new object of type ICL

     //Implementation of 2 threads
     breadth=new Thread(icl);
     depth = new Thread(icl);
  }


  public void run(){

    if(Thread.currentThread().getName().equals("Thread-0"))
    	canvasDepthFirst.labelDepth();

    if(Thread.currentThread().getName().equals("Thread-1"))
    	canvasBreadthFirst.labelBreadth();

  }
  
  // class that detects the action of the user and fires the appropiate
  // action
  private class theChoice implements ActionListener
  {
    public void actionPerformed ( ActionEvent event )
    {
      Object source = event.getSource();

      if ( source == generateImage)
      {
        try
        {
          String userInput =  chooseDimensionText.getText();
          int dimension = Integer.parseInt(userInput);
          if(dimension>15 || dimension<2){           
        	  String message = "Please enter a dimension between 2 and 15";
        	  JOptionPane.showMessageDialog(icl, message);
          }
          else{
        	  canvasBreadthFirst.setDimension(dimension);
        	  canvasDepthFirst.setDimension(dimension);
        	  canvasBreadthFirst.generateImage(dimension);
        	  canvasDepthFirst.generateImage(dimension);
          }
        }

        catch ( NumberFormatException e )
        {

        }
      }

      if ( source == quit )
          System.exit(0);
          
      if ( source == labelComponents )
      {
       /*This is where the call for the labelBreadth and labelDepth of the
       ICLLogic class is going to occur*/
      canvasDepthFirst.labelDepth();
      canvasBreadthFirst.labelBreadth();
      }

    }
  }
}



