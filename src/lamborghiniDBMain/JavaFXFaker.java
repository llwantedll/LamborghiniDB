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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lamborghiniDBMain.lDataBase.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class JavaFXFaker{

    private static String addTypeJob;
    private static int addJobCar;
    private static String date;
    private Label label;
    private static final String path = "src/pics/";
    private ImageView imageView;
    private ObservableList<String> colors;
    private ObservableList<String> models;
    private static String modelCurrent;
    private static String colorCurrent = "yellow";

    JavaFXFaker(){
        addTypeJob = null;
        addJobCar = 0;
        date = null;
    }

    public String getModelCurrent() {
        return modelCurrent;
    }

    public String getColorCurrent() {
        return colorCurrent;
    }

    public String getAddTypeJob() {
        return addTypeJob;
    }

    public int getAddJobOrder() {
        return addJobCar;
    }

    public String getDate() {
        return date;
    }

    void submitFX(ActionEvent actionEvent, ArrayList<Car> cars) throws IOException {
        String source = "purchaseSubmit.fxml";
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        VBox vBox = new VBox();
        int price = 0;
        for (Car car : cars) {
            if (car.getModel().equals(getModelCurrent()) && car.getColor().equals(getColorCurrent())) {
                {
                    price = car.getPrice();
                }
                break;
            }
        }
        Label label = new Label("Are you sure that you want to buy "+
                getColorCurrent()+" Lamgorghini "+getModelCurrent()+
                " for "+(price(getColorCurrent())+price)+"$ ?");
        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setPrefHeight(200);
        vBox.setPrefWidth(600);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<Job> jobViewFX(ActionEvent actionEvent, TableView<Job> jobTable) throws IOException {
        String source = "jobsView.fxml";
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        ObservableList<Job> obs = FXCollections.observableArrayList();
        TableColumn<Job, Integer> idT = new TableColumn<>("ID");
        TableColumn<Job, String> typeT = new TableColumn<>("Type");
        TableColumn<Job, Integer> employeePNT = new TableColumn<>("EmployeePN");
        TableColumn<Job, Integer> carSNT = new TableColumn<>("CarSN");
        TableColumn<Job, String> dateT = new TableColumn<>("Date");
        jobTable.getColumns().addAll(idT, typeT, employeePNT, carSNT, dateT);
        idT.setCellValueFactory(e -> e.getValue().idProperty().asObject());
        typeT.setCellValueFactory(e -> e.getValue().typeProperty());
        employeePNT.setCellValueFactory(e -> e.getValue().employeePersonnelNumberProperty().asObject());
        carSNT.setCellValueFactory(e -> e.getValue().carSerialNumberProperty().asObject());
        dateT.setCellValueFactory(e -> e.getValue().dateProperty());
        idT.setPrefWidth(20);
        jobTable.setItems(obs);
        jobTable.setPrefWidth(600);
        jobTable.setPrefHeight(400);
        Group group = new Group(root, jobTable);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        return obs;
    }

    public void addJobFX(ActionEvent actionEvent, Parent root,
                         ArrayList<Car> cars, ArrayList<Job> jobs,
                         ArrayList<Client> clients, ArrayList<ClientOrder> clientOrders,
                         ArrayList<Employee> employees) {
        VBox vBox = new VBox();
        MenuItem type1 = new MenuItem();
        MenuItem type2 = new MenuItem();
        MenuItem type3 = new MenuItem();
        MenuItem type4 = new MenuItem();
        type1.setText("Press");
        type2.setText("Body");
        type3.setText("Paint");
        type4.setText("Assembly");
        ArrayList<Car> unsoldCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isSold()) {
                unsoldCars.add(car);
            }
        }

        MenuItem[] carsList = new MenuItem[unsoldCars.size()];
        DatePicker datePicker = new DatePicker();
        label = new Label();
        label.setText(addJobCar +" "+addTypeJob+" "+date);
        datePicker.setOnAction(event -> {
            date = datePicker.getValue().toString();
            label.setText(addJobCar +" "+addTypeJob+" "+date);
        });
        for (int i = 0; i < unsoldCars.size(); i++) {
                carsList[i] = new MenuItem(unsoldCars.get(i).getSerialNumber()+"");
        }
        MenuButton tsmb = new SplitMenuButton(type1, type2, type3, type4);
        MenuButton osmb = new SplitMenuButton(carsList);
        tsmb.setPrefWidth(220);
        osmb.setPrefWidth(220);
        tsmb.setText("Job Type");
        osmb.setText("Car ID");
        vBox.getChildren().add(tsmb);
        vBox.getChildren().add(osmb);
        vBox.getChildren().add(datePicker);
        vBox.getChildren().add(label);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(10);
        vBox.setPrefWidth(600);
        vBox.setPrefHeight(400);
        vBox.setAlignment(Pos.CENTER);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        actionType(type1, tsmb, "Press");
        actionType(type2, tsmb, "Body");
        actionType(type3, tsmb, "Paint");
        actionType(type4, tsmb, "Assembly");
        for (int i = 0; i < cars.size(); i++) {
            for (int j = 0; j < carsList.length; j++) {
                if(cars.get(i).getSerialNumber() == Integer.parseInt(carsList[j].getText()))
                    actionCar(carsList[j], osmb, carsList[j].getText(), cars.get(i).getModel());
            }
        }
    }

    private void actionType(MenuItem type1, MenuButton smb, String s) {
        type1.setOnAction(event -> {smb.setText("Job Type = "+s);
        if(s!=null)
        addTypeJob = s;
            label.setText(addJobCar +" "+s+" "+date);});

    }

    private void actionCar(MenuItem type1, MenuButton smb, String s, String f) {
        type1.setOnAction(event -> {smb.setText("Car ID = "+s+" "+f);
            if(s!=null)
                addJobCar = Integer.parseInt(s);
            label.setText(s+" "+addTypeJob+" "+date);
        });
    }

    public void personOrderFX(ActionEvent actionEvent, String source, ArrayList<Car> cars, ArrayList<Job> jobs) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        ChoiceBox<String> modelsBox = new ChoiceBox<>();
        ChoiceBox<String> colorBox = new ChoiceBox<>();
        colors = FXCollections.observableArrayList();
        models = FXCollections.observableArrayList();
        HashSet<String> availableCars = new HashSet<>();
        for (Car car : cars) {
            if(!car.isSold())
                availableCars.add(car.getModel());
        }
        models.addAll(availableCars);
        modelsBox.setItems(models);
        colorBox.setItems(colors);
        modelsBox.setPrefWidth(200);
        colorBox.setPrefWidth(200);
        FileInputStream inputStream = new FileInputStream(path + "logo.jpg");
        Image image = new Image(inputStream);
        imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setPrefWidth(600);
        vBox.setPrefHeight(400);
        vBox.setSpacing(10);
        vBox.getChildren().add(modelsBox);
        vBox.getChildren().add(colorBox);
        vBox.getChildren().add(imageView);
        Group group = new Group(root, vBox);
        Scene scene = new Scene(group);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        modelsBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if(newValue!=null) {
                    InputStream inputStream1 = new FileInputStream(path + colorCurrent + "_" + newValue + ".png");
                    Image image1 = new Image(inputStream1);
                    imageView.setImage(image1);
                    modelCurrent = newValue;
                }
                colorBox.getItems().clear();
                HashSet<String> availableColors = new HashSet<>();
                HashSet<Car> carsHash = new HashSet<>();
                for(Job job : jobs) {
                    for (Car car : cars) {
                        if(car.getSerialNumber() == job.getCarSerialNumber())
                                carsHash.add(car);
                    }
                }
                for (Car car : carsHash) {
                    if(car.getModel().equals(newValue))
                        if(!car.isSold())
                            availableColors.add(car.getColor());
                }
                colors.addAll(availableColors);
                colorBox.setValue(availableColors.iterator().next());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        colorBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if(newValue!=null) {
                    InputStream inputStream12 = new FileInputStream(path+newValue+"_"+modelCurrent+".png");
                    colorCurrent = newValue;
                    Image image12 = new Image(inputStream12);
                    imageView.setImage(image12);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public int price(String color){
        int price = 0;
        switch (color) {
            case "Purple":
                price += 70000;
                break;
            case "Black":
                price += 50000;
                break;
            case "Grey":
                price += 20000;
                break;
            default:
                price += 5000;
                break;
        }
        return price;
    }

}
