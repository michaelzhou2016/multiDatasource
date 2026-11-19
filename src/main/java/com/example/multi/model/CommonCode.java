package com.example.multi.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * common_code
 * @author 2631014
 * @since 2026-07-07 16:22:23
 */
@Data
public class CommonCode implements Serializable {
    private String langCd;

    private String cd;

    /**
     */
    private String prntCd;

    /**
     */
    private String cdNm;

    /**
     */
    private String cdEnnm;

    /**
     */
    private String cdZhnm;

    /**
     */
    private Integer cdDept;

    /**
     */
    private String cdVal;

    /**
     */
    private String cdOpt1;

    /**
     */
    private String cdOpt2;

    /**
     */
    private String cdOpt3;

    /**
     */
    private String rem;

    /**
     */
    private String rgn;

    /**
     */
    private LocalDateTime rgstDt;

    /**
     */
    private String updr;

    /**
     */
    private LocalDateTime updeDt;

    /**
     */
    private String cdType;

    /**
     */
    private String range;

    /**
     */
    private Integer sortseq;

    private static final long serialVersionUID = 1L;
}