package org.bigdatacenter.naver_crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


/**
 * Created by PENHCHET on 11/8/2017.
 */
public class TwitterWebDriver {

    static{
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "warn");
    }

    public static void main(String args[]){
        System.setProperty("webdriver.chrome.driver","C:/chromedriver_win32/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.twitter.com/login");
        WebElement username = driver.findElement(By.className("js-username-field"));
        username.sendKeys("tolapheng99@gmail.com");
        WebElement password = driver.findElement(By.className("js-password-field"));
        password.sendKeys("Apple0123456");

        password.sendKeys(Keys.chord(Keys.ENTER));

        driver.get("https://twitter.com/search?src=typd&q=토마토%20since%3A2010-01-01%20until%3A2010-01-31");

        List<String> texts = new ArrayList<>();

        int i=0;
        while(true){
            if(i>10){
                break;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Document document = Jsoup.parse(driver.getPageSource());

            List<Element> contents = document.select(".js-stream-item");
            for(Element content: contents) {
                String id = content.attr("data-item-id");
                System.out.println("ID ==> " + id);
                System.out.println("Text ==> " + content.select(".tweet-text").first().text());
                System.out.println("Like Count ==> " + document.select("#profile-tweet-action-favorite-count-aria-"+id).first().text());
                System.out.println("Reply Count ==> " + document.select("#profile-tweet-action-reply-count-aria-"+id).first().text());
                System.out.println("Retweet Count ==> " + document.select("#profile-tweet-action-retweet-count-aria-"+id).first().text());
                texts.add(content.select(".tweet-text").first().text() +
                        " - Like Count: " + document.select("#profile-tweet-action-favorite-count-aria-"+id).first().text() +
                        " - Reply Count: " + document.select("#profile-tweet-action-reply-count-aria-"+id).first().text() +
                        " - Retweet Count: " + document.select("#profile-tweet-action-retweet-count-aria-"+id).first().text());
            }

            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight);");
            }
            i++;
        }

        for(String text: texts){
            System.out.println(" ==> " + text);
        }
    }
}
