package org.bigdatacenter.naver_crawling.restcontrollers;

import org.bigdatacenter.naver_crawling.Pagination;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP1 on 4/3/2017.
 */
@RestController
public class NaverScrappingRestController {

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    @RequestMapping(value="/NAVER", method = RequestMethod.POST)
    public String scrappingBySearchKeywordAndStartDateAndEndDate(@RequestParam(value= "SearchKeyword", defaultValue = "초정광천수") String searchQuery,
                                                                 @RequestParam(value= "StartDate", defaultValue = "20070101") String startDate,
                                                                 @RequestParam(value= "EndDate", defaultValue = "20101231") String endDate){
        try{
            Document totalCountDocument = Jsoup.connect("https://search.naver.com/search.naver?sm=tab_hty.top&where=post&date_from="+ startDate +"&date_to=" + endDate + "&date_option=8&oquery=" + searchQuery + "&ie=utf8&query=" + searchQuery).timeout(1000000).get();
            Element totalCountElement = totalCountDocument.select(".title_num").first();

            Long totalCount = Long.valueOf(totalCountElement.text().replaceAll("1-10 / ","").replaceAll("건","").replaceAll(",",""));

            Pagination pagination = new Pagination();
            pagination.setLimit(10);
            pagination.setTotalCount(totalCount);

            System.out.println(totalCount);

            //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(searchQuery + "_" +  startDate + "_" + endDate +".csv")));
            bufferedWriter.append("Title,Date");
            bufferedWriter.append(NEW_LINE_SEPARATER);

            for(int page=1; page<=pagination.totalPages(); page++){
                pagination.setPage(page);
                String url = "https://search.naver.com/search.naver?where=post&sm=tab_pge&query=" + searchQuery + "&st=sim&date_from=" + startDate + "&date_to=" + endDate + "&date_option=8&dup_remove=1&post_blogurl=&post_blogurl_without=&srchby=all&nso=&ie=utf8&start=" + (pagination.offset() + 1);
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
            return "SUCCESSFULLY";
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "FAILURE";
    }

    @RequestMapping(value="/NAVER-SCRAP-BY-YEAR-AND-KEYWORD", method = RequestMethod.POST)
    public String scrappingBySearchKeywordAndByYear(@RequestParam(value= "SearchKeyword", defaultValue = "초정광천수") String searchQuery,
                                                                 @RequestParam(value= "year", defaultValue = "2007") int year){
        try{

            //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(searchQuery + "_" + year + "0101_" + year + "1231.csv")));
            bufferedWriter.append("Title,Date");
            bufferedWriter.append(NEW_LINE_SEPARATER);

            for (int month = 1; month <= 12; month++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(year + "/" + month + "/01"));

                DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String startDate = sdf.format(calendar.getTime());
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.DATE, -1);

                Date lastDayOfMonth = calendar.getTime();
                String endDate = sdf.format(lastDayOfMonth);

                Document totalCountDocument = Jsoup.connect("https://search.naver.com/search.naver?sm=tab_hty.top&where=post&date_from=" + startDate + "&date_to=" + endDate + "&date_option=8&oquery=" + searchQuery + "&ie=utf8&query=" + searchQuery).timeout(1000000).get();
                Element totalCountElement = totalCountDocument.select(".title_num").first();

                Long totalCount = Long.valueOf(totalCountElement.text().replaceAll("1-10 / ", "").replaceAll("건", "").replaceAll(",", ""));

                Pagination pagination = new Pagination();
                pagination.setLimit(10);
                pagination.setTotalCount(totalCount);

                System.out.println(totalCount);



                for (int page = 1; page <= pagination.totalPages(); page++) {
                    pagination.setPage(page);
                    String url = "https://search.naver.com/search.naver?where=post&sm=tab_pge&query=" + searchQuery + "&st=sim&date_from=" + startDate + "&date_to=" + endDate + "&date_option=8&dup_remove=1&post_blogurl=&post_blogurl_without=&srchby=all&nso=&ie=utf8&start=" + (pagination.offset() + 1);
                    System.out.println(url);
                    Document document = Jsoup.connect(url).timeout(1000000).get();
                    Elements elements = document.select("#elThumbnailResultArea li");
                    System.out.println("PAGE ==> " + page);
                    for (Element element : elements) {
                        Element titleElement = element.select("._sp_each_title").first();
                        String title = titleElement.text();
                        String date = element.select(".txt_inline").text().replaceAll(". 보내기", "");
                        if (!"".equals(titleElement.attr("title"))) {
                            title = titleElement.attr("title");
                        }
                        System.out.println(" ==> " + title.replaceAll(",", " ") + " ==> " + date);
                        bufferedWriter.append(title.replaceAll(",", " "));
                        bufferedWriter.append(COMMA_DELIMITER);
                        bufferedWriter.append(date);
                        bufferedWriter.append(NEW_LINE_SEPARATER);
                    }
                }
            }
            //TODO: TO SAVE
            bufferedWriter.flush();
            bufferedWriter.close();
            return "SUCCESSFULLY";
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "FAILURE";

    }
}
