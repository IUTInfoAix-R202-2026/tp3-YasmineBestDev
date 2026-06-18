package fr.univ_amu.iut.exercice6;

import fr.univ_amu.iut.exercice5.SiteCarte;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ListeSitesController {
  @FXML private VBox conteneurCartes;
  private int compteurDemo = 0;

  public int ajouterSiteDemo() {
    compteurDemo++;

    SiteCarte nouvelleCarte = new SiteCarte();
    nouvelleCarte.setNumeroCarre("Carré " + (640000 + compteurDemo));
    nouvelleCarte.setNomConvivial("📍 Site de démonstration #" + compteurDemo);
    nouvelleCarte.setNombrePoints((compteurDemo % 3) + 1);
    nouvelleCarte.setNombrePassages(compteurDemo * 2);
    nouvelleCarte.setJoursDepuisDernierPassage(compteurDemo * 4);

    conteneurCartes.getChildren().add(0, nouvelleCarte);

    return conteneurCartes.getChildren().size();
  }
}
