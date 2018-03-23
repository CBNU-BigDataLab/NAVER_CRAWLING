package org.bigdatacenter.naver_crawling.models;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by HP1 on 9/29/2017.
 */
public class FourthChildren extends BaseDrug<FifthChildren> {

    private Long drugID;

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    @Override
    public void setStatistic(BufferedWriter bufferedWriter) throws IOException {
        String[] stastics = this.getTitle().split("\\|");
        this.setTitle(stastics[0]);
        this.setTotalSideEffects(Long.valueOf(stastics[1]));
        this.setTotalSideEffectsPercentage(Double.valueOf(stastics[2]));
        this.setTotalLabels(Long.valueOf(stastics[3]));
        this.setTotalLabelsPercentage(Double.valueOf(stastics[4]));
        this.setDrugID(Long.valueOf(stastics[5]));

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
        bufferedWriter.append("http://sideeffects.embl.de/drugs/" + this.drugID);
        bufferedWriter.append(NEW_LINE_SEPARATER);
    }

    public Long getDrugID() {
        return drugID;
    }

    public void setDrugID(Long drugID) {
        this.drugID = drugID;
    }
}
