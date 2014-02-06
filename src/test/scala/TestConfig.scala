import org.openqa.selenium._

trait TestConfig {
  def createDriver: WebDriver
}
