package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import javax.swing.*;
import java.time.Duration;



public class Main {

    public static void firstEx() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        System.out.println("Title of the current page is: "+driver.getTitle());
        System.out.println("URL of the current page is: "+driver.getCurrentUrl());
        driver.findElement(By.className("logo")).click();
        driver.get("http://qa1magento.dev.evozon.com/women/new-arrivals.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.quit();


        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        driver.findElement(By.className("skip-account")).click();

        Thread.sleep(3000);
        driver.quit();
    }

    public static void secondEx() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        WebElement element = driver.findElement(By.id("select-language"));
         driver.findElement(By.id("select-language")).click();

        Select select = new Select(element);

        select.selectByIndex(1);

        driver.quit();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        WebElement element1=driver.findElement(By.id("search_mini_form"));
        element = driver.findElement(By.id("search"));
        element.clear();
        element.sendKeys("woman");
        element1.submit();
        driver.quit();

    }
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        firstEx();
       secondEx();


    }
}
