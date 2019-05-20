package com.example.lock.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountWallet {
    private Integer id;

    private String userOpenId;

    private BigDecimal userAmount;

    private Date createTime;

    private Date updateTime;

    private String payPassword;

    private Integer isOpen;

    private String checkKey;

    private Integer version;
}