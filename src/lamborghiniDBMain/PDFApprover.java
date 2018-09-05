package lamborghiniDBMain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class PDFApprover {

    public void printFile(String text, String filename) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        Paragraph paragraph = new Paragraph(text);
        document.open();
        Image image = Image.getInstance("src/pics/Check.png");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        image.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(image);
        document.close();
    }
}
