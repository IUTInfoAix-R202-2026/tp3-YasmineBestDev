package fr.univ_amu.iut.exercice1;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PremiereVueFXML extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // 1. Charger le fichier FXML
    URL fxmlLocation = getClass().getResource("PremiereVueFXML.fxml");

    // 2. Charger la racine (BorderPane)
    Parent root = FXMLLoader.load(fxmlLocation);

    // 3. Créer la scène et l'assigner au stage
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);

    // 4. Configurer le titre et afficher
    primaryStage.setTitle("Première vue FXML");
    primaryStage.show();
  }
}
