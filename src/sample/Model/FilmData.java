package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FilmData {

    private final static FilmData filmData = new FilmData();

    private final DateTimeFormatter formatter;
    ObservableList<Film> filmObservableList;

    public static FilmData getInstance() {

        return filmData;

    }

    private FilmData() {

        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    }

    public void readFilmFromFile() {

        filmObservableList = FXCollections.observableArrayList();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("films.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] oneLine = line.split("\t");

                String filmTitle = oneLine[0];
                String filmDetail = oneLine[1];
                LocalDate filmLocalDate = LocalDate.parse(oneLine[2],formatter);

                Film f = new Film(filmTitle, filmDetail, filmLocalDate);

                filmObservableList.add(f);

            }
            System.out.println(filmObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFilmToFile() {
//                      append değeri
        
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("films.txt"))) {


            for (Film oankiFilm : filmObservableList) {

                bufferedWriter.write(String.format("%s\t%s\t%s\n", oankiFilm.getBaslik(), oankiFilm.getDetay(), oankiFilm.getLocalDate().format(formatter)));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filmEkle(Film film) {

        filmObservableList.add(film);
    }

    public void filmSil(Film film) {

        filmObservableList.remove(film);
    }
}
