package lamborghiniDBMain.lDataBase;

import javafx.beans.property.*;

public class Car implements DataBaseClass{
    private IntegerProperty serialNumber;
    private StringProperty color;
    private StringProperty model;
    private IntegerProperty price;
    private BooleanProperty sold;

    public Car(int serialNumber, String color, String model, int price, boolean sold) {
        this.serialNumber = new SimpleIntegerProperty(serialNumber);
        this.color = new SimpleStringProperty(color);
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleIntegerProperty(price);
        this.sold = new SimpleBooleanProperty(sold);
    }

    public boolean isSold() {
        return sold.getValue();
    }

    public BooleanProperty soldProperty() {
        return sold;
    }

    public IntegerProperty serialNumberProperty() {
        return serialNumber;
    }

    public StringProperty colorProperty() {
        return color;
    }

    public StringProperty modelProperty() {
        return model;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public int getSerialNumber() {
        return serialNumber.getValue();
    }

    public String getColor() {
        return color.getValue();
    }

    public String getModel() {
        return model.getValue();
    }

    public int getPrice() {
        return price.getValue();
    }

}
