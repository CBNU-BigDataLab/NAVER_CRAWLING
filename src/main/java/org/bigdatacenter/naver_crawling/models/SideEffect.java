package org.bigdatacenter.naver_crawling.models;

/**
 * Created by HP1 on 10/13/2017.
 */
public class SideEffect {
    private String term;
    private String ipx;
    private String type;
    private String termUI;
    private String ID;
    private String sider;
    private String ATC_code;
    private String notFound;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getIpx() {
        return ipx;
    }

    public void setIpx(String ipx) {
        this.ipx = ipx;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTermUI() {
        return termUI;
    }

    public void setTermUI(String termUI) {
        this.termUI = termUI;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSider() {
        return sider;
    }

    public void setSider(String sider) {
        this.sider = sider;
    }

    public String getATC_code() {
        return ATC_code;
    }

    public void setATC_code(String ATC_code) {
        this.ATC_code = ATC_code;
    }

    public String getNotFound() {
        return notFound;
    }

    public void setNotFound(String notFound) {
        this.notFound = notFound;
    }

}
