package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Signup {
    @FindBy(id ="firstName")
    WebElement txtFirstName;
    @FindBy(id ="lastName")
    WebElement txtLastName;
    @FindBy(id ="username")
    WebElement txtUsername;
    @FindBy(name ="Passwd")
    WebElement txtPasswd;
    @FindBy(name ="ConfirmPasswd")
    WebElement txtConfirmPasswd;
    @FindBy(xpath = "//span[contains(text(),'Use 8 characters or more for your password')]")
    WebElement lblLessThen8character;
    @FindBy(xpath = "//span[contains(text(),'Please choose a stronger password. Try a mix of le')]")
    WebElement lblNotStrongPassword;
    @FindBy(tagName = "button")
    List<WebElement> btnNext;

    public Signup(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doSignupWithPasswordLessThen8Letters(String passwordLessThenEight) {
        clearFields();
        txtFirstName.sendKeys("Test");
        txtLastName.sendKeys("user");
        txtUsername.sendKeys("hellotestuser10101010");
        txtPasswd.sendKeys(passwordLessThenEight);
        txtConfirmPasswd.sendKeys(passwordLessThenEight);
        btnNext.get(1).click();
        clearFields();
    }
    public String getErrorForPasswordLessThen8Letters() {
        return lblLessThen8character.getText();
    }
    public void doSignupWithPasswordLength8orMoreButNotStrong(String passwordNotStrong) {
        clearFields();
        txtFirstName.sendKeys("Test");
        txtLastName.sendKeys("user");
        txtUsername.sendKeys("hellotestuser10101010");
        txtPasswd.sendKeys(passwordNotStrong);
        txtConfirmPasswd.sendKeys(passwordNotStrong);
        btnNext.get(1).click();
        clearFields();
    }
    public String getErrorForNotStrongPassword() {
        return lblNotStrongPassword.getText();
    }
    public void clearFields() {
        txtFirstName.clear();
        txtLastName.clear();
        txtUsername.clear();
        txtPasswd.clear();
        txtConfirmPasswd.clear();
    }
}
