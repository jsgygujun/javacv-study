package com.jsgygujun.javacv.opencv_cookbook

import java.io.File

import javax.swing.WindowConstants
import org.bytedeco.javacv.CanvasFrame
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat
import org.bytedeco.opencv.opencv_core.Mat
import org.bytedeco.opencv.global.opencv_imgcodecs._

/**
 * 简化OpenCV API使用的辅助方法
 *
 * @author gujun@qiyi.com
 * @since 2020/7/2 3:12 下午
 */
object OpenCVUtils {

  /**
   * 加载图像并在CanvasFrame中显示。如果无法加载图像，则应用程序将退出并显示代码-1
   * @param file
   * @param flags
   * @return
   */
  def loadOrExit(file: File, flags: Int=IMREAD_COLOR): Mat = {
    // 读取图片
    val image = imread(file.getAbsolutePath, flags)
    if (image.empty()) {
      System.err.println("Failed to load image: " + file.getAbsolutePath)
      sys.exit(-1)
    }
    image
  }

  /**
   * 加载图像并在CanvasFrame中显示。如果无法加载图像，则应用程序将退出并显示代码-1
   * @param file
   * @param flags
   * @return
   */
  def loadAndShowOrExit(file: File, flags: Int=IMREAD_COLOR): Mat = {
    // 读取图片
    val image = loadOrExit(file, flags)
    show(image, file.getName)
    image
  }

  /**
   * 在窗口中显示图像。关闭窗口将退出应用程序。
   * @param mat
   * @param title
   */
  def show(mat: Mat, title: String): Unit = {
    val converter = new ToMat()
    val canvas = new CanvasFrame(title, 1)
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    canvas.showImage(converter.convert(mat))
  }
}
