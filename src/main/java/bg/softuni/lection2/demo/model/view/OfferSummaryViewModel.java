package bg.softuni.lection2.demo.model.view;

import bg.softuni.lection2.demo.model.enums.EngineEnum;
import bg.softuni.lection2.demo.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public class OfferSummaryViewModel {


    private EngineEnum engine;
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private int year;
    private TransmissionEnum transmission;

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }
}
