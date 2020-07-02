package com.jsgygujun.javacv.opencv_cookbook

import java.awt._
import java.io.File

import javax.swing.WindowConstants
import org.bytedeco.javacv.CanvasFrame
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat
import org.bytedeco.opencv.opencv_core.Mat
import org.bytedeco.opencv.global.opencv_imgcodecs._

/**
 * 简化OpenCV API使用的辅助方法
 */
object OpenCVUtils {

  /**
   * 加载图像并在CanvasFrame中显示。如果无法加载图像，则应用程序将退出并显示代码-1
   * @param file 文件路径
   * @param flags 指明加载图片的颜色类型， >0: 3通道彩色图片; =0: 灰度图片; <0: 按照图片本身
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
   * @param file 文件路径
   * @param flags 指明加载图片的颜色类型， >0: 3通道彩色图片; =0: 灰度图片; <0: 按照图片本身
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
   * @param mat 图片
   * @param title 标题
   */
  def show(mat: Mat, title: String): Unit = {
    val converter = new ToMat()
    val canvas = new CanvasFrame(title, 1)
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    canvas.showImage(converter.convert(mat))
  }

  /**
   * 在窗口中显示图像。关闭窗口将退出应用程序。
   * @param image 图片
   * @param title 标题
   */
  def show(image: Image, title: String): Unit = {
    val canvas = new CanvasFrame(title, 1)
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    canvas.showImage(image)
  }

  /**
   * 保存图片到指定文件
   * @param file 文件
   * @param image 图片
   */
  def save(file: File, image: Mat): Unit = {
    imwrite(file.getAbsolutePath, image)
  }

}
