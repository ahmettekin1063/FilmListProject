package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private ContextMenu listeSilMenusu;

    @FXML
    private ListView<Film> filmListesi;


    @FXML
    private TextArea textArea;

    @FXML
    private BorderPane anaPencere;

    @FXML
    void filmEkleDialog(ActionEvent event) throws Exception {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(anaPencere.getScene().getWindow());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("yeniFilmDialog.fxml"));

        dialog.setTitle("Yeni Film Ekle");


        dialog.getDialogPane().setContent(loader.load());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> sonuc = dialog.showAndWait();

        if (sonuc.get() == ButtonType.APPLY) {
            System.out.println("apply tıklandı");

            YeniFilmController yeniFilmController = loader.getController();

            Film eklenenFilm = yeniFilmController.yeniNotuEkle();

            filmListesi.getSelectionModel().select(eklenenFilm);
        }


    }

    @FXML
    void uygulamayiKapat(ActionEvent event) {

        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listeSilMenusu = new ContextMenu();

        MenuItem filmSil = new MenuItem("Filmi Listeden Kaldır");
        filmSil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                secilenFilmiSil(filmListesi.getSelectionModel().getSelectedItem());
            }
        });

        listeSilMenusu.getItems().add(filmSil);

        filmListesi.setItems(FilmData.getInstance().getFilmObservableList());


               filmListesi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Film>() {


                   @Override
                   public void changed(ObservableValue<? extends Film> observableValue, Film film, Film t1) {

                       textArea.setText(t1.getDetay() + "\n" + t1.getLocalDate().toString());

                   }

               });


        filmListesi.getSelectionModel().selectFirst();

        filmListesi.setCellFactory(new Callback<ListView<Film>, ListCell<Film>>() {
            @Override
            public ListCell<Film> call(ListView<Film> filmListView) {

                ListCell<Film> yeniFilmListesi = new ListCell<>() {
                    @Override
                    protected void updateItem(Film film, boolean b) {

                        if (b || film == null) {
                            setText(null);
                        } else {
                            setText(film.getBaslik());
                            if (film.getBaslik().charAt(0) == 'a') {
                                setTextFill(Color.GREEN);
                            } else {
                                setTextFill(Color.RED);
                            }
                        }
                        super.updateItem(film, b);
                    }
                };
                yeniFilmListesi.setContextMenu(listeSilMenusu);
                return yeniFilmListesi;
            }
        });

    }

    private void secilenFilmiSil(Film silinecekFilm) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Emin misin?");
        alert.setHeaderText("silinecek film: " + silinecekFilm.getBaslik());

        Optional<ButtonType> sonuc = alert.showAndWait();
        if (sonuc.get() == ButtonType.OK) {
            FilmData.getInstance().filmSil(silinecekFilm);
        }
    }

    public Controller() {


    }


}
