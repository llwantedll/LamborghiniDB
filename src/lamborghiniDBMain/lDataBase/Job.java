package lamborghiniDBMain.lDataBase;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Job implements DataBaseClass{
    private IntegerProperty id;
    private StringProperty type;
    private IntegerProperty employeePersonnelNumber;
    private IntegerProperty carSerialNumber;
    private StringProperty date;

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public IntegerProperty employeePersonnelNumberProperty() {
        return employeePersonnelNumber;
    }

    public IntegerProperty carSerialNumberProperty() {
        return carSerialNumber;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public Job(int id, String type, int employeePersonnelNumber, int carSerialNumber, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.employeePersonnelNumber = new SimpleIntegerProperty(employeePersonnelNumber);
        this.carSerialNumber = new SimpleIntegerProperty(carSerialNumber);
        this.date = new SimpleStringProperty(date);
    }

    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getType() {
        return type.getValue();
    }

    public int getEmployeePersonnelNumber() {
        return employeePersonnelNumber.getValue();
    }

    public int getCarSerialNumber() {
        return carSerialNumber.getValue();
    }

    public String getDate() {
        return date.getValue();
    }

}
