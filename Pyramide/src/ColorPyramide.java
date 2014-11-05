package Pyramide;

import java.awt.Color;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;

public class ColorPyramide{

	
	//Originalt for QuadArray | Del av opprinnelige kode
    /*private static final float[] verts = {

    	
    	  // front face
	      1.0f, -1.0f, 1.0f, 				0.0f, 1.0f, 0.0f, 
	      0.0f, 1.0f, 0.0f, 				-1.0f, -1.0f, 1.0f,
	      
	      // back face
	      -1.0f, -1.0f, -1.0f, 				0.0f, 1.0f, 0.0f, 
	      0.0f, 1.0f, 0.0f, 				1.0f, -1.0f, -1.0f,
	      
	      // right face
	      1.0f, -1.0f, -1.0f, 				0.0f, 1.0f, 0.0f, 
	      0.0f, 1.0f, 0.0f, 				1.0f, -1.0f, 1.0f,
	      
	      // left face
	      -1.0f, -1.0f, 1.0f, 				0.0f, 1.0f, 0.0f, 
	      0.0f, 1.0f, 0.0f, 				-1.0f,-1.0f, -1.0f,
	      
	      // top face
	      0.0f, 1.0f, 0.0f, 				0.0f, 1.0f, 0.0f, 
	      0.0f, 1.0f, 0.0f, 				0.0f, 1.0f, 0.0f,
	      
	      // bottom face
	      -1.0f, -1.0f, 1.0f, 				-1.0f, -1.0f, -1.0f, 
	      1.0f, -1.0f, -1.0f, 				1.0f, -1.0f, 1.0f, 
	};
    */
   /* private static final float[] colors = {

		  // front face (cyan)
		  0.0f, 1.0f, 1.0f, 				0.0f, 1.0f, 1.0f, 
		  0.0f, 1.0f, 1.0f, 				0.0f, 1.0f, 1.0f,
		  
		  // back face (magenta)
		  1.0f, 0.0f, 1.0f, 				1.0f, 0.0f, 1.0f, 
		  1.0f, 0.0f, 1.0f, 				1.0f, 0.0f, 1.0f,
		  
		  // right face (yellow)
		  1.0f, 1.0f, 0.0f, 				1.0f, 1.0f, 0.0f, 
		  1.0f, 1.0f, 0.0f, 				1.0f, 1.0f, 0.0f,
		  
		  // left face (blue)
		  0.0f, 0.0f, 1.0f, 				0.0f, 0.0f, 1.0f, 
		  0.0f, 0.0f, 1.0f, 				0.0f, 0.0f, 1.0f,
		  
		  // top face (green)
		  0.0f, 1.0f, 0.0f, 				0.0f, 1.0f, 0.0f, 
		  0.0f, 1.0f, 0.0f, 				0.0f, 1.0f, 0.0f,
		  
		  // bottom face (red)
		  1.0f, 0.0f, 0.0f, 				1.0f, 0.0f, 0.0f, 
		  1.0f, 0.0f, 0.0f, 				1.0f, 0.0f, 0.0f,

    };*/
	
	//Coordinates for TriangleArray
	private static final float[] verts = {
    	
  	  // front face
	      1.0f, -1.0f, 1.0f, 				0.0f, 1.0f, 0.0f, 
	      -1.0f, -1.0f, 1.0f,
	      
	      // back face
	      -1.0f, -1.0f, -1.0f, 				0.0f, 1.0f, 0.0f, 
	      1.0f, -1.0f, -1.0f,
	      
	      // right face
	      1.0f, -1.0f, -1.0f, 				0.0f, 1.0f, 0.0f, 
	      1.0f, -1.0f, 1.0f,
	      
	      // left face
	      -1.0f, -1.0f, 1.0f, 				0.0f, 1.0f, 0.0f, 
	      -1.0f,-1.0f, -1.0f,
	      
	      // bottom face 1/2
	      1.0f, -1.0f, -1.0f, 				1.0f, -1.0f, 1.0f, 
	      -1.0f, -1.0f, -1.0f,
	      
	      // bottom face 2/2 
	      -1.0f, -1.0f, 1.0f, 				-1.0f, -1.0f, -1.0f, 
	      1.0f, -1.0f, 1.0f, 
	};
	
	private static final float[] colors = {

		  // front face (cyan)
		  0.0f, 1.0f, 1.0f, 				0.0f, 1.0f, 1.0f, 
		  0.0f, 1.0f, 1.0f,
		  
		  // back face (magenta)
		  1.0f, 0.0f, 1.0f, 				1.0f, 0.0f, 1.0f, 
		  1.0f, 0.0f, 1.0f,
		  
		  // right face (yellow)
		  1.0f, 1.0f, 0.0f, 				1.0f, 1.0f, 0.0f, 
		  1.0f, 1.0f, 0.0f,
		  
		  // left face (blue)
		  0.0f, 0.0f, 1.0f, 				0.0f, 0.0f, 1.0f, 
		  0.0f, 0.0f, 1.0f,
		  
		  // top face (green)
		  0.0f, 1.0f, 0.0f, 				0.0f, 1.0f, 0.0f, 
		  0.0f, 1.0f, 0.0f,
		  
		  // bottom face (red)
		  1.0f, 0.0f, 0.0f, 				1.0f, 0.0f, 0.0f, 
		  1.0f, 0.0f, 0.0f,

  };
	
    private Shape3D shape;

    public ColorPyramide() {
        //QuadArray cube = new QuadArray(24,QuadArray.COORDINATES);
        TriangleArray pyramid = new TriangleArray(18,TriangleArray.COORDINATES);
    	
        pyramid.setCoordinates(0, verts);
        //cube.setColors(0, colors);
        
        shape = new Shape3D(pyramid); //, new Appearance());
        
        Appearance app = new Appearance();
        ColoringAttributes ca = new ColoringAttributes();
        Color3f color = new Color3f(0,255,0); //green color
        ca.setColor(color);
        app.setColoringAttributes(ca);
        
        shape.setAppearance(app);
        
    }
    
    //For making the object appear in wireframe mode
    public static Appearance getWireframeAppearance() {
        Appearance app = new Appearance();
        Color awtColor = new Color(255, 255, 255);
        Color3f color = new Color3f(awtColor);
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(color);
        app.setColoringAttributes(ca);
        PolygonAttributes pa = new PolygonAttributes();
        pa.setPolygonMode(pa.POLYGON_LINE);
        pa.setCullFace(pa.CULL_NONE);
        app.setPolygonAttributes(pa);
        return app;
    }
    
    public Shape3D getShape() {
        return shape;
    }

}


