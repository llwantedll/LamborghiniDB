package lamborghiniDBMain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lamborghiniDBMain.lDataBase.Car;
import lamborghiniDBMain.lDataBase.Client;
import lamborghiniDBMain.lDataBase.ClientOrder;
import lamborghiniDBMain.lDataBase.Employee;

import java.io.IOException;
import java.util.ArrayList;

public class JavaFXFakerAdmin {
    private int chosenCriterion;
    private static int chosenSign;
    private static String model;
    private static String color;

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public ObservableList<Employee> javaFXEmployeeList(ActionEvent actionEvent, String source, TableView<Employee> employeeTable) throws IOException {
        ObservableList<Employee> obs = FXCollections.observableArrayList();
        TableColumn<Employee, Integer> idT = new TableColumn<>("ID");
        TableColumn<Employee, String> nameT = new TableColumn<>("Name");
        TableColumn<Employee, String> tNumberT = new TableColumn<>("Phone Number");
        TableColumn<Employee, String> cardNumberT = new TableColumn<>("CardN");
        TableColumn<Employee, String> addressT = new TableColumn<>("Address");
        TableColumn<Employee, String> passwordT = new TableColumn<>("Pass");
        TableColumn<Employee, String> loginT = new TableColumn<>("Login");
        TableColumn<Employee, Boolean> isAdminT = new TableColumn<>("Adm");
        TableColumn<Employee, String> firedT = new TableColumn<>("Fired");
        employeeTable.getColumns().addAll(idT, nameT, tNumberT, cardNumberT, addressT, passwordT, loginT, isAdminT, firedT);
        idT.setCellValueFactory(e -> e.getValue().personnelNumberProperty().asObject());
        nameT.setCellValueFactory(e -> e.getValue().nameProperty());
        tNumberT.setCellValueFactory(e -> e.getValue().tNumberProperty());
        cardNumberT.setCellValueFactory(e -> e.getValue().cardNumberProperty());
        addressT.setCellValueFactory(e -> e.getValue().adressProperty());
        passwordT.setCellValueFactory(e -> e.getValue().passwordProperty());
        loginT.setCellValueFactory(e -> e.getValue().loginProperty());
        isAdminT.setCellValueFactory(e -> e.getValue().isAdminProperty());
        firedT.setCellValueFactory(e -> e.getValue().isFiredProperty());
        employeeTable.setRowFactory((TableView<Employee> paramP) -> new TableRow<Employee>() {
            @Override
            protected void updateItem(Employee employee, boolean paramBoolean) {
                if (employee != null) {
                    if (!employee.isIsFired().equals("0")) {
                        setStyle("-fx-background-color:#FF99AA; -fx-text-background-color: black;");
                    }
                    else
                        setStyle("-fx-background-color:#FFFFAA; -fx-text-background-color: black;");
                    super.updateItem(employee, paramBoolean);
                }
            }});
        idT.setPrefWidth(20);
        isAdminT.setPrefWidth(40);
        employeeTable.setItems(obs);
        employeeTable.setPrefWidth(600);
        employeeTable.setPrefHeight(400);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(employeeTable);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        return obs;
    }

    public ObservableList<Car> javaFXCarList(ActionEvent actionEvent, String source, TableView<Car> carTable) throws IOException {
        ObservableList<Car> obs = FXCollections.observableArrayList();
        TableColumn<Car, Integer> serialT = new TableColumn<>("Serial");
        TableColumn<Car, String> colorT = new TableColumn<>("Color");
        TableColumn<Car, String> modelT = new TableColumn<>("Model");
        TableColumn<Car, Integer> priceT = new TableColumn<>("Price");
        TableColumn<Car, Boolean> soldT = new TableColumn<>("isSold");
        carTable.getColumns().addAll(serialT, colorT, modelT, priceT, soldT);
        serialT.setCellValueFactory(e -> e.getValue().serialNumberProperty().asObject());
        colorT.setCellValueFactory(e -> e.getValue().colorProperty());
        modelT.setCellValueFactory(e -> e.getValue().modelProperty());
        priceT.setCellValueFactory(e -> e.getValue().priceProperty().asObject());
        soldT.setCellValueFactory(e -> e.getValue().soldProperty());
        carTable.setRowFactory((TableView<Car> paramP) -> new TableRow<Car>() {
            @Override
            protected void updateItem(Car car, boolean paramBoolean) {
                if (car != null) {
                    if (car.isSold()) {
                        setStyle("-fx-background-color:#FF99AA; -fx-text-background-color: black;");
                    }
                    else
                        setStyle("-fx-background-color:#FFFFAA; -fx-text-background-color: black;");
                super.updateItem(car, paramBoolean);
            }
        }});
        carTable.setItems(obs);
        carTable.setPrefWidth(600);
        carTable.setPrefHeight(400);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(carTable);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        return obs;
    }

    public ObservableList<Client> javaFXClientList(ActionEvent actionEvent, String source, TableView<Client> clientTable) throws IOException {
        ObservableList<Client> obs = FXCollections.observableArrayList();
        TableColumn<Client, String> cardNumberT = new TableColumn<>("Card Number");
        TableColumn<Client, String> nameT = new TableColumn<>("Name");
        TableColumn<Client, String> tNumberT = new TableColumn<>("Phone");
        clientTable.getColumns().addAll(cardNumberT, nameT, tNumberT);
        cardNumberT.setCellValueFactory(e -> e.getValue().cardNumberProperty());
        nameT.setCellValueFactory(e -> e.getValue().nameProperty());
        tNumberT.setCellValueFactory(e -> e.getValue().tNumberProperty());
        cardNumberT.setPrefWidth(150);
        nameT.setPrefWidth(150);
        clientTable.setItems(obs);
        clientTable.setPrefWidth(600);
        clientTable.setPrefHeight(400);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(clientTable);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        return obs;
    }

    public ObservableList<ClientOrder> javaFXClientOrderList(ActionEvent actionEvent, String source, TableView<ClientOrder> clientOrderTable) throws IOException {
        ObservableList<ClientOrder> obs = FXCollections.observableArrayList();
        TableColumn<ClientOrder, Integer> idT = new TableColumn<>("ID");
        TableColumn<ClientOrder, Integer> priceT = new TableColumn<>("Price");
        TableColumn<ClientOrder, String> dataT = new TableColumn<>("Data");
        TableColumn<ClientOrder, String> ccnT = new TableColumn<>("ClientCN");
        TableColumn<ClientOrder, Boolean> executedT = new TableColumn<>("Executed");
        TableColumn<ClientOrder, Integer> jobIdT = new TableColumn<>("JobID");
        clientOrderTable.getColumns().addAll(idT, priceT, dataT, ccnT, executedT, jobIdT);
        idT.setCellValueFactory(e -> e.getValue().idProperty().asObject());
        priceT.setCellValueFactory(e -> e.getValue().priceProperty().asObject());
        dataT.setCellValueFactory(e -> e.getValue().dateProperty());
        ccnT.setCellValueFactory(e -> e.getValue().clientCardNumberProperty());
        executedT.setCellValueFactory(e -> e.getValue().executedProperty());
        jobIdT.setCellValueFactory(e -> e.getValue().jobIDProperty().asObject());
        clientOrderTable.setRowFactory((TableView<ClientOrder> paramP) -> new TableRow<ClientOrder>() {
            @Override
            protected void updateItem(ClientOrder clientOrder, boolean paramBoolean) {
                if (clientOrder != null) {
                    if (clientOrder.isExecuted()) {
                        setStyle("-fx-background-color:#FF99AA; -fx-text-background-color: black;");
                    }
                    else
                        setStyle("-fx-background-color:#FFFFAA; -fx-text-background-color: black;");
                    super.updateItem(clientOrder, paramBoolean);
                }
            }});
        idT.setPrefWidth(25);
        clientOrderTable.setItems(obs);
        clientOrderTable.setPrefWidth(600);
        clientOrderTable.setPrefHeight(400);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(clientOrderTable);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        return obs;
    }

    public void createDeletePage(ActionEvent actionEvent, String source, TextField deleteField, int mode) throws IOException {
        chosenCriterion = 0;
        chosenSign = 0;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        VBox vBox = new VBox();
        MenuButton signMenu = new SplitMenuButton();
        MenuButton tsmb = new SplitMenuButton();
        ArrayList<MenuItem> criterions = new ArrayList<>();
        if(mode==1)
         criterions = menuDeleteEmployees();
        tsmb.getItems().addAll(criterions);
        ArrayList<MenuItem> signs = new ArrayList<>();
        signs.add(new MenuItem("1. x=find"));
        signs.add(new MenuItem("2. x!=find"));
        signs.add(new MenuItem("3. x<find"));
        signs.add(new MenuItem("4. x>find"));
        signs.add(new MenuItem("5. x<=find"));
        signs.add(new MenuItem("6. x>=find"));
        signMenu.getItems().addAll(signs);
        signMenu.setText("Select sign");
        tsmb.setText("Select delete");
        tsmb.setPrefWidth(200);
        actionType(tsmb, signMenu);
        actionSign(signMenu);
        signMenu.setVisible(false);
        deleteField.setPrefWidth(200);
        deleteField.setMinWidth(200);
        deleteField.setMaxWidth(200);
        Label text1 = new Label("Enter value of chosen criterion");
        vBox.getChildren().add(tsmb);
        vBox.getChildren().add(text1);
        vBox.getChildren().add(deleteField);
        vBox.getChildren().add(signMenu);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(600);
        vBox.setPrefHeight(400);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private ArrayList<MenuItem> menuDeleteEmployees(){
        ArrayList<MenuItem> criterions = new ArrayList<>();
        criterions.add(new MenuItem("1.ID"));
        criterions.add(new MenuItem("2.Name"));
        criterions.add(new MenuItem("3.Phone Number"));
        criterions.add(new MenuItem("4.Card Number"));
        criterions.add(new MenuItem("5.Login"));
        return criterions;
    }

    private void actionType(MenuButton smb, MenuButton signMenu) {
        MenuItem [] menuItems = new MenuItem[smb.getItems().size()];
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i] = smb.getItems().get(i);
        }
        for (MenuItem menuItem : smb.getItems()) {
            menuItem.setOnAction(event -> {
                smb.setText(menuItem.getText());
                if(menuItem.getText().equals(menuItems[0].getText()))
                    signMenu.setVisible(true);
                else
                    signMenu.setVisible(false);
                chosenCriterion = Integer.parseInt(menuItem.getText().charAt(0)+"");
                System.out.println(chosenCriterion);
            });
        }
    }

    private void actionSign(MenuButton smb) {
        for (MenuItem menuItem : smb.getItems()) {
            menuItem.setOnAction(event -> {
                smb.setText(menuItem.getText());
                chosenSign = Integer.parseInt(menuItem.getText().charAt(0)+"");
                System.out.println(chosenSign);
            });
        }
    }

    ArrayList createSubmitPage(ActionEvent actionEvent, String source, String entered, ArrayList<Employee> employees) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        VBox vBox = new VBox();
        ArrayList<Employee> find = employeesFind(entered, employees);
        String text = "Do you really want to delete records about these employees?";
        for (Employee aFind : find) {
            text = text.concat("\n" + aFind.toString());
        }
        vBox.setPrefWidth(600);
        vBox.setPrefHeight(400);
        vBox.setAlignment(Pos.CENTER);
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        return find;
    }

    private ArrayList<Employee> employeesFind(String entered, ArrayList<Employee> employees) {
        boolean pass;
        ArrayList<Employee> find = new ArrayList<>();
        if(chosenCriterion == 0){
            pass = isPass(entered);
            if(pass)
                for (Employee employee : employees) {
                    System.out.println(employee.getPersonnelNumber()+" "+Integer.parseInt(entered)+" "+chosenSign);
                    if (signOf(employee.getPersonnelNumber(),Integer.parseInt(entered))) {
                        find.add(employee);
                    }
                }
        }
        else if(chosenCriterion == 1){
            for (Employee employee : employees) {
                if (employee.getName().equals(entered)) {
                    find.add(employee);
                }
            }
        }
        else if(chosenCriterion == 2){
            for (Employee employee : employees) {
                if (employee.getNumber().equals(entered)) {
                    find.add(employee);
                }
            }
        }
        else if(chosenCriterion == 3){
            for (Employee employee : employees) {
                if (employee.getCardNumber().equals(entered)) {
                    find.add(employee);
                }
            }
    }
        for (Employee employee : employees) {
            if (employee.getLogin().equals(entered)) {
                find.add(employee);
            }
        }
        return find;
    }

    private boolean isPass(String entered) {
        boolean pass = true;
        for (int i = 0; i < entered.length(); i++) {
            if(!Character.isDigit(entered.charAt(i))) {
                pass = false;
                break;
            }
        }
        return pass;
    }

    private boolean signOf(int a, int b){
        if(chosenSign==2)
            return a!=b;
        else if(chosenSign==3)
            return a<b;
        else if(chosenSign==4)
            return a>b;
        else if(chosenSign==5)
            return a<=b;
        else if(chosenSign==6)
            return a>=b;
        else
            return a==b;
    }

    public void registerCarFX(ActionEvent actionEvent, String source, TextField price) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        MenuItem [] colorItems= {
                new MenuItem("Yellow"),
                new MenuItem("Orange"),
                new MenuItem("Green"),
                new MenuItem("Purple"),
                new MenuItem("Grey"),
                new MenuItem("Black")};
        MenuItem [] modelItems= {
                new MenuItem("Reventon"),
                new MenuItem("Diablo"),
                new MenuItem("Gallardo"),
                new MenuItem("Huraccan"),
                new MenuItem("Aventador"),
                new MenuItem("Countach"),
                new MenuItem("Murcielago")};
        MenuButton modelMenu = new SplitMenuButton(modelItems);
        modelMenu.setText("Pick model");
        MenuButton colorMenu = new SplitMenuButton(colorItems);
        colorMenu.setText("Pick color");
        VBox vBox = new VBox();
        modelMenu.setPrefWidth(220);
        colorMenu.setPrefWidth(220);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(10);
        vBox.setPrefWidth(600);
        vBox.setPrefHeight(400);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.getChildren().add(new Label("price"));
        hBox.getChildren().add(price);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(modelMenu);
        vBox.getChildren().add(colorMenu);
        vBox.getChildren().add(hBox);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        for (MenuItem colorItem : colorItems) {
            colorItem.setOnAction(event -> {
                colorMenu.setText("Color = " + colorItem.getText());
                color = colorItem.getText();
            });
        }
        for (MenuItem modelItem : modelItems) {
            modelItem.setOnAction(event -> {
                modelMenu.setText("Model = " + modelItem.getText());
                model = modelItem.getText();
            });
        }
    }



}
