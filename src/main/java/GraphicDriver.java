/*
    CSCI 211
    Robert McCurdy
    Student ID: 010715877
    Program 3

    In keeping with the UM Honor Code, I have neither given
    nor received assistance from anyone other than the
    instructor.
 */

import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphicDriver {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        int choice = 5, start, end;
        String oneLine, inFile, outFile;
        String menu = "1: encode\n2: encode a graphic file\n3: decode\n4: decode a graphic file\n5: exit";


        //Hard code a file name
        String filename = "olemissBaseball.jpg";

        //Alternatively, read in from the keyboard
/*
        System.out.println("Enter file name from which to create Huffman Tree");
        String filename = scan.nextLine();
*/

        HuffmanGraphic huffmanCoding = new HuffmanGraphic(filename);


        System.out.println("\n\n************ Printing Codes");
        System.out.println(huffmanCoding);

        System.out.println("\n\n************ Printing Subset of Codes");
        System.out.println(huffmanCoding.printCodes(0, 9));

        //NOTE: You may choose to hard-code the input and output files and the starting and ending pixels to print
        //      This way you will not need to enter the filename each time.
        do {
            System.out.println("Choose a menu option (number)\n"+menu);
            choice = Integer.parseInt(scan.nextLine());
            switch(choice) {
                case 1:
                    System.out.println("Enter string to encode");
                    oneLine = scan.nextLine();
                    System.out.println("Encoded String for " +  oneLine + " is " + huffmanCoding.encode(oneLine));
                break;

                case 2:
                    System.out.println("Enter input file to encode and output file to write to");
                    inFile = scan.nextLine();
                    outFile = scan.nextLine();
                    huffmanCoding.encodeGraphic(inFile, outFile);
                    System.out.println("Now enter the starting and ending pixels to print");
                    start = Integer.parseInt(scan.nextLine());
                    end = Integer.parseInt(scan.nextLine());
                    System.out.println(huffmanCoding.printPixels(outFile, start, end));
                break;

                case 3:
                    System.out.println("Enter string to decode");
                    oneLine = scan.nextLine();
                    System.out.println("Decoded String for " +  oneLine + " is " + huffmanCoding.decode(oneLine));
                break;

                case 4:
                    System.out.println("Enter input file to decode and output file to write to");
                    inFile = scan.nextLine();
                    outFile = scan.nextLine();
                    huffmanCoding.decodeGraphic(inFile, outFile);
                    System.out.println("Now enter the starting and ending pixels to print");
                    start = Integer.parseInt(scan.nextLine());
                    end = Integer.parseInt(scan.nextLine());
                    System.out.println(huffmanCoding.printPixels(outFile, start, end));
                break;

                default:
                    done = true;
            }

        }while(!done);


    }
}
