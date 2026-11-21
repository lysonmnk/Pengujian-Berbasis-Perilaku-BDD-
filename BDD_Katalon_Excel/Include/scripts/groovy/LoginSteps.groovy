import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

class LoginSteps {

    @Given("I open the CURA application")
    def openCuraApplication() {
        WebUI.openBrowser('')
        WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
        WebUI.waitForPageLoad(20)
    }

    @When("I open the login form")
    def openLoginForm() {
        WebUI.click(findTestObject('Object Repository/CURA/a_Make Appointment'))
        WebUI.waitForPageLoad(20)
    }

    @When("I enter username {string} and password {string}")
    def enterCredentials(String username, String password) {
        WebUI.setText(findTestObject('Object Repository/CURA/input_userName'), username)
        WebUI.setText(findTestObject('Object Repository/CURA/input_password'), password)
    }

    @When("I submit login")
    def submitLogin() {
        WebUI.click(findTestObject('Object Repository/CURA/button_Login'))
        WebUI.waitForPageLoad(20)
    }

    @Then("the login result should be {string}")
    def verifyLoginResult(String expectedResult) {
        try {
            if (expectedResult == 'SUCCESS') {
                WebUI.verifyTextPresent('Make Appointment', false)
            } else if (expectedResult == 'FAILURE') {
                WebUI.verifyTextPresent(
                    'Login failed! Please ensure the username and password are valid.', false)
            } else {
                throw new IllegalArgumentException(
                    'expectedResult harus SUCCESS atau FAILURE: ' + expectedResult)
            }
        } finally {
            WebUI.closeBrowser()
        }
    }
}