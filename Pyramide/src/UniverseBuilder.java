package Pyramide;
 
import java.awt.*;
import java.awt.event.*;
 
import javax.media.j3d.*;
import javax.vecmath.*;
 
public class UniverseBuilder {
 
    // User-specified canvas
 
    Canvas3D canvas;
 
    // Scene graph elements to which the user may want access
 
    VirtualUniverse               universe;
    Locale                        locale;
    TransformGroup                vpTrans;
    View                          view;
 
    public UniverseBuilder(Canvas3D c) {
        this.canvas = c;
 
        // Establish a virtual universe that has a single
        // hi-res Locale
 
        universe = new VirtualUniverse();
        locale = new Locale(universe);
 
        // Create a PhysicalBody and PhysicalEnvironment object
 
        PhysicalBody body = new PhysicalBody();
        PhysicalEnvironment environment = new PhysicalEnvironment();
 
        // Create a View and attach the Canvas3D and the physical
        // body and environment to the view.
 
        view = new View();
        view.addCanvas3D(c);
        view.setPhysicalBody(body);
        view.setPhysicalEnvironment(environment);
 
        // Create a BranchGroup node for the view platform
 
        BranchGroup vpRoot = new BranchGroup();
 
        TransformGroup vpRotation = new TransformGroup();
        vpRotation.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        vpRoot.addChild(vpRotation);
       
        Transform3D aboutXAxis = new Transform3D();
        aboutXAxis.rotZ(Math.PI/2);
        Alpha rotationAlpha = new Alpha(
                -1, Alpha.INCREASING_ENABLE,
                0, 0,4000, 0, 0, 0, 0, 0);
        RotationInterpolator rotator = new RotationInterpolator(
                rotationAlpha, vpRotation, aboutXAxis,
                0.0f, (float) Math.PI*2.0f);
        BoundingSphere bounds =
            new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        rotator.setSchedulingBounds(bounds);
        vpRoot.addChild(rotator);
 
        // Create a ViewPlatform object, and its associated
        // TransformGroup object, and attach it to the root of the
        // subgraph. Attach the view to the view platform.
 
        Transform3D t = new Transform3D();
        Transform3D s = new Transform3D();
        //t.set(new Vector3f(0.0f, 0.0f, 10.0f));
       
        t.rotX(-Math.PI/4);
        s.set(new Vector3f(0.0f, 0.0f, 10.0f)); //s.set(new Vector3f(0.0f, 0.0f, 10.0f));
        t.mul(s);
               
        ViewPlatform vp = new ViewPlatform();
        vpTrans = new TransformGroup(s);  //Bytt ut med t for Œ se kuben ovenfra
        vpTrans.addChild(vp);
        vpRotation.addChild(vpTrans);
 
        view.attachViewPlatform(vp);

        // Attach the branch graph to the universe, via the
        // Locale. The scene graph is now live!
 
        locale.addBranchGraph(vpRoot);
    }
 
    public void addBranchGraph(BranchGroup bg) {
        locale.addBranchGraph(bg);
    }
 
}