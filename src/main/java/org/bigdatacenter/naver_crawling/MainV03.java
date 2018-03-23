package org.bigdatacenter.naver_crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by HP1 on 3/30/2017.
 */
public class MainV03 {

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public static void main (String args[]) throws IOException {
        String files[] = {
          "초정스파텔",
          "세계3대광천수",
          "초정약수",
          "초정약수원탕",
          "초정약수축제",
          "초정탄산수",
          "초정광천수",
          "초정미라클",
        };
        String searchQuery = "초정광천수";
        Document totalCountDocument = Jsoup.connect("https://search.naver.com/search.naver?sm=tab_hty.top&where=post&date_from=20130101&date_to=20170403&date_option=8&oquery=" + searchQuery + "&ie=utf8&query=" + searchQuery).timeout(1000000).get();
        Element totalCountElement = totalCountDocument.select(".title_num").first();

        Long totalCount = Long.valueOf(totalCountElement.text().replaceAll("1-10 / ","").replaceAll("건","").replaceAll(",",""));

        Pagination pagination = new Pagination();
        pagination.setLimit(10);
        pagination.setTotalCount(totalCount);

        System.out.println(totalCount);

        //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(searchQuery + "20130101_20170403.csv")));
        bufferedWriter.append("Title,Date");
        bufferedWriter.append(NEW_LINE_SEPARATER);

        for(int page=1; page<=pagination.totalPages(); page++){
            pagination.setPage(page);
            String url = "https://search.naver.com/search.naver?where=post&sm=tab_pge&query=" + searchQuery + "&st=sim&date_from=20130101&date_to=20170403&date_option=8&dup_remove=1&post_blogurl=&post_blogurl_without=&srchby=all&nso=&ie=utf8&start=" + (pagination.offset() + 1);
            System.out.println(url);
            Document document = Jsoup.connect(url).timeout(1000000).get();
            Elements elements = document.select("#elThumbnailResultArea li");
            System.out.println("PAGE ==> " + page);
            for(Element element: elements){
                Element titleElement = element.select("._sp_each_title").first();
                String title = titleElement.text();
                String date =  element.select(".txt_inline").text().replaceAll(". 보내기", "");
                if(!"".equals(titleElement.attr("title"))){
                    title = titleElement.attr("title");
                }
                System.out.println(" ==> " + title.replaceAll(","," ") + " ==> " + date);
                bufferedWriter.append(title.replaceAll(","," "));
                bufferedWriter.append(COMMA_DELIMITER);
                bufferedWriter.append(date);
                bufferedWriter.append(NEW_LINE_SEPARATER);
            }
        }
        //TODO: TO SAVE
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
