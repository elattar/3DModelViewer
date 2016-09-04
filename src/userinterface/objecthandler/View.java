package userinterface.objecthandler;

import javafx.scene.PerspectiveCamera;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import userinterface.graphicobject.Object3D;
import userinterface.graphicobject.Rotation;


public class View 
{
	/**
	 * Perspective camera for rendering the 3D world onto flat surface.
	 */
	private PerspectiveCamera camera;
	
	/**
	 * 3D object imported in application.
	 */
	private Object3D object;
	
	/**
	 * Maximum window width.
	 */
	private double maxWidth;
	
	/**
	 * Maximum window height.
	 */
	private double maxHeight;
	
	/**
	 * The minimum scale factor while zooming out.
	 */
	private double minScaleFactor;
	
	/**
	 * The maximum scale factor while zooming in.
	 */
	private double maxScaleFactor;
	
	private Rotation rotateValues = new Rotation();
	/**
	 * The scale factor;
	 */
	private double scaleFactor;
	
	/**
	 * Gets scale factor
	 * @return scale factor
	 */
	public double getScaleFactor() {
		return scaleFactor;
	}

	/**
	 * Sets Scale factor
	 * @param scaleFactor scale factor
	 */
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	/**
	 * Get the minimum scale factor can be reached while zooming out.
	 * @return minimum scale factor
	 */
	public double getMinScaleFactor() 
	{
		return minScaleFactor;
	}
	
	/**
	 * Set the minimum scale factor can be reached while zooming out.
	 * @param minScaleFactor The value of minimum scale factor. 
	 */
	public void setMinScaleFactor(double minScaleFactor) 
	{
		this.minScaleFactor = minScaleFactor;
	}
	
	/**
	 * Get the maximum scale factor can be reached while zooming in.
	 * @return maximum scale factor
	 */
	public double getMaxScaleFactor() 
	{
		return maxScaleFactor;
	}
	
	/**
	 * Set the maximum scale factor can be reached while zooming in.
	 * @param maxScaleFactor The value of maximum scale factor. 
	 */
	public void setMaxScaleFactor(double maxScaleFactor)
	{
		this.maxScaleFactor = maxScaleFactor;
	}
	
	/**
	 * Get maximum width of the window.
	 * @return maximum width of the window.
	 */
	public double getMaxWidth() {
		return maxWidth;
	}
	/**
	 * Gets Perspective Camera.
	 * @return PerspectiveCamera.
	 */
	public PerspectiveCamera getCamera() {
		return camera;
	}

	/**
	 * Set
	 * @param maxWidth
	 */
	public void setMaxWidth(double maxWidth) {
		this.maxWidth = maxWidth;
	}
	
	/**
	 * Get maximum height of the window.
	 * @return maximum height of the window.
	 */
	public double getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(double maxHeight) {
		this.maxHeight = maxHeight;
	}

	/**
	 * Main Constructor used to set camera at right position.
	 * @param object 3D object imported in application.
	 */
	public View(Object3D object) 
	{
		this.object = object;
		this.camera = new PerspectiveCamera(false);
		camera.getTransforms().addAll(rotateValues.getRotateX(), rotateValues.getRotateY(), new Translate(0, 0, 0));
		
		// setting pivots for x, y, z
		rotateValues.getRotateX().setPivotX(object.getPosition().getX());
		rotateValues.getRotateX().setPivotY(object.getPosition().getY());
		rotateValues.getRotateX().setPivotZ(object.getPosition().getZ());
		
		rotateValues.getRotateY().setPivotX(object.getPosition().getX());
		rotateValues.getRotateY().setPivotY(object.getPosition().getY());
		rotateValues.getRotateY().setPivotZ(object.getPosition().getZ());
		
        
		rotateValues.getRotateZ().setPivotX(object.getPosition().getX());
		rotateValues.getRotateZ().setPivotY(object.getPosition().getY());
		rotateValues.getRotateZ().setPivotZ(object.getPosition().getZ());
		
		
	}
	
	/**
	 * Translates the camera on the X-axis and Y-axis.
	 * 
	 * @param horizontal A value of the delta movement in order to change the position in x.
	 */
	public void translateObject(double xTranslate, double yTranslate)
	{
		for (MeshView meshView : object.getMeshViews()) 
        {
			meshView.setTranslateX(object.getPosition().getX() + xTranslate);
			meshView.setTranslateY(object.getPosition().getY() + yTranslate);
			
			if(meshView.getTranslateX() > maxWidth)
				meshView.setTranslateX(maxWidth);
			
			if(meshView.getTranslateX() < 0)
				meshView.setTranslateX(0);
			
			if(meshView.getTranslateY() > maxHeight)
				meshView.setTranslateY(maxHeight);
			
			if(meshView.getTranslateY() < 0)
				meshView.setTranslateY(0);
        }				
	}
	
	/**
	 * Rotates the camera around the X-axis, Y-axis, and Z-axis.
	 * 
	 * @param vertical A value of the delta movement in order to change the rotation in x.
	 */
	public void rotateObject(double xRotate, double yRotate)
	{
		for (MeshView meshView : object.getMeshViews()) 
        {
			meshView.getTransforms().add(new Rotate(xRotate, Rotate.X_AXIS));
			meshView.getTransforms().add(new Rotate(yRotate, Rotate.Y_AXIS));
        }        
	}
	
	/**
	 * Scales the object.
	 * 
	 * @param scaleFactor The scale factor. 
	 * @param zoomIn True if scale factor greater than 1.
	 * @param zoomOut True if scale factor is less than 1.
	 */
	public void scaleObject(double scaleFactor, boolean zoomIn, boolean zoomOut)
	{
		for (MeshView meshView : object.getMeshViews()) 
        {
			if( (meshView.getScaleX()) < getMaxScaleFactor() && zoomIn) 
    		{
				meshView.setScaleX(meshView.getScaleX() * scaleFactor);
				meshView.setScaleY(meshView.getScaleY() * scaleFactor);
				meshView.setScaleZ(meshView.getScaleZ() * scaleFactor);
    		}
    		else if( (meshView.getScaleX()) > getMinScaleFactor()  && zoomOut)
    		{
    			meshView.setScaleX(meshView.getScaleX() * scaleFactor);
    			meshView.setScaleY(meshView.getScaleY() * scaleFactor);
    			meshView.setScaleZ(meshView.getScaleZ() * scaleFactor);
    		}
		}
	}
}
