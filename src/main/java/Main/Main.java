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
        driver.findElement(By.cssSelector(".account-cart-wrapper a")).click();
        Thread.sleep(2000);
        driver.quit();
    }

    public static void languages() throws InterruptedException {

        WebDriver driver = resetDriver();
        WebElement element = driver.findElement(By.cssSelector("select#select-language"));
        element.click();

        Select select = new Select(element);
        Thread.sleep(2000);
        select.selectByIndex(1);
        Thread.sleep(2000);
        driver.quit();

    }

    public static void search() throws InterruptedException {
        WebDriver driver = resetDriver();
        WebElement element1 = driver.findElement(By.cssSelector("form#search_mini_form"));
        WebElement element = driver.findElement(By.cssSelector("input#search"));
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
        List<WebElement> list = driver.findElements(By.cssSelector("li.item.last"));
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
        WebElement hover = driver.findElement(By.cssSelector("li.level0.nav-1.first.parent"));

        Actions actions = new Actions(driver);
        actions.moveToElement(hover).build().perform();

        By category = By.cssSelector("li.level1.nav-1-2 a.level1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        driver.findElement(By.cssSelector("ul.products-grid a.product-image")).click();
        driver.findElement(By.cssSelector("ul#configurable_swatch_color span.swatch-label")).click();
        driver.findElement(By.cssSelector("li.option-s span.swatch-label")).click();

        WebElement quantity=driver.findElement(By.cssSelector("input#qty"));
        quantity.clear();
        quantity.sendKeys("2");
        driver.findElement(By.cssSelector("div.add-to-cart-buttons button")).click();

        Thread.sleep(3000);
        driver.quit();


    }

    public static void removeProductFromCart() throws InterruptedException {
        WebDriver driver = resetDriver();
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(By.cssSelector("li.level0.nav-1.first.parent"));
        actions.moveToElement(hover).build().perform();

        By category = By.cssSelector("li.level1.nav-1-2 a.level1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        driver.findElement(By.cssSelector("ul.products-grid a.product-image")).click();
        driver.findElement(By.cssSelector("ul#configurable_swatch_color span.swatch-label")).click();
        driver.findElement(By.cssSelector("li.option-s span.swatch-label")).click();
        WebElement quantity=driver.findElement(By.cssSelector("input#qty"));
        quantity.clear();
        quantity.sendKeys("2");
        driver.findElement(By.cssSelector("div.add-to-cart-buttons button")).click();
        WebElement hover1 = driver.findElement(By.cssSelector("li.level0.nav-1.first.parent"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.level0.nav-1.first.parent")));
        actions.moveToElement(hover1).build().perform();
        By category1 = By.cssSelector("li.level1.nav-1-3 a.level1");
        WebElement subcategory1 = driver.findElement(category1);
        subcategory1.click();
        driver.findElement(By.cssSelector("ul.products-grid a.product-image")).click();
        driver.findElement(By.cssSelector("ul#configurable_swatch_color span.swatch-label")).click();
        driver.findElement(By.cssSelector("li.option-26 span.swatch-label")).click();
        WebElement quantity1=driver.findElement(By.cssSelector("input#qty"));
        quantity1.clear();
        quantity1.sendKeys("3");
        driver.findElement(By.cssSelector("div.add-to-cart-buttons button")).click();
        driver.findElement(By.cssSelector("td.product-cart-remove a.btn-remove")).click();
        Thread.sleep(3000);
        driver.quit();

    }

    public static void submitReview() throws InterruptedException {
        WebDriver driver = resetDriver();
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(By.cssSelector("li.level0.nav-1.first.parent"));
        actions.moveToElement(hover).build().perform();

        By category = By.cssSelector("li.level1.nav-1-2 a.level1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        Random rand = new Random();
        List<WebElement> list = driver.findElements(By.cssSelector(".item.last"));
        WebElement elem = list.get(rand.nextInt(list.size()));
        elem.click();
        By review = By.cssSelector("div.extra-info span.separator+a");
        if (driver.findElements(review).size() == 0) {
            driver.findElement(By.cssSelector("ul.toggle-tabs li.last ")).click();
            driver.findElement(By.cssSelector("p.no-rating a")).click();


        } else {
            driver.findElement(review).click();
        }
        driver.findElement(By.cssSelector("#Quality_1")).click();
        driver.findElement(By.cssSelector("#Price_1")).click();
        driver.findElement(By.cssSelector("#Value_1")).click();
        driver.findElement(By.cssSelector("#review_field")).sendKeys("Cool item");
        driver.findElement(By.cssSelector("#summary_field")).sendKeys("Awesome");
        driver.findElement(By.cssSelector("#nickname_field")).sendKeys("Anonymous");
        driver.findElement(By.cssSelector(".form-add button")).click();
        Thread.sleep(3000);
        driver.quit();

    }


    public static void register() throws InterruptedException {
        WebDriver driver = resetDriver();
        driver.findElement(By.cssSelector(".account-cart-wrapper a")).click();
        driver.findElement(By.cssSelector("div#header-account li:nth-child(5) a")).click();
        driver.findElement(By.cssSelector("#firstname")).sendKeys("MyName");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("MyLastName");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("example@mail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("qawsed");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("qawsed");
        driver.findElement(By.cssSelector("div.buttons-set button")).click();
        Thread.sleep(2000);
        driver.quit();

    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

//        homepage();
//        account();
//        languages();
//        search();
//        newListProducts();
//        navigateToPage("Sale");
//        addProductToCart();
//        removeProductFromCart();
//        submitReview();
        register();

    }
}
