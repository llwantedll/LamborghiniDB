package lamborghiniDBMain;

import com.itextpdf.text.DocumentException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import lamborghiniDBMain.lDataBase.Car;
import lamborghiniDBMain.lDataBase.Employee;
import lamborghiniDBMain.lDataBase.Job;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeModeController extends Controller {



    public void toEmployeeMode(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "employeeMode.fxml";
        loading(source, actionEvent);
        refreshBD();
    }

    public void enterAddJobView(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "addJobView.fxml";
        Parent root = FXMLLoader.load(getClass().getResource("fxml/"+source));
        refreshBD();
        jfx.addJobFX(actionEvent, root,cars,jobs,clients,clientOrders,employees);
    }


    public void confirmJob(ActionEvent actionEvent) throws IOException, SQLException, DocumentException {
        if(Main.getEnteredEmployee()==null || jfx.getAddJobOrder()==0 ||
                jfx.getAddTypeJob()==null || jfx.getDate()==null)
        {
            System.out.println("Error");
        }
        else {
            addData(actionEvent);
        }
    }

    private synchronized void addData(ActionEvent actionEvent) throws SQLException, IOException, DocumentException {
        refreshBD();
        Employee employeeFind = null;
        for (Employee employee : employees) {
            if (employee.getLogin().equals(Main.getEnteredEmployee()))
                employeeFind = employee;
        }
        Car carFind = null;
        for(Car car : cars){
            if(car.getSerialNumber() == jfx.getAddJobOrder()){
                carFind = car;
            }
        }
        int jobID = jobs.size()+1;
        if(employeeFind!=null && carFind!=null) {
            ldb.addJob(jfx.getAddTypeJob(), employeeFind.getPersonnelNumber(), jfx.getAddJobOrder(), jfx.getDate());
            String s = "Your job" +
                    "\nJob ID : " + jobID +
                    "\nEmployee ID - " + employeeFind.getPersonnelNumber()+
                    "\nEmployee Name - " + employeeFind.getName()+
                    "\nEmployee Login - " + employeeFind.getLogin() +
                    "\nJob Type - " + jfx.getAddTypeJob() +
                    "\nCar Serial Number - " + carFind.getSerialNumber() +
                    "\nCar model - " + carFind.getModel() +
                    "\nCar color - " + carFind.getColor() +
                    "\nDate = " + jfx.getDate() +
                    "\nHas been reported!" +
                    "\n\nThank you for report!";
            Main.getPdfApprover().printFile(s, "Job_" + jobID + "_Check.pdf");
            back(actionEvent);
        }
    }


}
