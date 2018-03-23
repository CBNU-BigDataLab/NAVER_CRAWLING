package org.bigdatacenter.naver_crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by HP1 on 11/13/2017.
 */
public class FindArtInfoMain {
    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public static void main(String args[]) throws IOException {
        System.setProperty("webdriver.chrome.driver","C:/chromedriver_win32/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        //ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.get("http://www.findartinfo.com/english.html");
        WebElement username = driver.findElement(By.id("Email"));
        username.sendKeys("darapenhchet@gmail.com");
        WebElement password = driver.findElement(By.id("PassWord"));
        password.sendKeys("01031992rupp!@#$");

        password.sendKeys(Keys.chord(Keys.ENTER));

        //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("artists.csv"));
        bufferedWriter.append("Artist Name");
        bufferedWriter.append(NEW_LINE_SEPARATER);
        for(int i=1;i<=14601;i++){
            driver.get("http://www.findartinfo.com/english/Artists/MomResult?artistName=&born=&die=&countryId=&pageIndex=" + i);
            WebElement table6 = driver.findElement(By.id("table6"));
            List<WebElement> webElements = table6.findElements(By.cssSelector("tr[onmouseout]"));
            System.out.println("SIZE ==> " + webElements.size());
            for(WebElement webElement: webElements){
                try {
                    System.out.println("====> " + webElement.findElement(By.cssSelector("td:first-child")).getText());
                    bufferedWriter.append(webElement.findElement(By.cssSelector("td:nth-child(1)")).getText());
                    bufferedWriter.append(COMMA_DELIMITER);
                    bufferedWriter.append(webElement.findElement(By.cssSelector("td:nth-child(2)")).getText());
                    bufferedWriter.append(COMMA_DELIMITER);
                    bufferedWriter.append(webElement.findElement(By.cssSelector("td:nth-child(3)")).getText());
                    bufferedWriter.append(COMMA_DELIMITER);
                    bufferedWriter.append(webElement.findElement(By.cssSelector("td:nth-child(4)")).getText());
                    bufferedWriter.append(NEW_LINE_SEPARATER);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        //TODO: TO SAVE
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
