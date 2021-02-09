package abiz.ir.crwlr.some;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.RidgeBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.awt.*;
import java.io.*;
import java.util.concurrent.ExecutionException;

/*
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
*/

public class Tst {
    public static final String SRC = "sample.html";
    public static final String DEST = "rrpsum.pdf";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        InputStream in = new BufferedInputStream(new FileInputStream("d:\\input.docx"));
/*
        IConverter converter = LocalConverter.builder()
                .baseFolder(new File("D:\\input"))
                .workerPool(20, 25, 2, TimeUnit.SECONDS)
                .processTimeout(5, TimeUnit.SECONDS)
                .build();

        Future<Boolean> conversion = converter
                .convert(in).as(DocumentType.MS_WORD)
                .to(bo).as(DocumentType.PDF)
                .prioritizeWith(1000) // optional
                .schedule();
        conversion.get();
        try (OutputStream outputStream = new FileOutputStream("D:\\output.pdf")) {
            bo.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        in.close();
        bo.close();
*/
    }

    public void manipulatePdf(String htmlSource, String pdfDest) throws IOException {
        PdfWriter writer = new PdfWriter(pdfDest);
        PdfDocument pdfDocument = new PdfDocument(writer);
        String header = "<br/>pdfHtml Header and footer example using page-events<br/><br/><br/>";
        Header headerHandler = new Header(header);
        Footer footerHandler = new Footer();
        Document document = new Document(pdfDocument);
        document.setMargins(500, 136, 120, 136);
        pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, headerHandler);
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, footerHandler);

        // Base URI is required to resolve the path to source files
        //ConverterProperties converterProperties = new ConverterProperties().setBaseUri(SRC);
        HtmlConverter.convertToDocument(new FileInputStream(SRC),writer);

        // Write the total number of pages to the placeholder
        footerHandler.writeTotal(pdfDocument);
        pdfDocument.close();
    }

    // Header event handler
    protected class Header implements IEventHandler {
        private String header;

        public Header(String header) {
            this.header = header;
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();

            PdfPage page = docEvent.getPage();
            Rectangle pageSize = page.getPageSize();
            Canvas canvas = new Canvas(page, new Rectangle(pageSize.getX(), pageSize.getY()));
            //canvas.setFontSize(18);

            canvas.setBorderTop(new RidgeBorder(DeviceCmyk.CYAN,9500));
            // Write text at position
            canvas.showTextAligned("X",
                    pageSize.getWidth() / 2,
                    pageSize.getTop() - 330, TextAlignment.CENTER);
            canvas.close();
        }
    }

    // Footer event handler
    protected class Footer implements IEventHandler {
        protected PdfFormXObject placeholder;
        protected float side = 20;
        protected float x = 300;
        protected float y = 215;
        protected float space = 4.5f;
        protected float descent = 3;

        public Footer() {
            placeholder = new PdfFormXObject(new Rectangle(0, 0, side, side));
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            int pageNumber = pdf.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();

            // Creates drawing canvas
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Canvas canvas = new Canvas(page, new Rectangle(pageSize.getX(), pageSize.getY()));

            Paragraph p = new Paragraph()
                    .add("Page ")
                    .add(String.valueOf(pageNumber))
                    .add(" of");

            canvas.showTextAligned(p, x, y, TextAlignment.RIGHT);
            canvas.close();

            // Create placeholder object to write number of pages
            pdfCanvas.addXObject(placeholder, x + space, y - descent);
            pdfCanvas.release();
        }

        public void writeTotal(PdfDocument pdf) {
            Canvas canvas = new Canvas(placeholder, pdf);
            canvas.showTextAligned(String.valueOf(pdf.getNumberOfPages()),
                    0, descent, TextAlignment.LEFT);

            canvas.close();
        }
    }

}
