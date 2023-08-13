package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class SampleAppPage {
    @FindBy(name = "UserName")
    private WebElement username;

    @FindBy(name = "Password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "loginstatus")
    private WebElement loginStatus;

    public SampleAppPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String checkAuthMessage(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
        return loginStatus.getText();
    }

    public String checkLogoutMessage() {
        loginButton.click();
        return loginStatus.getText();
    }
}