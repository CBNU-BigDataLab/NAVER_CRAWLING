package org.bigdatacenter.naver_crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP1 on 3/30/2017.
 */
public class NaverBlog {

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public static void main (String args[]) throws IOException {
        String searchQuery = "토마토";
        for(int month = 1; month <= 12; month++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(2016 + "/" + month + "/" + "01"));
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for(int day = 0; day < maxDay; day++){
                String startDate = sdf.format(calendar.getTime());
                calendar.add(Calendar.DATE, 1);
                System.out.println(maxDay + ", START DATE ==> " + startDate + ", END DATE ==> " + startDate);

                Document totalCountDocument = Jsoup.connect("https://section.blog.naver.com/sub/SearchBlog.nhn?type=post&option.keyword=" + searchQuery + "&term=period&option.startDate=" + startDate + "&option.endDate=" + startDate).timeout(1000000).get();
                Element totalCountElement = totalCountDocument.select(".several_post").first();

                Long totalCount = Long.valueOf(totalCountElement.text().replaceAll("포스트 검색결과 ","").replaceAll("건","").replaceAll(" ","").trim());

                Pagination pagination = new Pagination();
                pagination.setLimit(10);
                pagination.setTotalCount(totalCount);

                System.out.println(totalCount);

                //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(searchQuery + ".csv")));
                bufferedWriter.append("Title");
                bufferedWriter.append(NEW_LINE_SEPARATER);

                System.out.println(pagination.totalPages());

                for(int page=1; page<=pagination.totalPages(); page++){
                    pagination.setPage(page);
                    String url = "https://section.blog.naver.com/sub/SearchBlog.nhn?type=post&option.keyword=" + searchQuery + "&term=period&option.startDate=" + startDate + "&option.endDate=" + startDate + "&option.page.currentPage=" + pagination.getPage();
                    System.out.println(url);
                    Document document = Jsoup.connect(url).timeout(1000000).get();
                    Elements elements = document.select(".search_list li");
                    System.out.println("PAGE ==> " + page);
                    for(Element element: elements){
                        Element titleElement = element.select("h5").first();
                        String title = titleElement.text().replaceAll(","," ");
                        System.out.println(" ==> " + title);
                        bufferedWriter.append(title);
                        bufferedWriter.append(NEW_LINE_SEPARATER);
                    }
                }
                //TODO: TO SAVE
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }
    }
}
