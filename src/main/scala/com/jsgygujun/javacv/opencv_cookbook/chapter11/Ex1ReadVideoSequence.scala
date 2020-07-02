package com.jsgygujun.javacv.opencv_cookbook.chapter11

import javax.swing.WindowConstants
import org.bytedeco.javacv.{CanvasFrame, FFmpegFrameGrabber}

import scala.collection.Iterator.continually

/**
 *
 * @author gujun@qiyi.com
 * @since 2020/7/2 3:47 下午
 */
object Ex1ReadVideoSequence extends App {

  val grabber = new FFmpegFrameGrabber("data/bike.avi")
  // 打开视频文件
  grabber.start()

  // 创建窗口显示帧
  val canvasFrame = new CanvasFrame("Extract Frame", 1)
  canvasFrame.setCanvasSize(grabber.getImageWidth, grabber.getImageHeight)
  canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  // 根据帧率设置延迟时间
  val delay = math.round(1000d / grabber.getFrameRate)

  // 一帧一帧读取
  for (frame <- continually(grabber.grab()).takeWhile(_ != null) if canvasFrame.isVisible) {
    canvasFrame.showImage(frame)
    Thread.sleep(delay)
  }
  // 关闭视频文件
  grabber.release()
}
