package lamborghiniDBMain.lDataBase;

import javafx.beans.property.*;

public class Employee implements DataBaseClass{
    private IntegerProperty personnelNumber;
    private StringProperty name;
    private StringProperty tNumber;
    private StringProperty cardNumber;
    private StringProperty adress;
    private StringProperty password;
    private StringProperty login;
    private BooleanProperty isAdmin;
    private StringProperty isFired;

    @Override
    public String toString() {
        return "ID=" + personnelNumber.getValue() +
                ", name=" + name.getValue() +
                ", phone=" + tNumber.getValue() +
                ", cardN=" + cardNumber.getValue() +
                ", log=" + login.getValue() +
                ", fired=" + isFired.getValue();
    }

    public Employee(int personnelNumber, String name, String tNumber, String cardNumber, String adress, String password, String login, boolean isAdmin, String isFired) {
        this.personnelNumber = new SimpleIntegerProperty(personnelNumber);
        this.name = new SimpleStringProperty(name);
        this.tNumber = new SimpleStringProperty(tNumber);
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.adress = new SimpleStringProperty(adress);
        this.password = new SimpleStringProperty(password);
        this.login = new SimpleStringProperty(login);
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
        this.isFired = new SimpleStringProperty(isFired);
    }

    public IntegerProperty personnelNumberProperty() {
        return personnelNumber;
    }

    public StringProperty tNumberProperty() {
        return tNumber;
    }

    public StringProperty cardNumberProperty() {
        return cardNumber;
    }

    public StringProperty adressProperty() {
        return adress;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty loginProperty() {
        return login;
    }

    public boolean isIsAdmin() {
        return isAdmin.get();
    }

    public BooleanProperty isAdminProperty() {
        return isAdmin;
    }

    public String isIsFired() {
        return isFired.get();
    }

    public StringProperty isFiredProperty() {
        return isFired;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin.getValue();
    }

    public void setAdmin(boolean admin) {
        this.isAdmin.setValue(admin);
    }

    public int getPersonnelNumber() {
        return personnelNumber.getValue();
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber.setValue(personnelNumber);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getNumber() {
        return tNumber.getValue();
    }

    public void setNumber(String tNumber) {
        this.tNumber.setValue(tNumber);
    }

    public String getCardNumber() {
        return cardNumber.getValue();
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.setValue(cardNumber);
    }

    public String getAdress() {
        return adress.getValue();
    }

    public void setAdress(String adress) {
        this.adress.setValue(adress);
    }

    public String getPassword() {
        return password.getValue();
    }

    public String getLogin() {
        return login.getValue();
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public void setLogin(String login) {
        this.login.setValue(login);
    }

}
