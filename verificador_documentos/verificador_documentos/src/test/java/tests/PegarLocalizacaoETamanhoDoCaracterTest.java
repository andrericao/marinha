package tests;

import net.bytebuddy.asm.MemberSubstitution;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.List;

public class PegarLocalizacaoETamanhoDoCaracterTest extends PDFTextStripper {

    public PegarLocalizacaoETamanhoDoCaracterTest() throws IOException {}

    @Test
    public void dadoUmArquivoPDF_quandoProcessado_EntaoPegarTamanhoELocalizacao() throws IOException{

    PDDocument arquivo = null;
    String caminhoPDF = "/home/mandarinopaulo/Downloads/Extrato-IR-2023.pdf";
    try {
        arquivo = Loader.loadPDF(new RandomAccessReadBufferedFile(caminhoPDF));
        PDFTextStripper stripper = new PegarLocalizacaoETamanhoDoCaracterTest();
        stripper.setSortByPosition(true);
        stripper.setStartPage(0);
        stripper.setEndPage( arquivo.getNumberOfPages());

        Writer dummy = new OutputStreamWriter( new ByteArrayOutputStream());
        stripper.writeText(arquivo, dummy);
    } finally {
        if( arquivo != null){
            arquivo.close();
        }
    }

    }
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        for (TextPosition text : textPositions) {
            System.out.println(text.getUnicode()+ " [(X=" + text.getXDirAdj() + ",Y=" + text.getYDirAdj() + ") height=" + text.getHeightDir() + " width=" + text.getWidthDirAdj() + "]");
        }
    }

}
