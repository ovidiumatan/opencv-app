package org.tsm.opencv.ex4;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class CarRecognition {
    static String CARS_IMG="Cars-on-Busy-Street.jpg";

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        CascadeClassifier carsClassifier = new CascadeClassifier();
        boolean loaded = carsClassifier.load("cars.xml");
        System.out.println("+haar... loaded:" + loaded);
        Mat frame=Imgcodecs.imread(CARS_IMG);
        MatOfRect detectedCars = new MatOfRect();
            carsClassifier.detectMultiScale(frame, detectedCars);
            Rect cars[] = detectedCars.toArray();
            if (cars.length > 0) {
                System.out.println("Cars detected:"+cars.length);
                for (Rect face : cars) {
                    Imgproc.rectangle(frame, new Point(face.x, face.y), new Point(face.x + face.width, face.y + face.height), new Scalar(0, 255, 0), 3);
                }
                Imgcodecs.imwrite("detectedCars" + System.currentTimeMillis() + ".jpg", frame);
            } else {
            System.out.println("no cars detected");
        }
    }
}
