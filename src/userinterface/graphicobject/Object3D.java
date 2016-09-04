package userinterface.graphicobject;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import userinterface.objecthandler.View;

/**
 * This class represents an object in the 3D space.
 * 
 * @author El-Attar
 * @version 1.0
 */
public class Object3D extends Group implements Transform, ObjImporter
{
	/**
	 * The obj mesh importer.	
	 */
	public final ObjModelImporter OBJ_IMPORTER = new ObjModelImporter();	
	
	/**
	 * Conversion factor from radians into degrees.
	 */
	public static final double RAD_TO_DEG = 180.0 / Math.PI;
	
	/**
	 * Represents the translation of x, y, z.
	 */
	private Translate position  = new Translate(0,0,0);
	
	/**
	 * Represents the rotation around x, y, and z axis.
	 */
	private Rotation rotation = new Rotation();
	
	/**
	 * Represents the scale of object at x, y, z.
	 */
    private Scale scale = new Scale();
    
    private View camera;
    /**
     * Saves all 3D object MeshViews.
     */
    private MeshView[] meshViews;
    
    /**
     * Parameterized constructor.
     * 
     * @param path The file path of obj format.
     * @param posX The position on x-axis to translate to.
     * @param posY The position on y-axis to translate to.
     * @param posZ The position on z-axis to translate to.
     * @param startScaleFactor The scale factor at the beginning.
     */
    public Object3D(String path, double posX, double posY, double posZ, double startScaleFactor) 
    { 
        super(); 
        importObjMesh(path);
        setPosition(posX, posY, posZ);
        setScale(startScaleFactor);
        setRotation(0.0, 0.0, 0.0);
        setCamera();
    }
    
    public void setCamera()
    {
    	camera = new View(this);
    }
    public View getView()
    {
    	return this.camera;
    }
    public PerspectiveCamera getCamera()
    {
    	return camera.getCamera();
    }
    /**
     * Returns the Translate object for the position.
     * 
     * @return The Translate object for the position.
     */
    public Translate getPosition()
    {
    	return this.position;
    }
    
    /**
     * Returns the Rotation object for the rotation.
     * 
     * @return The Rotation object for the rotation.
     */
    public Rotation getRotation()
    {
    	return this.rotation;
    }
    
    /**
     * Returns the Scale object for the scale.
     * 
     * @return The Scale object for the scale.
     */
    public Scale getScale()
    {
    	return this.scale;
    }
    
    /**
     * Sets the translation from the three axis.
     * 
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     * @param z The translation from the z axis.
     */
    public void setPosition(double x, double y, double z) 
    {
    	for (MeshView meshView : meshViews) 
        {        	
        	meshView.setTranslateX(x);
            meshView.setTranslateY(y) ;
            meshView.setTranslateZ(z);
        }
    }

    /**
     * Sets the translation from the x and y axis.
     * 
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     */
    public void setPosition(double x, double y)
    {
    	for (MeshView meshView : meshViews) 
        {        	
        	meshView.setTranslateX(x);
            meshView.setTranslateY(y) ;
        }
    }

    /**
     * Sets the rotation around the three axis.
     * 
     * @param x The rotation around the x axis.
     * @param y The rotation around the y axis.
     * @param z The rotation around the z axis.
     */
    public void setRotation(double x, double y, double z)
    {
    	rotation.setRotation(x, y, z);
    	for (MeshView meshView : meshViews) 
        {
    		meshView.getTransforms().add(new Rotate(x, Rotate.X_AXIS));
    		meshView.getTransforms().add(new Rotate(y, Rotate.X_AXIS));
    		meshView.getTransforms().add(new Rotate(z, Rotate.X_AXIS));
        }
    }
    
    /**
     * Sets the scale factor for the three axis.
     * 
     * @param scaleFactor The scale factor.
     */
    public void setScale(double scaleFactor)
    {
    	for (MeshView meshView : meshViews) 
        {
    		meshView.setScaleX(scaleFactor);
            meshView.setScaleY(scaleFactor);
            meshView.setScaleZ(scaleFactor);           
        }
    }

    
    /**
     * Sets the scale for the three axis.
     * 
     * @param scaleX The scale for the x axis.
     * @param scaleY The scale for the y axis.
     * @param scaleZ The scale for the z axis.
     */
    public void setScale(double scaleX, double scaleY, double scaleZ) 
    {
    	for (MeshView meshView : meshViews) 
        {
    		meshView.setScaleX(scaleX);
            meshView.setScaleY(scaleY);
            meshView.setScaleZ(scaleZ);           
        }
    }

    /**
     * Resets the position to the origin of the axis, the rotation parallel to the axis 
     * and the scale to the unitary values.
     */
    public void reset() 
    {
    	setPosition(0.0, 0.0, 0.0);
        setRotation(0.0, 0.0, 0.0);
        setScale(1.0);
    }

    /**
	 * Imports the MeshViews from a obj file format.
	 * 
	 * @param path The file path.
	 */
	public void importObjMesh(String path)
	{
		try
		{
			OBJ_IMPORTER.read(path);
		}
		catch(Exception e)
		{
			System.out.println("Error: Can't open the file ...");
			return;
		}
	
		this.meshViews = OBJ_IMPORTER.getImport();
	}
	
	/**
	 * Get the MeshViews
	 * 
	 * @return MeshViews
	 */
	public MeshView[] getMeshViews()
	{
		return this.meshViews;
	}
	
    /**
     * Returns the string representation of this transform.
     */
    @Override 
    public String toString() 
    {
        return "Transform [t = (" +
        				   position.getX() + ", " +
        				   position.getY() + ", " +
        				   position.getZ() + ")  " +
        				   rotation.toString() +
                           "s = (" +
                           scale.getX() + ", " + 
                           scale.getY() + ", " + 
                           scale.getZ() + ") ]";
    }

}
