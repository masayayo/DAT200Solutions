package HelloWorld2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class StrikkClassPanel extends JPanel implements MouseListener, MouseMotionListener
{     

	//Variable for checking if the cursor is inside the circle
	Boolean insideCircle = false;
	
	//Variables for the drawings
	private int ovalXstart = 247;
	private int ovalYstart = 247;
	private final int l1xstart = 100;
	private int l1xend = 247;
	private final int l1ystart = 250;
	private int l1yend = 250;
	private int l2xstart = 253;
	private final int l2xend = 400;
	private int l2ystart = 250;
	private final int l2yend = 250;
	
   public void paintComponent(Graphics g)
   {  
      super.paintComponent(g);
      
      //Sets the background yellow
      super.setBackground(new Color(255,255,0));
      
      //Red square
      g.setColor(new Color(255,0,0));
      g.fillRect(100,100,300,300);
      
      //To lines that meet in the center
      g.setColor(new Color(0,0,0));
      g.drawLine(l1xstart,l1ystart,l1xend,l1yend);
      g.drawLine(l2xstart,l2ystart,l2xend,l2yend);
      
      //Circle
      g.drawOval(ovalXstart, ovalYstart, 6, 6);
      
   }
   
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		insideCircle = false;
		ovalXstart = 247;
		ovalYstart = 247;
		l1xend = 247;
		l1yend = 250;
		l2xstart = 253;
		l2ystart = 250;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(insideCircle){
			ovalXstart = e.getX()-3;
			ovalYstart = e.getY()-3;
			l1xend = e.getX()-3;
			l1yend = e.getY();
			l2xstart = e.getX()+3;
			l2ystart = e.getY();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//Checks if the cursor is inside the circle
		int dX = (e.getX()-ovalXstart)/6;
		int dY = (e.getY()-ovalYstart)/6;
		
		if(dX*dX+dY*dY<1){
			insideCircle = true;
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		}else{
			insideCircle = false;
			setCursor(Cursor.getDefaultCursor());
		}
		repaint();
	}	
} //End class HelloWorldPanel

class StrikkClassFrame extends JFrame
{     
   public StrikkClassFrame()
   {
      setTitle("Strikk");
      setSize(500, 500);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );
      
      Container contentPane = getContentPane();
      StrikkClassPanel strikkClassPanel = new StrikkClassPanel();
      contentPane.add(strikkClassPanel);
      strikkClassPanel.addMouseListener(strikkClassPanel);
      strikkClassPanel.addMouseMotionListener(strikkClassPanel);
   }
}

public class StrikkClass
{  
   public static void main(String[] args)
   {  
      JFrame frame = new StrikkClassFrame();   
      
      Action actionListener = new AbstractAction(){
    	  public void actionPerformed(ActionEvent actionEvent){
    		  System.out.println("System exited properly");
    		  System.exit(0);
    	  }
      };
      JPanel content = (JPanel)frame.getContentPane();
      KeyStroke stroke = KeyStroke.getKeyStroke("Q");
      
      InputMap inputMap = content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
      inputMap.put(stroke, "CLOSE");
      content.getActionMap().put("CLOSE", actionListener);
      frame.setVisible(true);           
   }
}
