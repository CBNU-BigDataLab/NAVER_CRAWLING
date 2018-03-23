package org.bigdatacenter.momcafe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP1 on 2/26/2018.
 */
public class MomCafe {

    public static void main(String args[]) throws Exception {

        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        webClient.waitForBackgroundJavaScript(5000);

        HtmlPage currPage = webClient.getPage("https://nid.naver.com/nidlogin.login?url=https%3A%2F%2Fwww.naver.com");

        HtmlForm form = currPage.getFormByName("frmNIDLogin");
        HtmlTextInput inputId = form.getInputByName("id");
        HtmlPasswordInput inputPw = (HtmlPasswordInput)form.getInputByName("pw");
        HtmlInput button = (HtmlInput)form
                .getByXPath("//*[@id=\"frmNIDLogin\"]/fieldset/input").get(0);

        inputId.setValueAttribute("#####ID#####");
        inputPw.setValueAttribute("#####PASSWORD######");
        currPage = (HtmlPage)button.click();

        if(currPage.asText().contains("Naver Sign in")) {
            System.out.println("cannot login with the id and pw");
            return;
        } else {
            System.out.println("Successfully");
        }

        Map<String, String> cookies = new HashMap<>();
        CookieManager cookieManager = webClient.getCookieManager();
        java.util.Set<Cookie> cookieSet = cookieManager.getCookies();
        for(Cookie c : cookieSet) {
            cookies.put(c.getName(), c.getValue());
        }

        System.out.println(cookies);


//        Map<String, String> cookies = loginForm.cookies();
//        cookies.put("ASID", "d273b6a50000015f959db96a0000004f");
//        cookies.put("JSESSIONID", "FB815BC234EFD4E03FDFE62ACC5CD70F");
//        cookies.put("NIB", "2Zp4tfAiFAtScsTglzAw");
//        cookies.put("NID_AUT", "taZSpL0sgv7qVKrXHkMgorIVHUrwUiNM6UB9WA8LUcKXHXHQDXldAUlAjiC7AoJk");
//        cookies.put("NID_SES", "AAABkpxqroiDdwWvaZv9Qb/sbPgbD6OnEOIAB7x5druatgvJ/ypsoxOd+kExlFD4aarixP8oY/zZeyGek8tmVaMURGSTqOGz40XE5i7k/VGHmmOJ3wsyDnrUmEPkbUAqXCXwFBtNqddv13rJXfD+BCMiu+vrjdX+wKe7Ge3a9KtqdT/tQLzHmt33NbebgcLjCtwjG+XdYyOll6LnKOmWCIf952zx75Jqj9xZK5lEt3b+dA9tgZLHm5DMcyqt8a3F3UXqECp82GjF2FyaRh33Akg1OCxbJpYQ42W8Oydulm8XzWa6Dd3Hm14RuKegaNxK66L+Nm3Zgbw1hYLvM/U4G0eZJes4NnS83kC9bOJDLeCZMwPJBSNbbzzc0piL0gGaRzNq8lK7xxUiWfWLx/XiACEkxK1gJZBi7Qfumxm2cpqzwZT917rIOLIiK1gO4+cyf7+m27Q3Ebr9yobbOYWqFQfmxMkpD/BbZJk6zvhgaM7MPPWRKcFO1YHbWm72fGG3+Hgct2NEuWzVNepeYKaYgVsGCahUF8OPmBJthg/kU8GLeIrj");
//        cookies.put("NNB", "WKJHMUM6GTOFS");
//        cookies.put("gnbFav", "%7B%22gnbFav%22%3A%5B%22game%22%2C%22weather%22%2C%22shopping%22%5D%7D");
//        cookies.put("nci4", "c0f413332d6705b7e50b4c5b56a7d9be0c51a356366cab509c9ea570ce9f12c51d7232ec4b7b9cfb542060d0105d33ea6c1bf60b91a6b172dd1acf633a62d7317e4f5f5c59465e58545059645f5f425c6767426552626e61785f6c24565972526346404f6a4d7a3446496145723d4e3e1a3d0b48393413300139212c0b2a1b5457585a5a5c5f5e101f3c1b28696f046e6a6f1f6c731c697727");
//        cookies.put("ncmc4", "86b255756b2143f1a34d0a1d10e397e75249b4570224cb30ea1e13cb7c79bc45ad548061838f510c74cebb55ef77f826dfc9c5cd29367f661cde7d09");
//        cookies.put("ncu", "80bb5569337a14a6ff1156414c65796fc5e514888e74");
//        cookies.put("ncvc2", "7e1ee6c296a0a21339c7b988b14c2959f3bd5cb9efbb5a8119a68651e8c22ce527c708d30b29bf95e27b2ff3409d12cc35231d02e1cdebc1f2378f4e8fbe0cc1a8a4a7b3a9b5a8aea7a7a1a7a2ab43");
//        cookies.put("ncvid", "#vid#_210.115.182.165553W");
//        cookies.put("nid_buk", "WKJHMUM6GTOFS");
//        cookies.put("nid_enctp", "1");
//        cookies.put("nid_inf", "693126161");
////        cookies.put("nid_sec", "hsh+w4+y7mvIeduvEtxfrScPGSQo/2DTnugL9s+PWTgTlgJyXKb+fa8uq5RQjv7V");
//        cookies.put("nid_slevel", "1");
//        cookies.put("nx_ssl", "2");
//        cookies.put("personaconmain|kely0322", "CBAE72AEA54971838C10A83824CD9F50E90F7B7F72670FCDAEE1AAB42C735304");
//        cookies.put("personacon|kely0322", "32609A2D54AC1C335D6E7D297182182152D4EBFA05532EB0FE9CF2938079337F8573BEE04DD64ED9AEC3CE1567CF805A40F8D069EC7C6DF7B3642537D5E13B2FDF742CDD54489AFBB104A8BFB1C0566E18767A543203FB65");

        Document menuDocument = Jsoup.connect("http://cafe.naver.com/imsanbu")
                .cookies(cookies)
                .post();

        Elements menuElements = menuDocument.select("ul#group146 li");

        for(Element menuElement: menuElements) {
            String url = menuElement.select("a").attr("href");
            try {
                Map<String, String> params = new HashMap<String, String>();
                String[] urlParts = url.split("\\?");
                if (urlParts.length > 1) {
                    String query = urlParts[1];
                    for (String param : query.split("&")) {
                        String[] pair = param.split("=");
                        String key = URLDecoder.decode(pair[0], "UTF-8");
                        String value = "";
                        if (pair.length > 1) {
                            value = URLDecoder.decode(pair[1], "UTF-8");
                        }
                        params.put(key, value);
                    }
                }

                String clubID = params.get("search.clubid");

                String menuID = params.get("search.menuid");

                for(int page=1; page <= 1000; page ++) {
                    System.out.println("=======> http://cafe.naver.com/ArticleList.nhn?search.clubid=" + clubID + "&search.menuid=" + menuID + "&search.boardtype=L&search.page=" + page);

                    Document documentList = Jsoup.connect("http://cafe.naver.com/ArticleList.nhn?search.clubid=" + clubID + "&search.menuid=" + menuID + "&search.boardtype=L&search.page=" + page)
                            .cookies(cookies)
                            .post();

                    //System.out.println(documentList.select("form[name=ArticleList]").html());

                    Elements trElements = documentList.select("form[name=ArticleList] tr[align=center]");

                    int index = 1;
                    for(Element trElement: trElements) {
                        String articleID = trElement.select(".list-count").text();
                        System.out.println("==========================================");
                        System.out.println("LOADING =====================> " + index++);
                        System.out.println("==========================================");
                        System.out.println(trElement.text());
                        System.out.println(articleID);

                        System.out.println("http://cafe.naver.com" + trElement.select(".board-list a").attr("href"));

                        Document document1 = Jsoup.connect("http://cafe.naver.com" + trElement.select(".board-list a").attr("href"))
                                .cookies(cookies)
                                .post();

                        System.out.println(document1.select(".inbox .tbody").text());

                        Document document = Jsoup.connect("http://cafe.naver.com/CommentView.nhn?search.clubid=" + clubID + "&search.menuid=" + menuID + "&search.articleid=" + articleID + "&search.lastpageview=true&lcs=Y")
                                .cookies(cookies)
                                .post();

                        System.out.println(document.body().text());

                        ObjectMapper mapper = new ObjectMapper();

                        String commentJSON = document.body().text();

                        try{
                            MomResult result = mapper.readValue(commentJSON.trim(), MomResult.class);

                            System.out.println(result.getResult().getList());
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }
                }
            } catch (UnsupportedEncodingException ex) {
                throw new AssertionError(ex);
            }
        }
    }
}