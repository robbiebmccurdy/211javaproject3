import java.io.FileNotFoundException;
import java.io.IOException;

public interface HuffmanGraphicADT {
    public String encode(String filename);
    public void encodeGraphic(String inputFile, String outputFile) throws IOException;
    public String decode(String filename);
    public void decodeGraphic(String inputFile, String outputFile) throws IOException;
}
