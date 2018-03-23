package org.bigdatacenter.coupang;

import org.bigdatacenter.naver_crawling.Pagination;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CoupangMain {

    public static void main(String args[]) throws IOException {


        Document document = Jsoup.connect("http://www.coupang.com/np/search?q=계절+가전제품&sorter=scoreDesc&listSize=72").get();

        Elements productElements = document.select("#productList li a");

        Set<String> productDetailsColumns = new HashSet<>();

        for(Element productElement: productElements){
            try {
                Product product = new Product();
                product.setProductId(Long.valueOf(productElement.attr("data-product-id")));
                product.setItemId(Long.valueOf(productElement.attr("data-item-id")));
                product.setVendorItemId(Long.valueOf(productElement.attr("data-vendor-item-id")));
                System.out.println(product);
                Document productDetailsDocument = Jsoup.connect(product.getProductDetailsPageURL()).get();
                System.out.println(productDetailsDocument.select(".prod-buy-header__title").first().text());

                Element productDetailsElement = Jsoup.connect(product.getProductDetailsSectionURL())
                            .get().select(".product-item__detail").first();

                Elements thProductDetailsElements = productDetailsElement.select("tbody th");

                for(Element th: thProductDetailsElements){
                    System.out.println(th.text());
                    productDetailsColumns.add(th.text());
                }

                Document totalReviewCountDocument = Jsoup.connect("http://www.coupang.com/vp/product/reviews?productId=" + product.getProductId() + "&page=1&size=100&sortBy=ORDER_SCORE_ASC&ratings=&q=&ratingSummary=true").get();
                Long totalCount = Long.valueOf(totalReviewCountDocument.select(".js_reviewArticleTotalCountHiddenValue").attr("data-review-total-count"));
                //System.out.println(totalReviewCountDocument.select(".js_reviewArticleTotalCountHiddenValue").attr("data-review-total-count"));

                Pagination pagination = new Pagination();
                pagination.setLimit(100);
                pagination.setTotalCount(totalCount);

                System.out.println("REVIEWS TOTAL PAGES ==> " +pagination.totalPages());

                for (int page = 1; page <= pagination.totalPages(); page++) {
                    String reviewURL = "http://www.coupang.com/vp/product/reviews?productId=" + product.getProductId() + "&page=" + page + "&size=100&sortBy=ORDER_SCORE_ASC&ratings=&q=&ratingSummary=true";
                    System.out.println(reviewURL);
                    Document reviewDocument = Jsoup.connect(reviewURL).get();
                    Elements reviewArticles = reviewDocument.select("article.js_reviewArticleReviewList");

                    for(Element reviewArticle: reviewArticles) {
                        Review review = new Review();
                        review.setReviewer(reviewArticle.select(".sdp-review__article__list__info__user").text());
                        review.setRating(Double.valueOf(reviewArticle.select(".sdp-review__article__list__info__product-info__star-orange").attr("data-rating")));
                        review.setReviewDate(reviewArticle.select(".sdp-review__article__list__info__product-info__reg-date").text());
                        review.setProductName(reviewArticle.select(".sdp-review__article__list__info__product-info__name").text());
                        review.setReviewContent(reviewArticle.select(".sdp-review__article__list__review__content").text());
                        System.out.println(review);
                    }
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        System.out.println("COLUMNS NAME ==> ");
        System.out.println(productDetailsColumns);



//        Document documentItem = Jsoup.connect("http://www.coupang.com/vp/products/8391626?itemId=36580309&vendorItemId=3084099067&q=계절+가전제품&itemsCount=36&searchId=151189fd6f2a41e6a2316399587e7b79&rank=1\n").get();
//
//        Element element = documentItem.select(".prod-buy-header__title").first();
//
//
//        Element productDetailsElement = Jsoup.connect("http://www.coupang.com/vp/products/8391626/vendor-items/3084099067?isFixedVendorItem=true&type=sdp")
//                .get().select(".product-item__detail").first();
//
//        Elements thProductDetailsElements = productDetailsElement.select("tbody th");
//
//        for(Element th: thProductDetailsElements){
//            System.out.println(th.text());
//        }

//        System.out.println(element.text());

//        Document reviewDocument = Jsoup.connect("http://www.coupang.com/vp/product/reviews?productId=22724933&page=1&size=100&sortBy=ORDER_SCORE_ASC&ratings=&q=&ratingSummary=true").get();
//
//        System.out.println(reviewDocument.select(".js_reviewArticleTotalCountHiddenValue").attr("data-review-total-count"));

//        System.out.println(reviewDocument.select("article.js_reviewArticleReviewList").size());
    }
}
