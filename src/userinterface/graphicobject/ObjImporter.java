package userinterface.graphicobject;

import javafx.scene.shape.MeshView;

/**
 * This interface provides a method to import Obj file into Mesh Views.
 * 
 * @author El-Attar
 *
 */
public interface ObjImporter 
{
	/**
	 * Imports Obj file into Mesh Views.
	 * 
	 * @param path The file path.
	 */
	public void importObjMesh(String path);
	
	/**
	 * Get the MeshViews
	 * 
	 * @return MeshViews
	 */
	public MeshView[] getMeshViews();
}
