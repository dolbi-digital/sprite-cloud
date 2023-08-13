package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomePage {
    private final WebDriver driver;

    @FindBy(xpath = "//section[@id='overview']//a")
    private List<WebElement> names;

    @FindBy(xpath = "//section[@id='overview']//p")
    private List<WebElement> descriptions;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToChapter(String name){
        driver.findElement(By.xpath("//a[contains(text(), '" + name + "')]")).click();
    }

    public List<String> getActualText(List<WebElement> elements) {
        List<String> actual = new ArrayList<>();
        for (WebElement el : elements) {
            actual.add(el.getText());
        }
        return actual;
    }

    public List<String> getExpectedText(String fileName) {
        List<String> expected = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src//test//resources//" + fileName));
            while (scanner.hasNextLine()) {
                expected.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return expected;
    }

    public List<WebElement> getNames() {
        return names;
    }

    public List<WebElement> getDescriptions() {
        return descriptions;
    }
}