import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.time.LocalTime;

public class SupremeBot {
    public static void main(String[] args) throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "D:\\Other\\chromedriver_win32\\chromedriver.exe"); //set path of chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("no-proxy-server");
        options.addArguments("proxy-server='direct://'");
        options.addArguments("proxy-bypass-list=*");
        options.addArguments("disable-infobars");
        options.addArguments("user-data-dir=C:/Users/NAME/AppData/Local/Google/Chrome/User Data");
        options.addArguments("start-maximized");
        options.addExtensions(new File("C:\\Users\\NAME\\Downloads\\Block-image_v1.0.crx")); //disables images

        WebDriver driver = new ChromeDriver(options); //new instance of chrome
        JavascriptExecutor js = (JavascriptExecutor) driver;
        LocalTime time = LocalTime.now();
        LocalTime end = LocalTime.of(11,0,0); //SET TIME OF SUPREME DROP IN MILITARY TIME
        int counter = 1;

        //SIGN INTO GMAIL TO AVOID CAPTCHA
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        WebElement username = driver.findElement(By.id("identifierId"));
        username.sendKeys("USERNAME@gmail.com"); //TURN OFF 2 STEP VERIFICATION
        username.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("PASSWORD");
        password.sendKeys(Keys.ENTER);
        Thread.sleep(5000);//waits to login to Google before starting loop

        while (true){
            if(time.isAfter(end)){
                driver.get("https://www.supremenewyork.com/shop/all/sweatshirts"); //change depending on the item

                driver.findElement(By.xpath("//*[contains(text(), 'NAME OF ITEM OR KEYWORD')]")).click();

                long timer = System.currentTimeMillis();

                Thread.sleep(500);
                driver.findElement(By.cssSelector("input[value='add to cart']")).click(); //clicks add to cart
                Thread.sleep(200);
                driver.get("https://www.supremenewyork.com/checkout");

                WebElement name = driver.findElement(By.id("order_billing_name"));
                js.executeScript("arguments[0].value='Enter name here';", name);

                WebElement email = driver.findElement(By.id("order_email"));
                js.executeScript("arguments[0].value='Enter email here';", email); 

                WebElement phone = driver.findElement(By.id("order_tel"));
                js.executeScript("arguments[0].value='Enter phone number Year';", phone);

                WebElement address = driver.findElement(By.id("bo"));
                js.executeScript("arguments[0].value='Enter address Here';", address);

                WebElement zip = driver.findElement(By.id("order_billing_zip"));
                js.executeScript("arguments[0].value='Enter zip here';", zip);

                WebElement city  =driver.findElement(By.id("order_billing_city"));
                js.executeScript("arguments[0].value='Enter billing city here';", city);

                WebElement state = driver.findElement(By.id("order_billing_state"));
                js.executeScript("arguments[0].value='Enter state here';", state);

                WebElement card = driver.findElement(By.id("nnaerb"));
                js.executeScript("arguments[0].value='Enter card number here';", card);

                WebElement cvv  =driver.findElement(By.id("orcer"));
                js.executeScript("arguments[0].value='Enter cvv here';", cvv);

                WebElement month = driver.findElement(By.id("credit_card_month"));
                js.executeScript("arguments[0].value='Enter credit card month here';", month);

                WebElement select = driver.findElement(By.xpath("//*[@id=\"credit_card_year\"]"));
                js.executeScript("arguments[0].value='Enter credit card year here';", select);

                WebElement iCheckHelper = driver.findElement(By.id("order_terms"));
                Actions builder = new Actions(driver);
                builder.moveToElement(iCheckHelper).click(iCheckHelper);
                builder.perform();

                driver.findElement(By.cssSelector("input[value='process payment']")).click(); //clicks submit payment

                long timerEnd = System.currentTimeMillis() - timer;
                long totalTime = timerEnd / 1000;
                System.out.println(totalTime);

                System.out.println("Purchase Complete");
                Thread.sleep(8000);
                driver.quit();
                break;
            }
            else{
                time = LocalTime.now();
                System.out.println(counter + " Not Time Yet: " + time);
                Thread.sleep(500); //updates every half second
                counter++;
            }
        }
        Thread.sleep(1000);
        System.out.println("DONE");
        // TODO consider website lag
        // TODO shop/new --> //*[@id="container"]/article[1]/div/a/img  --> first item on website
        // TODO test recaptcha --> SUPREME DATA SITEKEY --> 6LeWwRkUAAAAAOBsau7KpuC9AV-6J8mhw4AjC3Xz

    }

}

