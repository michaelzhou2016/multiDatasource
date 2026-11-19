package com.example.multi.vo;

import java.util.List;

public class QueryUpdateFailedVO {
    private int days;
    private List<String> updateErrCodeList;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public List<String> getUpdateErrCodeList() {
        return updateErrCodeList;
    }

    public void setUpdateErrCodeList(List<String> updateErrCodeList) {
        this.updateErrCodeList = updateErrCodeList;
    }
}
