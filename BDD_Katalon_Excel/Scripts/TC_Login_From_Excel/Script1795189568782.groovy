import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

WebUI.openBrowser('')
try {
    WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
    WebUI.waitForPageLoad(20)

    WebUI.click(findTestObject('Object Repository/CURA/a_Make Appointment'))
    WebUI.waitForElementVisible(findTestObject('Object Repository/CURA/input_userName'), 10)

    WebUI.setText(findTestObject('Object Repository/CURA/input_userName'), username)
    WebUI.setText(findTestObject('Object Repository/CURA/input_password'), password)
    WebUI.click(findTestObject('Object Repository/CURA/button_Login'))
    WebUI.waitForPageLoad(20)

    if (expectedResult == 'SUCCESS') {
        WebUI.verifyTextPresent('Make Appointment', false)
    } else if (expectedResult == 'FAILURE') {
        WebUI.verifyTextPresent('Login failed! Please ensure the username and password are valid.', false)
    } else {
        throw new IllegalArgumentException('Nilai expectedResult tidak dikenali: ' + expectedResult)
    }

    WebUI.comment('Data yang diuji: ' + testCaseId)
} finally {
    WebUI.closeBrowser()
}