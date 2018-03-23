package org.bigdatacenter.momcafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by HP1 on 2/26/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MomResult {
    private MomResultWrapper result;
    private String isSuccess;
    private String errorMsg;

    public MomResultWrapper getResult() {
        return result;
    }

    public void setResult(MomResultWrapper result) {
        this.result = result;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "MomResult{" +
                "result=" + result +
                ", isSuccess='" + isSuccess + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
