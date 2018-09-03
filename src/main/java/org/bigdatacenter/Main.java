package org.bigdatacenter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:/chromedriver_win32/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://auto.naver.com/company/main.nhn?mnfcoNo=48&importYn=N");
        System.out.println(driver.getPageSource());

        List<WebElement> listElements = driver.findElements(By.xpath("//*[@id=\"modelListArea\"]/ul/li"));

        for(WebElement list: listElements){
            System.out.println(list.getText());
        }

        System.out.println("COUNT PAGE 1 ==> " + listElements.size());

        driver.get("https://auto.naver.com/company/main.nhn?mnfcoNo=48&importYn=N&page=2");
        System.out.println(driver.getPageSource());

        List<WebElement> listElements2 = driver.findElements(By.xpath("//*[@id=\"modelListArea\"]/ul/li"));

        for(WebElement list: listElements2){
            System.out.println(list.getText());
        }

        System.out.println("COUNT PAGE 2  ==> " + listElements.size());

        driver.get("https://auto.naver.com/company/main.nhn?mnfcoNo=48&importYn=N&page=3");
        System.out.println(driver.getPageSource());

        List<WebElement> listElements3 = driver.findElements(By.xpath("//*[@id=\"modelListArea\"]/ul/li"));

        for(WebElement list: listElements3){
            System.out.println(list.getText());
        }

        System.out.println("COUNT PAGE 3==> " + listElements.size());
    }
}
