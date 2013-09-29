import org.scalatest.FlatSpec
import org.scalatest.matchers._

import org.openqa.selenium._
import firefox._
import java.util.concurrent.TimeUnit

import org.openqa.selenium.support.ui._
import org.openqa.selenium.support.ui.ExpectedConditions._

trait TestHelper {
  this: FlatSpec =>
  import scala.util.control.Exception._
  import java.net
  import scala.collection.JavaConversions._

  def using(f: WebDriver => Unit)(implicit url: String): Unit = {
    val driver =  new FirefoxDriver
    driver.get(url)
    try {
      f(driver)
    } finally {
      driver.quit
    }
  }

  /**
   * 指定した処理を実行後、条件を満たすまでwaitします。
   *
   * @param f ajaxの実行等のwaitを必要とする処理
   * @param p waitを終了する為の条件
   */
  def waitForLoading(f: Unit => Unit)(p: Unit => Boolean)(implicit driver: WebDriver) : Unit = {
    val end = System.currentTimeMillis() + 5000
    val current = driver.getCurrentUrl
    f()
    while (System.currentTimeMillis() < end && !p()) {
    }
  }
  def currentUrl(implicit driver: WebDriver) : String = { 
    java.net.URLDecoder.decode(driver.getCurrentUrl, "UTF-8")
  }
}
