package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import userinterface.graphicobject.Object3D;
import userinterface.userinputshandlers.MouseInputsHandler;

/**
 * This class is used to run the application.
 * 
 * @author El-Attar
 *
 */
public class Main extends Application
{

	@Override
	public void start(Stage window) throws Exception 
	{
		Object3D object = new Object3D("models/bunny.obj", 300, 200, 0, 20);
		Group root3D = new Group(object.getMeshViews());
		
		MouseInputsHandler mouseHandler = new MouseInputsHandler();
		mouseHandler.handleMouse(root3D, object.getMeshViews(), object.getView());
		
		Scene scene = new Scene(root3D, 900, 700, true);
        scene.setFill(Color.STEELBLUE);
        scene.setCamera(object.getCamera());
        
        window.setTitle("JavaFx 3D Object Test");
        window.setScene(scene);
        window.show();
        
	}
	
	public static void main(String[] args) 
    {
        launch(args);
    }
}
