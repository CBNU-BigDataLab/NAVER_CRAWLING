package org.bigdatacenter.naver_crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        System.setProperty("webdriver.chrome.driver","/Users/penhchet/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("http://210.99.67.17/ibmcognos/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=storeID(%27i5001f2a124a443aaae4b0cfbb276f1d3%27)&CAMUsername=GUEST1&CAMPassword=GUEST1&fbclid=IwAR30KLwRfIXM4O8XWyiLHRQVUhVFsBT-YLr9yBxuLwxSTC6aOlL0XiGnWjo#");
        System.out.println(driver.getPageSource());
//        WebElement element = ((ChromeDriver) driver).findElementById("PRMT_SV_CONTAINER_N1634F700x17579AF0_NS_");

        WebElement myDynamicElement = (new WebDriverWait(driver, 1000))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("PRMT_SV_CONTAINER_N1634F700x17579AF0_NS_")));

        System.out.println(myDynamicElement.getText());


    }
}