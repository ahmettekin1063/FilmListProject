package sample;

import java.time.LocalDate;

public class Film {

    private String baslik;
    private String detay;
    private LocalDate localDate;

    public Film(String baslik, String detay, LocalDate localDate) {
        this.baslik = baslik;
        this.detay = detay;
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    @Override
    public String toString() {
        return baslik;
    }
}
