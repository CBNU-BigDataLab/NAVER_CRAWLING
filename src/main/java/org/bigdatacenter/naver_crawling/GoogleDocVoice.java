package org.bigdatacenter.naver_crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class GoogleDocVoice {

    public static void main(String args[]){
        System.setProperty("webdriver.chrome.driver","C:/chromedriver_win32/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        //ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://docs.google.com/document/d/1EQehm54VHPTFwixAbZXRttBCWiKwLDJ3ttFRx7h5Zwo/edit");

        // driver.get("https://docs.google.com/document/d/12ThaiKoQA89-KYd4_YVs_EBcgJJ1Lx_Z9ThOERF7YfA/edit");

        //TODO: TO LOGIN WITH GMAIL TA PU
        //driver.findElement(By.id("identifierId")).sendKeys("YOUR EMAIL");
        driver.findElement(By.id("identifierId")).sendKeys("####EMAIL####");
        driver.findElement(By.id("identifierNext")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //what is "//input[@name='password']")) SELECT INPUT name='password' for sendKey Password tov
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(password));
        //password.sendKeys("YOUR EMAIL PASSWORD");
        password.sendKeys("####PASSWORD####");
        driver.findElement(By.id("passwordNext")).click();

        password.sendKeys(Keys.chord(Keys.ENTER));

        //Then invoke method again for your second request(I am not try this code maybe you need to create new driver object)

        /// ERROR THIS PLACE IT DOESN'T OPEN THIS LINK OPEN BY SELF TIC TOV AND TRY TO FIND THIS ERROR PONG WHY???
        //driver.get("https://docs.google.com/document/d/12ThaiKoQA89-KYd4_YVs_EBcgJJ1Lx_Z9ThOERF7YfA/edit");
        //https://docs.google.com/document/d/1XIiiJwBsWYqPo-fzVPRGPKVgn-e5b8lR2RAhXvqWjUI/edit


        //driver.findElement(By.className("docs-homescreen-templates-templateview-preview docs-homescreen-templates-templateview-preview-showcase")).click();

        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.TAB));
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.TAB));
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.TAB));
        // driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.ENTER));

//        driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, "s"));


        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        List<WebElement> pages = driver.findElements(By.className("kix-appview-editor"));

        System.out.println("PAGE COUNT ===> " + pages.size());

        int pageNo = 1;

        for(WebElement page: pages){
            System.out.println(" ===> pageNo=" + pageNo + " ======= " + page.getText());
            pageNo++;
        }




//        while(true){
//            try {
//                if(driver.findElement(By.cssSelector("docs-mic-control-recording")) == null){
//                    driver.findElement(By.cssSelector("docs-mic-control")).click();
//                }
//            }catch(Exception ex){
//                ex.printStackTrace();
//                driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, "s"));
//            }
//        }
    }
}
