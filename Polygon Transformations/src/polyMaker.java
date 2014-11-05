import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class MyPanel extends JPanel implements ActionListener
{     
	int nClicks;
	int aClicks = -1;
	double[] xCoordinates;
	double[] yCoordinates;
	Polygon p = new Polygon();
	Point center;
	
	double centerX = 0;
	double centerY = 0;
	double dX;
	double dY;
	
	Boolean fillPol = false;
	boolean translate = false;
	boolean rotate = false;
	boolean scale = false;
	
	JButton newPol = new JButton("New Polygon");
	JButton t = new JButton("Translate");
	JButton s = new JButton("Scale");
	JButton r = new JButton("Rotate");

	public MyPanel()
	{
		nClicks = 0;
		
		this.addMouseListener(new MouseListener());
		this.addMouseMotionListener(new MouseMotionListener());
		this.add(newPol);
		this.add(t);
		this.add(s);
		this.add(r);
		newPol.addActionListener(this);
		t.addActionListener(this);
		s.addActionListener(this);
		r.addActionListener(this);
	}

	public void paintComponent(Graphics g)
	{  
		super.paintComponent(g);
		g.setColor(Color.black);
		
		if(nClicks == aClicks){
			g.fillPolygon(p);
			g.setColor(Color.RED);
			g.drawOval(center.x-3,center.y-3,6,6);
		}
	}
	
	public class MouseListener extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e){
			if(fillPol == true){
				xCoordinates[nClicks] = e.getX();
				yCoordinates[nClicks] = e.getY();
				p.addPoint((int)xCoordinates[nClicks], (int)yCoordinates[nClicks]);
				nClicks++;
				if(nClicks == aClicks){
					repaint();
					fillPol = false;
					findCenter();
				}
			}
		}
	}
	public class MouseMotionListener extends MouseMotionAdapter{
		 
		@Override
		public void mouseDragged(MouseEvent e){
			if(translate == true){
				for(int i = 0;i < nClicks; i++){
					
					dX = e.getX()-center.x;
					dY = e.getY()-center.y;
					
					p.xpoints[i] = (int) (p.xpoints[i] + dX+0.5);
					p.ypoints[i] = (int) (p.ypoints[i] + dY+0.5);
				}
				findCenter();
			}
	
			if(scale == true){
				
				double x1;
				double y1;
				
				double sX = 0;
				double sY = 0;
				
				for(int i = 0; i < nClicks; i++){
					//Translates the polygon to have the center in (0,0)
					x1 = p.xpoints[i] - center.x;
					y1 = p.ypoints[i] - center.y;
					
					//Adds a scale
					if(e.getX() > center.x){ //Higher than
						sX = (x1*1.1);
						sY = (y1*1.1);
						
						//Translates back to the original location
						p.xpoints[i] = (int)(sX + center.x+0.5);
						p.ypoints[i] = (int)(sY + center.y+0.5);
					}else if(e.getX() < center.x){ //Less than
						sX = (x1*0.9);
						sY = (y1*0.9);
						
						//Translates back to the original location
						p.xpoints[i] = (int)(sX + center.x+0.5);
						p.ypoints[i] = (int)(sY + center.y+0.5);
					}	
				}
			}
			if(rotate == true){
				double x1;
				double y1;
				
				double rot_x;
				double rot_y;
				
				for(int i = 0; i < nClicks; i++){
					//Translates the polygon to have the center in (0,0)
					x1 = p.xpoints[i] - center.x;
					y1 = p.ypoints[i] - center.y;
					
					//Adds a rotation
					if(e.getX() > center.x){ //Rotates clockwise
						rot_x = x1 * Math.cos(1) - y1 * Math.sin(1);
						rot_y = x1 * Math.sin(1) + y1 * Math.cos(1);
					}else{ //Rotates counter clockwise
						rot_x = x1 * Math.cos(1) - y1 * Math.sin(-1);
						rot_y = x1 * Math.sin(-1) + y1 * Math.cos(1);
					}
					//Translates back to x,y position
					p.xpoints[i] = (int)(rot_x + center.x+0.5);
					p.ypoints[i] = (int)(rot_y + center.y+0.5);
				}
			}
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(newPol)){
			fillPol = true;
			aClicks = Integer.parseInt(JOptionPane.showInputDialog(this,"How many points do you want the polygon to have?"));
			xCoordinates = new double[aClicks];
			yCoordinates = new double[aClicks];
			
		}
		else if(arg0.getSource().equals(t)){
			if(translate == true){
				translate = false;
				System.out.println("Translate is false");
			}else{
				translate = true;
				scale = false;
				rotate = false;
				System.out.println("Translate is true");
			}
		}
		else if(arg0.getSource().equals(s)){
			if(scale){
				scale = false;
				System.out.println("Scale is false");
			}else{
				scale = true;
				translate = false;
				rotate = false;
				System.out.println("Scale is true");
			}
		}
		else{
			if(rotate){
				rotate = false;
				System.out.println("Rotate is false");
			}else{
				rotate = true;
				translate = false;
				scale = false;
				System.out.println("Rotate is true");
			}
		}
	}
	public void findCenter(){
		if(!rotate){
			//middle x and middle y with respect to all the polygonpoints
			centerX = 0;
			centerY = 0;
			for(int i = 0; i < aClicks;i++){
				centerX = centerX + p.xpoints[i];
				centerY = centerY + p.ypoints[i];
			}
			centerX = centerX / nClicks;
			centerY = centerY / nClicks;
			
			//Creates a new Point of center, which is the center of the polygon
			center = new Point((int)centerX,(int)centerY);
			System.out.println(center.getX());
			System.out.println(center.getY());
		}
	}
} //End class MyPanel

class MyFrame extends JFrame
{     
	public MyFrame()
	{
		setTitle("PolyMaker");
		setSize(800, 800);
		addWindowListener(new WindowAdapter()
		{  public void windowClosing(WindowEvent e)
		{  System.exit(0);
		}
		} );
		Container contentPane = getContentPane();
		contentPane.add(new MyPanel());
	}
} //End class MyFrame

public class polyMaker
{  
	public static void main(String[] args)
	{  
		JFrame frame = new MyFrame();
		frame.setVisible(true);
	}
} //End class polyMaker