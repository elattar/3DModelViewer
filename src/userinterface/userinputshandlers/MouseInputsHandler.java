package userinterface.userinputshandlers;

import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import userinterface.objecthandler.View;

/**
 * This class handles the mouse events.
 * 
 * @author El-Attar
 *
 */
public class MouseInputsHandler 
{
	//private View view;
	//Mouse buttons
	/**
	 * Boolean check for Left Mouse Button.
	 */
	private boolean leftMouseButton;
	
	/**
	 * Boolean check for Right Mouse Button.
	 */
	private boolean rightMouseButton;
		
	//Mouse position
	/**
	 * Mouse positions, 
	 * x is saved in index 0, and y is saved in index 1.
	 */
	private double[] mousePosition = new double[2];
	
	/**
	 * Last Mouse Position,
	 * oldX is saved in index 0, and y is saved in index 1.
	 */
	private double[] lastMousePosition = new double[2];
	
	/**
	 * Returns whether the left mouse button is pressed or not.  
	 * 
	 * @return True if the left mouse button is pressed, false otherwise.
	 */
	public boolean isLeftButtonPressed()
	{
		return leftMouseButton;
	}
	
	/**
	 * Sets whether the left mouse button is pressed or not.
	 * 
	 * @param leftMouseButton True if the left mouse button is pressed, false otherwise.
	 */
	public void setLeftButtonPressed(boolean leftMouseButton)
	{
		this.leftMouseButton = leftMouseButton;
	}

	/**
	 * Returns whether the right mouse button is pressed or not.  
	 * 
	 * @return True if the right mouse button is pressed, false otherwise.
	 */
	public boolean isRightButtonPressed()
	{
		return rightMouseButton;
	}

	/**
	 * Sets whether the right mouse button is pressed or not.
	 * 
	 * @param rightMouseButton True if the right mouse button is pressed, false otherwise.
	 */
	public void setRightButtonPressed(boolean rightMouseButton) 
	{
		this.rightMouseButton = rightMouseButton;
	}
	
	/**
	 * Returns the x position of the cursor on the screen.
	 * 
	 * @return The x position of the cursor on the screen.
	 */
	public double getX()
	{
		return mousePosition[0];
	}
	
	/**
	 * Returns the y position of the cursor on the screen.
	 * 
	 * @return The y position of the cursor on the screen.
	 */
	public double getY()
	{
		return mousePosition[1];
	}

	/**
	 * Handles the mouse's events.
	 * 
	 * @param scene The 3D scene.
	 * @param camera The camera of the scene.
	 */
	public void handleMouse(Group root, MeshView[] meshViews, View view)
	{
        root.setOnMousePressed(event -> {
        	lastMousePosition[0] = event.getSceneX();
        	lastMousePosition[1] = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
        	if(event.isPrimaryButtonDown())
        	{
        		
//    			double degreesX =  rotateX.getAngle() - (event.getSceneY() - lastMousePosition[1]);
//    			double degreesY =  rotateY.getAngle() + (event.getSceneX() - lastMousePosition[0]);
        	
        		for (MeshView meshView : meshViews) 
                {
        			double xRotate = meshView.getRotate() - (-event.getSceneY() + lastMousePosition[1]);
        			double yRotate = meshView.getRotate() + (-event.getSceneX() + lastMousePosition[0]);
        			
        			view.rotateObject(xRotate, yRotate);
                }
        		lastMousePosition[0] = event.getSceneX();
        		lastMousePosition[1] = event.getSceneY();
        	}
        	else if (event.isSecondaryButtonDown())
        	{
        		double scaleFactor;
        		double dy = event.getSceneY() - lastMousePosition[1];
        		boolean zoomIn = false, zoomOut = false;
        		
        		// zoom in
        		if(dy < 0)
        		{
        			scaleFactor = view.getScaleFactor();
        			zoomIn = true;
        			view.scaleObject(scaleFactor, zoomIn, zoomOut);
        		}
        		// zoom out
        		else
        		{
        			scaleFactor = 1/view.getScaleFactor();
        			zoomOut = true;
        			view.scaleObject(scaleFactor, zoomIn, zoomOut);
        		}        		        		
        	}
        });
        
	}
}
