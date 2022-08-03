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

    public static void firstEx(WebDriver driver) throws InterruptedException {
        System.out.println("Title of the current page is: " + driver.getTitle());
        System.out.println("URL of the current page is: " + driver.getCurrentUrl());
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
        driver = new ChromeDriver();
    }

    public static void secondEx(WebDriver driver) throws InterruptedException {

        WebElement element = driver.findElement(By.id("select-language"));
        driver.findElement(By.id("select-language")).click();

        Select select = new Select(element);

        select.selectByIndex(1);

        driver.quit();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        WebElement element1 = driver.findElement(By.id("search_mini_form"));
        element = driver.findElement(By.id("search"));
        element.clear();
        element.sendKeys("woman");
        element1.submit();
        driver.quit();
        driver = new ChromeDriver();

    }

    public static void thirdExRemove(WebDriver driver) throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/a"));
        actions.moveToElement(hover).build().perform();

        By category = By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/ul/li[3]/a");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        driver.findElement(By.className("product-image")).click();
        driver.findElement(By.className("swatch-label")).click();
        driver.findElement(By.className("option-s")).click();

        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        WebElement hover1 = driver.findElement(By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/a"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/a")));
        actions.moveToElement(hover1).build().perform();
        By category1 = By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/ul/li[4]/a");
        WebElement subcategory1 = driver.findElement(category1);
        subcategory1.click();
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/div[3]/ul/li[1]/a/img")).click();
        driver.findElement(By.className("swatch-label")).click();
        driver.findElement(By.className("option-26")).click();
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr.first.odd > td.a-center.product-cart-remove.last > a")).click();
        Thread.sleep(3000);
        driver.quit();
        driver = new ChromeDriver();

    }

    public static void submitReview(){
        
    }

    public static void thirdEx(WebDriver driver) throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/a"));
        actions.moveToElement(hover).build().perform();

        By category = By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/ul/li[3]/a");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        driver.findElement(By.className("product-image")).click();
        driver.findElement(By.className("swatch-label")).click();
        driver.findElement(By.className("option-s")).click();
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        Thread.sleep(3000);
        driver.quit();

        driver = new ChromeDriver();
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        //firstEx(driver);
        //secondEx(driver);
        //    thirdEx(driver);
        thirdExRemove(driver);

    }
}
