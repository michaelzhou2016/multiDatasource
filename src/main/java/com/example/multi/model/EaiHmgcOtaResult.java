package com.example.multi.model;


import java.io.Serializable;
import java.util.Date;

/**
 * eai_hmgc_ota_result
 *
 * @author zhouliliang
 * @since 2026-01-29 10:44:05
 */
public class EaiHmgcOtaResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * VIN
     */
    private String vin;

    /**
     * 车型
     */
    private String carModel;

    /**
     * 源版本
     */
    private String sourceVersion;

    /**
     * 目标版本
     */
    private String targetVersion;

    /**
     * 事件序列号
     */
    private String tgtVer;

    /**
     * 下载是否成功
     */
    private String downloadSuccYn;

    /**
     * 下载时间
     */
    private Date downloadTime;

    /**
     * 下载失败原因代码
     */
    private String downloadResult;

    /**
     * 更新结果代码
     */
    private String updateResult;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 失败原因代码
     */
    private String updateStatusResult;

    /**
     * 연동여부
     */
    private String linkYn;

    /**
     * 연동시간
     */
    private Date linkTime;

    /**
     * 创建时间
     */
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getSourceVersion() {
        return sourceVersion;
    }

    public void setSourceVersion(String sourceVersion) {
        this.sourceVersion = sourceVersion;
    }

    public String getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
    }

    public String getTgtVer() {
        return tgtVer;
    }

    public void setTgtVer(String tgtVer) {
        this.tgtVer = tgtVer;
    }

    public String getDownloadSuccYn() {
        return downloadSuccYn;
    }

    public void setDownloadSuccYn(String downloadSuccYn) {
        this.downloadSuccYn = downloadSuccYn;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getDownloadResult() {
        return downloadResult;
    }

    public void setDownloadResult(String downloadResult) {
        this.downloadResult = downloadResult;
    }

    public String getUpdateResult() {
        return updateResult;
    }

    public void setUpdateResult(String updateResult) {
        this.updateResult = updateResult;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStatusResult() {
        return updateStatusResult;
    }

    public void setUpdateStatusResult(String updateStatusResult) {
        this.updateStatusResult = updateStatusResult;
    }

    public String getLinkYn() {
        return linkYn;
    }

    public void setLinkYn(String linkYn) {
        this.linkYn = linkYn;
    }

    public Date getLinkTime() {
        return linkTime;
    }

    public void setLinkTime(Date linkTime) {
        this.linkTime = linkTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}