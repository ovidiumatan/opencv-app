package org.tsm.opencv.ex3;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class PeopleRecognition {
    public static int CAMERA_ID = 0;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture camera = new VideoCapture(CAMERA_ID);
        CascadeClassifier faceDetector = new CascadeClassifier();
        boolean loaded = faceDetector.load("haarcascade_frontalface_alt.xml");
        System.out.println("+haar... loaded:" + loaded);
        MatOfRect detectedFaces = new MatOfRect();
        boolean faceDetected = false;
        Mat frame = new Mat();
        while (!faceDetected) if (camera.read(frame)) {
            faceDetector.detectMultiScale(frame, detectedFaces);
            Rect faces[] = detectedFaces.toArray();
            if (faces.length > 0) {
                System.out.println("Faces detected");
                for (Rect face : faces) {
                    Imgproc.rectangle(frame, new Point(face.x, face.y), new Point(face.x + face.width, face.y + face.height), new Scalar(0, 255, 0), 3);
                    Imgcodecs.imwrite("detectedFaceImg" + System.currentTimeMillis() + ".jpg", frame);
                }
                faceDetected = true;
            }
        }
    }
}
