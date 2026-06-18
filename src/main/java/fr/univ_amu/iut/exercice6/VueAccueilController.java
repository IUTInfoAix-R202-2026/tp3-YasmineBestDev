package fr.univ_amu.iut.exercice6;

import javafx.fxml.FXML;

public class VueAccueilController {
  @FXML private EnTeteController enTeteController;
  @FXML private ListeSitesController listeSitesController;

  @FXML
  private void initialize() {
    enTeteController.setActionNouveauSite(
        () -> {
          int total = listeSitesController.ajouterSiteDemo();
          enTeteController.mettreAJourCompteur(total);
        });

    enTeteController.mettreAJourCompteur(0);
  }
}
