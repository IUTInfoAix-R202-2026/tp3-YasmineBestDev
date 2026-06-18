package fr.univ_amu.iut.exercice4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CoquilleAccueilController {

  @FXML private Label labelTitre;
  @FXML private Label labelStatut;

  private void updateUI(String titre, String statut) {
    labelTitre.setText(titre);
    labelStatut.setText("Rubrique active : " + statut);
  }

  @FXML
  private void onMesSites() {
    updateUI("Mes sites de suivi", "Mes sites");
  }

  @FXML
  private void onImporter() {
    updateUI("Importer une nuit", "Importer une nuit");
  }

  @FXML
  private void onVueTabulaire() {
    updateUI("Vue tabulaire des passages", "Vue tabulaire");
  }

  @FXML
  private void onParametres() {
    updateUI("Paramètres de l'application", "Paramètres");
  }

  @FXML
  private void onQuitter() {
    // Récupère la fenêtre (Stage) à partir de la scène du label
    Stage stage = (Stage) labelTitre.getScene().getWindow();
    stage.close();
  }
}
