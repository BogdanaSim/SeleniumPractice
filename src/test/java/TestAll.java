import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(JUnit4.class)
public class TestAll {
    private static WebDriver driver;

    @BeforeClass
    public static void setDriverLocation() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

    }


    @Before
    public void resetDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa1magento.dev.evozon.com/");


    }

    @Test
    public void testAccount() {
        driver.findElement(By.cssSelector(".account-cart-wrapper a")).click();
        Assert.assertEquals(1, driver.findElements(By.cssSelector("div#header-account")).size());
    }

    @Test
    public void testHomepage() {
        driver.get("http://qa1magento.dev.evozon.com/");
        driver.findElement(By.className("logo")).click();
        driver.get("http://qa1magento.dev.evozon.com/women/new-arrivals.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        Assert.assertEquals("New Arrivals - Women", driver.getTitle());
    }

    @Test
    public void testLanguages() {
        WebElement element = driver.findElement(By.cssSelector("select#select-language"));
        element.click();
        Select select = new Select(element);
        select.selectByIndex(1);
        element = driver.findElement(By.cssSelector("select#select-language"));
        Select select1 = new Select(element);
        Assert.assertEquals("French", select1.getFirstSelectedOption().getText());
    }


    @Test
    public void testSearch() {
        WebElement element1 = driver.findElement(By.cssSelector("form#search_mini_form"));
        WebElement element = driver.findElement(By.cssSelector("input#search"));
        element.clear();
        element.sendKeys("woman");
        element1.submit();
        List<WebElement> results = driver.findElements(By.cssSelector("ul.products-grid li.item.last"));
//        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(), "SEARCH RESULTS FOR 'WOMAN'");
        Assert.assertTrue(results.size() > 0);
        for (WebElement element2 : results) {
            String s = element2.getText().split("\n")[0];
            Assert.assertTrue(s.toLowerCase().contains("woman"));
        }
    }

    @Test
    public void testNewProducts() {
        driver.get("http://qa2magento.dev.evozon.com/");
        List<WebElement> list = driver.findElements(By.cssSelector("li.item.last"));
        List<String> names = new ArrayList<>();
        String[] array = {
                "LINEN BLAZER", "ELIZABETH KNIT TOP", "CHELSEA TEE", "LAFAYETTE CONVERTIBLE DRESS",
                "TORI TANK"};
        for (WebElement element : list) {
            String s = element.getText().split("\n")[0];
            names.add(s);

        }
        Assert.assertEquals(names.size(), 5);
        Assert.assertArrayEquals(Arrays.stream(array).toArray(), array);


    }

    @Test
    public void navigateTest(){
        List<WebElement> list = driver.findElements(By.cssSelector("li.level0"));
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase("Sale")) {
                element.click();
                break;
            }
        }
        Assert.assertEquals(driver.getTitle(),"Sale");
    }

    @Test
    public void addProductTest(){
        WebElement hover = driver.findElement(By.cssSelector("li.level0.nav-1.first.parent"));

        Actions actions = new Actions(driver);
        actions.moveToElement(hover).build().perform();

        By category = By.cssSelector("li.level1.nav-1-2 a.level1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        String product_name=driver.findElement(By.cssSelector("ul.products-grid h2.product-name")).getText();
        driver.findElement(By.cssSelector("ul.products-grid a.product-image")).click();


        driver.findElement(By.cssSelector("ul#configurable_swatch_color span.swatch-label")).click();
        driver.findElement(By.cssSelector("li.option-s span.swatch-label")).click();

        WebElement quantity=driver.findElement(By.cssSelector("input#qty"));
        quantity.clear();
        quantity.sendKeys("2");
        driver.findElement(By.cssSelector("div.add-to-cart-buttons button")).click();
        Assert.assertEquals(product_name,driver.findElement(By.cssSelector("tbody tr:first-child h2.product-name a")).getText());
        Assert.assertEquals("2",driver.findElement(By.cssSelector("tbody tr:first-child input.input-text.qty")).getAttribute("value"));

    }

    @Test
    public void removeProduct(){
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(By.cssSelector("li.level0.nav-1.first.parent"));
        actions.moveToElement(hover).build().perform();

        By category = By.cssSelector("li.level1.nav-1-2 a.level1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(category));
        WebElement subcategory = driver.findElement(category);
        subcategory.click();
        String product_name=driver.findElement(By.cssSelector("ul.products-grid h2.product-name")).getText();

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
        Assert.assertNotEquals(product_name,driver.findElement(By.cssSelector("tbody tr:first-child h2.product-name a")).getText());

    }

    @Test
    public void testSubmitReview(){
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

        Assert.assertTrue( driver.findElement(By.cssSelector(".error-msg span")).getText().equalsIgnoreCase("Your review has been accepted for moderation."));
    }

    @Test
    public void testRegister(){
        driver.findElement(By.cssSelector(".account-cart-wrapper a")).click();
        driver.findElement(By.cssSelector("div#header-account li:nth-child(5) a")).click();
        driver.findElement(By.cssSelector("#firstname")).sendKeys("MyName");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("MyLastName");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("example@mail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("qawsed");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("qawsed");
        driver.findElement(By.cssSelector("div.buttons-set button")).click();
        Assert.assertTrue( driver.getTitle().equalsIgnoreCase("My Account"));
        Assert.assertTrue( driver.findElement(By.cssSelector("p.welcome-msg")).getText().equalsIgnoreCase("Welcome, MyName MyLastName!"));
    }

    @Test
    public void checkLogIn(){
        driver.findElement(By.cssSelector(".account-cart-wrapper a")).click();
        driver.findElement(By.cssSelector("div#header-account li:last-child a")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys("example@mail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("qawsed");
        driver.findElement(By.cssSelector("#send2")).click();
        Assert.assertTrue( driver.getTitle().equalsIgnoreCase("My Account"));
        Assert.assertTrue( driver.findElement(By.cssSelector("p.welcome-msg")).getText().equalsIgnoreCase("Welcome, MyName MyLastName!"));

    }

    @After
    public void quitPage() {
        driver.quit();
    }
}
