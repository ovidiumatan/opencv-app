package org.tsm.opencv.ex2;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CannyEffect{

    static String IMG_URL="https://www.todaysoftmag.ro/images/covers/cover_n74_ro@2x.jpg";
    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat cover = getImageFromURL(IMG_URL);
        Imgcodecs.imwrite("original.png", cover);

        Imgproc.cvtColor(cover,cover,Imgproc.COLOR_RGB2GRAY);
        Imgcodecs.imwrite("gray.png", cover);

        Imgproc.Canny(cover,cover,150.0,300.0,5,true);

        Imgcodecs.imwrite("canny.png", cover);
    }


    private static Mat getImageFromURL(String image_path) throws IOException {
        URL url=new URL(image_path);
        URLConnection conn=url.openConnection();
        byte[] imgData=conn.getInputStream().readAllBytes();

        return Imgcodecs.imdecode(new MatOfByte(imgData), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }
}