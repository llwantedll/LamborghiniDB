package lamborghiniDBMain;

import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lamborghiniDBMain.lDataBase.Car;
import lamborghiniDBMain.lDataBase.Client;
import lamborghiniDBMain.lDataBase.Job;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserModeController extends Controller {

    @FXML TextField phoneNumber;
    @FXML TextField name;
    @FXML TextField cardNumber;
    private static String cardNumberText;
    private static String phoneNumberText;
    private static String nameText;

    public void toUserMode(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "userMode.fxml";
        loading(source, actionEvent);
        refreshBD();
    }

    public void toPersonnelOrder(ActionEvent actionEvent) throws SQLException, IOException {
        String source = "personnelOrder.fxml";
        refreshBD();
        jfx.personOrderFX(actionEvent, source, cars, jobs);
    }

    public void orderCar(ActionEvent actionEvent) throws SQLException, IOException {
        boolean pass = true;
        cardNumberText = cardNumber.getText();
        phoneNumberText = phoneNumber.getText();
        nameText = name.getText();
        if(nameText.isEmpty() || nameText.length()<3 || nameText.length()>33){
            System.out.println("Please enter name field correctly (4-32 characters)");
            pass = false;
        }
        if(phoneNumberText.isEmpty() || phoneNumberText.length()<3 || phoneNumberText.length()>16){
            System.out.println("Please enter phone number field correctly (max 15 characters)");
            pass = false;
        }
        cardNumberText = cardNumberText.replaceAll(" ", "");
        if(cardNumberText.isEmpty() || cardNumberText.length()!=16){
            System.out.println("Please enter card number field correctly (XXXX XXXX XXXX XXXX)");
            pass = false;
        }
        if(pass) {
            refreshBD();
            jfx.submitFX(actionEvent, cars);
        }

    }

    public void toClientInfoAddPage(ActionEvent actionEvent) throws IOException, SQLException {
        String source = "clientInfoPage.fxml";
        loading(source, actionEvent);
        refreshBD();
    }

    public void buyCar(ActionEvent actionEvent) throws SQLException, IOException, DocumentException {
        refreshBD();
        boolean clientDub = true;
        for (Client client : clients) {
            if (client.getCardNumber().equals(cardNumberText))
            {
                clientDub = false;
                break;
            }
        }
        if(clientDub)
            ldb.addClient(nameText, phoneNumberText, cardNumberText);
        int jobID = 0;
        int price = 0;
        Car carExt = null;
        for (Job job : jobs) {
            for (Car car : cars) {
                if (job.getCarSerialNumber() == car.getSerialNumber())
                    if (car.getModel().equals(jfx.getModelCurrent()) && car.getColor().equals(jfx.getColorCurrent())) {
                        {
                            carExt = car;
                            jobID = job.getId();
                            price = car.getPrice();
                        }
                        break;
                    }
            }
        }
        int clientOrderID = clientOrders.size()+1;
        ldb.addClientOrder(jfx.price(jfx.getColorCurrent())+price, LocalDate.now().toString(), cardNumberText, jobID);
        String s = "";
        if(carExt!=null)
            s = "Your order" +
                    "\nOrder ID : "+clientOrderID +
                    "\nCar model - "+carExt.getModel() +
                    "\nCar color - "+carExt.getColor() +
                    "\nPrice - "+(jfx.price(jfx.getColorCurrent())+price) + "$" +
                    "\nClient name - "+nameText +
                    "\nClient card - "+cardNumberText +
                    "\nClient phone number - "+phoneNumberText +
                    "\nDate = "+LocalDate.now().toString() +
                    "\nHas been sent!"+
                    "\n\nThank you for buying our product!";
        Main.getPdfApprover().printFile(s,"Order_" + clientOrderID + "_Check.pdf");
        toUserMode(actionEvent);
    }

}
