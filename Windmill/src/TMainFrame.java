package windmill;

import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TMainFrame extends JFrame{
	
	public TMainFrame(){
		setTitle("Vindmølle animasjon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicsConfigTemplate gtemplets = new GraphicsConfigTemplate3D();
		GraphicsConfiguration gcfg = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getBestConfiguration(gtemplets);
		Canvas3D canvas3D = new Canvas3D(gcfg);
		add("Center", canvas3D);
		
		new TMyUniverse(canvas3D);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
	}
}
