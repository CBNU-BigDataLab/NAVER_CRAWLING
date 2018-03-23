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
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage currPage = webClient.getPage("http://gall.dcinside.com/board/view/?id=smartphone&no=4754276&page=1");
        webClient.waitForBackgroundJavaScript(1000);

        System.out.println(currPage.asXml());

        final Document document = Jsoup.parse(currPage.asXml());

        Elements trElements = document.select("#comment_list tr.reply_line");

        for(Element trElement: trElements) {
            System.out.println(trElement.text());
        }
    }
}