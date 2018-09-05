package lamborghiniDBMain.lDataBase;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client implements DataBaseClass{
    private StringProperty cardNumber;
    private StringProperty name;
    private StringProperty tNumber;

    public Client(String cardNumber, String name, String tNumber) {
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.name = new SimpleStringProperty(name);
        this.tNumber = new SimpleStringProperty(tNumber);
    }

    public StringProperty cardNumberProperty() {
        return cardNumber;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String gettNumber() {
        return tNumber.get();
    }

    public StringProperty tNumberProperty() {
        return tNumber;
    }

    public String getCardNumber() {
        return cardNumber.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getNumber() {
        return tNumber.getValue();
    }

}
