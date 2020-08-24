package com.jsgygujun.javacv.examples

import javax.swing.WindowConstants
import org.bytedeco.javacv.{CanvasFrame, FFmpegFrameGrabber}

import scala.collection.Iterator.continually

/**
 * @author GuJun(jsgygujun@gmail.com)
 */
object ExtractFrames {
  def main(args: Array[String]): Unit = {
    val grabber = new FFmpegFrameGrabber("data/bike.avi")
    // 打开视频文件
    grabber.start()

    // 创建窗口显示帧
    val canvasFrame = new CanvasFrame("Extract Frame", 1)
    canvasFrame.setCanvasSize(grabber.getImageWidth, grabber.getImageHeight)
    canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

    val fps = grabber.getFrameRate
    val step = Math.floor(fps/2.5)

    var framePos = 0
    for (frame <- continually(grabber.grab()).takeWhile(_ != null)) {
      if (framePos % step == 0) {
        canvasFrame.showImage(frame)
        Thread.sleep(1000L)
      }
      framePos += 1
    }

    grabber.stop()
  }
}
