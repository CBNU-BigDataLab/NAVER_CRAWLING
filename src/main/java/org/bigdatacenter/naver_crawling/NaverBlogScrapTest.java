package org.bigdatacenter.naver_crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;

/**
 * Created by HP1 on 10/16/2017.
 */
public class NaverBlogScrapTest {

    public void test(){
    }

    //https://docs.google.com/document/d/12ThaiKoQA89-KYd4_YVs_EBcgJJ1Lx_Z9ThOERF7YfA/edit

    public static void main(String args[]) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:/chromedriver_win32/chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        driver.get("http://blog.naver.com/PostView.nhn?blogId=ej930905&logNo=221115336273&redirect=Dlog&widgetTypeCall=true&directAccess=false");
        System.out.println(driver.getPageSource());
        //WebElement element = driver.findElement(By.id("widget-stat"));
        List<WebElement> listElements = driver.findElements(By.xpath("//div[@id='widget-stat']/div/ul/li"));

        List<WebElement> imageElements = driver.findElements(By.className("_photoImage"));

        for(WebElement list: listElements){
            System.out.println(list.getText());
        }

        for(WebElement image: imageElements){
            System.out.println(image.getAttribute("src"));
        }

        System.out.println("Number of Images  " + imageElements.size());
//        System.out.println(element.getText());
        //Thread.sleep(1000);
        //element.sendKeys("shahash");
        //element.submit();
        //driver.quit();
    }
}