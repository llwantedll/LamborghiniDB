package lamborghiniDBMain;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lamborghiniDBMain.lDataBase.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

 JavaFXFaker jfx = new JavaFXFaker();
 LamborghiniDataBase ldb = Main.getLdb();
 ArrayList<Job> jobs;
 ArrayList<Car> cars;
 ArrayList<Employee> employees;
 ArrayList<Client> clients;
 ArrayList<ClientOrder> clientOrders;
 private static String prevPage;

  public void setPrevPage(String prevPage){
    Controller.prevPage = prevPage;
  }

  void loading(String source, ActionEvent actionEvent) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
   Scene scene = new Scene(root);
   Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
   stage.setScene(scene);
   stage.show();
   prevPage = source;
  }

  public void toMain(ActionEvent actionEvent) throws IOException {
   String source = "LamborghiniApp.fxml";
   loading(source, actionEvent);
  }

  void enterLoginMenu(ActionEvent actionEvent) throws IOException{
   String source = "login.fxml";
   loading(source, actionEvent);
  }

 public void enterRegisterMenu(ActionEvent actionEvent) throws IOException{
  String source = "adminMode/adminEmployeeRegisterPage.fxml";
  loading(source, actionEvent);
 }

  void refreshBD() throws SQLException {
  clientOrders = ldb.getClientOrders();
  jobs = ldb.getJobs();
  cars = ldb.getCars();
  clients = ldb.getClients();
  employees = ldb.getEmployees();
 }

 public void enterJobsView(ActionEvent actionEvent) throws IOException, SQLException {
  refreshBD();
  TableView<Job> jobTable = new TableView<>();
  ObservableList<Job> obs = jfx.jobViewFX(actionEvent, jobTable);
  obs.addAll(jobs);
 }

 public void back(ActionEvent actionEvent) throws IOException {
      loading(prevPage, actionEvent);
 }

}
