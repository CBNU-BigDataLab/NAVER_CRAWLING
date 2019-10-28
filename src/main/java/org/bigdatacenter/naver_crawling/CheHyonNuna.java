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
public class CheHyonNuna {

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public static void main (String args[]) throws IOException {
        String searchQuery = "세계3대광천수";
        Document totalCountDocument = Jsoup.connect("https://www.data.go.kr/search/index.do?index=DATA&query=&searchField=IN2_INTEGRATION&dataFF=&openFF=&insttFF=&brmFF=&useFF=&tagFF=&extFF=&coreFF=&insttDtlFF=&coreDataPk=&returnFileds=FF_OPENAPI_SERVICE_TY%2FFF_DATA_SERVICE_TY%2FFF_INSTT%2FFF_BRM_N_CD%2FFF_DATA_FORMAT%2FFF_USE_SCOPE%2FIN2_INDEX%2FDOC_ID%2FDOC_TITLE%2FDOC_CONTENT%2FIN2_INTEGRATION%2FBRM_N_CD%2FBRM_N_NM%2FINSTT_DTL_NM%2FC_DATE_DISPLAY%2FU_DATE_DISPLAY%2FDATE_TO_DISPLAY%2FDOWNLOAD_COUNT%2FREQUEST_COUNT%2FVIEW_COUNT%2FDATA_FORMAT%2FSUB_CATEGORY%2FALIAS%2FEXCEL_ROW_INFO%2FBOARD_VIEW_DATE%2FHBRD_SE%2FCOMPNY_NM%2FBOARD_VIEW_WRITER%2FAUSPC_INSTT_NM%2FPROGRS_STTUS_NM%2FSVC_NM%2FFAQ_CODE%2FAUSPC_INSTT_NM%2FQNA_PROCESS_STTUS_NM%2FQNA_TY_CODE_NM%2FINSTT_DTL%2FPRBLM_SE_NM%2FDOC_TITLE_QUERY%2FPRCUSE_CASE_NM%2FFF_CORE_DATA_AT&groupByFields=FF_OPENAPI_SERVICE_TY%2FFF_DATA_SERVICE_TY%2FFF_INSTT%2FFF_BRM_N_CD%2FFF_DATA_FORMAT%2FFF_USE_SCOPE%2FFF_SEARCH_KEYWORD%2FFF_CORE_DATA_AT&sortType=VIEW_COUNT&sortOrder=DESC&currentPage=1&countPerPage=10&leftSnb=&exceptionTag=&isAddCameo=Y").timeout(1000000).post();
        Element totalCountElement = totalCountDocument.select(".count").first();

        Long totalCount = Long.valueOf(totalCountElement.text().replaceAll(",",""));

        Pagination pagination = new Pagination();
        pagination.setLimit(10);
        pagination.setTotalCount(totalCount);

        System.out.println(totalCount);

        for(int page=1; page<=pagination.totalPages(); page++){
            pagination.setPage(page);
            String url = "https://www.data.go.kr/search/index.do?index=DATA&query=&searchField=IN2_INTEGRATION&dataFF=&openFF=&insttFF=&brmFF=&useFF=&tagFF=&extFF=&coreFF=&insttDtlFF=&coreDataPk=&returnFileds=FF_OPENAPI_SERVICE_TY%2FFF_DATA_SERVICE_TY%2FFF_INSTT%2FFF_BRM_N_CD%2FFF_DATA_FORMAT%2FFF_USE_SCOPE%2FIN2_INDEX%2FDOC_ID%2FDOC_TITLE%2FDOC_CONTENT%2FIN2_INTEGRATION%2FBRM_N_CD%2FBRM_N_NM%2FINSTT_DTL_NM%2FC_DATE_DISPLAY%2FU_DATE_DISPLAY%2FDATE_TO_DISPLAY%2FDOWNLOAD_COUNT%2FREQUEST_COUNT%2FVIEW_COUNT%2FDATA_FORMAT%2FSUB_CATEGORY%2FALIAS%2FEXCEL_ROW_INFO%2FBOARD_VIEW_DATE%2FHBRD_SE%2FCOMPNY_NM%2FBOARD_VIEW_WRITER%2FAUSPC_INSTT_NM%2FPROGRS_STTUS_NM%2FSVC_NM%2FFAQ_CODE%2FAUSPC_INSTT_NM%2FQNA_PROCESS_STTUS_NM%2FQNA_TY_CODE_NM%2FINSTT_DTL%2FPRBLM_SE_NM%2FDOC_TITLE_QUERY%2FPRCUSE_CASE_NM%2FFF_CORE_DATA_AT&groupByFields=FF_OPENAPI_SERVICE_TY%2FFF_DATA_SERVICE_TY%2FFF_INSTT%2FFF_BRM_N_CD%2FFF_DATA_FORMAT%2FFF_USE_SCOPE%2FFF_SEARCH_KEYWORD%2FFF_CORE_DATA_AT&sortType=VIEW_COUNT&sortOrder=DESC&countPerPage=10&leftSnb=&exceptionTag=&isAddCameo=Y&currentPage=" + page;
            System.out.println(url);
            Document document = Jsoup.connect(url).timeout(1000000).get();
            Elements elements = document.select("#file-list-wrapper .data-title");
            System.out.println("PAGE ==> " + page);
            for(Element element: elements){
                Element titleElement = element.select("a").first();
                String title = titleElement.text();
                System.out.println(title);
            }
        }

    }
}
