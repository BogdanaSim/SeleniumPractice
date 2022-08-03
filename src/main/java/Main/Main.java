package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class Main {

    public static WebDriver resetDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");
        return driver;

    }


    public static void homepage() throws InterruptedException {
        WebDriver driver = resetDriver();
        System.out.println("Title of the current page is: " + driver.getTitle());
        System.out.println("URL of the current page is: " + driver.getCurrentUrl());
        driver.findElement(By.className("logo")).click();
        driver.get("http://qa1magento.dev.evozon.com/women/new-arrivals.html");
        driver.navigate().back();
        driver.navigate().forward();
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        driver.quit();


    }

    public static void account() throws InterruptedException {
        WebDriver driver = resetDriver();
        Thread.sleep(1000);
        driver.findElement(By.className("skip-account")).click();
        Thread.sleep(2000);
        driver.quit();
    }

    public static void languages() throws InterruptedException {

        WebDriver driver = resetDriver();
        WebElement element = driver.findElement(By.id("select-language"));
        driver.findElement(By.id("select-language")).click();

        Select select = new Select(element);
        Thread.sleep(2000);
        select.selectByIndex(1);
        Thread.sleep(2000);
        driver.quit();

    }

    public static void search() throws InterruptedException {
        WebDriver driver = resetDriver();
        WebElement element1 = driver.findElement(By.id("search_mini_form"));
        WebElement element = driver.findElement(By.id("search"));
        element.clear();
        Thread.sleep(2000);
        element.sendKeys("woman");
        element1.submit();
        Thread.sleep(3000);
        driver.quit();
    }

    public static void newListProducts() {
        WebDriver driver = resetDriver();
        driver.get("http://qa2magento.dev.evozon.com/");
        List<WebElement> list = driver.findElements(By.cssSelector(".item.last"));
        System.out.println("Number of new products: " + list.size());
        System.out.println("New products: ");
        for (WebElement element : list) {
            String s = element.getText().split("\n")[0];
            System.out.println(s);
        }
        driver.quit();
    }

    public static void navigateToPage(String pageName) throws InterruptedException {
        WebDriver driver = resetDriver();
        List<WebElement> list = driver.findElements(By.cssSelector("li.level0"));
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(pageName)) {
                element.click();
                Thread.sleep(2000);
                break;
            }
        }
        driver.quit();
    }

    public static void addProductToCart() throws InterruptedException {
        WebDriver driver = resetDriver();
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
        WebElement quantity=driver.findElement(By.id("qty"));
        quantity.clear();
        quantity.sendKeys("2");
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        Thread.sleep(3000);
        driver.quit();


    }

    public static void removeProductFromCart() throws InterruptedException {
        WebDriver driver = resetDriver();
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

    public static void submitReview() throws InterruptedException {
        WebDriver driver = resetDriver();
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
        Thread.sleep(3000);
        driver.quit();

    }


    public static void register() throws InterruptedException {
        WebDriver driver = resetDriver();
        driver.findElement(By.xpath("/html/body/div/div/header/div/div[2]/div/a/span[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div/header/div/div[5]/div/ul/li[5]/a")).click();
        driver.findElement(By.id("firstname")).sendKeys("MyName");
        driver.findElement(By.id("lastname")).sendKeys("MyLastName");
        driver.findElement(By.id("email_address")).sendKeys("example@mail.com");
        driver.findElement(By.id("password")).sendKeys("qawsed");
        driver.findElement(By.id("confirmation")).sendKeys("qawsed");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/form/div[2]/button")).click();
        Thread.sleep(2000);
        driver.quit();

    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        homepage();
        account();
        languages();
        search();
        newListProducts();
        navigateToPage("Sale");
        addProductToCart();
        removeProductFromCart();
        submitReview();
        register();

    }
}
