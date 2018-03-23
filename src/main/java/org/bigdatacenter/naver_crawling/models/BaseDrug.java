package org.bigdatacenter.naver_crawling.models;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by HP1 on 9/29/2017.
 */
public class BaseDrug<T> {
    private String key;
    private String title;
    private Long totalSideEffects;
    private Double totalSideEffectsPercentage;
    private Long totalLabels;
    private Double totalLabelsPercentage;
    private String parentKey;
    private List<T> children;

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTotalSideEffects() {
        return totalSideEffects;
    }

    public void setTotalSideEffects(Long totalSideEffects) {
        this.totalSideEffects = totalSideEffects;
    }

    public Double getTotalSideEffectsPercentage() {
        return totalSideEffectsPercentage;
    }

    public void setTotalSideEffectsPercentage(Double totalSideEffectsPercentage) {
        this.totalSideEffectsPercentage = totalSideEffectsPercentage;
    }

    public Long getTotalLabels() {
        return totalLabels;
    }

    public void setTotalLabels(Long totalLabels) {
        this.totalLabels = totalLabels;
    }

    public Double getTotalLabelsPercentage() {
        return totalLabelsPercentage;
    }

    public void setTotalLabelsPercentage(Double totalLabelsPercentage) {
        this.totalLabelsPercentage = totalLabelsPercentage;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public void setStatistic(BufferedWriter bufferedWriter) throws IOException {
        String[] stastics = this.getTitle().split("\\|");
        this.setTitle(stastics[0]);
        this.setTotalSideEffects(Long.valueOf(stastics[1]));
        this.setTotalSideEffectsPercentage(Double.valueOf(stastics[2]));
        this.setTotalLabels(Long.valueOf(stastics[3]));
        this.setTotalLabelsPercentage(Double.valueOf(stastics[4]));

        bufferedWriter.append(this.getKey());
        bufferedWriter.append(COMMA_DELIMITER);
        bufferedWriter.append("\"" + this.getTitle() + "\"");
        bufferedWriter.append(COMMA_DELIMITER);
        bufferedWriter.append(this.getTotalSideEffects() + "");
        bufferedWriter.append(COMMA_DELIMITER);
        bufferedWriter.append(this.getTotalSideEffectsPercentage() + "");
        bufferedWriter.append(COMMA_DELIMITER);
        bufferedWriter.append(this.getTotalLabels() + "");
        bufferedWriter.append(COMMA_DELIMITER);
        bufferedWriter.append(this.getTotalLabelsPercentage() + "");
        bufferedWriter.append(COMMA_DELIMITER);
        bufferedWriter.append(this.getParentKey());
        bufferedWriter.append(COMMA_DELIMITER);
        bufferedWriter.append("");
        bufferedWriter.append(NEW_LINE_SEPARATER);
    }

    @Override
    public String toString() {
        return "BaseDrug{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", totalSideEffects=" + totalSideEffects +
                ", totalSideEffectsPercentage=" + totalSideEffectsPercentage +
                ", totalLabels=" + totalLabels +
                ", totalLabelsPercentage=" + totalLabelsPercentage +
                ", parentKey='" + parentKey + '\'' +
                ", children=" + children +
                '}';
    }
}
