package org.bigdatacenter.dcinside;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by HP1 on 2/27/2018.
 */
public class GallDcinside {

    public static void main(String args[]) throws IOException {
//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage currPage = webClient.getPage("http://210.99.67.17/ibmcognos/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=storeID(%27i5001f2a124a443aaae4b0cfbb276f1d3%27)&CAMUsername=GUEST1&CAMPassword=GUEST1&fbclid=IwAR30KLwRfIXM4O8XWyiLHRQVUhVFsBT-YLr9yBxuLwxSTC6aOlL0XiGnWjo#");
        webClient.waitForBackgroundJavaScript(1000);

        System.out.println(currPage.asXml());

        final Document document = Jsoup.parse(currPage.asXml());

//        System.out.println(document.html());

        Elements options = document.select("#PRMT_SV_N1634F700x17579AF0_NS_ option");
        for(Element option: options){
            System.out.println("1" + option.text());
        }
    }
}