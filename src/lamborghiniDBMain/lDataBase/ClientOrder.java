package lamborghiniDBMain.lDataBase;

import javafx.beans.property.*;

public class ClientOrder implements DataBaseClass{
    private IntegerProperty id;
    private IntegerProperty price;
    private StringProperty date;
    private StringProperty clientCardNumber;
    private IntegerProperty jobID;
    private BooleanProperty executed;

    public ClientOrder(int id, int price, String date, String clientCardNumber, int jobID, boolean executed) {
        this.id = new SimpleIntegerProperty(id);
        this.price = new SimpleIntegerProperty(price);
        this.date = new SimpleStringProperty(date);
        this.clientCardNumber = new SimpleStringProperty(clientCardNumber);
        this.jobID = new SimpleIntegerProperty(jobID);
        this.executed = new SimpleBooleanProperty(executed);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty clientCardNumberProperty() {
        return clientCardNumber;
    }

    public int getJobID() {
        return jobID.get();
    }

    public IntegerProperty jobIDProperty() {
        return jobID;
    }

    public BooleanProperty executedProperty() {
        return executed;
    }

    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id.setValue(id);
    }

    public int getPrice() {
        return price.getValue();
    }

    public String getDate() {
        return date.getValue();
    }

    public String getClientCardNumber() {
        return clientCardNumber.getValue();
    }

    public boolean isExecuted() {
        return executed.getValue();
    }

}
