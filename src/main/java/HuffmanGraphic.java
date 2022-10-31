/*
    CSCI 211
    Robert McCurdy
    Student ID: 010715877
    Program 3

    In keeping with the UM Honor Code, I have neither given
    nor received assistance from anyone other than the
    instructor.
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanGraphic {
    protected BinaryTreeNode<ColorCode> root;
    protected int modCount;
    private ArrayList<ColorCode> colors;
    private String filename;

    public HuffmanGraphic(String filename) throws IOException {
        this.filename = filename;
        this.root = null;
        this.colors = new ArrayList<>();

        //256 possible colors for each channel
        for(int i = 0; i <=255; i++)
            colors.add(new ColorCode(i));

        processGraphic();
        build();
        getCodes();
    }

    /**
     * method: processGraphic
     * Complete method to increment corresponding the colors ArrayList index for the alpha value, red value, green value
     * and blue value.
     * Example: Say the ARGB value is 255, 132, 65, 297. Then, colors ArrayList index is incremented by one, along
     * with indexes 132, 65 and 297.
     * @throws IOException
     */
    private void processGraphic() throws IOException {
        int pixel=0, alpha=0, red=0, green=0, blue=0;
        int aFreq = 0, rFreq = 0, gFreq = 0, bFreq = 0;

        //Get height and width of image
        BufferedImage image = ImageIO.read(new File(this.filename));
        int height = image.getHeight();
        int width = image.getWidth();

        //Step through each pixel, parsing the A, R, G and B values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = image.getRGB(x, y);
                alpha = (pixel >> 24) & 0xff;
                red = (pixel >> 16) & 0xff;
                green = (pixel >> 8) & 0xff;
                blue = (pixel) & 0xff;

                // TODO: increment corresponding index number for A, R, G and B in colors ArrayList
                //      Example: Say the ARGB value is 255, 132, 65, 297. Then, colors ArrayList index is incremented by one, along
                //      with indexes 132, 65 and 297.
                //******************************************************
                colors.get(alpha).setFrequency(aFreq++);
                colors.get(red).setFrequency(rFreq++);
                colors.get(green).setFrequency(gFreq++);
                colors.get(blue).setFrequency(bFreq++);

                //******************************************************
            }
        }
    }
    /**
     * method: build
     * Constructs Huffman Tree
     */
    private void build() {
        // Make leaf nodes in the tree/forest for each color value
        ArrayList<BinaryTreeNode<ColorCode>> nodeList = new ArrayList<BinaryTreeNode<ColorCode>>();
        BinaryTreeNode<ColorCode> temp = null;
        for (int i=0; i<colors.size(); i++) {
            temp = new BinaryTreeNode<ColorCode>(new ColorCode(colors.get(i).getColorValue(),
                    colors.get(i).getFrequency()));
            nodeList.add(temp);
        }
        /* Use standard idea of Huffman encoding to build tree from leaf nodes.
         * Repeatedly, find the two subtrees with minimum weight and join them.
         * Internal nodes don't use the "colorValue" field, so just make them a constant
         * (-1).  The frequency used for the internal node is the sum of the frequency
         * of the two children.  Stop when one node left in forest--it is a tree!
         */
        BinaryTreeNode<ColorCode> node1, node2;
        while (nodeList.size() > 1) {
            node1 = getMin(nodeList);
            node2 = getMin(nodeList);

            ColorCode internalElement = new ColorCode(-1, node1.getElement().getFrequency() +
                    node2.getElement().getFrequency());
            BinaryTreeNode<ColorCode> internal = new
                    BinaryTreeNode<ColorCode>(internalElement);
            internal.setLeft(node1);
            internal.setRight(node2);
            nodeList.add(internal);
        }

        // The one remaining node is the root
        root = nodeList.get(0);
    }

    /**
     * method: getMin returns the color code with the smallest frequency
     *
     * @param nodes
     * @return
     */
    private BinaryTreeNode<ColorCode> getMin(ArrayList<BinaryTreeNode<ColorCode>> nodes) {
        int min=0;   // index of min
        // Find the node in the forest with the least frequency
        for (int i=1; i<nodes.size(); i++) {
            if (nodes.get(i).getElement().getFrequency() <
                    nodes.get(min).getElement().getFrequency()) {
                min = i;
            }
        }
        // Save, remove, then return the smallest node
        BinaryTreeNode<ColorCode> smallest = nodes.get(min);
        nodes.remove(min);
        return smallest;
    }

    /**
     * method: getRootElement
     * Returns the root's element
     *
     * @return
     * @throws EmptyCollectionException
     */
    public ColorCode getRootElement() throws EmptyCollectionException {
        if (root == null)
            throw new EmptyCollectionException("Huffman Tree");
        else return root.getElement();
    }

    /**
     * method: getRootNode
     * Returns the root's reference
     *
     * @return
     * @throws EmptyCollectionException
     */
    protected BinaryTreeNode<ColorCode> getRootNode() throws EmptyCollectionException {
        if (root == null)
            throw new EmptyCollectionException("Huffman Tree");
        else return root;
    }

    /**
     * method: getLeft
     * Returns "this" node's left child
     *
     * @return
     */
    public BinaryTreeNode<ColorCode> getLeft() {
        return root.left;
    }

    /**
     * method: getRight
     * Returns "this" node's right child
     *
     * @return
     */
    public BinaryTreeNode<ColorCode> getRight()  {
        return root.right;
    }

    /**
     * method: isEmpty
     * Returns true if the tree is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return (root == null);
    }
    /**
     * method: getCodes
     * This method returns an ArrayList of the codes in the Huffman Tree.
     * The Compress class has a name and its corresponding encoding.  In the
     * tree, right edges are designated as / and left edges as *.
     *
     * DO NOT CHANGE THIS METHOD, but you need to write the traverse method.
     *
     * @return
     */
    public void getCodes() {
        ArrayList<ColorCode> huffman = new ArrayList<>();
        if (root == null) throw new InvalidHTMLException();
        traverse(root.left, "0");
        traverse( root.right, "1");
    }

    /**
     * method: traverse
     * Recursive method to traverse the Huffman tree.
     * For each leaf node, add a Code record to the ArrayList.
     */
    private void traverse(BinaryTreeNode<ColorCode> node, String prefix) {
        // TODO:  Fill in this method
        // If the node is internal, recursively call traverse, adding 0 to the prefix when going left
        // and 1 to the prefix when going right

        // If the node is a leaf, you have your color value so set the color code (instance variable in ColorCode class) to the prefix
        // Be sure to reset the prefix!
        //******************************************************

        if(root == null || node == null){
            throw new InvalidHuffmanCodeException();
        }

        //System.out.println(node.getElement().getColorValue()); // color value is -1

        if (node.getElement().getColorValue() != -1) {
            node.getElement().setCode(prefix);
            prefix = "";
        } else {
            if(node.getLeft() != null && node.getRight() != null){
                traverse(node.getLeft(), "0");
                traverse(node.getRight(), "1");
            }

            if(node.getLeft() != null && node.getRight() == null){
                traverse(node.getLeft(), "0");
            }

            if(node.getLeft() == null && node.getRight() != null){
                traverse(node.getRight(), "1");
            }
        }



        System.out.println("Node Element: " + node.getElement().getCode());



        //******************************************************
    }

    /**
     * method: encode
     * The encode method should use the traverse method above to produce a look up
     * table.  Then, step through the parameter string and simply "look up" the code
     * for each color, appending the code to the end of the encoded string.  A
     * sequential search through the ArrayList for each color is fine since
     * there are only 255 color values in the list.
     *
     * @return
     */
    public String encode(String str) throws InvalidHuffmanCodeException {
        // TODO: fill in this method
        // The String to encode is comma-delimited
        // Step through the colors ArrayList, when you find the color value that matches the color to encode,
        // append the color's code to the colorEncode variable

        //Throw an Exception for an empty str or if the code is not found in colors ArrayList.
        //************************************



        //************************************
        return null;   // DELETE once encode is implemented
    }
    /**
     * method: encodeGraphic
     * The encodeGraphic method accepts and input file to encode (must be .jpg) as well as an output file (must be .txt)
     * Step through each pixel in the input file graphic.
     * Encode it (call encode method)- See processGraphic method for reading the pixel values and parsing the A, R, G and B channels
     * Write the encoded String to the output file. Don't forget to end the String with a newline
     *
     */
    public void encodeGraphic(String inputFile, String outputFile) throws InvalidHuffmanCodeException, IOException {
        // TODO: fill in this method
        // Use ImageIO's read to get this image's pixels (see process processGraphic method
        // Use right bit shifter, >> and bitwise masking, 0xff, to get the alpha, red, green and blue channels
        // Convert to a String, comma-delimiting A, R, G and B channels
        // Call encode with this encoded String
        // To the encoded String that is returned, add a newline and write to the output file

        // Note that graphic files can be large, so I've provided small graphics for faster execution

        //Printwriter object instantiated
        PrintWriter out = new PrintWriter(outputFile);
        //************************************



        //************************************
    }

    /**
     * method: decode
     * The decode method accepts a string of 0's and 1's and uses the Huffman
     * tree to determine the original string.  Because it is a prefix code, you
     * can start at the root of the Huffman tree and traverse left for '0' and right
     * for '1' until a leaf is reached.  The color value associated with that code
     * (stored in the node) can be appended to the decoded string, along with a comma (NOTE the
     * last color value will NOT have a comma after it.
     *
     * Then, reset back to the root and continue stepping through the string
     * parameter until the end of the string is reached.
     *
     * @return
     */
    public String decode(String str) throws InvalidHuffmanCodeException{
        // TODO: Fill in this method
        // Begin at the root
        // If the current character is -1, you need to continue traversing tree - you have an internal node
        //        If the string's character is 0, traverse left
        //        If the string's character is 1, traverse right
        //        If the string's character is anything other than 0 or 1, throw and Exception
        //
        // When you are at the leaf node, append the color value and a comma (unless it is the last color value - in this
        // case only append the color value)

        //Throw an Exception for an empty str or if the any character is invalid (not 0 or 1).
        //*************************************
        if(root == null){
            return null;
        }




        //************************************

        return null;   //DELETE once code is implemented
    }
    /**
     * method: decodeGraphic
     * Like the encodeGraphic method, the decodeGraphic accepts an inputFile to decode (must be .txt file)
     * and an output file (must be .png file)
     *
     * Create an output image file by using BufferedReader (i.e., BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB) )
     * The height and width are the first two line in the input file. The remaining lines are the pixels.
     * Using nested for-loops, call decode for each line in the output file.
     * Parse on the comma to get the A, R, G and B values
     * Combine the channels using the left bit-shifter and bitwise OR, |
     * Use setRGB to set each pixel
     * Once all the pixels are set use ImageIO's static method "write" to write the graphic file (output file that is .png)
     *
     */
    public void decodeGraphic(String inputFile, String outputFile) throws InvalidHuffmanCodeException, IOException {
        // TODO: Fill in this method
        // Create and output file using BufferedReader (i.e., BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB) )
        // Note the height and width must be read in from the input file (first two lines)
        // The remaining lines in the input file are the pixels
        // Call the decode method for each line.
        // The returned String must be parsed on the comma to get the A, R, G and B values
        // Combine channels into one "bit" by using the left bit-shiffter, <<, and bitwise or, |
        // Call setRGB to set the pixel
        // When all the pixels are set, use ImageIO's static write method to write the graphic file (.png file)
        //*************************************




        //************************************
    }

    /**
     * method: toString
     * returns color value, frequency and compressed code
     * @return
     */
    @Override
    public String toString() {
        String output = "";
        for(ColorCode oneColor: colors)
            output += oneColor.getColorValue() + ": " + oneColor.getFrequency() + ": " + oneColor.getCode() + "\n";
        return output;
    }

    /**
     * method: print Codes
     * Does the same as toString but only prints the subset beginning at start and ending at end (end included)
     * @param start
     * @param end
     * @return
     */
    public String printCodes(int start, int end) {
        String output = "";
        if(start < 0 || start > end)
            start = end;
        if(end > colors.size()-1)
            end = colors.size()-1;

        for(int i = start; i <= end; i++) {
            output += colors.get(i).getColorValue() + ": " + colors.get(i).getCode() + "\n";
        }
        return output;
    }

    /**
     * method: printPixels prints the range of pixels from start to end (end is included)
     * The method first evaluates whether this is the .txt file or the .png file and processes accordingly
     *
     * @param filename
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public String printPixels(String filename, int start, int end) throws IOException {
        int pixel, alpha, red, green, blue, height, width, count = 0;

        String oneLine, output = "";

        //Determine whether the file is encoded (binary) or decoded (decimal)
        //Text file is the encoded "compressed" binary file
        if(filename.substring((filename.indexOf(".")+1)).equals("txt")) {
            Scanner in = new Scanner(new File(filename));
            height = Integer.parseInt(in.nextLine());
            width = Integer.parseInt(in.nextLine());
            count = 0;

            if (start > end)
                start = end;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    count++;
                    oneLine = in.nextLine();
                    if (count >= start && count <= end)
                        output += oneLine + "\n";
                }
            }
            in.close();
        }
        //PNG file is the decoded "uncompressed" ARGB file
        else{
            BufferedImage image = ImageIO.read(new File(filename));
            height = image.getHeight();
            width = image.getWidth();
            count = 0;

            if (start > end)
                start = end;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    count++;
                    pixel = image.getRGB(x, y);
                    alpha = (pixel >> 24) & 0xff;
                    red = (pixel >> 16) & 0xff;
                    green = (pixel >> 8) & 0xff;
                    blue = (pixel) & 0xff;
                    //System.out.println("Pixel: " + alpha + "," + red + "," + green + "," + blue);
                    if (count >= start && count <= end)
                        output += alpha + "," + red + "," + green + "," + blue + "\n";
                }
            }
        }
        return output;
    }

}
