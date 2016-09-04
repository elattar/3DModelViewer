package userinterface.graphicobject;

import javafx.scene.transform.Rotate;

/**
 * This class represents a rotation in the 3D space. 
 * 
 * @author El-Attar
 * @version 1.0
 */
public class Rotation
{
	/**
	 * The rotate value about x-axis.
	 */
    private Rotate rotateX = new Rotate();
    
    /**
     * The rotate value about y-axis.
     */
    private Rotate rotateY = new Rotate();
    
    /**
     * The rotate value about z-axis.
     */
    private Rotate rotateZ = new Rotate();
    
    /**
     * Default Constructor.
     * 
     * Sets the rotation to (0,0,0).
     */
    public Rotation()
    {

        rotateX.setAxis(Rotate.X_AXIS);
        rotateY.setAxis(Rotate.Y_AXIS);
        rotateZ.setAxis(Rotate.Z_AXIS);
        
    	setRotation(0,0,0);
    }
    
    /** 
     * Returns the Rotate object for the X axis.
     * 
     * @return The Rotate object for the X axis.
     */
    public Rotate getRotateX()
    {
    	return this.rotateX;
    }
    
    /** 
     * Returns the Rotate object for the Y axis.
     * 
     * @return The Rotate object for the Y axis.
     */
    public Rotate getRotateY()
    {
    	return this.rotateY;
    }
    
    /** 
     * Returns the Rotate object for the Z axis.
     * 
     * @return The Rotate object for the Z axis.
     */
    public Rotate getRotateZ()
    {
    	return this.rotateZ;
    }
    
    /**
     * Sets the rotation of the transform around the three axis.
     * 
     * @param rotateX The rotation around the x axis.
     * @param rotateY The rotation around the y axis.
     * @param rotateZ The rotation around the z axis.
     */
    public void setRotation(double rotateX, double rotateY, double rotateZ)
    {
        this.rotateX.setAngle(rotateX);
        this.rotateY.setAngle(rotateY);
        this.rotateZ.setAngle(rotateZ);
    }
    
    /**
     * Sets the rotation around the x axis.
     * 
     * @param rotateX The rotation around the x axis.
     */
    public void setX(double rotateX) 
    { 
    	this.rotateX.setAngle(rotateX); 
    }
    
    /**
     * Sets the rotation around the y axis.
     * 
     * @param rotateY The rotation around the y axis.
     */
    public void setY(double rotateY) 
    { 
    	this.rotateY.setAngle(rotateY); 
    }
    
    /**
     * Sets the rotation around the z axis.
     * 
     * @param rotateZ The rotation around the z axis.
     */
    public void setZ(double rotateZ) 
    { 
    	this.rotateZ.setAngle(rotateZ); 
    }
    
    /**
     * Gets the rotation around the x axis.
     * 
     * @return The rotation around the x axis.
     */
    public double getX() 
    {
    	return this.rotateX.getAngle();
    }
    
    /**
     * Gets the rotation around the y axis.
     * 
     * @return The rotation around the y axis.
     */
    public double getY() 
    {
    	return this.rotateY.getAngle(); 
    }
    
    /**
     * Gets the rotation around the z axis.
     * 
     * @return The rotation around the z axis.
     */
    public double getZ()
    { 
    	return this.rotateZ.getAngle(); 
    }

    /**
     * Returns a string representation of the rotation.
     * 
     * @return The string representation.
     */
    @Override
    public String toString()
    {
	    return "r = (" +
	    rotateX.getAngle() + ", " +
	    rotateY.getAngle() + ", " +
	    rotateZ.getAngle() + ")  ";
    }

}
