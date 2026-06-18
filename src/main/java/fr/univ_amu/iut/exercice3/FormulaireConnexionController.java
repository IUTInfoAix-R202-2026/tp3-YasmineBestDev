package fr.univ_amu.iut.exercice3;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FormulaireConnexionController {

  @FXML private TextField champIdentifiant;
  @FXML private PasswordField champMotDePasse;
  @FXML private Button boutonOk;
  @FXML private Button boutonAnnuler;
  @FXML private Label labelMessage;

  @FXML
  private void initialize() {

    champMotDePasse
        .editableProperty()
        .bind(Bindings.greaterThanOrEqual(champIdentifiant.textProperty().length(), 6));

    boutonAnnuler
        .disableProperty()
        .bind(
            Bindings.and(
                Bindings.equal(0, champIdentifiant.textProperty().length()),
                Bindings.equal(0, champMotDePasse.textProperty().length())));

    BooleanBinding motDePasseInvalide =
        new BooleanBinding() {
          {
            super.bind(champMotDePasse.textProperty());
          }

          @Override
          protected boolean computeValue() {
            String pwd = champMotDePasse.getText();
            boolean tropCourt = pwd.length() < 8;
            boolean pasDeMajuscule = !pwd.matches(".*[A-Z].*");
            boolean pasDeChiffre = !pwd.matches(".*[0-9].*");
            return tropCourt || pasDeMajuscule || pasDeChiffre;
          }
        };

    boutonOk.disableProperty().bind(motDePasseInvalide);
  }

  @FXML
  private void valider() {
    String login = champIdentifiant.getText();
    int nbCaracteres = champMotDePasse.getText().length();
    String etoiles = "*".repeat(nbCaracteres);
    labelMessage.setText(login + " " + etoiles);
  }

  @FXML
  private void annuler() {
    champIdentifiant.clear();
    champMotDePasse.clear();
    labelMessage.setText("");
  }
}
