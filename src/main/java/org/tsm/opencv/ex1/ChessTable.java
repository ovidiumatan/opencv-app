package org.tsm.opencv.ex1;

import org.opencv.core.*;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;

/**
 * Creates a chess table using OpenCV
 */
public class ChessTable {
    public static void main(String[] args) {
        int nrRows = 8;
        int squareSize = 20;
        int tableSize = nrRows * squareSize;

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat mainTable = new Mat(tableSize, tableSize, CvType.CV_8UC3);
        //set color to white
        mainTable.setTo(new Scalar(255, 255, 255));
        for (int j = 0; j < nrRows; j++)
            for (int i = 0; i < nrRows; i++) {
                if ((i%2^j%2)==1){//Longer version: i % 2 != 0 && j%2==0 || i%2==0 && j%2!=0) {
                    Mat square = mainTable.submat(new Rect(i * squareSize, j*squareSize, squareSize, squareSize));
                    square.setTo(new Scalar(0, 0, 0));
                }

            }
        imwrite("chessTable.png", mainTable);
        System.out.println(mainTable);
    }
}
