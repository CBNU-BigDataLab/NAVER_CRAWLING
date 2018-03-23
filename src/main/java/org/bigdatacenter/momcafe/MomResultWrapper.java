package org.bigdatacenter.momcafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by HP1 on 2/26/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MomResultWrapper {
    private boolean hasPrev;
    private Long articleid;
    private int countPerPage;
    private Long clubid;
    private boolean hasNext;
    private boolean secret;
    private String fontCSSURL;
    private int totalCount;
    private int currentPage;
    private List<MomComment> list;
    private int currentSize;
    private int commentCount;

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public Long getClubid() {
        return clubid;
    }

    public void setClubid(Long clubid) {
        this.clubid = clubid;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public String getFontCSSURL() {
        return fontCSSURL;
    }

    public void setFontCSSURL(String fontCSSURL) {
        this.fontCSSURL = fontCSSURL;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<MomComment> getList() {
        return list;
    }

    public void setList(List<MomComment> list) {
        this.list = list;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "MomResultWrapper{" +
                "hasPrev=" + hasPrev +
                ", articleid=" + articleid +
                ", countPerPage=" + countPerPage +
                ", clubid=" + clubid +
                ", hasNext=" + hasNext +
                ", secret=" + secret +
                ", fontCSSURL='" + fontCSSURL + '\'' +
                ", totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", list=" + list +
                ", currentSize=" + currentSize +
                ", commentCount=" + commentCount +
                '}';
    }
}
