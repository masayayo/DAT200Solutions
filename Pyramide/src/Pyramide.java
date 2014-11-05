package Pyramide;

import java.awt.*; 
import java.awt.event.*;
import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.*;

class PyramidePanel extends JPanel
{

	public PyramidePanel()
	{
		setLayout(new BorderLayout());

		GraphicsConfigTemplate template = new GraphicsConfigTemplate3D();
		GraphicsConfiguration gcfg =
			GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice().getBestConfiguration(template);

		Canvas3D c = new Canvas3D(gcfg);

		add("Center", c);

		// Create a simple scene and attach it to the virtual
		// universe

		BranchGroup scene = createSceneGraph();
		UniverseBuilder u = new UniverseBuilder(c);
		u.addBranchGraph(scene);
		
	}

	public BranchGroup createSceneGraph() {

        // Create the root of the branch graph

        BranchGroup objRoot = new BranchGroup();


        // Create the TransformGroup node and initialize it to the
        // identity. Enable the TRANSFORM_WRITE capability so that
        // our behavior code can modify it at run time. Add it to
        // the root of the subgraph.

        TransformGroup objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRoot.addChild(objTrans);

        // Create a simple Shape3D node; add it to the scene graph.

        objTrans.addChild(new ColorPyramide().getShape());


        // Create a new Behavior object that will perform the
        // desired operation on the specified transform and add
        // it into the scene graph. 


        Transform3D xAxis = new Transform3D();
        Alpha rotationAlpha = new Alpha(
                -1, Alpha.INCREASING_ENABLE,
                0, 0,4000, 0, 0, 0, 0, 0);
        RotationInterpolator rotator = new RotationInterpolator(
                rotationAlpha, objTrans, xAxis,
                0.0f, (float) Math.PI*2.0f);
        BoundingSphere bounds =
            new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        rotator.setSchedulingBounds(bounds);
        objTrans.addChild(rotator);   
      
        
        
        return objRoot;
    }
}

class PyramideFrame extends JFrame
{  
	public PyramideFrame()
	{  
		setTitle("Pyramide");
		setSize(700, 700);
		addWindowListener(new WindowAdapter()
		{  
			public void windowClosing(WindowEvent e)
			{  
				System.exit(0);
			}
		} );

		Container contentPane = getContentPane();
		contentPane.add(new PyramidePanel());
	}
}

public class Pyramide
{

	public static void main(String[] args)
	{
		JFrame kube = new PyramideFrame();
		kube.setVisible(true);
	}
}




