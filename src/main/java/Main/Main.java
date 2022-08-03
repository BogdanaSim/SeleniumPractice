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
import java.util.List;
import java.util.Random;


public class Main {

    public static void resetDriver(WebDriver driver) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");

    }

    public static void homepage(WebDriver driver) throws InterruptedException {
        resetDriver(driver);
        System.out.println("Title of the current page is: " + driver.getTitle());
        System.out.println("URL of the current page is: " + driver.getCurrentUrl());
        driver.findElement(By.className("logo")).click();
        driver.get("http://qa1magento.dev.evozon.com/women/new-arrivals.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.quit();


    }

    public static void account(WebDriver driver) throws InterruptedException {
        resetDriver(driver);
        driver.findElement(By.className("skip-account")).click();
        Thread.sleep(3000);
        driver.quit();
    }

    public static void languages(WebDriver driver) throws InterruptedException {

        resetDriver(driver);
        WebElement element = driver.findElement(By.id("select-language"));
        driver.findElement(By.id("select-language")).click();

        Select select = new Select(element);

        select.selectByIndex(1);

        driver.quit();

    }

    public static void search(WebDriver driver) {
        resetDriver(driver);
        WebElement element1 = driver.findElement(By.id("search_mini_form"));
        WebElement element = driver.findElement(By.id("search"));
        element.clear();
        element.sendKeys("woman");
        element1.submit();
        driver.quit();
    }

    public static void newListProducts(WebDriver driver) {
        resetDriver(driver);
        driver.get("http://qa2magento.dev.evozon.com/");
        List<WebElement> list = driver.findElements(By.cssSelector(".item.last"));
        System.out.println("Number of new products: " + list.size());
        for (WebElement element : list) {
            String s = element.getText().split("\n")[0];
            System.out.println(s);
        }
    }

    public static void navigateToPage(WebDriver driver, String pageName) throws InterruptedException {
        List<WebElement> list = driver.findElements(By.cssSelector("li.level0"));
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(pageName)) {
                element.click();
                break;
            }
        }
    }

    public static void addProductToCart(WebDriver driver) throws InterruptedException {

        resetDriver(driver);
        WebElement hover = driver.findElement(By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/a"));
        Actions actions = new Actions(driver);
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


    }

    public static void removeProductFromCart(WebDriver driver) throws InterruptedException {
        resetDriver(driver);
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

    }

    public static void submitReview(WebDriver driver) {
        resetDriver(driver);
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/a"));
        actions.moveToElement(hover).build().perform();

        By category = By.xpath("/html/body/div/div/header/div/div[3]/nav/ol/li[1]/ul/li[3]/a");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        Random rand = new Random();
        List<WebElement> list = driver.findElements(By.cssSelector(".item.last"));
        WebElement elem = list.get(rand.nextInt(list.size()));
        elem.click();
        By review = By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[3]/div[1]/form/div[3]/div[3]/div/p/a[2]");
        if (driver.findElements(review).size() == 0) {
            driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[3]/div[2]/ul/li[3]/span")).click();
            driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[3]/div[2]/dl/dd[3]/div/div/p/a")).click();


        } else {
            driver.findElement(review).click();
        }
        driver.findElement(By.id("Quality_1")).click();
        driver.findElement(By.id("Price_1")).click();
        driver.findElement(By.id("Value_1")).click();
        driver.findElement(By.id("review_field")).sendKeys("Cool item");
        driver.findElement(By.id("summary_field")).sendKeys("Awesome");
        driver.findElement(By.id("nickname_field")).sendKeys("Anonymous");
        driver.findElement(By.ByXPath.xpath("/html/body/div/div/div[2]/div/div[2]/div[3]/div[3]/div/form/div[2]/button")).click();
        driver.quit();

    }


    public static void register(WebDriver driver) {
        resetDriver(driver);
        driver.findElement(By.xpath("/html/body/div/div/header/div/div[2]/div/a/span[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div/header/div/div[5]/div/ul/li[5]/a")).click();
        driver.findElement(By.id("firstname")).sendKeys("MyName");
        driver.findElement(By.id("lastname")).sendKeys("MyLastName");
        driver.findElement(By.id("email_address")).sendKeys("example@mail.com");
        driver.findElement(By.id("password")).sendKeys("qawsed");
        driver.findElement(By.id("confirmation")).sendKeys("qawsed");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/form/div[2]/button")).click();


    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        homepage(driver);
        account(driver);
        languages(driver);
        search(driver);
        newListProducts(driver);
        navigateToPage(driver, "Sale");
        addProductToCart(driver);
        removeProductFromCart(driver);
        submitReview(driver);
        register(driver);
        register(driver);
    }
}
