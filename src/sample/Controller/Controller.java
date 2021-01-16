package sample.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

public class Controller {

    @FXML
    private BorderPane anaPencere;
    @FXML
    void filmEkleDialog(ActionEvent event) {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(anaPencere.getScene().getWindow());


    }

    @FXML
    void uygulamayiKapat(ActionEvent event) {

        Platform.exit();
    }

}
