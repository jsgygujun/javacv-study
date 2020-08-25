package com.jsgygujun.javacv.examples.video;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

/**
 * 抽取视频帧信息
 * @author GuJun
 * @date 2020/8/25
 */
public class ExtractFramesExp {

    public static void main(String[] args) throws FrameGrabber.Exception {
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber("data/bike.avi");
        // 打开视频文件
        frameGrabber.start();
        for (int i = 0; i < frameGrabber.getFrameNumber(); ++i) {
            // 抽取到的帧信息
            Frame frame = frameGrabber.grab();
            // Java2DFrameUtils是在Mat，IplImage，BufferedImage和Frame对象之间执行各种转换的工具类
            // 方法是同步的，因为底层的JavaCV转换器对于并发访问并不安全。
            // 所有创建的Frame，Mat，IplImages和BufferedImages在创建后都会在内部进行克隆，以便在对创建它们的转换器进行垃圾回收之后，它们的内存位置仍然有效。
            // 这对于调用者来说比较安全，但可能会更慢。
            Mat mat = Java2DFrameUtils.toMat(frame);
            Mat grayMat = new Mat();
            // 色彩空间转换
            cvtColor(mat, grayMat, COLOR_BGRA2GRAY);
        }
        frameGrabber.stop();
    }

}
