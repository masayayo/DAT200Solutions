package windmill;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

import com.mnstarfire.loaders3d.Inspector3DS;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class TMyUniverse extends VirtualUniverse {
	
	private BoundingSphere bndsphWorld;
	
	public TMyUniverse(Canvas3D aCanvas3D){
		bndsphWorld = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100000);
		
		Locale locale = new Locale(this);
		BranchGroup viewBranch = createViewBranch(aCanvas3D);
		locale.addBranchGraph(viewBranch);
		
		BranchGroup objectBranch = new BranchGroup();
		setBackground(objectBranch);
		setLight(objectBranch);
		
		TransformGroup tfgFundamentModel = loadObject("C:\\Users\\Main Computer\\Documents\\3DS Max Projects Folder\\export\\windmill_fundament_detailed2.3ds");
		TransformGroup tfgRotorModel = loadObject("C:\\Users\\Main Computer\\Documents\\3DS Max Projects Folder\\export\\windmill_rotor_detailed3.3ds");
		
		objectBranch.addChild(tfgFundamentModel);
		tfgFundamentModel.addChild(tfgRotorModel);
		tfgRotorModel.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
        Transform3D aboutZAxis = new Transform3D();
        aboutZAxis.rotX(Math.PI/2);
        Alpha rotationAlpha = new Alpha(
                -1, Alpha.INCREASING_ENABLE,
                0, 0,4000, 0, 0, 0, 0, 0);
        RotationInterpolator rotator = new RotationInterpolator(
                rotationAlpha, tfgRotorModel, aboutZAxis,
                0.0f, (float) Math.PI*2.0f);
        BoundingSphere bounds =
            new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
        rotator.setSchedulingBounds(bounds);
        tfgFundamentModel.addChild(rotator);
               
		objectBranch.compile();
		locale.addBranchGraph(objectBranch);
				
	}

	private TransformGroup loadObject(String aFile) {
      
		Inspector3DS loader = new Inspector3DS(aFile); // constructor
		loader.parseIt(); // process the file
		TransformGroup tfgModel = loader.getModel(); // get the resulting 3D model as a Transform Group with Shape3Ds as children
		
		Transform3D matDScale=new Transform3D();
		matDScale.setScale(0.0009f);
		tfgModel.setTransform(matDScale);
		return tfgModel;
	}

	private void setLight(BranchGroup aObjectGraph) {

		BoundingSphere boundsb = new BoundingSphere(new Point3d(0.0,0.0,0.0), 300.0);


		AmbientLight al = new AmbientLight(true,new Color3f(1,1,1));
		al.setInfluencingBounds(boundsb);

		Point3f point = new Point3f(60,0,120);
		PointLight lgt1 = new PointLight();
		SpotLight lgt2 = new SpotLight();
		Color3f lColor1 = new Color3f(1.0f,1.0f,1.0f);
		lgt1.setPosition(point);
		lgt1.setColor(lColor1);
		lgt1.setInfluencingBounds(boundsb);

		lgt2.setDirection(-100.0f, 0.0f, -100.0f);
		lgt2.setPosition(100.0f, 0.0f, 100.0f);
		lgt2.setSpreadAngle((float)Math.PI/64);
		lgt2.setInfluencingBounds(boundsb);

		aObjectGraph.addChild(lgt1);
		aObjectGraph.addChild(lgt2);
		aObjectGraph.addChild(al);
		
	}

	private void setBackground(BranchGroup aObjectBranch) {
		Background b=new Background(1,1,1);
		BoundingSphere boundsb = new BoundingSphere(new Point3d(0.0,0.0,0.0), 500.0);
		b.setApplicationBounds(boundsb);
		aObjectBranch.addChild(b);
	}

	private BranchGroup createViewBranch(Canvas3D aCanvas3D) {
		// Lager den Branchgroup som denne funksjonen skal returnere.
		BranchGroup bgReturn = new BranchGroup();
		
		Viewer viewer = new Viewer(aCanvas3D);
		ViewingPlatform viewingPlatform = new ViewingPlatform();
		viewer.setViewingPlatform(viewingPlatform);

		OrbitBehavior orbit = new OrbitBehavior(aCanvas3D, OrbitBehavior.REVERSE_ROTATE);
		orbit.setSchedulingBounds(bndsphWorld);
		viewingPlatform.setViewPlatformBehavior(orbit);
		viewingPlatform.setNominalViewingTransform();
		
		View view = viewer.getView();
		view.setBackClipDistance(bndsphWorld.getRadius());
		view.setPhysicalBody(new PhysicalBody());
		view.setPhysicalEnvironment(new PhysicalEnvironment());
		
		TransformGroup viewGroup = viewingPlatform.getViewPlatformTransform(); 
		viewGroup = new TransformGroup();
		viewGroup.addChild(viewingPlatform);
		bgReturn.addChild(viewGroup);
		
		return bgReturn;
	}
}
