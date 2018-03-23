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
public class Main {

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public static void main (String args[]) throws IOException {
        String searchQuery = "세계3대광천수";
        Document totalCountDocument = Jsoup.connect("https://search.naver.com/search.naver?sm=tab_hty.top&where=post&oquery=" + searchQuery + "&ie=utf8&query=" + searchQuery).timeout(1000000).get();
        Element totalCountElement = totalCountDocument.select(".title_num").first();

        Long totalCount = Long.valueOf(totalCountElement.text().replaceAll("1-10 / ","").replaceAll("건","").replaceAll(",",""));

        Pagination pagination = new Pagination();
        pagination.setLimit(10);
        pagination.setTotalCount(totalCount);

        System.out.println(totalCount);

        //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(searchQuery + ".csv")));
        bufferedWriter.append("Title,Date");
        bufferedWriter.append(NEW_LINE_SEPARATER);

        for(int page=1; page<=pagination.totalPages(); page++){
            pagination.setPage(page);
            String url = "https://search.naver.com/search.naver?where=post&sm=tab_pge&query=" + searchQuery + "&start=" + (pagination.offset() + 1);
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
