package tests;



import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class PDFReaderTest {

    @Test
    public void dadoUmaAmostradePDF_quandoUsandoPDFBox_EntaoCompareOutput() throws IOException{

        File arquivo = new File("/home/mandarinopaulo/Downloads/Extrato-IR-2023.pdf");
        PDDocument documento = Loader.loadPDF(new RandomAccessReadBufferedFile(arquivo));
        int paginas =  documento.getNumberOfPages();

        documento.close();

        Assertions.assertEquals(paginas, 1);
    }


    @Test
    public void readPDFFile() throws Exception{

        File file = new File("/home/mandarinopaulo/Downloads/Relatorio-Especial-IPO-Carrefour.pdf");
        PDDocument pdfDocument = Loader.loadPDF(new RandomAccessReadBufferedFile (file));

        int pagesCount = pdfDocument.getNumberOfPages();

        System.out.printf("O documento tem %d páginas!", pagesCount);

        pdfDocument.close();

        Assertions.assertEquals(pagesCount, 27);
    }
/*
    WebDriver driver;
    String url = "/home/mandarinopaulo/Downloads/Relatorio-Especial-IPO-Carrefour.pdf";


    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get(url);
    }
    */

    @Test
    public void pdfReaderTest() throws IOException {
        File pdfUrl = new File("/home/mandarinopaulo/Downloads/Relatorio-Especial-IPO-Carrefour.pdf");

        PDDocument pdDocument = Loader.loadPDF(new RandomAccessReadBufferedFile(pdfUrl));
        int pageCount = pdDocument.getNumberOfPages();

        PDFTextStripper textoPDF = new PDFTextStripper();
        String texto = textoPDF.getText(pdDocument);

        System.out.printf("%s", texto);

        Assertions.assertEquals(pageCount, 27);
    }

    /*
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
    */
}
