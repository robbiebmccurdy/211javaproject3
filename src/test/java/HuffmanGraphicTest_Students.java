import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanGraphicTest_Students {
    @Test
    public void testPrintCodes1() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        assertEquals("240: 01100000111\n" +
                "241: 01010110110\n" +
                "242: 01110111000\n" +
                "243: 11001101110\n" +
                "244: 11010101110\n" +
                "245: 01111100010\n" +
                "246: 11000001011\n" +
                "247: 11000100110\n" +
                "248: 0001011010\n" +
                "249: 11111101101\n" +
                "250: 0001101111\n" +
                "251: 0100010101\n" +
                "252: 0101001100\n" +
                "253: 0010111000\n" +
                "254: 0100010100\n" +
                "255: 10\n", evaluator.printCodes(240, 255));
    }

    @Test
    public void testEncode1() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        assertEquals("10101010", evaluator.encode("255,255,255,255"));
    }

    @Test
    public void testEncode3() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        assertEquals("0001000", evaluator.encode("9"));
    }

    @Test
    public void testEncode4() throws IOException {
        assertThrows(InvalidHuffmanCodeException.class, () -> {
            HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
            evaluator.encode("");

        });
    }
    @Test
    public void testDecode1() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        assertEquals("255,255,255,255", evaluator.decode("10101010"));
    }

    @Test
    public void testDecode5() throws IOException {
        assertThrows(InvalidHuffmanCodeException.class, () -> {
            HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
            evaluator.decode("012");

        });
    }

    @Test
    public void testEncodeGraphic1() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.encodeGraphic("olemissBaseball_small.jpg", "olemissBaseball_small_Encoded.txt");
        assertEquals( "1011111101111101101111100011100\n" +
                "100110010110001001110011010\n" +
                "10000011000000001110110101\n" +
                "1011010101010001001110000100\n" +
                "10111000001111110111110101101\n" +
                "10110110010101001111101111\n" +
                "10000100011100111111100011\n" +
                "10001100001111111111111111\n", evaluator.printPixels("olemissBaseball_small_Encoded.txt", 62, 69));
    }

    @Test
    public void testEncodeGraphic2() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.encodeGraphic("hottyToddy_small.jpg", "hottyToddy_small_Encoded.txt");
        assertEquals( "100111110000011111000111111100\n" +
                "101111001000010010000111101110\n" +
                "10011111001001001000000010011\n" +
                "10011100110110001000001111101000\n" +
                "1000010111110110111011000001010\n" +
                "100011000010000011100001011\n" +
                "100100010000001011011010\n" +
                "10010010001111100110010110011001\n" +
                "101101101111111001111000101111\n" +
                "101110010110011010001000011\n" +
                "10110000101101010110111110110100\n" +
                "1001001000010000001111000100\n" +
                "1000011000110010101100010001\n", evaluator.printPixels("hottyToddy_small_Encoded.txt", 859, 871));
    }

    @Test
    public void testEncodeGraphic3() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.encodeGraphic("WeirHall_small.jpg", "WeirHall_small_Encoded.txt");
        assertEquals( "101100110001100101011111100\n" +
                "100101000100110000101010111\n" +
                "100101000000111100001111001\n" +
                "100101000100111100011111110\n" +
                "100101000000111100111001010\n" +
                "10010100000111111100001100\n" +
                "100000110000110000111001110\n" +
                "100001110110101011111111110\n", evaluator.printPixels("WeirHall_small_Encoded.txt", 601, 608));
    }

    @Test
    public void testEncodeGraphic4() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.encodeGraphic("WalkOfChampions_small.jpg", "WalkOfChampions_small_Encoded.txt");
        assertEquals( "1000000111001101100111110000\n" +
                "101101101111111100110001101010101\n" +
                "101100110111111000100001100010000\n" +
                "101111011000111100000101100001011\n" +
                "10001011100111010101101110000011\n" +
                "10010100110101010110100110101011\n", evaluator.printPixels("WalkOfChampions_small_Encoded.txt", 1685, 1690));
    }

    @Test
    public void testEncodeGraphic5() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.encodeGraphic("footballStadium_small.jpg", "footballStadium_small_Encoded.txt");
        assertEquals( "10011111000101100000101101010110110\n" +
                "1000010110101111110110111010101110\n" +
                "10110000010111100000101111000001011\n" +
                "10110001001101100010011011000100110\n" +
                "10110011011101101010111011000001011\n" +
                "101100010011000010110100001101111\n" +
                "10011101110001101010111011001101110\n" +
                "10011111000101100010011011010101110\n" +
                "10011101110001101010111001010110110\n", evaluator.printPixels("footballStadium_small_Encoded.txt", 1, 9));
    }

    @Test
    public void testDecodedGraphic1() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.decodeGraphic("olemissBaseball_small_Encoded.txt", "olemissBaseball_small_Decoded.png");
        assertEquals("255,50,103,145\n" +
                "255,42,109,152\n" +
                "255,49,112,155\n" +
                "255,70,109,150\n" +
                "255,61,114,156\n" +
                "255,25,127,175\n" +
                "255,9,135,183\n" +
                "255,13,136,180\n", evaluator.printPixels("oleMissBaseball_small_Decoded.png", 62, 69));
    }

    @Test
    public void testDecodedGraphic2() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.decodeGraphic("hottyToddy_small_Encoded.txt", "hottyToddy_small_Decoded.png");
        assertEquals("255,79,95,120\n255,63,84,111\n255,60,85,115\n255,41,67,100\n255,34,58,96\n"+
                "255,13,32,65\n255,219,231,255\n255,82,98,123\n255,103,132,166\n255,133,167,205\n"+
                "255,65,94,134\n255,84,110,147\n255,17,40,72\n", evaluator.printPixels("hottyToddy_small_Decoded.png", 859, 871));
    }

    @Test
    public void testDecodedGraphic3() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.decodeGraphic("WeirHall_small_Encoded.txt", "WeirHall_small_Decoded.png");
        assertEquals("255,37,21,19\n" +
                "255,45,30,27\n" +
                "255,43,28,26\n" +
                "255,45,28,24\n" +
                "255,43,26,21\n" +
                "255,43,24,17\n" +
                "255,49,30,22\n" +
                "255,48,27,24\n", evaluator.printPixels("WeirHall_small_Decoded.png", 601, 608));
    }

    @Test
    public void testDecodedGraphic4() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.decodeGraphic("WalkOfChampions_small_Encoded.txt", "WalkOfChampions_small_Decoded.png");
        assertEquals("255,112,78,79\n" +
                "255,103,69,70\n" +
                "255,97,67,67\n" +
                "255,92,66,65\n" +
                "255,88,62,61\n" +
                "255,107,83,81\n", evaluator.printPixels("WalkOfChampions_small_Decoded.png", 1685, 1690));
    }

    @Test
    public void testDecodedGraphic5() throws IOException {
        HuffmanGraphic evaluator = new HuffmanGraphic("olemissBaseball.jpg");
        evaluator.decodeGraphic("footballStadium_small_Encoded.txt", "footballStadium_small_Decoded.png");
        assertEquals("255,245,246,241\n" +
                "255,248,249,244\n" +
                "255,246,246,246\n" +
                "255,247,247,247\n" +
                "255,243,244,246\n" +
                "255,247,248,250\n" +
                "255,242,244,243\n" +
                "255,245,247,244\n" +
                "255,242,244,241\n", evaluator.printPixels("footballStadium_small_Decoded.png", 1, 9));
    }
}