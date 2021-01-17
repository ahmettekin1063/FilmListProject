package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class YeniFilmController {

    @FXML
    private DatePicker dateCikisTarihi;

    @FXML
    private TextField txtFilmAdi;

    @FXML
    private TextArea txtFilmDetay;

    public Film yeniNotuEkle() {

        String filmAdi=txtFilmAdi.getText().trim();
        String filmDetay= txtFilmDetay.getText().trim();

        LocalDate filmTarih = dateCikisTarihi.getValue();

        Film f = new Film(filmAdi,filmDetay,filmTarih);

        FilmData.getInstance().filmEkle(f);

        return f;
    }
}
