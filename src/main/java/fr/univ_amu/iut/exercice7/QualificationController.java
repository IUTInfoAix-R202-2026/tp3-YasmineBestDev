package fr.univ_amu.iut.exercice7;

import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class QualificationController {

  @FXML private TableView<Sequence> tableView;
  @FXML private TableColumn<Sequence, java.time.LocalTime> colHorodatage;
  @FXML private TableColumn<Sequence, Number> colFrequence;
  @FXML private TableColumn<Sequence, Number> colDuree;
  @FXML private TableColumn<Sequence, String> colStatut;
  @FXML private Label labelSelection;
  @FXML private Button boutonEcouter;
  @FXML private Label labelLecture;
  @FXML private ChoiceBox<String> choiceBoxVerdict;
  @FXML private TextArea zoneCommentaire;
  @FXML private Label labelVerdictGlobal;

  private final NuitVerification nuit = NuitVerification.genererJeu(10);

  @FXML
  private void initialize() {

    colHorodatage.setCellValueFactory(c -> c.getValue().horodatageProperty());
    colFrequence.setCellValueFactory(c -> c.getValue().frequenceDominanteKHzProperty());
    colDuree.setCellValueFactory(c -> c.getValue().dureeSecondesProperty());
    colStatut.setCellValueFactory(c -> c.getValue().statutProperty());
    tableView.setItems(nuit.getSequences());

    tableView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              if (newVal == null) {
                labelSelection.setText("(sélectionnez une séquence dans le tableau)");
              } else {
                labelSelection.setText(
                    String.format(
                        "Séquence %s - %.1f kHz",
                        newVal.getHorodatage(), newVal.getFrequenceDominanteKHz()));
              }
            });

    boutonEcouter
        .disableProperty()
        .bind(tableView.getSelectionModel().selectedItemProperty().isNull());

    choiceBoxVerdict.setItems(FXCollections.observableArrayList("OK", "Douteux", "À jeter"));

    labelVerdictGlobal
        .textProperty()
        .bind(
            Bindings.when(nuit.verdictGlobalProperty().isEmpty())
                .then("Verdict global : (à saisir)")
                .otherwise(Bindings.concat("Verdict global : ", nuit.verdictGlobalProperty())));

    zoneCommentaire.textProperty().bindBidirectional(nuit.commentaireProperty());
    labelSelection.setText("(sélectionnez une séquence dans le tableau)");
  }

  @FXML
  private void ecouter() {
    Sequence seq = tableView.getSelectionModel().getSelectedItem();
    if (seq != null) {
      seq.setStatut("Écoutée");
      labelLecture.setText("Lecture en cours...");

      PauseTransition pause = new PauseTransition(Duration.millis(600));
      pause.setOnFinished(e -> labelLecture.setText(""));
      pause.play();
    }
  }

  @FXML
  private void enregistrerVerdict() {
    String selection = choiceBoxVerdict.getValue();
    if (selection != null) {
      nuit.setVerdictGlobal(selection);
    }
  }

  public NuitVerification getNuit() {
    return nuit;
  }
}
