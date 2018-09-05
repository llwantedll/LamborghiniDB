package lamborghiniDBMain;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import lamborghiniDBMain.lDataBase.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminModeController extends Controller {

    private TableView<Employee> employeeTable = new TableView<>();
    private TableView<Car> carTable = new TableView<>();
    private TableView<Client> clientTable = new TableView<>();
    private TableView<ClientOrder> clientOrdersTable = new TableView<>();
    @FXML CheckBox isAdminReg;
    @FXML TextField loginReg;
    @FXML TextField passwordReg;
    @FXML TextField nameReg;
    @FXML TextField pNumberReg;
    @FXML TextField cardNumberReg;
    @FXML TextField adressReg;
    @FXML Label error;
    @FXML TextField orderIdT;
    private static ArrayList find = new ArrayList<>();
    private static String entered;
    private TextField deleteField = Main.getDeleteField();
    private static TextField textField = new TextField();
    private JavaFXFakerAdmin javaFX = new JavaFXFakerAdmin();
    private Coder coder = Main.getCoder();

    public void toAdminMode(ActionEvent actionEvent) throws IOException, SQLException {
        String source;
        source = "adminMode/adminMode.fxml";
        loading(source, actionEvent);
        refreshBD();
    }

    public void toEmployeeDelete(ActionEvent actionEvent) throws SQLException, IOException {
        String source = "adminMode/deletePage.fxml";
        refreshBD();
        javaFX.createDeletePage(actionEvent, source, deleteField, 1);
    }

    public void toSubmitPage(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "adminMode/submitPage.fxml";
        refreshBD();
        entered = deleteField.getText();
        find = javaFX.createSubmitPage(actionEvent, source, entered, employees);
        System.out.println(employees.get(1).getName());
    }

    public void toEmployeeList(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "adminMode/listPage.fxml";
        refreshBD();
        ObservableList<Employee> obs = javaFX.javaFXEmployeeList(actionEvent, source, employeeTable);
        obs.addAll(employees);
    }

    public void toAdminEmployeeEdit(ActionEvent actionEvent) throws IOException, SQLException {
        String source;
        source = "adminMode/adminEmployeeEditPage.fxml";
        loading(source, actionEvent);
        refreshBD();
    }

    public void registerConf(ActionEvent actionEvent) throws SQLException, IOException {
        refreshBD();
        String loginText = loginReg.getText();
        String passText = passwordReg.getText();
        String nameText = nameReg.getText();
        String pNumberText = pNumberReg.getText();
        String cardNumberText = cardNumberReg.getText();
        String adressText = adressReg.getText();
        error.setText("");
        boolean pass = true;
        if(loginText.isEmpty() || loginText.length()<4 || loginText.length()>17) {
            error.setText(error.getText() + "Please enter login field correctly (4-16 characters)\n");
            pass = false;
        }
        else {
            for (Employee employee : employees) {
                if (loginText.equals(employee.getLogin())) {
                    pass = false;
                    error.setText(error.getText() + "This login has already registered\n");
                    break;
                }
            }
        }
        if(passText.isEmpty() || passText.length()<3 || passText.length()>17){
            error.setText(error.getText()+"Please enter password field correctly (4-16 characters)\n");
            pass = false;
        }
        passText = coder.md5Custom(passText);
        if(nameText.isEmpty() || nameText.length()<3 || nameText.length()>33){
            error.setText(error.getText()+"Please enter name field correctly (4-32 characters)\n");
            pass = false;
        }
        if(pNumberText.isEmpty() || pNumberText.length()<3 || pNumberText.length()>16){
            error.setText(error.getText()+"Please enter phone number field correctly (max 15 characters)\n");
            pass = false;
        }
        cardNumberText = cardNumberText.replaceAll(" ", "");
        if(cardNumberText.isEmpty() || cardNumberText.length()!=16){
            error.setText(error.getText()+"Please enter card number field correctly (XXXX XXXX XXXX XXXX)\n");
            pass = false;
        }
        if(adressText.isEmpty()){
            error.setText(error.getText()+"Please enter address field correctly (max 30 characters)\n");
            pass = false;
        }
        if(pass){
            addEmployeeSubmit(loginText, passText, nameText, pNumberText, cardNumberText, adressText, isAdminReg.isSelected());
            toAdminMode(actionEvent);
        }
    }

    private synchronized void addEmployeeSubmit(String loginText, String passText, String nameText, String pNumberText, String cardNumberText, String adressText, boolean isAdmin) throws SQLException {
        ldb.addEmployee(nameText, pNumberText, cardNumberText, adressText, passText, loginText, isAdmin);
    }

    public void submitOffered(ActionEvent actionEvent) throws SQLException {
        refreshBD();
        boolean hesCool = false;
        for (Employee employee: (ArrayList<Employee>)find) {
            for (int i = 0; i < jobs.size(); i++) {
                if(jobs.get(i).getEmployeePersonnelNumber()==employee.getPersonnelNumber()) {
                    hesCool = true;
                    break;
                }
            }
            if(!hesCool)
                ldb.deleteEmployee(employee.getPersonnelNumber());
            else
                ldb.editEmployee(employee.getPersonnelNumber(),
                        employee.getName(),employee.getNumber(),
                        employee.getCardNumber(),employee.getAdress(),
                        employee.getPassword(),employee.getLogin(),
                        employee.isAdmin(),
                        Date.valueOf(LocalDate.now()).toString());
        }
    }

    public void toAdminCarEdit(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "adminMode/adminCarEditPage.fxml";
        loading(source, actionEvent);
        refreshBD();
    }

    public void toAdminCarRegister(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "adminMode/adminCarRegisterPage.fxml";
        refreshBD();
        javaFX.registerCarFX(actionEvent, source, textField);
    }

    public void registerCar(ActionEvent actionEvent) throws SQLException {
        refreshBD();
        boolean isCool = true;
        for (int i = 0; i < textField.getText().length(); i++) {
            if(!Character.isDigit(textField.getText().charAt(i)))
            {
                isCool = false;
            }
        }
        if(isCool)
            ldb.addCar(javaFX.getColor(), javaFX.getModel(), Integer.parseInt(textField.getText()));
        else
            System.out.println("Enter digits only");
    }

    public void toCarListPage(ActionEvent actionEvent) throws SQLException, IOException {
        String source = "adminMode/listPage.fxml";
        refreshBD();
        ObservableList<Car> obs = javaFX.javaFXCarList(actionEvent, source, carTable);
        obs.addAll(cars);
    }

    public void toAdminClientEdit(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "adminMode/adminClientEditPage.fxml";
        loading(source, actionEvent);
    }

    public void toClientListPage(ActionEvent actionEvent) throws SQLException, IOException {
        String source = "adminMode/listPage.fxml";
        refreshBD();
        ObservableList<Client> obs = javaFX.javaFXClientList(actionEvent, source, clientTable);
        obs.addAll(clients);
    }

    public void toAdminJobEdit(ActionEvent actionEvent) throws IOException {
        String source = "adminMode/adminJobEditPage.fxml";
        loading(source, actionEvent);
    }

    public void toAdminClientOrderEdit(ActionEvent actionEvent) throws IOException {
        String source = "adminMode/adminClientOrderEditPage.fxml";
        loading(source, actionEvent);
    }

    public void toJobListPage(ActionEvent actionEvent) throws SQLException, IOException {
        refreshBD();
        enterJobsView(actionEvent);
    }

    public void toClientOrdersListPage(ActionEvent actionEvent) throws SQLException, IOException {
        String source = "adminMode/listPage.fxml";
        refreshBD();
        ObservableList<ClientOrder> obs = javaFX.javaFXClientOrderList(actionEvent, source, clientOrdersTable);
        obs.addAll(clientOrders);
    }

    public void enterAddJobView(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "addJobView.fxml";
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        refreshBD();
        jfx.addJobFX(actionEvent, root,cars,jobs,clients,clientOrders,employees);
    }

    public void toOrderComplete(ActionEvent actionEvent) throws IOException {
        String source = "adminMode/completeOrder.fxml";
        loading(source, actionEvent);
    }

    public void completeOrder(ActionEvent actionEvent) throws SQLException, IOException {
        refreshBD();
        String orderId = orderIdT.getText();
        boolean isCool = true;
        for (int i = 0; i < orderId.length(); i++) {
            if(!Character.isDigit(orderId.charAt(i))) {
                isCool = false;
                break;
            }
        }
        if(isCool) {
            int orderIdInt = Integer.parseInt(orderId);
            ClientOrder clientOrderIns = null;
            boolean isFound = false;
            for (ClientOrder clientOrder1 : clientOrders) {
                if (clientOrder1.getId() == orderIdInt) {
                    clientOrderIns = clientOrder1;
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                ldb.editOrder(clientOrderIns.getId(), clientOrderIns.getPrice(), clientOrderIns.getDate(), clientOrderIns.getClientCardNumber(), !clientOrderIns.isExecuted(), clientOrderIns.getJobID());
                for (int i = 0; i < jobs.size(); i++) {
                    if(jobs.get(i).getId() == clientOrderIns.getJobID()){
                        for (int j = 0; j < cars.size(); j++) {
                            if(cars.get(j).getSerialNumber()==jobs.get(i).getCarSerialNumber())
                            {
                                Car car = cars.get(j);
                                System.out.println(car.getSerialNumber());
                                ldb.editCar(car.getSerialNumber(), car.getColor(), car.getModel(), car.getPrice(), !car.isSold());
                            }
                        }

                    }
                }
                toAdminClientOrderEdit(actionEvent);
            }
        }
    }
}