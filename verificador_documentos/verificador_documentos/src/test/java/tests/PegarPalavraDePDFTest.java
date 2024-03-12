package tests;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LINK do tutorial
 * https://www.tutorialkart.com/pdfbox/extract-words-from-pdf-document/#gsc.tab=0
 */

public class PegarPalavraDePDFTest extends PDFTextStripper {

    static List<String> palavras = new ArrayList<String>();

    public PegarPalavraDePDFTest() throws IOException{}

    @Test
    public void dadoUmArquivoPDF_quandoProcessado_EntaoPegarPalavraEspecifica() throws IOException{

        PDDocument arquivo = null;
        String caminhoPDF = "/home/mandarinopaulo/Downloads/Extrato-IR-2023.pdf";
        try {
            arquivo = Loader.loadPDF( new RandomAccessReadBufferedFile(caminhoPDF) );
            PDFTextStripper stripper = new PegarPalavraDePDFTest();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 0 );
            stripper.setEndPage( arquivo.getNumberOfPages() );

            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(arquivo, dummy);

            // print words
            for(String palavra:palavras){
                System.out.println(palavra);
            }
        }
        finally {
            if( arquivo != null ) {
                arquivo.close();
            }
        }
    }

    /**
     * Override the default functionality of PDFTextStripper.writeString()
     */
    @Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        String[] wordsInStream = str.split(getWordSeparator());
        if(wordsInStream!=null){
            for(String word :wordsInStream){
                palavras.add(word);
            }
        }
    }
}
