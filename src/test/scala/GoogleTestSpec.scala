import org.scalatest.FlatSpec
import org.scalatest.matchers._
import org.openqa.selenium._
import org.openqa.selenium.safari._
import firefox._
import java.io.File

class GoogleTestSpec extends FlatSpec with TestHelper with TestConfig {

  def createDriver: WebDriver = {
    new SafariDriver
  }

  implicit val url = "http://www.google.co.jp"
  "top page" should "have title" in {
    using { implicit driver =>
      assert(driver.getTitle == "Google")
      capture(new File("top_page.png"))
    }
  }

  "search by a" should "return 10 results" in {
    using { implicit driver =>
    driver.findElement(By.name("q")).sendKeys("a")
    waitForLoading { _ =>
      driver.findElement(By.name("q")).submit()
    } { _ =>
      !driver.findElements(By.className("g")).isEmpty
    }

    assert(driver.findElements(By.className("g")).size == 10)
    capture(new File("search_by_a.png"))
    }
  }
}
