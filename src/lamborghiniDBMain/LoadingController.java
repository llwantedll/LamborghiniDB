package lamborghiniDBMain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lamborghiniDBMain.lDataBase.Employee;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class LoadingController extends Controller{

    @FXML TextField login;
    @FXML PasswordField password;
    private Coder coder = Main.getCoder();
    private static int mode;

    public void passwordInit(ActionEvent actionEvent) throws IOException, SQLException, InterruptedException {
        refreshBD();
        tryToLogin(actionEvent);
    }

    private void tryToLogin(ActionEvent actionEvent) throws IOException, SQLException, InterruptedException {
        Employee current = null;
        int pass = 0;
        String loginText = login.getText();
        String passwordText = coder.md5Custom(password.getText());
        for (Employee employee : employees) {
            if (employee.getLogin().equals(loginText)) {
                if (passwordText.equals(employee.getPassword())) {
                    current = employee;
                    if(employee.isIsFired().equals("0"))
                        pass=1;
                    else
                        System.out.println("You have been fired :*((((");
                    break;
                } else {
                    System.out.println("Wrong Password");
                    sleep(1000);
                }
            }
        }

        if(pass == 1){
            sleep(200);
            if(getMode() == 1) {
                EmployeeModeController emc = new EmployeeModeController();
                emc.toEmployeeMode(actionEvent);
                Main.setEnteredEmployee(loginText);
            }
            else if(getMode() == 3)
                if(current.isAdmin()){
                    AdminModeController amc = new AdminModeController();
                    amc.toAdminMode(actionEvent);
                    Main.setEnteredEmployee(loginText);
                    System.out.println("Welcome");
                }
                else {
                    System.out.println("This user is not admin");
                }
        }
    }

    public void employeeEnter(ActionEvent actionEvent) throws IOException {
        mode = 1;
        enterLoginMenu(actionEvent);
    }

    public void adminEnter(ActionEvent actionEvent) throws IOException {
        mode = 3;
        enterLoginMenu(actionEvent);
    }

    public void userEnter(ActionEvent actionEvent) throws IOException, SQLException {
        ldb.setUser("JustUser");
        ldb.setPassword("123");
        ldb.database();
        new UserModeController().toUserMode(actionEvent);
    }


    private static int getMode(){
        return mode;
    }


    public void exitProgram(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
