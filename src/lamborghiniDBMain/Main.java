package lamborghiniDBMain;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lamborghiniDBMain.lDataBase.LamborghiniDataBase;

import java.io.IOException;

public class Main extends Application {

    private static final LamborghiniDataBase ldb = new LamborghiniDataBase();
    private static final Coder coder = new Coder();
    private static TextField deleteField = new TextField();
    private static String enteredEmployee;
    private static final PDFApprover pdfApprover = new PDFApprover();

    @Override
    public void start(Stage primaryStage) throws IOException {
        joinDriver();
        ldb.setUser("admin");
        ldb.setPassword("123lol");
        ldb.database();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/lamborghiniApp.fxml"));
        primaryStage.setTitle("LamborghiniAPP");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    private void joinDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    public static String getEnteredEmployee() {
        return enteredEmployee;
    }

    public static void setEnteredEmployee(String enteredEmployee) {
        Main.enteredEmployee = enteredEmployee;
    }

    static LamborghiniDataBase getLdb() {
        return ldb;
    }

    static Coder getCoder() {
        return coder;
    }

    static TextField getDeleteField() {
        return deleteField;
    }

    static PDFApprover getPdfApprover() {
        return pdfApprover;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
