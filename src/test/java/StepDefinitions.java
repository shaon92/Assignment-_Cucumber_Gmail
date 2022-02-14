import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Signup;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    public WebDriver driver;
    WebDriverWait wait;
    Signup signup;
    @Given("^User visits gmail signup page$")
    public void user_visits_gmail_signup_page() throws Exception {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops=new FirefoxOptions();
        ops.addArguments("--headed"); 
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com/signup/v2/webcreateaccount?hl=en&flowName=GlifWebSignIn&flowEntry=SignUp");
    }

    @When("^User inputs \"([^\"]*)\" less than eight chars$")
    public void user_inputs_less_than_eight_chars(String passwordLessThanEight) throws Exception {
        signup = new Signup(driver);
        signup.doSignupWithPasswordLessThen8Letters(passwordLessThanEight);
    }

    @Then("^Use eight characters or more for your password$")
    public void use_eight_characters_or_more_for_your_password() throws Exception {
        signup = new Signup(driver);
        String text = signup.getErrorForPasswordLessThen8Letters();
        Assert.assertEquals("Use 8 characters or more for your password", text);
    }

    @When("^User inputs \"([^\"]*)\" more than eight chars but it is not strong$")
    public void user_inputs_more_than_eight_chars_but_it_is_not_strong(String passwordNotStrong) throws Exception {
        signup = new Signup(driver);
        signup.doSignupWithPasswordLength8orMoreButNotStrong(passwordNotStrong);
    }

    @Then("^Please choose a stronger password$")
    public void please_choose_a_stronger_password() throws Exception {
        signup = new Signup(driver);
        String text =  signup.getErrorForNotStrongPassword();
        Assert.assertEquals("Please choose a stronger password. Try a mix of letters, numbers, and symbols.", text);
        driver.close();
    }
}
