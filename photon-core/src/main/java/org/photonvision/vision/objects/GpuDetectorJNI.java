package org.photonvision.vision.objects;

// NOTE: This JNI wrapper is used by the CUDA-backed AprilTag pipeline (AprilTagCuda).
// The native library `971apriltag` implements the CUDA detector and is loaded below.
// The Pipeline implementation calls into these natives (via this class) to run GPU detection.

import edu.wpi.first.apriltag.AprilTagDetection;
import java.io.IOException;

public class GpuDetectorJNI {
    static boolean libraryLoaded = false;

    static {
      if(!libraryLoaded)
        System.loadLibrary("971apriltag");
      libraryLoaded = true;
    }
    
    public static native long createGpuDetector(int width, int height);
    public static native void destroyGpuDetector(long handle);
    public static native void setparams(long handle, double fx, double cx,
		    double fy, double cy, double k1, double k2, double p1,
		    double p2, double k3);
    public static native AprilTagDetection[] processimage(long handle, long p);

}
